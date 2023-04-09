package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MealMapperTest {

    private MealMapper mealMapper;

    @BeforeEach
    public void setUp() {
        mealMapper = new MealMapperImpl();
    }
}
