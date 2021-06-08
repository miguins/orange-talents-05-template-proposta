package br.com.zupedu.lucasmiguins.proposta.util.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;


@Pattern(regexp = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$", message = "deve estar no formato base64")
@Constraint(validatedBy = { })
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64 {

    String message() default "O campo deve estar no formato base64";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
