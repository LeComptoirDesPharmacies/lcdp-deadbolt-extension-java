package fr.lcdp.deadbolt.actions;

import be.objectify.deadbolt.java.actions.AbstractDeadboltAction;
import be.objectify.deadbolt.java.cache.BeforeAuthCheckCache;
import be.objectify.deadbolt.java.cache.HandlerCache;
import fr.lcdp.deadbolt.exceptions.NotImplementedException;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import com.typesafe.config.Config;

public class ApiKeyAction extends AbstractDeadboltAction<ApiKey> {

    private final ApiKeyHandler apiKeyHandler;

    @Inject
    public ApiKeyAction(final HandlerCache handlerCache,
                        final BeforeAuthCheckCache beforeAuthCheckCache,
                        final Config config,
                        ApiKeyHandler apiKeyHandler) {
        super(handlerCache, beforeAuthCheckCache, config);
        this.apiKeyHandler = apiKeyHandler;
    }

    @Override
    public CompletionStage<Result> execute(final Http.RequestHeader request) {

        String apiKey = null;
        if(this.configuration.keyLocation().equals("header")){
            apiKey = this.extractHeaderApiKey(request.getHeaders(), this.configuration.keyName());
        }else{
            throw new NotImplementedException("This key location is not implemented yet");
        }

        if(!this.apiKeyHandler.isValid(request, apiKey)) {
            return this.unauthorizeAndFail(
                    request,
                    this.getDeadboltHandler(configuration.handlerKey()), Optional.empty()
            );
        }

        return this.authorizeAndExecute(request);
    }

    @Override
    protected boolean deferred() {
        return configuration.deferred();
    }

    @Override
    public Optional<String> getContent() {
        return Optional.ofNullable(configuration.content());
    }

    @Override
    public String getHandlerKey() {
        return configuration.handlerKey();
    }

    /**
     * Extract header value from key name
     * @param headers Request headers
     * @param keyName Key name
     *
     * @return if found return api key, else return null
     */
    private String extractHeaderApiKey(Http.Headers headers, String keyName) {
        return headers.get(keyName).orElse(null);
    }
}