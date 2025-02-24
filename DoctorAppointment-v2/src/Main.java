import repo.DoctorRepo;
import repo.PatientRepo;
import repo.impl.DoctorRepoImpl;
import repo.impl.PatientRepoImpl;
import services.DoctorService;
import services.PatientService;
import services.SlotService;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PatientRepo patientRepo = new PatientRepoImpl();
        DoctorRepo doctorRepo = new DoctorRepoImpl();
        PatientService patientService = new PatientService(new AtomicInteger(1), patientRepo);
        patientService.registerPatient("Alice");
        patientService.registerPatient("Sumit");
        SlotService slotService = new SlotService(new AtomicInteger(1));
        DoctorService doctorService = new DoctorService(new AtomicInteger(1),doctorRepo, slotService );
        doctorService.registerDoctor("Arun");
        doctorService.registerDoctor("Arjun");
        doctorService.markDoctorAvailability(1, List.of(LocalTime.now(), LocalTime.now().plusMinutes(30)));

    }
}
// I took 1 hour 40 min
