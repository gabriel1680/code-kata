package com.kata;

public class Presenter {

    public String goodbye(String aName) {
        return withPrefix("Adios %s".formatted(aName));
    }

    public String salute(String salutation, String aName) {
        return withPrefix("¡%s %s!".formatted(salutation, aName));
    }

    public String echo(String output) {
        return withPrefix("%s".formatted(output));
    }

    private String withPrefix(String text) {
        return "%s%s".formatted("> ", text);
    }
}
