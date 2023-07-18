package com.wu.ln.annotations;

import com.wu.ln.component.ServiceExceptionHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@Import({ServiceExceptionHandler.class})
public @interface EnableServiceExceptionHandler {
}
