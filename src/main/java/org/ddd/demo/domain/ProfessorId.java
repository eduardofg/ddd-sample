package org.ddd.demo.domain;

import java.util.UUID;

public class ProfessorId extends UniqueId {

    protected ProfessorId(UUID value) {
        super(value);
    }

    public static ProfessorId generate() {
        return new ProfessorId(UUID.randomUUID());
    }

    public static ProfessorId fromString(String value) {
        return value == null ? null : new ProfessorId(UUID.fromString(value));
    }
}
