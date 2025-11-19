package com.burgart.lr2.model;

import lombok.Getter;

@Getter
public enum Positions {

    DEV(2.2, false),
    HR(1.2, false),
    TL(2.6, true),
    QA(2.0, false),
    ACCOUNTANT(1.8, false),
    DIRECTOR(3.0, true);

    private final double positionCoefficient;
    private final boolean isManager;

    Positions(double positionCoefficient, boolean isManager) {
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }
}