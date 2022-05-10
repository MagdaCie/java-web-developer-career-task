package aero.smart4aviation.javawebdevelopercareertask.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class FlightWeightEntity {
    public long flightId;
    public List<BaggageEntity> baggage;
    public List<CargoEntity> cargo;
}
