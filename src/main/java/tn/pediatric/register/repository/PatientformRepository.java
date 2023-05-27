package tn.pediatric.register.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.pediatric.register.domain.Patientform;

/**
 * Spring Data JPA repository for the Patientform entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientformRepository extends JpaRepository<Patientform, Long> {}
