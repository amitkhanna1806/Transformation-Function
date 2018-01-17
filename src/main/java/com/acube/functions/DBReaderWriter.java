package com.acube.functions;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.table.*;

import javax.rmi.CORBA.Util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.acube.functions.Utility.STORAGE_CONNECTION_STRING_COSMOS;

public class DBReaderWriter {

    public static String blobToCosmos(String containerName) throws InvalidKeyException, StorageException, URISyntaxException, IOException {
        Blobber blobber =new Blobber();
        CloudBlobContainer container = blobber.getContainer(Utility.STORAGE_CONNECTION_STRING_BLOB, containerName);
        String data = blobber.getBlobs(container);
        return data;



    }

    public static void blobToCosmosSync(String data, String cosmosStr) {
        try {
        // Setup the cloud storage account.
        CloudStorageAccount account = CloudStorageAccount.parse(cosmosStr);

        // Create a table service client.
        CloudTableClient tableClient = account.createCloudTableClient();


            CloudTable table = tableClient.getTableReference("handset_device_metadata_m3_newer"
                    + UUID.randomUUID().toString().replace("-", "")     );

            table.createIfNotExists();

            System.out.println(table.getName());



            HandsetEntity tableEntity = new HandsetEntity();

            TableBatchOperation tableOperation = new TableBatchOperation();;

            String partitionKey = "Handset";




            List<String> dataLine = Arrays.asList(data.split("\n"));


            for (String s : dataLine) {
                String key = s.split("\u0001")[0];
                tableEntity.entity=s;
                tableEntity.setPartitionKey(partitionKey);
                tableEntity.setRowKey(key);
                tableOperation.insert(tableEntity);
                table.execute(TableOperation.insert(tableEntity));
            }


        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
