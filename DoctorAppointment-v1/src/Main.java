import constanta.Speciality;
import repo.DoctorRepo;
import repo.impl.AppointmentRepoImpl;
import repo.impl.DoctorRepoImpl;
import repo.impl.PatientRepoImpl;
import services.AppointmentService;
import services.DoctorService;
import services.PatientService;
import services.SlotService;
import strategy.impl.DoctorRankingByEarlierSlot;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DoctorRepo doctorRepo = new DoctorRepoImpl();
        SlotService slotService = new SlotService(new AtomicLong(1));
        DoctorService doctorService = new DoctorService(new AtomicLong(1), doctorRepo, new DoctorRankingByEarlierSlot(), slotService);
        doctorService.registerDoctor("Arun", Speciality.PHYSICIAN);
        doctorService.registerDoctor("Ashish", Speciality.PHYSICIAN);
        doctorService.registerDoctor("Asshish", Speciality.PHYSICIAN);
        doctorService.markAvailableSlots(List.of(LocalTime.now().plusMinutes(25)), 1L);
        doctorService.markAvailableSlots(List.of(LocalTime.now().plusMinutes(20)), 2L);
        doctorService.markAvailableSlots(List.of(LocalTime.now().plusMinutes(10)), 3L);
        PatientService patientService = new PatientService(new AtomicLong(1), new PatientRepoImpl());
        patientService.regsiterPatient("sushil");
        System.out.println(doctorService.findAvailableSlots(Speciality.PHYSICIAN));
        AppointmentService appointmentService = new AppointmentService(new AtomicLong(1), new AppointmentRepoImpl(), doctorService, patientService, slotService);
        appointmentService.createAppointment(2L,1L,2L);
        System.out.println(doctorService.findAvailableSlots(Speciality.PHYSICIAN));
    }
}

// started writing at 1 AM
// completed repo and models - 1:35 AM
// total time took 2 hour 15 min
