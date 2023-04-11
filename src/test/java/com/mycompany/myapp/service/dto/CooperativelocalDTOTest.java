package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class CooperativelocalDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CooperativelocalDTO.class);
        CooperativelocalDTO cooperativelocalDTO1 = new CooperativelocalDTO();
        cooperativelocalDTO1.setId(UUID.randomUUID());
        CooperativelocalDTO cooperativelocalDTO2 = new CooperativelocalDTO();
        assertThat(cooperativelocalDTO1).isNotEqualTo(cooperativelocalDTO2);
        cooperativelocalDTO2.setId(cooperativelocalDTO1.getId());
        assertThat(cooperativelocalDTO1).isEqualTo(cooperativelocalDTO2);
        cooperativelocalDTO2.setId(UUID.randomUUID());
        assertThat(cooperativelocalDTO1).isNotEqualTo(cooperativelocalDTO2);
        cooperativelocalDTO1.setId(null);
        assertThat(cooperativelocalDTO1).isNotEqualTo(cooperativelocalDTO2);
    }
}
