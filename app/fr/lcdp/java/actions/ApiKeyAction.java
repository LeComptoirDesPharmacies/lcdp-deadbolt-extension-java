package fr.lcdp.java.actions;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.NotImplementedException;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ApiKeyAction extends Action<ApiKey> {

    private final CaptchaHandler captchaHandler;

    @Inject
    public ApiKeyAction(CaptchaHandler captchaHandler) {
        this.captchaHandler = captchaHandler;
    }

    public ApiKeyAction(CaptchaHandler captchaHandler,
                        final ApiKey apiKey) {
        this(captchaHandler);
        this.configuration = apiKey;
    }

    @Override
    public CompletionStage<Result> call(Http.Context context) {

        String recaptchaToken = null;
        if(this.configuration.keyLocation().equals("header")){
            recaptchaToken = this.extractHeaderCaptchaToken(context.request().headers(), this.configuration.keyName());
        }else{
            throw new NotImplementedException("This key location is not implemented yet");
        }

        if(!this.captchaHandler.isCaptchaTokenValid(context, recaptchaToken))
            return CompletableFuture.completedFuture(forbidden());

        return delegate.call(context);
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