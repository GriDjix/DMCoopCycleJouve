package com.mycompany.myapp.domain.enumeration;

/**
 * The OrderStatus enumeration.
 */
public enum OrderStatus {
    CREATED("created"),
    PAID("paid"),
    IN_PROGRESS("in_progress"),
    DELIVERED("delivered"),
    CANCELED("canceled");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
