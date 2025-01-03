package com.innovasoft.remodify.platform.projectRequest.application.internal.commandservices;

import com.innovasoft.remodify.platform.projectRequest.domain.model.aggregates.ProjectRequest;
import com.innovasoft.remodify.platform.projectRequest.domain.model.commands.CreateProjectRequestCommand;
import com.innovasoft.remodify.platform.projectRequest.domain.services.ProjectRequestCommandService;
import com.innovasoft.remodify.platform.projectRequest.infrastructure.persistance.jpa.ProjectRequestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectRequestCommandServiceImpl implements ProjectRequestCommandService {

    private final ProjectRequestRepository projectRequestRepository;

    public ProjectRequestCommandServiceImpl(ProjectRequestRepository projectRequestRepository) {
        this.projectRequestRepository = projectRequestRepository;
    }

    @Override
    public Optional<ProjectRequest> handle(CreateProjectRequestCommand command){
        if(projectRequestRepository.existsByName(command.name())){
            throw new IllegalArgumentException("Project Request with same name already exists");
        }
        var projectRequest = new ProjectRequest(command);
        var createdProjectRequest = projectRequestRepository.save(projectRequest);
        return Optional.of(createdProjectRequest);
    }
}
