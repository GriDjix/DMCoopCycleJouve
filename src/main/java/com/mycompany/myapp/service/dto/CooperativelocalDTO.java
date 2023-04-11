package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Cooperativelocal} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CooperativelocalDTO implements Serializable {

    @NotNull
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String city;

    @NotNull
    private String country;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CooperativelocalDTO)) {
            return false;
        }

        CooperativelocalDTO cooperativelocalDTO = (CooperativelocalDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cooperativelocalDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CooperativelocalDTO{" +
            "id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            "}";
    }
}
