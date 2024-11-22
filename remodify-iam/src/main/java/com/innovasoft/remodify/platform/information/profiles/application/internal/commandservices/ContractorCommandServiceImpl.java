package com.innovasoft.remodify.platform.information.profiles.application.internal.commandservices;

import com.innovasoft.remodify.platform.iam.domain.model.aggregates.User;
import com.innovasoft.remodify.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.innovasoft.remodify.platform.information.profiles.domain.model.aggregates.Contractor;
import com.innovasoft.remodify.platform.information.profiles.domain.model.commands.CreateContractorCommand;
import com.innovasoft.remodify.platform.information.profiles.domain.services.ContractorCommandService;
import com.innovasoft.remodify.platform.information.profiles.infrastructure.persistence.jpa.repositories.ContractorRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractorCommandServiceImpl implements ContractorCommandService {

    private final ContractorRepository contractorRepository;
    private final UserRepository userRepository;

    public ContractorCommandServiceImpl(ContractorRepository contractorRepository, UserRepository userRepository) {
        this.contractorRepository = contractorRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Long handle(CreateContractorCommand command) {

        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        var contractor = new Contractor(command.description(), command.phone(),user);
        contractorRepository.save(contractor);
        return contractor.getId();
    }
}
