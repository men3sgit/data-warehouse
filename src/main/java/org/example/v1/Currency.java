package org.example.v1;

public record Currency(
        String code,
        String name,
        Double exchangeRate) {
}
