package com.innovasoft.remodify.platform.projectRequest.domain.model.queries;

public record GetProjectRequestByIdQuery(Long id) {
    public GetProjectRequestByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}
