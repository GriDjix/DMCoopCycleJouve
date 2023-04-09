package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Meal;
import com.mycompany.myapp.domain.Order;
import com.mycompany.myapp.domain.Restaurant;
import com.mycompany.myapp.service.dto.MealDTO;
import com.mycompany.myapp.service.dto.OrderDTO;
import com.mycompany.myapp.service.dto.RestaurantDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Meal} and its DTO {@link MealDTO}.
 */
@Mapper(componentModel = "spring")
public interface MealMapper extends EntityMapper<MealDTO, Meal> {
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "restaurantId")
    @Mapping(target = "order", source = "order", qualifiedByName = "orderId")
    MealDTO toDto(Meal s);

    @Named("restaurantId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    RestaurantDTO toDtoRestaurantId(Restaurant restaurant);

    @Named("orderId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderDTO toDtoOrderId(Order order);
}
