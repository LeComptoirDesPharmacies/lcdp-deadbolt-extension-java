package fr.lcdp.deadbolt.java.actions;

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

public class ApiKeyAction extends AbstractDeadboltAction<ApiKey> {

    private final ApiKeyHandler apiKeyHandler;

    @Inject
    public ApiKeyAction(HandlerCache handlerCache, Configuration config, ApiKeyHandler apiKeyHandler) {
        super(handlerCache, config);
        this.apiKeyHandler = apiKeyHandler;
    }

    public ApiKeyAction(HandlerCache handlerCache, Configuration config, ApiKeyHandler apiKeyHandler,
                        final ApiKey apiKey) {
        super(handlerCache, config);
        this.apiKeyHandler = apiKeyHandler;
        this.configuration = apiKey;
    }

    @Override
    public CompletionStage<Result> execute(Http.Context context) throws Exception {

        String apiKey = null;
        if(this.configuration.keyLocation().equals("header")){
            apiKey = this.extractHeaderApiKey(context.request().headers(), this.configuration.keyName());
        }else{
            throw new NotImplementedException("This key location is not implemented yet");
        }

        if(!this.apiKeyHandler.isValid(context, apiKey)) {
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
     * @return if found return api key, else return null
     */
    public String extractHeaderApiKey(Map<String, String[]> headers, String keyName) {
        String[] apiKey = headers.getOrDefault(keyName, null);
        if (ArrayUtils.isNotEmpty(apiKey)) {
            return apiKey[0];
        }
        return null;
    }
}