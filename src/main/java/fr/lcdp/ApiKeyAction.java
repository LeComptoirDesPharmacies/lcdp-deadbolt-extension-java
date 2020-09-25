package fr.lcdp;

import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Singleton
public class ApiKeyAction extends Action<ApiKeyAction> {

    private final CaptchaValidationInterface captchaValidationInterface;

    @Inject
    public ApiKeyAction(CaptchaValidationInterface captchaValidationInterface) {
        this.captchaValidationInterface = captchaValidationInterface;
    }

    @Override
    public CompletionStage<Result> call(Http.Context context) {

        if(this.captchaValidationInterface.isCaptchaTokenValid(context))
            return delegate.call(context);

        return CompletableFuture.completedFuture(forbidden());
    }
}