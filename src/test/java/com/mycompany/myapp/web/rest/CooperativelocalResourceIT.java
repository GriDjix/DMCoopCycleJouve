package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Cooperativelocal;
import com.mycompany.myapp.repository.CooperativelocalRepository;
import com.mycompany.myapp.service.dto.CooperativelocalDTO;
import com.mycompany.myapp.service.mapper.CooperativelocalMapper;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CooperativelocalResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CooperativelocalResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/cooperativelocals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private CooperativelocalRepository cooperativelocalRepository;

    @Autowired
    private CooperativelocalMapper cooperativelocalMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCooperativelocalMockMvc;

    private Cooperativelocal cooperativelocal;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cooperativelocal createEntity(EntityManager em) {
        Cooperativelocal cooperativelocal = new Cooperativelocal().name(DEFAULT_NAME).city(DEFAULT_CITY).country(DEFAULT_COUNTRY);
        return cooperativelocal;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cooperativelocal createUpdatedEntity(EntityManager em) {
        Cooperativelocal cooperativelocal = new Cooperativelocal().name(UPDATED_NAME).city(UPDATED_CITY).country(UPDATED_COUNTRY);
        return cooperativelocal;
    }

    @BeforeEach
    public void initTest() {
        cooperativelocal = createEntity(em);
    }

    @Test
    @Transactional
    void createCooperativelocal() throws Exception {
        int databaseSizeBeforeCreate = cooperativelocalRepository.findAll().size();
        // Create the Cooperativelocal
        CooperativelocalDTO cooperativelocalDTO = cooperativelocalMapper.toDto(cooperativelocal);
        restCooperativelocalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cooperativelocalDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Cooperativelocal in the database
        List<Cooperativelocal> cooperativelocalList = cooperativelocalRepository.findAll();
        assertThat(cooperativelocalList).hasSize(databaseSizeBeforeCreate + 1);
        Cooperativelocal testCooperativelocal = cooperativelocalList.get(cooperativelocalList.size() - 1);
        assertThat(testCooperativelocal.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCooperativelocal.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testCooperativelocal.getCountry()).isEqualTo(DEFAULT_COUNTRY);
    }

    @Test
    @Transactional
    void createCooperativelocalWithExistingId() throws Exception {
        // Create the Cooperativelocal with an existing ID
        cooperativelocalRepository.saveAndFlush(cooperativelocal);
        CooperativelocalDTO cooperativelocalDTO = cooperativelocalMapper.toDto(cooperativelocal);

        int databaseSizeBeforeCreate = cooperativelocalRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCooperativelocalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cooperativelocalDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cooperativelocal in the database
        List<Cooperativelocal> cooperativelocalList = cooperativelocalRepository.findAll();
        assertThat(cooperativelocalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = cooperativelocalRepository.findAll().size();
        // set the field null
        cooperativelocal.setName(null);

        // Create the Cooperativelocal, which fails.
        CooperativelocalDTO cooperativelocalDTO = cooperativelocalMapper.toDto(cooperativelocal);

        restCooperativelocalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cooperativelocalDTO))
            )
            .andExpect(status().isBadRequest());

        List<Cooperativelocal> cooperativelocalList = cooperativelocalRepository.findAll();
        assertThat(cooperativelocalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = cooperativelocalRepository.findAll().size();
        // set the field null
        cooperativelocal.setCity(null);

        // Create the Cooperativelocal, which fails.
        CooperativelocalDTO cooperativelocalDTO = cooperativelocalMapper.toDto(cooperativelocal);

        restCooperativelocalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cooperativelocalDTO))
            )
            .andExpect(status().isBadRequest());

        List<Cooperativelocal> cooperativelocalList = cooperativelocalRepository.findAll();
        assertThat(cooperativelocalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCountryIsRequired() throws Exception {
        int databaseSizeBeforeTest = cooperativelocalRepository.findAll().size();
        // set the field null
        cooperativelocal.setCountry(null);

        // Create the Cooperativelocal, which fails.
        CooperativelocalDTO cooperativelocalDTO = cooperativelocalMapper.toDto(cooperativelocal);

        restCooperativelocalMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cooperativelocalDTO))
            )
            .andExpect(status().isBadRequest());

        List<Cooperativelocal> cooperativelocalList = cooperativelocalRepository.findAll();
        assertThat(cooperativelocalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCooperativelocals() throws Exception {
        // Initialize the database
        cooperativelocalRepository.saveAndFlush(cooperativelocal);

        // Get all the cooperativelocalList
        restCooperativelocalMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cooperativelocal.getId().toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)));
    }

    @Test
    @Transactional
    void getCooperativelocal() throws Exception {
        // Initialize the database
        cooperativelocalRepository.saveAndFlush(cooperativelocal);

        // Get the cooperativelocal
        restCooperativelocalMockMvc
            .perform(get(ENTITY_API_URL_ID, cooperativelocal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cooperativelocal.getId().toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY));
    }

    @Test
    @Transactional
    void getNonExistingCooperativelocal() throws Exception {
        // Get the cooperativelocal
        restCooperativelocalMockMvc.perform(get(ENTITY_API_URL_ID, UUID.randomUUID().toString())).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCooperativelocal() throws Exception {
        // Initialize the database
        cooperativelocalRepository.saveAndFlush(cooperativelocal);

        int databaseSizeBeforeUpdate = cooperativelocalRepository.findAll().size();

        // Update the cooperativelocal
        Cooperativelocal updatedCooperativelocal = cooperativelocalRepository.findById(cooperativelocal.getId()).get();
        // Disconnect from session so that the updates on updatedCooperativelocal are not directly saved in db
        em.detach(updatedCooperativelocal);
        updatedCooperativelocal.name(UPDATED_NAME).city(UPDATED_CITY).country(UPDATED_COUNTRY);
        CooperativelocalDTO cooperativelocalDTO = cooperativelocalMapper.toDto(updatedCooperativelocal);

        restCooperativelocalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cooperativelocalDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cooperativelocalDTO))
            )
            .andExpect(status().isOk());

        // Validate the Cooperativelocal in the database
        List<Cooperativelocal> cooperativelocalList = cooperativelocalRepository.findAll();
        assertThat(cooperativelocalList).hasSize(databaseSizeBeforeUpdate);
        Cooperativelocal testCooperativelocal = cooperativelocalList.get(cooperativelocalList.size() - 1);
        assertThat(testCooperativelocal.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCooperativelocal.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testCooperativelocal.getCountry()).isEqualTo(UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    void putNonExistingCooperativelocal() throws Exception {
        int databaseSizeBeforeUpdate = cooperativelocalRepository.findAll().size();
        cooperativelocal.setId(UUID.randomUUID());

        // Create the Cooperativelocal
        CooperativelocalDTO cooperativelocalDTO = cooperativelocalMapper.toDto(cooperativelocal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCooperativelocalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cooperativelocalDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cooperativelocalDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cooperativelocal in the database
        List<Cooperativelocal> cooperativelocalList = cooperativelocalRepository.findAll();
        assertThat(cooperativelocalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCooperativelocal() throws Exception {
        int databaseSizeBeforeUpdate = cooperativelocalRepository.findAll().size();
        cooperativelocal.setId(UUID.randomUUID());

        // Create the Cooperativelocal
        CooperativelocalDTO cooperativelocalDTO = cooperativelocalMapper.toDto(cooperativelocal);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCooperativelocalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cooperativelocalDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cooperativelocal in the database
        List<Cooperativelocal> cooperativelocalList = cooperativelocalRepository.findAll();
        assertThat(cooperativelocalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCooperativelocal() throws Exception {
        int databaseSizeBeforeUpdate = cooperativelocalRepository.findAll().size();
        cooperativelocal.setId(UUID.randomUUID());

        // Create the Cooperativelocal
        CooperativelocalDTO cooperativelocalDTO = cooperativelocalMapper.toDto(cooperativelocal);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCooperativelocalMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cooperativelocalDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cooperativelocal in the database
        List<Cooperativelocal> cooperativelocalList = cooperativelocalRepository.findAll();
        assertThat(cooperativelocalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCooperativelocalWithPatch() throws Exception {
        // Initialize the database
        cooperativelocalRepository.saveAndFlush(cooperativelocal);

        int databaseSizeBeforeUpdate = cooperativelocalRepository.findAll().size();

        // Update the cooperativelocal using partial update
        Cooperativelocal partialUpdatedCooperativelocal = new Cooperativelocal();
        partialUpdatedCooperativelocal.setId(cooperativelocal.getId());

        restCooperativelocalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCooperativelocal.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCooperativelocal))
            )
            .andExpect(status().isOk());

        // Validate the Cooperativelocal in the database
        List<Cooperativelocal> cooperativelocalList = cooperativelocalRepository.findAll();
        assertThat(cooperativelocalList).hasSize(databaseSizeBeforeUpdate);
        Cooperativelocal testCooperativelocal = cooperativelocalList.get(cooperativelocalList.size() - 1);
        assertThat(testCooperativelocal.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCooperativelocal.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testCooperativelocal.getCountry()).isEqualTo(DEFAULT_COUNTRY);
    }

    @Test
    @Transactional
    void fullUpdateCooperativelocalWithPatch() throws Exception {
        // Initialize the database
        cooperativelocalRepository.saveAndFlush(cooperativelocal);

        int databaseSizeBeforeUpdate = cooperativelocalRepository.findAll().size();

        // Update the cooperativelocal using partial update
        Cooperativelocal partialUpdatedCooperativelocal = new Cooperativelocal();
        partialUpdatedCooperativelocal.setId(cooperativelocal.getId());

        partialUpdatedCooperativelocal.name(UPDATED_NAME).city(UPDATED_CITY).country(UPDATED_COUNTRY);

        restCooperativelocalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCooperativelocal.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCooperativelocal))
            )
            .andExpect(status().isOk());

        // Validate the Cooperativelocal in the database
        List<Cooperativelocal> cooperativelocalList = cooperativelocalRepository.findAll();
        assertThat(cooperativelocalList).hasSize(databaseSizeBeforeUpdate);
        Cooperativelocal testCooperativelocal = cooperativelocalList.get(cooperativelocalList.size() - 1);
        assertThat(testCooperativelocal.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCooperativelocal.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testCooperativelocal.getCountry()).isEqualTo(UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    void patchNonExistingCooperativelocal() throws Exception {
        int databaseSizeBeforeUpdate = cooperativelocalRepository.findAll().size();
        cooperativelocal.setId(UUID.randomUUID());

        // Create the Cooperativelocal
        CooperativelocalDTO cooperativelocalDTO = cooperativelocalMapper.toDto(cooperativelocal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCooperativelocalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cooperativelocalDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cooperativelocalDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cooperativelocal in the database
        List<Cooperativelocal> cooperativelocalList = cooperativelocalRepository.findAll();
        assertThat(cooperativelocalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCooperativelocal() throws Exception {
        int databaseSizeBeforeUpdate = cooperativelocalRepository.findAll().size();
        cooperativelocal.setId(UUID.randomUUID());

        // Create the Cooperativelocal
        CooperativelocalDTO cooperativelocalDTO = cooperativelocalMapper.toDto(cooperativelocal);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCooperativelocalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cooperativelocalDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cooperativelocal in the database
        List<Cooperativelocal> cooperativelocalList = cooperativelocalRepository.findAll();
        assertThat(cooperativelocalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCooperativelocal() throws Exception {
        int databaseSizeBeforeUpdate = cooperativelocalRepository.findAll().size();
        cooperativelocal.setId(UUID.randomUUID());

        // Create the Cooperativelocal
        CooperativelocalDTO cooperativelocalDTO = cooperativelocalMapper.toDto(cooperativelocal);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCooperativelocalMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cooperativelocalDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cooperativelocal in the database
        List<Cooperativelocal> cooperativelocalList = cooperativelocalRepository.findAll();
        assertThat(cooperativelocalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCooperativelocal() throws Exception {
        // Initialize the database
        cooperativelocalRepository.saveAndFlush(cooperativelocal);

        int databaseSizeBeforeDelete = cooperativelocalRepository.findAll().size();

        // Delete the cooperativelocal
        restCooperativelocalMockMvc
            .perform(delete(ENTITY_API_URL_ID, cooperativelocal.getId().toString()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cooperativelocal> cooperativelocalList = cooperativelocalRepository.findAll();
        assertThat(cooperativelocalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
