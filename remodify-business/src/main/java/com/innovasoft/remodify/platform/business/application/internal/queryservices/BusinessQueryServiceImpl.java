package com.innovasoft.remodify.platform.business.application.internal.queryservices;

import com.innovasoft.remodify.platform.business.domain.model.queries.GetAllBusinessesQuery;
import com.innovasoft.remodify.platform.business.infrastructure.persistance.jpa.BusinessRepository;
import com.innovasoft.remodify.platform.business.domain.model.aggregates.Business;
import com.innovasoft.remodify.platform.business.domain.model.queries.GetBusinessByIdQuery;
import com.innovasoft.remodify.platform.business.domain.services.BusinessQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessQueryServiceImpl implements BusinessQueryService {

    private final BusinessRepository businessRepository;

    public BusinessQueryServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Override
    public Optional<Business> handle(GetBusinessByIdQuery query) {
        return businessRepository.findById(query.id());
    }

    @Override
    public List<Business> handle(GetAllBusinessesQuery query) {
        return businessRepository.findAll();
    }
}
