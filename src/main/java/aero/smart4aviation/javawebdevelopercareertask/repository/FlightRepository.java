package aero.smart4aviation.javawebdevelopercareertask.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FlightRepository {
    private FlightEntity[] flights;

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        flights = mapper.readValue(ResourceUtils.getFile("classpath:data/FlightEntities.json"), FlightEntity[].class);
    }

    public List<FlightEntity> findAll() {
        return List.of(flights);
    }

    public long findFlightIdByFlightNumberAndDate(long flightNumber, String date) {
        return findAll().stream()
                .filter(entity -> entity.flightNumber == flightNumber && entity.departureDate.startsWith(date))
                .mapToLong(entity -> entity.flightId)
                .findAny().orElseThrow();
    }

    public List<Long> findFlightIdsByDepartureAirportCodeAndDate(String departureAirportIATACode, String date) {
        return findAll().stream()
                .filter(entity -> entity.departureAirportIATACode.equals(departureAirportIATACode) & entity.departureDate.startsWith(date))
                .map(entity -> entity.flightId)
                .collect(Collectors.toList());
    }

    public List<Long> findFlightIdsByArrivalAirportCodeAndDate(String arrivalAirportIATACode, String date) {
        return findAll().stream()
                .filter(entity -> entity.arrivalAirportIATACode.equals(arrivalAirportIATACode) & entity.departureDate.startsWith(date))
                .map(entity -> entity.flightId)
                .collect(Collectors.toList());
    }

}
