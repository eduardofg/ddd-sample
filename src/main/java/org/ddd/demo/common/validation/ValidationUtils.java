package org.ddd.demo.common.validation;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.ddd.demo.common.exception.IntegrityException;

public class ValidationUtils {

    private static ValidationUtils instance;

    private Validator validator;

    private ValidationUtils() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    static private ValidationUtils getInstance() {
        if (instance == null) {
            instance = new ValidationUtils();
        }
        return instance;
    }

    static private Validator getValidatorInstance() {
        return getInstance().validator;
    }

    static public <T> void assertIntegrity(T object, Class<?>... groups) {

        Set<ConstraintViolation<T>> violations = getValidatorInstance().validate(object, groups);

        if (violations.isEmpty() == false) {
            throw new IntegrityException(violations);
        }
    }

    static public <T> Optional<Set<ConstraintViolation<T>>> validateIntegrity(T object, Class<?>... groups) {

        Set<ConstraintViolation<T>> violations = getValidatorInstance().validate(object, groups);

        return violations.isEmpty() ? Optional.empty() : Optional.ofNullable(violations);
    }

}
