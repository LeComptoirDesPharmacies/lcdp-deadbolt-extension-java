package fr.lcdp.deadbolt.actions;

import be.objectify.deadbolt.java.Constants;
import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.actions.DeferredDeadbolt;
import play.mvc.With;

import java.lang.annotation.*;

@With(ApiKeyAction.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Inherited
public @interface ApiKey
{
    /**
     * Indicates the expected response type.  Useful when working with non-HTML responses.  This is free text, which you
     * can use in {@link DeadboltHandler#onAuthFailure} to decide on how to handle the response.
     *
     * @return a content indicator
     */
    String content() default "";

    /**
     * Use a specific {@link be.objectify.deadbolt.java.DeadboltHandler} for this restriction in place of the global
     * one, identified by a key.
     *
     * @return the ky of the handler
     */
    String handlerKey() default Constants.DEFAULT_HANDLER_KEY;

    /**
     * If true, the annotation will only be run if there is a {@link DeferredDeadbolt} annotation at the class level.
     *
     * @return true iff the associated action should be deferred until class-level annotations are applied.
     */
    boolean deferred() default false;

    /**
     * Following are custom keys
     * @return
     */
    String keyLocation() default "";

    String keyName() default "";
}