package org.ddd.demo.domain;

import java.util.UUID;

public class TurmaId extends UniqueId {

    protected TurmaId(UUID value) {
        super(value);
    }

    public static TurmaId generate() {
        return new TurmaId(UUID.randomUUID());
    }

    public static TurmaId fromString(String value) {
        return value == null ? null : new TurmaId(UUID.fromString(value));
    }
}
