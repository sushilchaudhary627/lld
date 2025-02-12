package validators;

import models.User;
import repository.AircraftRepository;
import services.UserService;

/**
 * Validator class for aircraft creation.
 */
public class AircraftValidator {
    private final UserService userService;
    private final AircraftRepository aircraftRepository;

    public AircraftValidator(UserService userService, AircraftRepository aircraftRepository) {
        this.userService = userService;
        this.aircraftRepository = aircraftRepository;
    }

    /**
     * Validates the aircraft creation request.
     *
     * @param userId       the ID of the user attempting to add the aircraft.
     * @param aircraftName the name of the aircraft.
     * @param capacity     the seating capacity of the aircraft.
     * @throws IllegalArgumentException if any validation rule is violated.
     */
    public void validate(Integer userId, String aircraftName, Integer capacity) {
        User user = userService.getUserByUserId(userId);
        if (!user.isAdmin()) {
            throw new IllegalArgumentException("Only admin can add aircraft");
        }
        if (capacity > 150) {
            throw new IllegalArgumentException("Capacity should be less than or equal to 150");
        }
        if (capacity % 6 != 0) {
            throw new IllegalArgumentException("Capacity should be a multiple of 6");
        }
        if (aircraftRepository.getAircraftByName(aircraftName).isPresent()) {
            throw new IllegalArgumentException("Aircraft with the same name already exists");
        }
    }
}
