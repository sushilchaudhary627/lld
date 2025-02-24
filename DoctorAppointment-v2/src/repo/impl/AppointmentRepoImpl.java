package repo.impl;

import models.Appointment;
import repo.AppointmentRepo;

import java.util.*;

public class AppointmentRepoImpl implements AppointmentRepo {
    Map<Integer, Appointment> appointmentMap = new HashMap<>();
    Map<Integer, List<Appointment>>appointmentsByDoctorMap = new HashMap<>();
    Map<Integer, List<Appointment>>getAppointmentsByPatientMap = new HashMap<>();
    @Override
    public void save(Appointment appointment) {
        appointmentMap.put(appointment.getAppointmentId(), appointment);
        appointmentsByDoctorMap.computeIfAbsent(appointment.getDoctor().getDoctorId(), k ->new ArrayList<>()).add(appointment);
        getAppointmentsByPatientMap.computeIfAbsent(appointment.getPatient().getPatientId(), k-> new ArrayList<>()).add(appointment);
    }

    @Override
    public Optional<Appointment> findAppointmentById(Integer id) {
        return Optional.ofNullable(appointmentMap.get(id));
    }

    @Override
    public void update(Appointment appointment) {

    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(Integer doctorId) {
        return appointmentsByDoctorMap.getOrDefault(doctorId, List.of());
    }

    @Override
    public List<Appointment> getAppointmentForPatient(Integer patientId) {
        return getAppointmentsByPatientMap.getOrDefault(patientId, List.of());
    }
}
