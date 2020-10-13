package fr.lcdp.deadbolt.java.actions;

import play.mvc.Http;

public interface ApiKeyHandler {

    Boolean isValid(Http.Context context, String apiKey);
}