package com.acube.functions;

import com.microsoft.azure.keyvault.KeyVaultClient;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

public class Loader {

    public static void endToEnd(String id, String key, String cosmosStr) throws InvalidKeyException, StorageException, URISyntaxException, IOException {

        KeyVault.KeyVaultCredentialsImpl credentials = new KeyVault.KeyVaultCredentialsImpl(id,key);
        KeyVaultClient kv = new KeyVaultClient(credentials);


        String secretString = KeyVault.getConnectionString(kv);
        CloudBlobContainer container = Blobber.getContainer(secretString, "acuberemote");
        String data = Blobber.getBlobs(container);
        System.out.println(data);
        DBReaderWriter.blobToCosmosSync(data, cosmosStr);
    }

    public static void main(String[] args) throws IOException, StorageException, InvalidKeyException, URISyntaxException {
        Loader.endToEnd(Utility.KEY_VAULT_CLIENT_ID, Utility.KEY_VAULT_CLIENT_KEY, Utility.STORAGE_CONNECTION_STRING_COSMOS);

    }
}
