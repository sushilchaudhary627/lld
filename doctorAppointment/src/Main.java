import models.*;
import observer.impl.SlotCancellationObserverImpl;
import repository.*;
import repository.impl.*;
import services.*;
import strategy.impl.DoctorRankingByAvailabilityStrategy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        // Atomic Generators
        AtomicInteger doctorIdGenerator = new AtomicInteger(1);
        AtomicInteger slotIdGenerator = new AtomicInteger(1);
        AtomicInteger patientIdGenerator = new AtomicInteger(1);
        AtomicInteger appointmentIdGenerator = new AtomicInteger(1);

        // Repository Initialization
        DoctorRepository doctorRepository = new DoctorRepositoryImpl();
        PatientRepository patientRepository = new PatientRepositoryImpl();
        AppointmentRepository appointmentRepository = new AppointmentRepositoryImpl();

        // Service Initialization
        DoctorService doctorService = new DoctorService(doctorRepository, doctorIdGenerator, slotIdGenerator);
        PatientService patientService = new PatientService(patientRepository, patientIdGenerator);
        SlotService slotService = new SlotService(null);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository, patientService, doctorService, slotService, appointmentIdGenerator);

        // Observer Pattern for Auto-confirming Next Appointment in Queue
        SlotCancellationObserverImpl observer = new SlotCancellationObserverImpl(appointmentRepository);
        slotService.slotCancellationObserver = observer;

        // ✅ Step 1: Create Doctors
        Doctor doctor1 = doctorService.createDoctor("Dr. Smith", "Dermatologist");
        Doctor doctor2 = doctorService.createDoctor("Dr. Johnson", "Dermatologist");
        System.out.println("Created Doctors:");
        System.out.println(doctor1);
        System.out.println(doctor2);

        // ✅ Step 2: Mark Availability
        LocalDate today = LocalDate.now();
        LocalTime time1 = LocalTime.of(10, 0);
        LocalTime time2 = LocalTime.of(10, 30);

        doctorService.markAvailability(doctor1.getDoctorId(), today, List.of(time1, time2));
        doctorService.markAvailability(doctor2.getDoctorId(), today, List.of(time1));

        System.out.println("\nAvailable Cardiologists:");
        System.out.println(doctorService.getAvailableDoctorHavingSpeciality("Dermatologist", new DoctorRankingByAvailabilityStrategy()));

        // ✅ Step 3: Register Patients
        Patient patient1 = patientService.createPatient("John Doe");
        Patient patient2 = patientService.createPatient("Jane Roe");
        System.out.println("\nRegistered Patients:");
        System.out.println(patient1);
        System.out.println(patient2);

        // ✅ Step 4: Book Appointments
        Appointment appointment1 = appointmentService.bookAppointment(patient1.getPatientId(), doctor1.getDoctorId(), 1);
        Appointment appointment2 = appointmentService.bookAppointment(patient2.getPatientId(), doctor1.getDoctorId(), 2);

        System.out.println("\nBooked Appointments:");
        System.out.println(appointment1);
        System.out.println(appointment2);

        // ✅ Step 5: Cancel an Appointment
        appointmentService.cancelAppointment(appointment1.getAppointmentId(), patient1.getPatientId());
        System.out.println("\nAfter Cancellation, Appointments:");
        System.out.println(appointment1);

        // ✅ Step 6: Check Auto-Confirmation (If someone was in queue)
        System.out.println("\nChecking if the next appointment is auto-confirmed...");
        System.out.println(appointment2);
    }
}
