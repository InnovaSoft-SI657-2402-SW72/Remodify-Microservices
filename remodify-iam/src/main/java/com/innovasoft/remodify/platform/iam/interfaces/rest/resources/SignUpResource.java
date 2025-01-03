package com.innovasoft.remodify.platform.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(String username, String password, List<String> roles, String email, String firstName, String paternalSurname, String maternalSurname, String description, String phone, String image) {
}
