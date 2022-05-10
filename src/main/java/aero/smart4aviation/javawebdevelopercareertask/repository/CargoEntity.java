package aero.smart4aviation.javawebdevelopercareertask.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CargoEntity {
    public  long id;
    public long weight;
    public WeightUnit weightUnit;
    public long pieces;
}
