package com.wu.ln.annotations;

import com.wu.ln.component.ValidAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Import({ValidAutoConfiguration.class})
public @interface EnableValidAutoConfiguration {
}
