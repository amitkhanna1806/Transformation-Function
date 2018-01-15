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

public class DBReaderWriter {

    public static void main(String[] args) throws URISyntaxException, InvalidKeyException {
        // Setup the cloud storage account.
        CloudStorageAccount account = CloudStorageAccount.parse("DefaultEndpointsProtocol=https;AccountName=acube;AccountKey=d3ogxHhcfajnM6GmTGRrbvoSujAF686e5OjqxtcJDnTHCQnlIP4VnYe1UrOmzFBaAuzIJsoFi5oPPXj3otdTZw==;TableEndpoint=https://acube.table.cosmosdb.azure.com:443/;");

        // Create a table service client.
        CloudTableClient tableClient = account.createCloudTableClient();

        try {
            // Retrieve a reference to a table.
            // Append a random UUID to the end of the table name so that this
            // sample can be run more than once in quick succession.
            CloudTable table = tableClient.getTableReference("handset_device_metadata_m3_new"
                    + UUID.randomUUID().toString().replace("-", "")     );


            // Create the table if it doesn't already exist.
            table.createIfNotExists();

            System.out.println(table.getName());


//            String filePath = "/Users/sandeep.samudrala/azure/Transformation-Function/src/resources/handset_device_metadata_m3.csv";
//
//
//            FileReader fr = new FileReader(filePath);
//            BufferedReader br = new BufferedReader(fr);

            HandsetEntity tableEntity = new HandsetEntity();

            TableBatchOperation tableOperation = new TableBatchOperation();;
            String line;
            String partitionKey = "Handset";
//            while (( line = br.readLine()) != null)
//            {
//                String key = line.split("\u0001")[0];
//                tableEntity.entity=line;
//                tableEntity.setPartitionKey(partitionKey);
//                tableEntity.setRowKey(key);
//                tableOperation.insert(tableEntity);
//                table.execute(TableOperation.insert(tableEntity));
//            }


            String data = blobToCosmos();
//
//            System.out.println(data);


            List<String> dataLine = Arrays.asList(data.split("\n"));


            for (String s : dataLine) {
                String key = s.split("\u0001")[0];
                tableEntity.entity=s;
                tableEntity.setPartitionKey(partitionKey);
                tableEntity.setRowKey(key);
                tableOperation.insert(tableEntity);
                table.execute(TableOperation.insert(tableEntity));
            }




//            // Illustrates how to list the tables.
//            BasicListing();
//
//            // Illustrates how to form and execute a single insert operation.
//            BasicInsertEntity();
//
//            // Illustrates how to form and execute a batch operation.
//            BasicBatch();
//
//            // Illustrates how to form and execute a query operation.
//            BasicQuery();
//
//            // Illustrates how to form and execute an upsert operation.
//            BasicUpsert();
//
//            // Illustrates how to form and execute an entity delete operation.
//            BasicDeleteEntity();

            // Delete the table.
//            table.deleteIfExists();

        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static String blobToCosmos() throws InvalidKeyException, StorageException, URISyntaxException, IOException {
        Blobber blobber =new Blobber();
        CloudBlobContainer container = blobber.getContainer(Utility.STORAGE_CONNECTION_STRING_BLOB, "acube");
        String data = blobber.getBlobs(container);
        return data;



    }

    public static void blobToCosmosSync(String content) {
        try {
        // Setup the cloud storage account.
        CloudStorageAccount account = CloudStorageAccount.parse("DefaultEndpointsProtocol=https;AccountName=acube;AccountKey=d3ogxHhcfajnM6GmTGRrbvoSujAF686e5OjqxtcJDnTHCQnlIP4VnYe1UrOmzFBaAuzIJsoFi5oPPXj3otdTZw==;TableEndpoint=https://acube.table.cosmosdb.azure.com:443/;");

        // Create a table service client.
        CloudTableClient tableClient = account.createCloudTableClient();


            CloudTable table = tableClient.getTableReference("handset_device_metadata_m3_new"
                    + UUID.randomUUID().toString().replace("-", "")     );

            table.createIfNotExists();

            System.out.println(table.getName());



            HandsetEntity tableEntity = new HandsetEntity();

            TableBatchOperation tableOperation = new TableBatchOperation();;

            String partitionKey = "Handset";




            List<String> dataLine = Arrays.asList(content.split("\n"));


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
