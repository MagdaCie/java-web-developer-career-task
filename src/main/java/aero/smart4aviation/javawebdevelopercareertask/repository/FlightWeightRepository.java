package aero.smart4aviation.javawebdevelopercareertask.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Repository
public class FlightWeightRepository {
    private FlightWeightEntity[] weightEntities;

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        weightEntities = mapper.readValue(ResourceUtils.getFile("classpath:data/CargoEntities.json"), FlightWeightEntity[].class);
    }

    public List<FlightWeightEntity> findAll() {
        return List.of(weightEntities);
    }

    public FlightWeightEntity findByFlightId(long flightId) {
        return findAll().stream()
                .filter(entity -> entity.flightId == flightId)
                .findAny().orElseThrow();
    }
}
