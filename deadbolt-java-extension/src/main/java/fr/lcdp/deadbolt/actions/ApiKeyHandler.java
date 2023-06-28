package fr.lcdp.deadbolt.actions;

import play.mvc.Http;

public interface ApiKeyHandler {

    Boolean isValid(Http.RequestHeader request, String apiKey);
}