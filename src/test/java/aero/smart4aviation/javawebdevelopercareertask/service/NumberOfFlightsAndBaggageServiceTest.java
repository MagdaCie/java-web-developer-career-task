package aero.smart4aviation.javawebdevelopercareertask.service;

import aero.smart4aviation.javawebdevelopercareertask.repository.*;
import aero.smart4aviation.javawebdevelopercareertask.web.controller.NumberOfFlightsAndBaggageDTO;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class NumberOfFlightsAndBaggageServiceTest {

    private final FlightRepository flightRepository = mock(FlightRepository.class);
    private final FlightWeightRepository flightWeightRepository = mock(FlightWeightRepository.class);
    private static final String airportIATACode = "KRK";
    private static final String date = "2019-07-21";

    @Test
    void getFlightsData_shouldSumBaggagePieces() {
        //given
        NumberOfFlightsAndBaggageService service = new NumberOfFlightsAndBaggageService(flightRepository, flightWeightRepository);
        given(flightRepository.findFlightIdsByArrivalAirportCodeAndDate(airportIATACode, date)).willReturn(Collections.singletonList(1L));
        given(flightRepository.findFlightIdsByDepartureAirportCodeAndDate(airportIATACode, date)).willReturn(Collections.singletonList(2L));

        List<BaggageEntity> baggageList = List.of(new BaggageEntity(1, 1, WeightUnit.kg, 10),
                new BaggageEntity(2, 2, WeightUnit.kg, 20));
        given(flightWeightRepository.findByFlightId(1)).willReturn(new FlightWeightEntity(1, baggageList, List.of()));
        given(flightWeightRepository.findByFlightId(2)).willReturn(new FlightWeightEntity(1, baggageList, List.of()));
        //when
        NumberOfFlightsAndBaggageDTO flightsData = service.getFlightsData(airportIATACode, date);
        //then
        assertThat(flightsData.totalNumberBaggageArriving).isEqualTo(30);
        assertThat(flightsData.totalNumberBaggageDeparting).isEqualTo(30);
    }

    @Test
    void getFlightsData_shouldSumFlights() {
        //given
        NumberOfFlightsAndBaggageService service = new NumberOfFlightsAndBaggageService(flightRepository, flightWeightRepository);
        given(flightRepository.findFlightIdsByArrivalAirportCodeAndDate(airportIATACode, date)).willReturn(List.of(1L, 2L));
        given(flightRepository.findFlightIdsByDepartureAirportCodeAndDate(airportIATACode, date)).willReturn(List.of(3L, 4L, 5L));
        given(flightWeightRepository.findByFlightId(anyLong())).willReturn(new FlightWeightEntity(1, List.of(), List.of()));
        //when
        NumberOfFlightsAndBaggageDTO flightsData = service.getFlightsData(airportIATACode, date);
        //then
        assertThat(flightsData.flightsArriving).isEqualTo(2);
        assertThat(flightsData.flightsDeparting).isEqualTo(3);
    }

}