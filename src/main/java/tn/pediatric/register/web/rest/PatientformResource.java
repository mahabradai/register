package tn.pediatric.register.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import tn.pediatric.register.domain.Patientform;
import tn.pediatric.register.repository.PatientformRepository;
import tn.pediatric.register.service.PatientformService;
import tn.pediatric.register.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.pediatric.register.domain.Patientform}.
 */
@RestController
@RequestMapping("/api")
public class PatientformResource {

    private final Logger log = LoggerFactory.getLogger(PatientformResource.class);

    private final PatientformService patientformService;

    private final PatientformRepository patientformRepository;

    public PatientformResource(PatientformService patientformService, PatientformRepository patientformRepository) {
        this.patientformService = patientformService;
        this.patientformRepository = patientformRepository;
    }

    /**
     * {@code GET  /patientforms} : get all the patientforms.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientforms in body.
     */
    @GetMapping("/patientforms")
    public ResponseEntity<List<Patientform>> getAllPatientforms(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Patientforms");
        Page<Patientform> page = patientformService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /patientforms/:id} : get the "id" patientform.
     *
     * @param id the id of the patientform to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientform, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patientforms/{id}")
    public ResponseEntity<Patientform> getPatientform(@PathVariable Long id) {
        log.debug("REST request to get Patientform : {}", id);
        Optional<Patientform> patientform = patientformService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientform);
    }
}
