package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.CooperativelocalRepository;
import com.mycompany.myapp.service.CooperativelocalService;
import com.mycompany.myapp.service.dto.CooperativelocalDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Cooperativelocal}.
 */
@RestController
@RequestMapping("/api")
public class CooperativelocalResource {

    private final Logger log = LoggerFactory.getLogger(CooperativelocalResource.class);

    private static final String ENTITY_NAME = "cooperativelocal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CooperativelocalService cooperativelocalService;

    private final CooperativelocalRepository cooperativelocalRepository;

    public CooperativelocalResource(
        CooperativelocalService cooperativelocalService,
        CooperativelocalRepository cooperativelocalRepository
    ) {
        this.cooperativelocalService = cooperativelocalService;
        this.cooperativelocalRepository = cooperativelocalRepository;
    }

    /**
     * {@code POST  /cooperativelocals} : Create a new cooperativelocal.
     *
     * @param cooperativelocalDTO the cooperativelocalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cooperativelocalDTO, or with status {@code 400 (Bad Request)} if the cooperativelocal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cooperativelocals")
    public ResponseEntity<CooperativelocalDTO> createCooperativelocal(@Valid @RequestBody CooperativelocalDTO cooperativelocalDTO)
        throws URISyntaxException {
        log.debug("REST request to save Cooperativelocal : {}", cooperativelocalDTO);
        if (cooperativelocalDTO.getId() != null) {
            throw new BadRequestAlertException("A new cooperativelocal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CooperativelocalDTO result = cooperativelocalService.save(cooperativelocalDTO);
        return ResponseEntity
            .created(new URI("/api/cooperativelocals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cooperativelocals/:id} : Updates an existing cooperativelocal.
     *
     * @param id the id of the cooperativelocalDTO to save.
     * @param cooperativelocalDTO the cooperativelocalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cooperativelocalDTO,
     * or with status {@code 400 (Bad Request)} if the cooperativelocalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cooperativelocalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cooperativelocals/{id}")
    public ResponseEntity<CooperativelocalDTO> updateCooperativelocal(
        @PathVariable(value = "id", required = false) final UUID id,
        @Valid @RequestBody CooperativelocalDTO cooperativelocalDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Cooperativelocal : {}, {}", id, cooperativelocalDTO);
        if (cooperativelocalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cooperativelocalDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cooperativelocalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CooperativelocalDTO result = cooperativelocalService.update(cooperativelocalDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cooperativelocalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /cooperativelocals/:id} : Partial updates given fields of an existing cooperativelocal, field will ignore if it is null
     *
     * @param id the id of the cooperativelocalDTO to save.
     * @param cooperativelocalDTO the cooperativelocalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cooperativelocalDTO,
     * or with status {@code 400 (Bad Request)} if the cooperativelocalDTO is not valid,
     * or with status {@code 404 (Not Found)} if the cooperativelocalDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the cooperativelocalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/cooperativelocals/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CooperativelocalDTO> partialUpdateCooperativelocal(
        @PathVariable(value = "id", required = false) final UUID id,
        @NotNull @RequestBody CooperativelocalDTO cooperativelocalDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Cooperativelocal partially : {}, {}", id, cooperativelocalDTO);
        if (cooperativelocalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cooperativelocalDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cooperativelocalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CooperativelocalDTO> result = cooperativelocalService.partialUpdate(cooperativelocalDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cooperativelocalDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /cooperativelocals} : get all the cooperativelocals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cooperativelocals in body.
     */
    @GetMapping("/cooperativelocals")
    public List<CooperativelocalDTO> getAllCooperativelocals() {
        log.debug("REST request to get all Cooperativelocals");
        return cooperativelocalService.findAll();
    }

    /**
     * {@code GET  /cooperativelocals/:id} : get the "id" cooperativelocal.
     *
     * @param id the id of the cooperativelocalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cooperativelocalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cooperativelocals/{id}")
    public ResponseEntity<CooperativelocalDTO> getCooperativelocal(@PathVariable UUID id) {
        log.debug("REST request to get Cooperativelocal : {}", id);
        Optional<CooperativelocalDTO> cooperativelocalDTO = cooperativelocalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cooperativelocalDTO);
    }

    /**
     * {@code DELETE  /cooperativelocals/:id} : delete the "id" cooperativelocal.
     *
     * @param id the id of the cooperativelocalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cooperativelocals/{id}")
    public ResponseEntity<Void> deleteCooperativelocal(@PathVariable UUID id) {
        log.debug("REST request to delete Cooperativelocal : {}", id);
        cooperativelocalService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
