package tn.pediatric.register.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.pediatric.register.domain.Structure;
import tn.pediatric.register.repository.StructureRepository;

/**
 * Service Implementation for managing {@link Structure}.
 */
@Service
@Transactional
public class StructureService {

    private final Logger log = LoggerFactory.getLogger(StructureService.class);

    private final StructureRepository structureRepository;

    public StructureService(StructureRepository structureRepository) {
        this.structureRepository = structureRepository;
    }

    /**
     * Save a structure.
     *
     * @param structure the entity to save.
     * @return the persisted entity.
     */
    public Structure save(Structure structure) {
        log.debug("Request to save Structure : {}", structure);
        return structureRepository.save(structure);
    }

    /**
     * Update a structure.
     *
     * @param structure the entity to save.
     * @return the persisted entity.
     */
    public Structure update(Structure structure) {
        log.debug("Request to update Structure : {}", structure);
        return structureRepository.save(structure);
    }

    /**
     * Partially update a structure.
     *
     * @param structure the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Structure> partialUpdate(Structure structure) {
        log.debug("Request to partially update Structure : {}", structure);

        return structureRepository
            .findById(structure.getId())
            .map(existingStructure -> {
                if (structure.getName() != null) {
                    existingStructure.setName(structure.getName());
                }
                if (structure.getStype() != null) {
                    existingStructure.setStype(structure.getStype());
                }

                return existingStructure;
            })
            .map(structureRepository::save);
    }

    /**
     * Get all the structures.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Structure> findAll() {
        log.debug("Request to get all Structures");
        return structureRepository.findAll();
    }

    /**
     * Get one structure by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Structure> findOne(Long id) {
        log.debug("Request to get Structure : {}", id);
        return structureRepository.findById(id);
    }

    /**
     * Delete the structure by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Structure : {}", id);
        structureRepository.deleteById(id);
    }
}
