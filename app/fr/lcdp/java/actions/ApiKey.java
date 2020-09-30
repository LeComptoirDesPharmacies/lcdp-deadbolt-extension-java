package fr.lcdp.java.actions;

import play.mvc.With;

import java.lang.annotation.*;

@With(ApiKeyAction.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Inherited
public @interface ApiKey
{
    String keyLocation() default "";

    String keyName() default "";
}