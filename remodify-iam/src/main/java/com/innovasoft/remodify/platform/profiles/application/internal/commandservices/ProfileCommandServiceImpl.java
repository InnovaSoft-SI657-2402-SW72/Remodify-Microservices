package com.innovasoft.remodify.platform.profiles.application.internal.commandservices;

import com.innovasoft.remodify.platform.profiles.domain.model.aggregates.Profile;
import com.innovasoft.remodify.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.innovasoft.remodify.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.innovasoft.remodify.platform.profiles.domain.services.ProfileCommandService;
import com.innovasoft.remodify.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Long handle(CreateProfileCommand command) {
        var emailAddress = new EmailAddress(command.email());
        profileRepository.findByEmail(emailAddress).map(profile -> {
            throw new IllegalArgumentException("Profile with email " + command.email() + " already exists");
        });
        var profile = new Profile(command.email(), command.password(),command.typeUser(), command.firstName(), command.paternalSurname(), command.maternalSurname());
        profileRepository.save(profile);
        return profile.getId();
    }
}
