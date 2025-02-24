package repo.impl;

import models.Appointment;
import models.Doctor;
import repo.AppointmentRepo;

import java.util.*;

public class AppointmentRepoImpl implements AppointmentRepo {
    Map<Long, Appointment> appointmentMap = new HashMap<>();
    Map<Long, List<Appointment>>appointmentMapByDoctor = new HashMap<>();
    Map<Long, List<Appointment>>appointmentMapByPatient = new HashMap<>();
    @Override
    public void save(Appointment appointment) {
          appointmentMap.put(appointment.getId(), appointment);
          appointmentMapByDoctor
              .computeIfAbsent(appointment.getDoctor().getId(), k ->  new ArrayList<>())
              .add(appointment);
          appointmentMapByPatient.
              computeIfAbsent(appointment.getPatient().getId(), k -> new ArrayList<>())
              .add(appointment);
    }

    @Override
    public List<Appointment> findAppointmentForDoctor(Long doctorId) {
        return appointmentMapByDoctor.getOrDefault(doctorId, List.of());
    }

    @Override
    public Optional<Appointment> findAppointmentById(Long id) {
        return Optional.ofNullable(appointmentMap.get(id));
    }

    @Override
    public List<Appointment> findAppointmentForPatient(Long patientId) {
        return appointmentMapByPatient.getOrDefault(patientId, List.of());
    }
}
