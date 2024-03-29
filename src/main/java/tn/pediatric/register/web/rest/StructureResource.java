package tn.pediatric.register.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import tn.pediatric.register.domain.Structure;
import tn.pediatric.register.repository.StructureRepository;
import tn.pediatric.register.service.StructureService;
import tn.pediatric.register.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.pediatric.register.domain.Structure}.
 */
@RestController
@RequestMapping("/api")
public class StructureResource {

    private final Logger log = LoggerFactory.getLogger(StructureResource.class);

    private static final String ENTITY_NAME = "structure";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StructureService structureService;

    private final StructureRepository structureRepository;

    public StructureResource(StructureService structureService, StructureRepository structureRepository) {
        this.structureService = structureService;
        this.structureRepository = structureRepository;
    }

    /**
     * {@code POST  /structures} : Create a new structure.
     *
     * @param structure the structure to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new structure, or with status {@code 400 (Bad Request)} if the structure has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/structures")
    public ResponseEntity<Structure> createStructure(@Valid @RequestBody Structure structure) throws URISyntaxException {
        log.debug("REST request to save Structure : {}", structure);
        if (structure.getId() != null) {
            throw new BadRequestAlertException("A new structure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Structure result = structureService.save(structure);
        return ResponseEntity
            .created(new URI("/api/structures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /structures/:id} : Updates an existing structure.
     *
     * @param id the id of the structure to save.
     * @param structure the structure to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated structure,
     * or with status {@code 400 (Bad Request)} if the structure is not valid,
     * or with status {@code 500 (Internal Server Error)} if the structure couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/structures/{id}")
    public ResponseEntity<Structure> updateStructure(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Structure structure
    ) throws URISyntaxException {
        log.debug("REST request to update Structure : {}, {}", id, structure);
        if (structure.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, structure.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!structureRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Structure result = structureService.update(structure);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, structure.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /structures/:id} : Partial updates given fields of an existing structure, field will ignore if it is null
     *
     * @param id the id of the structure to save.
     * @param structure the structure to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated structure,
     * or with status {@code 400 (Bad Request)} if the structure is not valid,
     * or with status {@code 404 (Not Found)} if the structure is not found,
     * or with status {@code 500 (Internal Server Error)} if the structure couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/structures/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Structure> partialUpdateStructure(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Structure structure
    ) throws URISyntaxException {
        log.debug("REST request to partial update Structure partially : {}, {}", id, structure);
        if (structure.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, structure.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!structureRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Structure> result = structureService.partialUpdate(structure);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, structure.getId().toString())
        );
    }

    /**
     * {@code GET  /structures} : get all the structures.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of structures in body.
     */
    @GetMapping("/structures")
    public List<Structure> getAllStructures() {
        log.debug("REST request to get all Structures");
        return structureService.findAll();
    }

    /**
     * {@code GET  /structures/:id} : get the "id" structure.
     *
     * @param id the id of the structure to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the structure, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/structures/{id}")
    public ResponseEntity<Structure> getStructure(@PathVariable Long id) {
        log.debug("REST request to get Structure : {}", id);
        Optional<Structure> structure = structureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(structure);
    }

    /**
     * {@code DELETE  /structures/:id} : delete the "id" structure.
     *
     * @param id the id of the structure to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/structures/{id}")
    public ResponseEntity<Void> deleteStructure(@PathVariable Long id) {
        log.debug("REST request to delete Structure : {}", id);
        structureService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
