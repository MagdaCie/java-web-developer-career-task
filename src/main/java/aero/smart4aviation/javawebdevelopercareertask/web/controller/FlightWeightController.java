package aero.smart4aviation.javawebdevelopercareertask.web.controller;

import aero.smart4aviation.javawebdevelopercareertask.service.FlightWeightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
public class FlightWeightController {

    private final FlightWeightService flightWeightService;

    public FlightWeightController(FlightWeightService flightWeightService) {
        this.flightWeightService = flightWeightService;
    }

    @RequestMapping(path = "flight/weight")
    public FlightWeightDTO getFlightWeightData(@RequestParam long flightNumber, @RequestParam String date) {
        try {
            return flightWeightService.getFlightWeightData(flightNumber, date);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight with the provided parameters not found.");
        }
    }
}
