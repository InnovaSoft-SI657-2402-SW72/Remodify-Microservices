package com.innovasoft.remodify.platform.business.domain.model.queries;

public record GetBusinessByIdQuery(Long id) {
    public GetBusinessByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}
