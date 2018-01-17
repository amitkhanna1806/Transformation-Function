package com.acube.functions;

import com.microsoft.azure.storage.StorageException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

public class writeFail {
    public static void main(String[] args) throws IOException, StorageException, InvalidKeyException, URISyntaxException {
        Loader.endToEnd(Utility.KEY_VAULT_CLIENT_ID3, Utility.KEY_VAULT_CLIENT_KEY3, Utility.STORAGE_CONNECTION_STRING_COSMOS_READ);

    }
}
