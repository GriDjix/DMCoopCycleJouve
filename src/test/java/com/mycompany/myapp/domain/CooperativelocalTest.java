package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class CooperativelocalTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cooperativelocal.class);
        Cooperativelocal cooperativelocal1 = new Cooperativelocal();
        cooperativelocal1.setId(UUID.randomUUID());
        Cooperativelocal cooperativelocal2 = new Cooperativelocal();
        cooperativelocal2.setId(cooperativelocal1.getId());
        assertThat(cooperativelocal1).isEqualTo(cooperativelocal2);
        cooperativelocal2.setId(UUID.randomUUID());
        assertThat(cooperativelocal1).isNotEqualTo(cooperativelocal2);
        cooperativelocal1.setId(null);
        assertThat(cooperativelocal1).isNotEqualTo(cooperativelocal2);
    }
}
