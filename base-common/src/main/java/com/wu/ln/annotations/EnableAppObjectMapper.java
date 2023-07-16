package com.wu.ln.annotations;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({com.wu.ln.component.AppObjectMapperConfiguration.class})
public @interface EnableAppObjectMapper {
}
