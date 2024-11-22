package com.innovasoft.remodify.platform.information.profiles.domain.model.queries;

public record GetContractorByUserIdQuery (Long userId) {
    public GetContractorByUserIdQuery {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
    }

    public Long getUserId() {
        return userId;
    }
}
