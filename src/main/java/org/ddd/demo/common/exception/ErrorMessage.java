package org.ddd.demo.common.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(value = Include.NON_NULL)
public final class ErrorMessage implements Serializable {

    private static final long serialVersionUID = 2778976311240492443L;

    private String code;

    private String message;

    public static ErrorMessage from(final Exception e) {
        return ErrorMessage.builder().code(e.getClass().getSimpleName()).message(e.getLocalizedMessage()).build();
    }

}
