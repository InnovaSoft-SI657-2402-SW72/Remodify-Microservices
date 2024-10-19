package com.innovasoft.remodify.platform.information.profiles.application.internal.commandservices;

import com.innovasoft.remodify.platform.information.profiles.domain.model.aggregates.Remodeler;
import com.innovasoft.remodify.platform.information.profiles.domain.model.commands.CreateRemodelerCommand;
import com.innovasoft.remodify.platform.information.profiles.domain.services.RemodelerCommandService;
import com.innovasoft.remodify.platform.information.profiles.infrastructure.persistence.jpa.repositories.RemodelerRepository;
import org.springframework.stereotype.Service;

@Service
public class RemodelerCommandServiceImpl implements RemodelerCommandService {

    private final RemodelerRepository remodelerRepository;

    public RemodelerCommandServiceImpl(RemodelerRepository remodelerRepository) {
        this.remodelerRepository = remodelerRepository;
    }


    // Compare the implementation of this method with the handle method in ContractorCommandServiceImpl.java
    @Override
    public Long handle(CreateRemodelerCommand command) {
        var remodeler = new Remodeler(command.description(), command.phone());
        remodelerRepository.save(remodeler);
        return remodeler.getId();
    }
}
