package org.ddd.demo.common.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.ddd.demo.common.i18n.I18n;
import org.springframework.context.i18n.LocaleContextHolder;

public class IntegrityException extends IllegalArgumentException {

    private Set<? extends ConstraintViolation<?>> violations;

    private static final long serialVersionUID = 5471545598335027351L;

    public IntegrityException(Set<? extends ConstraintViolation<?>> violations) {
        super();
        this.violations = violations;
    }

    @Override
    public String getMessage() {
        return violations.stream().map(violation -> this.parseViolation(violation)).reduce("", String::concat);
    }

    private String parseViolation(final ConstraintViolation<?> violation) {

        List<Object> arguments = new ArrayList<>();

        arguments.add(violation.getInvalidValue());

        violation.getConstraintDescriptor().getAttributes().forEach((key, value) -> {
            if (!key.equals("message") && !key.equals("groups") && !key.equals("payload")) {
                arguments.add(value);
            }
        });

        return new StringBuilder(" - ")
                .append(I18n.instance().getMessage(violation.getMessage(),
                        arguments.toArray(new Object[arguments.size()]), LocaleContextHolder.getLocale()))
                .append("\n").toString();
    }

}