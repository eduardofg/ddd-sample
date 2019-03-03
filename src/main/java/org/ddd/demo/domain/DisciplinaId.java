package org.ddd.demo.domain;

import java.util.UUID;

public class DisciplinaId extends UniqueId {

    protected DisciplinaId(UUID value) {
        super(value);
    }

    public static DisciplinaId generate() {
        return new DisciplinaId(UUID.randomUUID());
    }

    public static DisciplinaId fromString(String value) {
        return value == null ? null : new DisciplinaId(UUID.fromString(value));
    }
}
