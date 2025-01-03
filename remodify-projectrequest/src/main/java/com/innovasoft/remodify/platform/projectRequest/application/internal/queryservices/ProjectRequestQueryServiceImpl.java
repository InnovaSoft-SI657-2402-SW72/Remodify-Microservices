package com.innovasoft.remodify.platform.projectRequest.application.internal.queryservices;

import com.innovasoft.remodify.platform.projectRequest.domain.model.aggregates.ProjectRequest;
import com.innovasoft.remodify.platform.projectRequest.domain.model.queries.GetAllProjectRequestsByBusinessIdQuery;
import com.innovasoft.remodify.platform.projectRequest.domain.model.queries.GetAllProjectRequestsByContractorIdQuery;
import com.innovasoft.remodify.platform.projectRequest.domain.model.queries.GetProjectRequestByIdQuery;
import com.innovasoft.remodify.platform.projectRequest.domain.services.ProjectRequestQueryService;
import com.innovasoft.remodify.platform.projectRequest.infrastructure.persistance.jpa.ProjectRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectRequestQueryServiceImpl implements ProjectRequestQueryService {

    private final ProjectRequestRepository projectRequestRepository;

    public ProjectRequestQueryServiceImpl(ProjectRequestRepository projectRequestRepository) {
        this.projectRequestRepository = projectRequestRepository;
    }

    @Override
    public List<ProjectRequest> handle(GetAllProjectRequestsByContractorIdQuery query){
        return projectRequestRepository.findAllByContractorId(query.contractorId());
    }

    @Override
    public List<ProjectRequest> handle(GetAllProjectRequestsByBusinessIdQuery query){
        return projectRequestRepository.findAllByBusinessId(query.businessId());
    }

    @Override
    public Optional<ProjectRequest> handle(GetProjectRequestByIdQuery query){
        return projectRequestRepository.findById(query.id());
    }
}
