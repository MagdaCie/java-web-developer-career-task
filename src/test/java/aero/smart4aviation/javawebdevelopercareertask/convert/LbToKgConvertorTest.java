package aero.smart4aviation.javawebdevelopercareertask.convert;

import aero.smart4aviation.javawebdevelopercareertask.repository.BaggageEntity;
import aero.smart4aviation.javawebdevelopercareertask.repository.CargoEntity;
import aero.smart4aviation.javawebdevelopercareertask.repository.WeightUnit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LbToKgConvertorTest {

    @Test
    void convert() {
        //given
        long weight = 1000;
        //when
        long weightInKg = LbToKgConvertor.convert(weight);
        //then
        assertThat(weightInKg).isEqualTo(454);
    }


    @Test
    void getWeightInKg_baggageInLb() {
        //given
        BaggageEntity baggageEntity = new BaggageEntity(1, 1000, WeightUnit.lb, 1);
        //when
        long weightInKg = LbToKgConvertor.getWeightInKg(baggageEntity);
        //then
        assertThat(weightInKg).isEqualTo(454);
    }

    @Test
    void getWeightInKg_baggageInKg() {
        //given
        BaggageEntity baggageEntity = new BaggageEntity(1, 1000, WeightUnit.kg, 1);
        //when
        long weightInKg = LbToKgConvertor.getWeightInKg(baggageEntity);
        //then
        assertThat(weightInKg).isEqualTo(1000);
    }

    @Test
    void getWeightInKg_cargoInLb() {
        //given
        CargoEntity cargoEntity = new CargoEntity(1, 1000, WeightUnit.lb, 1);
        //when
        long weightInKg = LbToKgConvertor.getWeightInKg(cargoEntity);
        //then
        assertThat(weightInKg).isEqualTo(454);
    }

    @Test
    void getWeightInKg_cargoInKg() {
        //given
        CargoEntity cargoEntity = new CargoEntity(1, 1000, WeightUnit.kg, 1);
        //when
        long weightInKg = LbToKgConvertor.getWeightInKg(cargoEntity);
        //then
        assertThat(weightInKg).isEqualTo(1000);
    }
}