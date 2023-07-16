package com.wu.ln.annotations;

import com.wu.ln.component.GatewayFilterHandlerInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({GatewayFilterHandlerInterceptor.class})
@Inherited
public @interface EnableGatewayFilter {

}