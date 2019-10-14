package com.example.timechecker.data;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class Retriever {
    // has list of eligible transportations
    List<AbstractEntity> entities;

    public Retriever() {
        entities = EntityBuilder.getAllEntities();
    }

    public List<Instant> getApplicableInstances(Instant currentTime, String entityName) {
        for(AbstractEntity entity : entities) {
            if(entityName.equals(entity.getEntityName())) {
                return entity.getApplicableTimes(currentTime);
            }
        }
        throw new IllegalStateException("Entity not recognized");
    }

    public Map<DayType, List<Instant>> getApplicableTimesAllDays(Instant currentTime,
                                                                 String entityName) {
        for(AbstractEntity entity : entities) {
            if(entityName.equals(entity.getEntityName())) {
                return entity.getApplicableTimesAllDays(currentTime);
            }
        }
        throw new IllegalStateException("Entity not recognized");
    }
}
