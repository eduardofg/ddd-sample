package org.ddd.demo.domain;

import static org.ddd.demo.common.validation.ValidationUtils.assertIntegrity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Nome {

    @NotNull(message = "sample.nome.null")
    @Size(min = 10, message = "sample.nome.size")
    private String nome;

    public Nome(String nome) {

        this.nome = nome;

        assertIntegrity(this);
    }

    public boolean equals(Object anObject) {

        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {

            Nome typedObject = (Nome) anObject;
            equalObjects = this.nome.equals(typedObject.nome);
        }

        return equalObjects;
    }

    @Override
    public int hashCode() {

        int hashCodeValue = +(43685 * 83) + this.nome.hashCode();

        return hashCodeValue;
    }

    @Override
    public String toString() {
        return nome;
    }
}
