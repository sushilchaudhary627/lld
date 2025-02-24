package repo;
import models.*;
import java.util.*;
public interface AppointmentRepo {
    public void save(Appointment appointment);
    public Optional<Appointment>findAppointmentById(Integer id);
    public void update(Appointment appointment);
    public List<Appointment>getAppointmentsForDoctor(Integer doctorId);
    public List<Appointment>getAppointmentForPatient(Integer patientId);
}
