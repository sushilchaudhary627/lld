package repository.impl;

import models.Lift;
import repository.LiftRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LiftRepositoryImpl implements LiftRepository {
    Map<Integer, Lift> lifts = new HashMap<>();
    @Override
    public void save(Lift lift) {

    }

    @Override
    public Optional<Lift> getLiftById(Integer liftId) {
        return Optional.empty();
    }
}
