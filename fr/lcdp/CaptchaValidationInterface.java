package fr.lcdp;

public interface CaptchaValidationInterface{

    Boolean isCaptchaTokenValid(Http.Context context);
}