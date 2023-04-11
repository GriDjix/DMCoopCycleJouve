package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Client;
import com.mycompany.myapp.domain.Cooperativelocal;
import com.mycompany.myapp.service.dto.ClientDTO;
import com.mycompany.myapp.service.dto.CooperativelocalDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {
    @Mapping(target = "coop", source = "coop", qualifiedByName = "cooperativelocalId")
    ClientDTO toDto(Client s);

    @Named("cooperativelocalId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CooperativelocalDTO toDtoCooperativelocalId(Cooperativelocal cooperativelocal);
}
