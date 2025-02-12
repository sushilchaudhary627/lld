package repository.impl;

import models.Aircraft;
import repository.AircraftRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AircraftRepositoryImpl implements AircraftRepository {
    Map<String, Aircraft> aircraftMap = new HashMap<>();
    @Override
    public void save(Aircraft aircraft) {
        aircraftMap.put(aircraft.getAircraftName(), aircraft);
    }

    @Override
    public Optional<Aircraft> getAircraftByName(String name) {
        return Optional.ofNullable(aircraftMap.get(name));
    }
}
