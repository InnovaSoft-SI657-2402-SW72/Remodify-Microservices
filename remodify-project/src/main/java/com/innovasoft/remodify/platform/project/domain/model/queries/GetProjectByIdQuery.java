package com.innovasoft.remodify.platform.project.domain.model.queries;

public record GetProjectByIdQuery(Long id) {
    public GetProjectByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}
