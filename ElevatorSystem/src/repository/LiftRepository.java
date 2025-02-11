package repository;

import models.Lift;

import java.util.Optional;

public interface LiftRepository {
    public void save(Lift lift);
    public Optional<Lift> getLiftById(Integer liftId);
}
