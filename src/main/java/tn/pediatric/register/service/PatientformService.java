package tn.pediatric.register.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.pediatric.register.domain.Patientform;
import tn.pediatric.register.repository.PatientformRepository;

/**
 * Service Implementation for managing {@link Patientform}.
 */
@Service
@Transactional
public class PatientformService {

    private final Logger log = LoggerFactory.getLogger(PatientformService.class);

    private final PatientformRepository patientformRepository;

    public PatientformService(PatientformRepository patientformRepository) {
        this.patientformRepository = patientformRepository;
    }

    /**
     * Save a patientform.
     *
     * @param patientform the entity to save.
     * @return the persisted entity.
     */
    public Patientform save(Patientform patientform) {
        log.debug("Request to save Patientform : {}", patientform);
        return patientformRepository.save(patientform);
    }

    /**
     * Update a patientform.
     *
     * @param patientform the entity to save.
     * @return the persisted entity.
     */
    public Patientform update(Patientform patientform) {
        log.debug("Request to update Patientform : {}", patientform);
        return patientformRepository.save(patientform);
    }

    /**
     * Partially update a patientform.
     *
     * @param patientform the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Patientform> partialUpdate(Patientform patientform) {
        log.debug("Request to partially update Patientform : {}", patientform);

        return patientformRepository
            .findById(patientform.getId())
            .map(existingPatientform -> {
                if (patientform.getPathologie() != null) {
                    existingPatientform.setPathologie(patientform.getPathologie());
                }
                if (patientform.getTypeobservation() != null) {
                    existingPatientform.setTypeobservation(patientform.getTypeobservation());
                }
                if (patientform.getCasfamiliaux() != null) {
                    existingPatientform.setCasfamiliaux(patientform.getCasfamiliaux());
                }

                return existingPatientform;
            })
            .map(patientformRepository::save);
    }

    /**
     * Get all the patientforms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Patientform> findAll(Pageable pageable) {
        log.debug("Request to get all Patientforms");
        return patientformRepository.findAll(pageable);
    }

    /**
     * Get one patientform by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Patientform> findOne(Long id) {
        log.debug("Request to get Patientform : {}", id);
        return patientformRepository.findById(id);
    }

    /**
     * Delete the patientform by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Patientform : {}", id);
        patientformRepository.deleteById(id);
    }
}
