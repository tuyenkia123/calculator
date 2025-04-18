package vn.salary.calculator.model.enumeration;

import lombok.Getter;

@Getter
public enum FundCategory {

    PLUS_FUND("Quỹ cộng"), MINUS_FUND("Quỹ trừ");

    private final String value;

    FundCategory(String value) {
        this.value = value;
    }
}
