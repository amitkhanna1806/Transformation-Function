package com.acube.functions;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.*;
import com.microsoft.azure.serverless.functions.annotation.*;
import com.microsoft.azure.serverless.functions.*;
import com.microsoft.azure.storage.StorageException;

///**
// * Azure Functions with HTTP Trigger.
// */
public class HTTPTrigger {
    //    /**
//     * This function listens at endpoint "/api/hello". Two ways to invoke it using "curl" command in bash:
//     * 1. curl -d "HTTP Body" {your host}/api/hello
//     * 2. curl {your host}/api/hello?name=HTTP%20Query
//     */
    @FunctionName("COSMOSLOAD")
    public HttpResponseMessage<String> hello(
            @HttpTrigger(name = "req", methods = {"get", "post"}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) throws IOException, StorageException, InvalidKeyException, URISyntaxException {
        context.getLogger().info("Java HTTP trigger processed a request.");


        Loader.endToEnd(Utility.KEY_VAULT_CLIENT_ID, Utility.KEY_VAULT_CLIENT_KEY, Utility.STORAGE_CONNECTION_STRING_COSMOS);
        return request.createResponse(200, "Cosmos Load initiated");
    }
}
//    @FunctionName("BlobReadFail")
//    public HttpResponseMessage<String> readFail(
//            @HttpTrigger(name = "req", methods = {"get", "post"}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
//            final ExecutionContext context) throws IOException, StorageException, InvalidKeyException, URISyntaxException {
//        context.getLogger().info("Java HTTP trigger processed a request.");
//
//
//        Loader.endToEnd(Utility.KEY_VAULT_CLIENT_ID2, Utility.KEY_VAULT_CLIENT_KEY2, Utility.STORAGE_CONNECTION_STRING_COSMOS);
//        return request.createResponse(404, "Blob read fail");
//    }
//    @FunctionName("CosmosWriteFail")
//    public HttpResponseMessage<String> writeFail(
//            @HttpTrigger(name = "req", methods = {"get", "post"}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
//            final ExecutionContext context) throws IOException, StorageException, InvalidKeyException, URISyntaxException {
//        context.getLogger().info("Java HTTP trigger processed a request.");
//
//
//        Loader.endToEnd(Utility.KEY_VAULT_CLIENT_ID3, Utility.KEY_VAULT_CLIENT_KEY3, Utility.STORAGE_CONNECTION_STRING_COSMOS_READ);
//        return request.createResponse(404, "Cosmos Load Fail");
//    }
//
//    @FunctionName("Dummy")
//    public HttpResponseMessage<String> Dummy(
//            @HttpTrigger(name = "req", methods = {"get", "post"}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
//            final ExecutionContext context) throws IOException, StorageException, InvalidKeyException, URISyntaxException {
//        context.getLogger().info("Java HTTP trigger processed a request.");
//
//
//        String a ="dummy";
//        return request.createResponse(200, a);
//    }
//
//    public static String dummy(){
//        return "Hi";
//    }
//}