package aero.smart4aviation.javawebdevelopercareertask.service;

import aero.smart4aviation.javawebdevelopercareertask.repository.FlightRepository;
import aero.smart4aviation.javawebdevelopercareertask.repository.FlightWeightRepository;
import aero.smart4aviation.javawebdevelopercareertask.web.controller.NumberOfFlightsAndBaggageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumberOfFlightsAndBaggageService {

    private final FlightRepository flightRepository;

    private final FlightWeightRepository flightWeightRepository;

    public NumberOfFlightsAndBaggageService(FlightRepository flightRepository, FlightWeightRepository flightWeightRepository) {
        this.flightRepository = flightRepository;
        this.flightWeightRepository = flightWeightRepository;
    }

    public NumberOfFlightsAndBaggageDTO getFlightsData(String airportCode, String date) {
        List<Long> arrivalFlightIds = flightRepository.findFlightIdsByArrivalAirportCodeAndDate(airportCode, date);
        List<Long> departureFlightIds = flightRepository.findFlightIdsByDepartureAirportCodeAndDate(airportCode, date);

        long arrivalBaggagePieces = arrivalFlightIds.stream()
                .map(flightWeightRepository::findByFlightId)
                .flatMap(arrivalFlightWeightEntity -> arrivalFlightWeightEntity.baggage.stream())
                .mapToLong(baggage -> baggage.pieces)
                .sum();
        long departureBaggagePieces = departureFlightIds.stream()
                .map(flightWeightRepository::findByFlightId)
                .flatMap(departureFlightWeightEntity -> departureFlightWeightEntity.baggage.stream())
                .mapToLong(baggage -> baggage.pieces)
                .sum();

        return new NumberOfFlightsAndBaggageDTO(departureFlightIds.size(), arrivalFlightIds.size(), arrivalBaggagePieces, departureBaggagePieces);
    }
}
