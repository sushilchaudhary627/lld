package repo;
import models.*;
import java.util.*;

public interface AppointmentRepo {
    public void save(Appointment appointment);
    public List<Appointment>findAppointmentForDoctor(Long doctorId);
    public Optional<Appointment>findAppointmentById(Long id);
    public List<Appointment>findAppointmentForPatient(Long patientId);
}
