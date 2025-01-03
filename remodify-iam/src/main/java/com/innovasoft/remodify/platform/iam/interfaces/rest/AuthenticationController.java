package com.innovasoft.remodify.platform.iam.interfaces.rest;

import com.innovasoft.remodify.platform.iam.domain.services.UserCommandService;
import com.innovasoft.remodify.platform.iam.domain.services.ValidateTokenCommandService;
import com.innovasoft.remodify.platform.iam.interfaces.rest.resources.*;
import com.innovasoft.remodify.platform.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.innovasoft.remodify.platform.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.innovasoft.remodify.platform.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.innovasoft.remodify.platform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication endpoints.")
public class AuthenticationController {

    private final UserCommandService userCommandService;
    private final ValidateTokenCommandService validateTokenCommandService;


    public AuthenticationController(UserCommandService userCommandService, ValidateTokenCommandService validateTokenCommandService) {
        this.userCommandService = userCommandService;
        this.validateTokenCommandService = validateTokenCommandService;

    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> SignIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);
        if (authenticatedUser.isEmpty()) return ResponseEntity.notFound().build();

        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> singUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
    @PostMapping("/validate-token")
    public ResponseEntity<ValidateTokenResponse> validateToken(@RequestBody TokenResource token) {
        var authenticatedUser = validateTokenCommandService.handle(token.token());
        if (authenticatedUser.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ValidateTokenResponse(authenticatedUser.get()));
    }
}
