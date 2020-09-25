package fr.lcdp;

import play.mvc.Http;

public interface CaptchaValidationInterface{

    Boolean isCaptchaTokenValid(Http.Context context);
}