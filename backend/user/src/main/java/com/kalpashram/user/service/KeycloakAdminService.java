package com.kalpashram.user.service;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycloakAdminService {

    private final Keycloak keycloak;

    public KeycloakAdminService() {
        keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:9090/")
                .realm("master")
                .grantType(OAuth2Constants.PASSWORD)
                .clientId("admin-cli")
                .username("admin")
                .password("admin")
                .build();
    }

    public List<UserRepresentation> getAllUsers(String realmName) {
        return keycloak.realm(realmName).users().list();
    }
}
