package repository;

import models.Appointment;

import java.util.Optional;

public interface AppointmentRepository {
    public void save(Appointment appointment);
    public Optional<Appointment> getAppointmentById(Integer id);
    public void update(Appointment appointment);
    public  Optional<Appointment> getEarliestWaitingAppointment(Integer doctorId, Integer slotId);
}
