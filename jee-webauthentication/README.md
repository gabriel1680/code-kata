# JEE WebAuthentication

Inspired on [this kata](https://codingdojo.org/kata/JEEWebAuthentication/)

## Description

In this Kata, your a programmer at ABC Corp and you’re making a new web app from scratch. After the head architect started working on this, it’s now up to you to make sure these tasks are completed:

- allow authentication using request parameters (GET or POST)
- all authentication/login attempts should be verified agains LDAP
- successful logins should be recorded in the single sign on registry

However, you’re not the only programmer (team) adressing this web app, so the LDAP is written by another team and what you have right now is this interface:

```java
public interface LdapAuthenticationGateway {
    boolean credentialsAreValid(String userName, String password);
}
```
and the single sign on registry is also written by another team, leaving you with this interface:

```java
public interface SingleSignOnRegistry {
    boolean tokenIsValid(String token);
    String registerNewSession(String userName);
    void endSession(String token);
}
```

Your job basically is to write one or more javax.servlet.Filter that handles incoming requests and act according to whether there’s a cookie with a SSO token, username+password parameters etc.

It’s assumed that there’s some form of DependencyInjection framework in place - to get a handle to the SingleSignOnRegistry or the LdapAuthenticationGateway , you’ll simply have to provide something like:

```java
public void setSingleSignOnRegistry(SingleSignOnRegistry registry) {
    // ...
}
```