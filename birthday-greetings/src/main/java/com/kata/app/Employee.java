package com.kata.app;

import java.time.Instant;

public record Employee(String firstName, String lastName, String email, Instant birthdate) {
}
