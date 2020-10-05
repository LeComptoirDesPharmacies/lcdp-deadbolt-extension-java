package fr.lcdp.java.actions;

import be.objectify.deadbolt.java.ConfigKeys;
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

    /**
     * Use a specific {@link be.objectify.deadbolt.java.DeadboltHandler} for this restriction in place of the global
     * one, identified by a key.
     *
     * @return the ky of the handler
     */
    String handlerKey() default ConfigKeys.DEFAULT_HANDLER_KEY;
}