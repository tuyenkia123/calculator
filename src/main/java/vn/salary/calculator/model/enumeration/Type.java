package vn.salary.calculator.model.enumeration;

import lombok.Getter;

@Getter
public enum Type {

    RECEIVER("Nhận"),
    BORROW("Vay"),
    PAID("Thanh toán"),
    PLUS_FUND("Quỹ cộng"),
    MINUS_FUND("Quỹ trừ");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public static Type[] account() {
        return new Type[]{RECEIVER, BORROW, PAID};
    }

    public static Type[] fund() {
        return new Type[]{PLUS_FUND, MINUS_FUND};
    }
}
