package custom_annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@NotNull(message = "Percentage must be there")
@Documented
public @interface PercentageValidator {
    String message() default "default message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
