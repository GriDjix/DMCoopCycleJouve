package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Cooperativelocal;
import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Cooperativelocal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CooperativelocalRepository extends JpaRepository<Cooperativelocal, UUID> {}
