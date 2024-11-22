package com.innovasoft.remodify.platform.information.profiles.infrastructure.persistence.jpa.repositories;

import com.innovasoft.remodify.platform.information.profiles.domain.model.aggregates.Remodeler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RemodelerRepository extends JpaRepository<Remodeler, Long> {
    Optional<Remodeler> findByPhone(String phone);
    //boolean existsByPhone(String phone);
    @Query("SELECT r FROM Remodeler r WHERE r.user.id = :userId")
    Optional<Remodeler> findByUserId(@Param("userId")Long userId);

}

