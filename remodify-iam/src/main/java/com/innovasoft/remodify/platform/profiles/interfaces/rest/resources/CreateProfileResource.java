package com.innovasoft.remodify.platform.profiles.interfaces.rest.resources;

public record CreateProfileResource(
        String email,
        String password,
        String typeUser,
        String firstName,
        String paternalSurname,
        String maternalSurname
) {
}