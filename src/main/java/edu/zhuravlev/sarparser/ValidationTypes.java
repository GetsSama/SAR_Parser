package edu.zhuravlev.sarparser;

import java.util.Objects;

final class ValidationTypes {
    private final int number;
    private final Double IAP;
    private final String activityType;

    ValidationTypes(int number, Double IAP, String activityType) {
        this.number = number;
        this.IAP = IAP;
        this.activityType = activityType;
    }

    public int getNumber() {
        return number;
    }

    public Double getIAP() {
        return IAP;
    }

    public String getActivityType() {
        return activityType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidationTypes that = (ValidationTypes) o;
        return number == that.number && IAP.equals(that.IAP) && activityType.equals(that.activityType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, IAP, activityType);
    }

    @Override
    public String toString() {
        return "ValidationTypes{" +
                "number=" + number +
                ", IAP=" + IAP +
                ", activityType='" + activityType + '\'' +
                '}';
    }
}
