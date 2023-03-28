package br.com.alticci.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Objects;

public class AlticciIndiceImpl implements ConstraintValidator<AlticciIndice, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !Objects.isNull(s) && !s.isEmpty() && s.matches("\\d+");
    }
}
