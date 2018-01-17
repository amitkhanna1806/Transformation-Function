package com.acube.functions;

import com.microsoft.azure.storage.StorageException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

public class readFail {
    public static void main(String[] args) throws IOException, StorageException, InvalidKeyException, URISyntaxException {
        Loader.endToEnd(Utility.KEY_VAULT_CLIENT_ID2, Utility.KEY_VAULT_CLIENT_KEY2, Utility.STORAGE_CONNECTION_STRING_COSMOS);

    }
}
