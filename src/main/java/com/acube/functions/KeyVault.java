package com.acube.functions;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import com.microsoft.azure.keyvault.KeyVaultClient;
import com.microsoft.azure.keyvault.authentication.KeyVaultCredentials;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class KeyVault {
//    String keyID = Utility.keyVaultKeyID;
//
//    // If no key ID was specified, we will create a new secret in Key Vault.
//    // To create a new secret, this client needs full permission to Key
//    // Vault secrets.
//    // Once the secret is created, its ID can be added to App.config. Once
//    // this is done,
//    // this client only needs read access to secrets.
//        if (keyID == null || keyID.isEmpty()) {
//        keyID = KeyVaultUtility.createSecret("KVGettingStartedSecret");
//    }

    // Retrieve storage account information from connection string
    // How to create a storage connection string -
    // https://azure.microsoft.com/en-us/documentation/articles/storage-configure-connection-string/
    static class KeyVaultCredentialsImpl extends KeyVaultCredentials {

        String id;
        String key;
        KeyVaultCredentialsImpl(String id, String key) {
            this.id =id;
            this.key=key;
        }

        @Override
        public String doAuthenticate(String authorization, String resource, String scope) {
            try {
                AuthenticationResult authResult = getAccessToken(authorization, resource);
                return authResult.getAccessToken();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        private AuthenticationResult getAccessToken(String authorization, String resource) throws Exception {

            AuthenticationResult result = null;
            ExecutorService service = null;
            service = Executors.newFixedThreadPool(1);
            AuthenticationContext context = new AuthenticationContext(authorization, false, service);
            Future<AuthenticationResult> future = null;
            ClientCredential credentials = new ClientCredential(id, key);
            future = context.acquireToken(resource, credentials, null);
            result = future.get();


            if (result == null) {
                throw new RuntimeException("authentication result was null");
            }

            return result;
        }
    }
    public static String getConnectionString(KeyVaultClient kv){

        String secretIdentifier = Utility.KEY_VAULT_SECRET_URL;
        String secretString = kv.getSecret(secretIdentifier).value();
        return secretString;

    }
}
