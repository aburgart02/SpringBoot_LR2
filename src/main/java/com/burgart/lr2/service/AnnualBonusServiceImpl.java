package com.burgart.lr2.service;

import com.burgart.lr2.model.Positions;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        int daysInYear = Year.now().isLeap() ? 366 : 365;
        return salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;
    }

    @Override
    public double calculateQuarterlyBonus(Positions position, double salary, double bonus) {
        if (!position.isManager()) {
            throw new IllegalArgumentException("Quarterly bonus is only applicable for managers.");
        }
        return salary * bonus * 0.25;
    }
}