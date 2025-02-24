import constants.Speciality;
import models.*;
import repo.AppointmentRepo;
import repo.DoctorRepo;
import repo.impl.AppointmentRepoImpl;
import repo.impl.DoctorRepoImpl;
import repo.impl.PatientRepoImpl;
import services.AppointmentService;
import services.DoctorService;
import services.PatientService;
import services.SlotService;
import strategy.impl.DoctorRankingByEarliestAvailability;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DoctorRepo doctorRepo = new DoctorRepoImpl();
        SlotService slotService = new SlotService(new AtomicInteger(1));
        DoctorService doctorService = new DoctorService(new AtomicInteger(1), doctorRepo, slotService, new DoctorRankingByEarliestAvailability());
        Doctor doctor1 = doctorService.registerDoctor("Aman", Speciality.PHYSICIAN);
        doctorService.registerDoctor("Aooo",Speciality.PHYSICIAN);
        LocalTime curr = LocalTime.now();
        doctorService.markAvailability(1, List.of(curr.plusMinutes(5), curr.plusMinutes(31)));
        doctorService.markAvailability(2, List.of(LocalTime.now().plusMinutes(1)));
        PatientService patientService = new PatientService(new AtomicInteger(1), new PatientRepoImpl());
        patientService.registerPatient("App");
        patientService.registerPatient("Appp");
        patientService.registerPatient("Apppp");

        System.out.println(doctorService.findDoctorsBySpeciality(Speciality.PHYSICIAN));
        AppointmentRepo appointmentRepo = new AppointmentRepoImpl();
        AppointmentService appointmentService = new AppointmentService(new AtomicInteger(1), appointmentRepo, patientService, doctorService, slotService);
//        appointmentService.createAppointment(1,1,1);
        appointmentService.createAppointment(1,1,2);
        System.out.println(appointmentService.findAppointmentForDoctor(1));
        System.out.println(appointmentService.findAppointmentForPatient(1));
        System.out.println(appointmentService.findAppointmentForPatient(2));
//        appointmentService.cancelAppointment(1);
        appointmentService.createAppointment(2,3,2);
        System.out.println(appointmentService.findAppointmentForPatient(2));
    }
}

// started at 12:25 and will close by 1:55
// total time - 55 min for service layer
