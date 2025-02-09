package observer.impl;

import models.*;
import observer.SlotCancellationObserver;
import repository.AppointmentRepository;
import services.AppointmentService;

public class SlotCancellationObserverImpl implements SlotCancellationObserver {
    private final AppointmentRepository appointmentRepository;

    public SlotCancellationObserverImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void notifyCancellation(Doctor doctor, Slot slot) {
        // Fetch the earliest waiting appointment for the given doctor and slot
        Appointment appointment = appointmentRepository.getEarliestWaitingAppointment(doctor.getDoctorId(), slot.getScheduleId())
                .orElse(null);
        if (appointment != null) {
            appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);
            appointment.getSlot().setScheduleStatus(ScheduleStatus.BOOKED);
            appointmentRepository.update(appointment);
        }
    }
}
