package com.burgart.lr2.service;

import com.burgart.lr2.model.Positions;
import org.springframework.stereotype.Service;

@Service
public interface AnnualBonusService {

    double calculate(Positions positions, double salary, double bonus, int workDays);

    double calculateQuarterlyBonus(Positions position, double salary, double bonus);
}