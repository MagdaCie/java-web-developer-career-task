package aero.smart4aviation.javawebdevelopercareertask.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    void findAll() {
        //when
        List<FlightEntity> allFlights = flightRepository.findAll();
        //then
        assertThat(allFlights).hasSize(5);
        assertThat(allFlights.get(4).flightNumber).isEqualTo(9052);
    }

    @Test
    void findFlightIdByFlightNumberAndDate() {
        //when
        long flightId = flightRepository.findFlightIdByFlightNumberAndDate(9052, "2018-05-24");
        //then
        assertThat(flightId).isEqualTo(4);
    }


    @Test
    void findFlightIdByAirportCodeAndDate() {
        //when
        List<Long> flightIds = flightRepository.findFlightIdsByDepartureAirportCodeAndDate("YYZ", "2019-07-21");
        //then
        assertThat(flightIds).containsOnly(1L);

    }

    @Test
    void findFlightIdByArrivalAirportCodeAndDate() {
        //when
        List<Long> flightIds = flightRepository.findFlightIdsByArrivalAirportCodeAndDate("KRK", "2019-07-21");
        //then
        assertThat(flightIds).containsOnly(1L);
    }
}