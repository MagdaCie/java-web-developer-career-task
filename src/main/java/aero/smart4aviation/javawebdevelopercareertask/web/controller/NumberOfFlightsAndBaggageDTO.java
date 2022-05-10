package aero.smart4aviation.javawebdevelopercareertask.web.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class NumberOfFlightsAndBaggageDTO {

    public int flightsDeparting;
    public int flightsArriving;
    public long totalNumberBaggageArriving;
    public long totalNumberBaggageDeparting;

}
