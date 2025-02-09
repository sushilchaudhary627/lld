package observer;

import models.Doctor;
import models.Slot;

public interface SlotCancellationObserver {
    void notifyCancellation(Doctor doctor, Slot slot);
}
