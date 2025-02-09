package repository.impl;

import models.Appointment;
import models.AppointmentStatus;
import repository.AppointmentRepository;

import java.util.*;

public class AppointmentRepositoryImpl implements AppointmentRepository {
    Map<Integer, Appointment> appointmentMap = new HashMap<>();
    Map<Integer, Queue<Appointment>>waitingAppointmentBySlot = new HashMap<>();
    @Override
    public void save(Appointment appointment) {
        appointmentMap.put(appointment.getAppointmentId(), appointment);
        if(Objects.equals(AppointmentStatus.IN_QUEUE, appointment.getAppointmentStatus())){
            waitingAppointmentBySlot.computeIfAbsent(appointment.getSlot().getScheduleId(), k -> new LinkedList<>()).add(appointment);
        }
    }

    @Override
    public Optional<Appointment> getAppointmentById(Integer id) {
        return Optional.ofNullable(appointmentMap.get(id));
    }

    @Override
    public void update(Appointment appointment) {

    }

    @Override
    public Optional<Appointment> getEarliestWaitingAppointment(Integer doctorId, Integer slotId) {
        if(!waitingAppointmentBySlot.containsKey(slotId) || waitingAppointmentBySlot.get(slotId).isEmpty())
         return Optional.empty();
        return Optional.ofNullable(waitingAppointmentBySlot.get(slotId).poll());
    }
}
