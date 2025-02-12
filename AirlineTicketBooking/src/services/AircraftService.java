package services;

import models.Aircraft;
import models.Seat;
import repository.AircraftRepository;
import util.AircraftSeatLabelGenerator;
import validators.AircraftValidator;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Service responsible for aircraft-related operations.
 */
public class AircraftService {
    private final AircraftRepository aircraftRepository;
    private final AtomicInteger aircraftIdGenerator;
    private final AircraftValidator aircraftValidator;

    public AircraftService(UserService userService,
                           AircraftRepository aircraftRepository,
                           AtomicInteger aircraftIdGenerator) {
        // Initialize the validator with the required dependencies.
        this.aircraftValidator = new AircraftValidator(userService, aircraftRepository);
        this.aircraftRepository = aircraftRepository;
        this.aircraftIdGenerator = aircraftIdGenerator;
    }

    /**
     * Adds a new aircraft after validating the creation request.
     *
     * @param userId       the ID of the user attempting to add the aircraft.
     * @param aircraftName the name of the aircraft.
     * @param capacity     the seating capacity of the aircraft.
     * @return the created Aircraft instance.
     */
    public Aircraft addAircraft(Integer userId, String aircraftName, Integer capacity) {
        // Delegate validation to the AircraftValidator.
        aircraftValidator.validate(userId, aircraftName, capacity);

        Integer aircraftId = aircraftIdGenerator.getAndIncrement();
        List<String> labels = AircraftSeatLabelGenerator.generate(capacity);
        Aircraft aircraft = new Aircraft(aircraftId, aircraftName, capacity);

        for (int i = 0; i < capacity; i++) {
            aircraft.addSeat(new Seat(i, labels.get(i)));
        }

        aircraftRepository.save(aircraft);
        return aircraft;
    }

    public Aircraft getAircraftByName(String name) {
        return aircraftRepository.getAircraftByName(name).orElseThrow();
    }
}
