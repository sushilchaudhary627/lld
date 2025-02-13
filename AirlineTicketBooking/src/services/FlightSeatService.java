package services;

import constants.FlightSeatStatus;
import models.FlightSeat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Service responsible for operations related to flight seat availability and status updates,
 * using per-seat locking to handle concurrent booking.
 */
public class FlightSeatService {
    // Map to maintain a lock per FlightSeat.
    private final ConcurrentHashMap<FlightSeat, ReentrantLock> seatLocks = new ConcurrentHashMap<>();

    /**
     * Attempts to check that all provided seats are available and then mark them as BOOKED.
     * This operation is performed atomically per seat by acquiring locks for all seats.
     *
     * @param flightSeats the list of flight seats to be checked and updated.
     * @throws IllegalArgumentException if any seat is not available.
     */
    public void checkAvailabilityAndMarkUnavailable(List<FlightSeat> flightSeats) {
        // Sort the seats in a consistent order to avoid deadlocks (using a comparator on a unique property).
        // Here, we assume FlightSeat has a proper equals/hashCode, so we can sort using hashCode.
        List<FlightSeat> sortedSeats = new ArrayList<>(flightSeats);
        sortedSeats.sort(Comparator.comparingInt(Object::hashCode));

        // Acquire locks for each seat in the sorted order.
        List<ReentrantLock> acquiredLocks = new ArrayList<>();
        try {
            for (FlightSeat seat : sortedSeats) {
                ReentrantLock lock = getLockForSeat(seat);
                lock.lock();
                acquiredLocks.add(lock);
            }
            // With all locks acquired, check availability.
            if (!areSeatsAvailable(flightSeats)) {
                throw new IllegalArgumentException("Some seats are unavailable.");
            }
            // Mark each seat as BOOKED.
            flightSeats.forEach(seat -> seat.setFlightSeatStatus(FlightSeatStatus.BOOKED));
        } finally {
            // Ensure all acquired locks are released.
            for (ReentrantLock lock : acquiredLocks) {
                lock.unlock();
            }
        }
    }

    /**
     * Marks all seats in the provided list as AVAILABLE.
     * Uses per-seat locks to ensure thread safety.
     *
     * @param flightSeats the list of flight seats to mark as available.
     */
    public void markAvailable(List<FlightSeat> flightSeats) {
        List<FlightSeat> sortedSeats = new ArrayList<>(flightSeats);
        sortedSeats.sort(Comparator.comparingInt(Object::hashCode));

        List<ReentrantLock> acquiredLocks = new ArrayList<>();
        try {
            for (FlightSeat seat : sortedSeats) {
                ReentrantLock lock = getLockForSeat(seat);
                lock.lock();
                acquiredLocks.add(lock);
            }
            flightSeats.forEach(seat -> seat.setFlightSeatStatus(FlightSeatStatus.AVAILABLE));
        } finally {
            for (ReentrantLock lock : acquiredLocks) {
                lock.unlock();
            }
        }
    }

    /**
     * Checks if all the provided flight seats are available.
     *
     * @param flightSeats the list of flight seats to check.
     * @return true if every seat's status is AVAILABLE; false otherwise.
     */
    private boolean areSeatsAvailable(List<FlightSeat> flightSeats) {
        return flightSeats.stream()
                .allMatch(seat -> seat.getFlightSeatStatus().equals(FlightSeatStatus.AVAILABLE));
    }

    /**
     * Returns the lock associated with the given flight seat.
     * If no lock exists yet, it creates a new one.
     *
     * @param seat the flight seat for which to obtain the lock.
     * @return the ReentrantLock associated with the seat.
     */
    private ReentrantLock getLockForSeat(FlightSeat seat) {
        return seatLocks.computeIfAbsent(seat, k -> new ReentrantLock());
    }
}
