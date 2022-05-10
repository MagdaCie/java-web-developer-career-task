package aero.smart4aviation.javawebdevelopercareertask.web.controller;

import aero.smart4aviation.javawebdevelopercareertask.service.NumberOfFlightsAndBaggageService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberOfFlightsAndBaggageController {

private final NumberOfFlightsAndBaggageService numberOfFlightsAndBaggageService;

    public NumberOfFlightsAndBaggageController(NumberOfFlightsAndBaggageService numberOfFlightsAndBaggageService) {
        this.numberOfFlightsAndBaggageService = numberOfFlightsAndBaggageService;
    }


    @RequestMapping(path = "airports/{airportCode}/totals")
    public NumberOfFlightsAndBaggageDTO getFlightsData(@PathVariable String airportCode, @RequestParam String date){
        return numberOfFlightsAndBaggageService.getFlightsData(airportCode, date);
    }
}
