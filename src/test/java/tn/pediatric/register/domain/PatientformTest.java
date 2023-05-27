package tn.pediatric.register.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.pediatric.register.web.rest.TestUtil;

class PatientformTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Patientform.class);
        Patientform patientform1 = new Patientform();
        patientform1.setId(1L);
        Patientform patientform2 = new Patientform();
        patientform2.setId(patientform1.getId());
        assertThat(patientform1).isEqualTo(patientform2);
        patientform2.setId(2L);
        assertThat(patientform1).isNotEqualTo(patientform2);
        patientform1.setId(null);
        assertThat(patientform1).isNotEqualTo(patientform2);
    }
}
