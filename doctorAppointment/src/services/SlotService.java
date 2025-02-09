package services;

import models.Doctor;
import models.ScheduleStatus;
import models.Slot;
import observer.SlotCancellationObserver;

public class SlotService {
    public SlotCancellationObserver slotCancellationObserver;

    public SlotService(SlotCancellationObserver slotCancellationObserver) {
        this.slotCancellationObserver = slotCancellationObserver;
    }

    public void slotIsCancelled(Doctor doctor, Slot slot) {
        slot.setScheduleStatus(ScheduleStatus.AVAILABLE);
        if (slotCancellationObserver != null) {
            slotCancellationObserver.notifyCancellation(doctor, slot);
        }
    }
}
