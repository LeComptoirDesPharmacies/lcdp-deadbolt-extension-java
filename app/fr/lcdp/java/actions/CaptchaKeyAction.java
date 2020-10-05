package fr.lcdp.java.actions;

import be.objectify.deadbolt.java.actions.AbstractDeadboltAction;
import be.objectify.deadbolt.java.cache.HandlerCache;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.NotImplementedException;
import play.Configuration;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class CaptchaKeyAction extends AbstractDeadboltAction<CaptchaKey> {

    private final CaptchaHandler captchaHandler;

    @Inject
    public CaptchaKeyAction(HandlerCache handlerCache, Configuration config, CaptchaHandler captchaHandler) {
        super(handlerCache, config);
        this.captchaHandler = captchaHandler;
    }

    public CaptchaKeyAction(HandlerCache handlerCache, Configuration config, CaptchaHandler captchaHandler,
                            final CaptchaKey apiKey) {
        super(handlerCache, config);
        this.captchaHandler = captchaHandler;
        this.configuration = apiKey;
    }

    @Override
    public CompletionStage<Result> execute(Http.Context context) throws Exception {

        String recaptchaToken = null;
        if(this.configuration.keyLocation().equals("header")){
            recaptchaToken = this.extractHeaderCaptchaToken(context.request().headers(), this.configuration.keyName());
        }else{
            throw new NotImplementedException("This key location is not implemented yet");
        }

        if(!this.captchaHandler.isCaptchaTokenValid(context, recaptchaToken)) {
            return this.unauthorizeAndFail(
                    context,
                    this.getDeadboltHandler(configuration.handlerKey()), Optional.empty()
            );
        }

        return this.authorizeAndExecute(context);
    }

    /**
     * Extract header value from key name
     * @param headers Request headers
     * @param keyName Key name
     *
     * @return if found return captcha token, else return null
     */
    public String extractHeaderCaptchaToken(Map<String, String[]> headers, String keyName) {
        String[] recaptchaToken = headers.getOrDefault(keyName, null);
        if (ArrayUtils.isNotEmpty(recaptchaToken)) {
            return recaptchaToken[0];
        }
        return null;
    }
}