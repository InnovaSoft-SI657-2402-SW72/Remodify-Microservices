package com.innovasoft.remodify.platform.business.infrastructure.persistance.jpa;

import com.innovasoft.remodify.platform.business.domain.model.aggregates.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

    boolean existsByName(String name);

    Optional<Business> findById(Long id);
}
