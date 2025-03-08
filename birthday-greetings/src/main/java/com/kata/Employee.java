package com.kata;

import java.time.Instant;

public record Employee(String firstName, String lastName, String email, Instant birthdate) {
}
