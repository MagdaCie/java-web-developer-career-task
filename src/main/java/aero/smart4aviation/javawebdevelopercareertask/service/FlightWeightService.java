package aero.smart4aviation.javawebdevelopercareertask.service;

import aero.smart4aviation.javawebdevelopercareertask.convert.LbToKgConvertor;
import aero.smart4aviation.javawebdevelopercareertask.repository.FlightRepository;
import aero.smart4aviation.javawebdevelopercareertask.repository.FlightWeightEntity;
import aero.smart4aviation.javawebdevelopercareertask.repository.FlightWeightRepository;
import aero.smart4aviation.javawebdevelopercareertask.web.controller.FlightWeightDTO;
import org.springframework.stereotype.Service;

@Service
public class FlightWeightService {

    private final FlightRepository flightRepository;
    private final FlightWeightRepository flightWeightRepository;


    public FlightWeightService(FlightRepository flightRepository, FlightWeightRepository flightWeightRepository) {
        this.flightRepository = flightRepository;
        this.flightWeightRepository = flightWeightRepository;
    }

    public FlightWeightDTO getFlightWeightData(long flightNumber, String date) {
        long flightId = flightRepository.findFlightIdByFlightNumberAndDate(flightNumber, date);
        FlightWeightEntity flightWeightEntity = flightWeightRepository.findByFlightId(flightId);
        long cargoWeight = flightWeightEntity.cargo.stream()
                .mapToLong(LbToKgConvertor::getWeightInKg)
                .sum();
        long baggageWeight = flightWeightEntity.baggage.stream()
                .mapToLong(LbToKgConvertor::getWeightInKg)
                .sum();
        return new FlightWeightDTO(cargoWeight, baggageWeight, cargoWeight + baggageWeight);
    }
}
