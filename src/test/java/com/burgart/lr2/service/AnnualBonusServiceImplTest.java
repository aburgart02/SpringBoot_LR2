package com.burgart.lr2.service;

import com.burgart.lr2.model.Positions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnnualBonusServiceImplTest {

    private final AnnualBonusServiceImpl annualBonusService = new AnnualBonusServiceImpl();

    @Test
    void calculate_shouldUseLeapOrCommonYearDays() {
        Positions position = Positions.DEV;
        double salary = 100000.0;
        double bonus = 2.0;
        int workDays = 250;

        int daysInYear = Year.now().isLeap() ? 366 : 365;
        double expected = salary * bonus * daysInYear * position.getPositionCoefficient() / workDays;

        double result = annualBonusService.calculate(position, salary, bonus, workDays);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"TL, 100000, 2.0", "DIRECTOR, 150000, 2.5"})
    void calculateQuarterlyBonus_forManager_shouldCalculateCorrectly(Positions position, double salary, double bonus) {
        double expected = salary * bonus * 0.25;

        double result = annualBonusService.calculateQuarterlyBonus(position, salary, bonus);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"DEV", "HR", "QA", "ACCOUNTANT"})
    void calculateQuarterlyBonus_forNonManager_shouldThrowException(Positions position) {
        double salary = 100000.0;
        double bonus = 2.0;

        assertThatThrownBy(() -> annualBonusService.calculateQuarterlyBonus(position, salary, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quarterly bonus is only applicable for managers.");
    }
}