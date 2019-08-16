package ru.digital.league.x5.sign.bindings.config.oauth2;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.Map;

public class CustomOAuth2Authentication extends OAuth2Authentication {
    private final String PERSONNEL_NUMBER = "personnel_number";

    private final Map<String, ?> decodedJWT;

    /**
     * Construct an OAuth 2 authentication. Since some grant types don't require user authentication, the user
     * authentication may be null.
     *
     * @param storedRequest      The authorization request (must not be null).
     * @param userAuthentication The user authentication (possibly null).
     */
    public CustomOAuth2Authentication(OAuth2Request storedRequest, Authentication userAuthentication, Map<String, ?> decodedJWT) {
        super(storedRequest, userAuthentication);
        this.decodedJWT = decodedJWT;
    }

    public String getPersonnelNumber() {
        return (String) decodedJWT.get(PERSONNEL_NUMBER);
    }
}
