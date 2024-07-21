package com.aarshinkov.datetimecalculator.enums;

public enum TimeUnits {

    HOUR("hour"),
    MINUTE("minute"),
    SECOND("second");

    private final String unitNameSingular;

    TimeUnits(String unitNameSingular) {
        this.unitNameSingular = unitNameSingular;
    }

    public String getUnitNameSingular() {
        return unitNameSingular.trim();
    }

    public String getUnitNamePlural() {
        if (unitNameSingular != null && !unitNameSingular.trim().isEmpty()) {
            return unitNameSingular.trim() + "s";
        }
        return null;
    }
}
