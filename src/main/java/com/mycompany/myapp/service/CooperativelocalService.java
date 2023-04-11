package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Cooperativelocal;
import com.mycompany.myapp.repository.CooperativelocalRepository;
import com.mycompany.myapp.service.dto.CooperativelocalDTO;
import com.mycompany.myapp.service.mapper.CooperativelocalMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Cooperativelocal}.
 */
@Service
@Transactional
public class CooperativelocalService {

    private final Logger log = LoggerFactory.getLogger(CooperativelocalService.class);

    private final CooperativelocalRepository cooperativelocalRepository;

    private final CooperativelocalMapper cooperativelocalMapper;

    public CooperativelocalService(CooperativelocalRepository cooperativelocalRepository, CooperativelocalMapper cooperativelocalMapper) {
        this.cooperativelocalRepository = cooperativelocalRepository;
        this.cooperativelocalMapper = cooperativelocalMapper;
    }

    /**
     * Save a cooperativelocal.
     *
     * @param cooperativelocalDTO the entity to save.
     * @return the persisted entity.
     */
    public CooperativelocalDTO save(CooperativelocalDTO cooperativelocalDTO) {
        log.debug("Request to save Cooperativelocal : {}", cooperativelocalDTO);
        Cooperativelocal cooperativelocal = cooperativelocalMapper.toEntity(cooperativelocalDTO);
        cooperativelocal = cooperativelocalRepository.save(cooperativelocal);
        return cooperativelocalMapper.toDto(cooperativelocal);
    }

    /**
     * Update a cooperativelocal.
     *
     * @param cooperativelocalDTO the entity to save.
     * @return the persisted entity.
     */
    public CooperativelocalDTO update(CooperativelocalDTO cooperativelocalDTO) {
        log.debug("Request to update Cooperativelocal : {}", cooperativelocalDTO);
        Cooperativelocal cooperativelocal = cooperativelocalMapper.toEntity(cooperativelocalDTO);
        cooperativelocal = cooperativelocalRepository.save(cooperativelocal);
        return cooperativelocalMapper.toDto(cooperativelocal);
    }

    /**
     * Partially update a cooperativelocal.
     *
     * @param cooperativelocalDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CooperativelocalDTO> partialUpdate(CooperativelocalDTO cooperativelocalDTO) {
        log.debug("Request to partially update Cooperativelocal : {}", cooperativelocalDTO);

        return cooperativelocalRepository
            .findById(cooperativelocalDTO.getId())
            .map(existingCooperativelocal -> {
                cooperativelocalMapper.partialUpdate(existingCooperativelocal, cooperativelocalDTO);

                return existingCooperativelocal;
            })
            .map(cooperativelocalRepository::save)
            .map(cooperativelocalMapper::toDto);
    }

    /**
     * Get all the cooperativelocals.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CooperativelocalDTO> findAll() {
        log.debug("Request to get all Cooperativelocals");
        return cooperativelocalRepository
            .findAll()
            .stream()
            .map(cooperativelocalMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one cooperativelocal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CooperativelocalDTO> findOne(UUID id) {
        log.debug("Request to get Cooperativelocal : {}", id);
        return cooperativelocalRepository.findById(id).map(cooperativelocalMapper::toDto);
    }

    /**
     * Delete the cooperativelocal by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Cooperativelocal : {}", id);
        cooperativelocalRepository.deleteById(id);
    }
}
