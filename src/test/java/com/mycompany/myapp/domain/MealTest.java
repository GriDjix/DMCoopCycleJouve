package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class MealTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Meal.class);
        Meal meal1 = new Meal();
        meal1.setId(UUID.randomUUID());
        Meal meal2 = new Meal();
        meal2.setId(meal1.getId());
        assertThat(meal1).isEqualTo(meal2);
        meal2.setId(UUID.randomUUID());
        assertThat(meal1).isNotEqualTo(meal2);
        meal1.setId(null);
        assertThat(meal1).isNotEqualTo(meal2);
    }
}
