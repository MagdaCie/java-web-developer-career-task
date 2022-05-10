package aero.smart4aviation.javawebdevelopercareertask.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FlightEntity {
    public long flightId;
    public long flightNumber;
    public String departureAirportIATACode;
    public String arrivalAirportIATACode;
    public String departureDate;
}
