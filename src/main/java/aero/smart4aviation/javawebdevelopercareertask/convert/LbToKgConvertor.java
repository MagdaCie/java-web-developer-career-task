package aero.smart4aviation.javawebdevelopercareertask.convert;

import aero.smart4aviation.javawebdevelopercareertask.repository.BaggageEntity;
import aero.smart4aviation.javawebdevelopercareertask.repository.CargoEntity;
import aero.smart4aviation.javawebdevelopercareertask.repository.WeightUnit;

public class LbToKgConvertor {

    public static long convert(long weight) {
        return (long) (weight * 0.454);
    }

    public static long getWeightInKg(BaggageEntity baggage) {
        if (baggage.weightUnit == WeightUnit.kg) {
            return baggage.weight;
        } else {
            return convert(baggage.weight);
        }
    }

    public static long getWeightInKg(CargoEntity cargo) {
        if (cargo.weightUnit == WeightUnit.kg) {
            return cargo.weight;
        } else {
            return convert(cargo.weight);
        }
    }
}
