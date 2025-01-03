package com.innovasoft.remodify.platform.business.application.internal.commandservices;

import com.innovasoft.remodify.platform.business.infrastructure.persistance.jpa.BusinessRepository;
import com.innovasoft.remodify.platform.business.domain.model.aggregates.Business;
import com.innovasoft.remodify.platform.business.domain.model.commands.CreateBusinessCommand;
import com.innovasoft.remodify.platform.business.domain.services.BusinessCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessCommandServiceImpl implements BusinessCommandService {

    private final BusinessRepository businessRepository;

    public BusinessCommandServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Override
    public Optional<Business> handle(CreateBusinessCommand command) {
        if (businessRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Business with same name already exists");
        }
        var business = new Business(command);
        var createdBusiness = businessRepository.save(business);
        return Optional.of(createdBusiness);
    }
}
