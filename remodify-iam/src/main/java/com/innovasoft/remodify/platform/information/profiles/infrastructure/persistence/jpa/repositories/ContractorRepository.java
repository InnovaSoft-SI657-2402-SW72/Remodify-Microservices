package com.innovasoft.remodify.platform.information.profiles.infrastructure.persistence.jpa.repositories;

import com.innovasoft.remodify.platform.iam.domain.model.aggregates.User;
import com.innovasoft.remodify.platform.information.profiles.domain.model.aggregates.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    Optional<Contractor> findByPhone(String phone);
    //Boolean existsByPhone(String phone);
    @Query("SELECT c FROM Contractor c WHERE c.user.id = :userId")
    Optional<Contractor> findByUserId(@Param("userId")Long userId);


}
