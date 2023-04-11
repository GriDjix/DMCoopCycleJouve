package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Cooperativelocal;
import com.mycompany.myapp.service.dto.CooperativelocalDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cooperativelocal} and its DTO {@link CooperativelocalDTO}.
 */
@Mapper(componentModel = "spring")
public interface CooperativelocalMapper extends EntityMapper<CooperativelocalDTO, Cooperativelocal> {}
