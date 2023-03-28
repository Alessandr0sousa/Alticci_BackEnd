package br.com.alticci.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {AlticciIndiceImpl.class} )
public @interface AlticciIndice {
    String message() default "Index alticci is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
