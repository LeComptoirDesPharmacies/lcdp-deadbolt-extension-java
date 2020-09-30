package fr.lcdp.java.actions;

import play.mvc.Http;

public interface CaptchaHandler {

    Boolean isCaptchaTokenValid(Http.Context context, String recaptchaToken);
}