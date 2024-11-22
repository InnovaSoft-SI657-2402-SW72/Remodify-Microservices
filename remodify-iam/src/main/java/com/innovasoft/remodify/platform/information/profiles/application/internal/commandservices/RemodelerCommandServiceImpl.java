package com.innovasoft.remodify.platform.information.profiles.application.internal.commandservices;

import com.innovasoft.remodify.platform.iam.domain.model.aggregates.User;
import com.innovasoft.remodify.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.innovasoft.remodify.platform.information.profiles.domain.model.aggregates.Remodeler;
import com.innovasoft.remodify.platform.information.profiles.domain.model.commands.CreateRemodelerCommand;
import com.innovasoft.remodify.platform.information.profiles.domain.services.RemodelerCommandService;
import com.innovasoft.remodify.platform.information.profiles.infrastructure.persistence.jpa.repositories.RemodelerRepository;
import org.springframework.stereotype.Service;

@Service
public class RemodelerCommandServiceImpl implements RemodelerCommandService {

    private final RemodelerRepository remodelerRepository;
    private final UserRepository userRepository;

    public RemodelerCommandServiceImpl(RemodelerRepository remodelerRepository, UserRepository userRepository) {
        this.remodelerRepository = remodelerRepository;
        this.userRepository = userRepository;
    }


    // Compare the implementation of this method with the handle method in ContractorCommandServiceImpl.java
    @Override
    public Long handle(CreateRemodelerCommand command) {

        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        var remodeler = new Remodeler(command.description(), command.phone(), user);
        remodelerRepository.save(remodeler);
        return remodeler.getId();
    }
}
