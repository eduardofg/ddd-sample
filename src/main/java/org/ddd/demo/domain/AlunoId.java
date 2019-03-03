package org.ddd.demo.domain;

import java.util.UUID;

public class AlunoId extends UniqueId {

    protected AlunoId(UUID value) {
        super(value);
    }

    public static AlunoId generate() {
        return new AlunoId(UUID.randomUUID());
    }

    public static AlunoId fromString(String value) {
        return value == null ? null : new AlunoId(UUID.fromString(value));
    }
}
