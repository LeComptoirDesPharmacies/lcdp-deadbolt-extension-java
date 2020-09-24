package fr.lcdp;

import models.Client;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import security.SecurityHelper;
import service.CaptchaService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Objects;
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