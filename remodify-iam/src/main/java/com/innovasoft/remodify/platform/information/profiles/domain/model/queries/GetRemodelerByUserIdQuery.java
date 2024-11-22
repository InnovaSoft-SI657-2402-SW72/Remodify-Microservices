package com.innovasoft.remodify.platform.information.profiles.domain.model.queries;

public record GetRemodelerByUserIdQuery(Long userId) {
    public GetRemodelerByUserIdQuery {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
    }

    public Long getUserId() {
        return userId;
    }
}
