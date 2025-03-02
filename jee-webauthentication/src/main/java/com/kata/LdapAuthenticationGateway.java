package com.kata;

public interface LdapAuthenticationGateway {
    boolean credentialsAreValid(String userName, String password);
}
