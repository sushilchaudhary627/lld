package repo.impl;

import models.Appointment;
import repo.AppointmentRepo;

import java.util.*;


public class AppointmentRepoImpl implements AppointmentRepo {
    Map<Integer , Appointment>appointmentByIdMap = new HashMap<>();
    Map<Integer, List<Appointment>>appointmentsByDoctorMap = new HashMap<>();
    Map<Integer, List<Appointment>>appointmentsByPatientMap = new HashMap<>();
    @Override
    public void save(Appointment appointment) {
        appointmentByIdMap.put(appointment.getAppointmentId(), appointment);
        appointmentsByDoctorMap.computeIfAbsent(appointment.getDoctor().getDoctorId(), k -> new ArrayList<>())
            .add(appointment);
        appointmentsByPatientMap.computeIfAbsent(appointment.getPatient().getPatientId(), k->new ArrayList<>())
            .add(appointment);
    }

    @Override
    public void update(Appointment appointment) {

    }

    @Override
    public List<Appointment> findAppointmentForPatient(Integer patientId) {
        return appointmentsByPatientMap.getOrDefault(patientId, List.of());
    }

    @Override
    public List<Appointment> findAppointmentForDoctor(Integer doctorId) {
        return appointmentsByDoctorMap.getOrDefault(doctorId, List.of());
    }

    @Override
    public Optional<Appointment> findAppointmentById(Integer appointmentId) {
        return Optional.ofNullable(appointmentByIdMap.get(appointmentId));
    }
}
