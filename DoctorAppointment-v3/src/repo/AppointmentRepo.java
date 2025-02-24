package repo;
import java.util.*;
import models.*;
public interface AppointmentRepo {
    public void save(Appointment appointment);
    public void update(Appointment appointment);
    public List<Appointment>findAppointmentForPatient(Integer patientId);
    public List<Appointment>findAppointmentForDoctor(Integer doctorId);
    public Optional<Appointment>findAppointmentById(Integer appointmentId);
}
