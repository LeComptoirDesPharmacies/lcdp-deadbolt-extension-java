package fr.lcdp;

import be.objectify.deadbolt.java.actions.Deferrable;
import play.mvc.With;

import java.lang.annotation.*;

@With(ApiKeyAction.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Inherited
@Deferrable
public @interface ApiKey
{

}