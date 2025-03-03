package com.kata.authentication;

import javax.servlet.ServletRequest;

interface RequestCredentialParser {
    Credentials getCredentialsOf(ServletRequest request);
}
