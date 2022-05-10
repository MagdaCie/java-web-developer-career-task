package aero.smart4aviation.javawebdevelopercareertask.service;

import aero.smart4aviation.javawebdevelopercareertask.repository.*;
import aero.smart4aviation.javawebdevelopercareertask.web.controller.FlightWeightDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class FlightWeightServiceTest {

    private final FlightRepository flightRepository = mock(FlightRepository.class);
    private final FlightWeightRepository flightWeightRepository = mock(FlightWeightRepository.class);

    @Test
    void getFlightWeightData_onlyKg() {
        //given
        FlightWeightService service = new FlightWeightService(flightRepository, flightWeightRepository);
        FlightWeightEntity entity = new FlightWeightEntity(
                1,
                List.of(new BaggageEntity(1, 1, WeightUnit.kg, 1),
                        new BaggageEntity(2, 2, WeightUnit.kg, 2)),
                List.of(new CargoEntity(3, 3, WeightUnit.kg, 3),
                        new CargoEntity(4, 4, WeightUnit.kg, 4)));
        given(flightRepository.findFlightIdByFlightNumberAndDate(1,"2018-05-24T07:12:57-02:00")).willReturn(1L);
        given(flightWeightRepository.findByFlightId(1)).willReturn(entity);
        //when
        FlightWeightDTO flightWeightData = service.getFlightWeightData(1, "2018-05-24T07:12:57-02:00");
        //then
        assertThat(flightWeightData.baggageWeight).isEqualTo(3);
        assertThat(flightWeightData.cargoWeight).isEqualTo(7);
        assertThat(flightWeightData.totalWeight).isEqualTo(10);
    }

    @Test
    void getFlightWeightData_KgAndLb() {
        //given
        FlightWeightService service = new FlightWeightService(flightRepository, flightWeightRepository);
        FlightWeightEntity entity = new FlightWeightEntity(
                1,
                List.of(new BaggageEntity(1, 100, WeightUnit.lb, 1),
                        new BaggageEntity(2, 200, WeightUnit.kg, 2)),
                List.of(new CargoEntity(3, 100, WeightUnit.lb, 3),
                        new CargoEntity(4, 400, WeightUnit.kg, 4)));
        given(flightRepository.findFlightIdByFlightNumberAndDate(1,"2018-05-24T07:12:57-02:00")).willReturn(1L);
        given(flightWeightRepository.findByFlightId(1)).willReturn(entity);
        //when
        FlightWeightDTO flightWeightData = service.getFlightWeightData(1, "2018-05-24T07:12:57-02:00");
        //then
        assertThat(flightWeightData.baggageWeight).isEqualTo(245);
        assertThat(flightWeightData.cargoWeight).isEqualTo(445);
        assertThat(flightWeightData.totalWeight).isEqualTo(690);
    }
}