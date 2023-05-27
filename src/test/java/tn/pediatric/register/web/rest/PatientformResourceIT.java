package tn.pediatric.register.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tn.pediatric.register.IntegrationTest;
import tn.pediatric.register.domain.Patientform;
import tn.pediatric.register.repository.PatientformRepository;

/**
 * Integration tests for the {@link PatientformResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PatientformResourceIT {

    private static final String DEFAULT_PATHOLOGIE = "AAAAAAAAAA";
    private static final String UPDATED_PATHOLOGIE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPEOBSERVATION = "AAAAAAAAAA";
    private static final String UPDATED_TYPEOBSERVATION = "BBBBBBBBBB";

    private static final String DEFAULT_CASFAMILIAUX = "AAAAAAAAAA";
    private static final String UPDATED_CASFAMILIAUX = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/patientforms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PatientformRepository patientformRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPatientformMockMvc;

    private Patientform patientform;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patientform createEntity(EntityManager em) {
        Patientform patientform = new Patientform()
            .pathologie(DEFAULT_PATHOLOGIE)
            .typeobservation(DEFAULT_TYPEOBSERVATION)
            .casfamiliaux(DEFAULT_CASFAMILIAUX);
        return patientform;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patientform createUpdatedEntity(EntityManager em) {
        Patientform patientform = new Patientform()
            .pathologie(UPDATED_PATHOLOGIE)
            .typeobservation(UPDATED_TYPEOBSERVATION)
            .casfamiliaux(UPDATED_CASFAMILIAUX);
        return patientform;
    }

    @BeforeEach
    public void initTest() {
        patientform = createEntity(em);
    }

    @Test
    @Transactional
    void getAllPatientforms() throws Exception {
        // Initialize the database
        patientformRepository.saveAndFlush(patientform);

        // Get all the patientformList
        restPatientformMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(patientform.getId().intValue())))
            .andExpect(jsonPath("$.[*].pathologie").value(hasItem(DEFAULT_PATHOLOGIE)))
            .andExpect(jsonPath("$.[*].typeobservation").value(hasItem(DEFAULT_TYPEOBSERVATION)))
            .andExpect(jsonPath("$.[*].casfamiliaux").value(hasItem(DEFAULT_CASFAMILIAUX)));
    }

    @Test
    @Transactional
    void getPatientform() throws Exception {
        // Initialize the database
        patientformRepository.saveAndFlush(patientform);

        // Get the patientform
        restPatientformMockMvc
            .perform(get(ENTITY_API_URL_ID, patientform.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(patientform.getId().intValue()))
            .andExpect(jsonPath("$.pathologie").value(DEFAULT_PATHOLOGIE))
            .andExpect(jsonPath("$.typeobservation").value(DEFAULT_TYPEOBSERVATION))
            .andExpect(jsonPath("$.casfamiliaux").value(DEFAULT_CASFAMILIAUX));
    }

    @Test
    @Transactional
    void getNonExistingPatientform() throws Exception {
        // Get the patientform
        restPatientformMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
