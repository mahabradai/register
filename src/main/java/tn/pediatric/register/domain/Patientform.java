package tn.pediatric.register.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Patientform.
 */
@Entity
@Table(name = "patientform")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Patientform implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "pathologie", nullable = false)
    private String pathologie;

    @Column(name = "typeobservation")
    private String typeobservation;

    @Column(name = "casfamiliaux")
    private String casfamiliaux;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Patientform id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPathologie() {
        return this.pathologie;
    }

    public Patientform pathologie(String pathologie) {
        this.setPathologie(pathologie);
        return this;
    }

    public void setPathologie(String pathologie) {
        this.pathologie = pathologie;
    }

    public String getTypeobservation() {
        return this.typeobservation;
    }

    public Patientform typeobservation(String typeobservation) {
        this.setTypeobservation(typeobservation);
        return this;
    }

    public void setTypeobservation(String typeobservation) {
        this.typeobservation = typeobservation;
    }

    public String getCasfamiliaux() {
        return this.casfamiliaux;
    }

    public Patientform casfamiliaux(String casfamiliaux) {
        this.setCasfamiliaux(casfamiliaux);
        return this;
    }

    public void setCasfamiliaux(String casfamiliaux) {
        this.casfamiliaux = casfamiliaux;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patientform)) {
            return false;
        }
        return id != null && id.equals(((Patientform) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Patientform{" +
            "id=" + getId() +
            ", pathologie='" + getPathologie() + "'" +
            ", typeobservation='" + getTypeobservation() + "'" +
            ", casfamiliaux='" + getCasfamiliaux() + "'" +
            "}";
    }
}
