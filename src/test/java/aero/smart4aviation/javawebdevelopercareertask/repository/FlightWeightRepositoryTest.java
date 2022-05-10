package aero.smart4aviation.javawebdevelopercareertask.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FlightWeightRepositoryTest {

    @Autowired
    private FlightWeightRepository flightWeightRepository;

    @Test
    void findAll() {
        //when
        List<FlightWeightEntity> allWeights = flightWeightRepository.findAll();
        //then
        assertThat(allWeights).hasSize(5);
        assertThat(allWeights.get(4).baggage.get(0).weight).isEqualTo(930);
    }

    @Test
    void findByFlightId() {
        //when
        FlightWeightEntity flightWeightEntity = flightWeightRepository.findByFlightId(2);
        //then
        assertThat(flightWeightEntity.baggage.get(0).weight).isEqualTo(948);
    }
}