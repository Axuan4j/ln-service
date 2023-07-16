package com.wu.ln.annotations;

import com.wu.ln.component.CommunicationInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({CommunicationInterceptor.class})
@Inherited
public @interface EnableInnerCommunication {
}