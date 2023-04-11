package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Cooperativelocal;
import com.mycompany.myapp.domain.Restaurant;
import com.mycompany.myapp.service.dto.CooperativelocalDTO;
import com.mycompany.myapp.service.dto.RestaurantDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Restaurant} and its DTO {@link RestaurantDTO}.
 */
@Mapper(componentModel = "spring")
public interface RestaurantMapper extends EntityMapper<RestaurantDTO, Restaurant> {
    @Mapping(target = "coop", source = "coop", qualifiedByName = "cooperativelocalId")
    RestaurantDTO toDto(Restaurant s);

    @Named("cooperativelocalId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CooperativelocalDTO toDtoCooperativelocalId(Cooperativelocal cooperativelocal);
}
