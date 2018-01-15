package com.acube.functions;
// Include the following imports to use blob APIs.
import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

public class Blobber {

    public static void main(String[] args) throws InvalidKeyException, URISyntaxException, IOException, StorageException {
        // Define the connection-string with your values
       String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=acube;AccountKey=BIsAZCGgSQPcqR+8vOJqaGs5T0/WfAo9HhMREyYatrhRPWmiQ8ENLHaURVmesC9e2n0mmIsCspje6ORcS+znbw==;EndpointSuffix=core.windows.net";

       CloudBlobContainer container = getContainer(storageConnectionString, "acubetest");

       CloudBlockBlob blob = updateBlob("/Users/sandeep.samudrala/azure/Transformation-Function/src/resources/handset_device_metadata_m3.csv", "handset_device_metadata_m3.csv", container);

       getBlobs(container);

    }

    public static CloudBlobContainer getContainer(String storageConnectionString, String containerName ) throws URISyntaxException, InvalidKeyException, StorageException {
        // Retrieve storage account from connection-string.
        CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

        // Create the blob client.
        CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

        // Get a reference to a container.
        // The container name must be lower case
        CloudBlobContainer container = blobClient.getContainerReference(containerName);

        // Create the container if it does not exist.
        container.createIfNotExists();

        return container;

    }

    public static CloudBlockBlob updateBlob(String filePath,  String blobName, CloudBlobContainer container)
            throws URISyntaxException, StorageException, IOException {

        // Create or overwrite the blob with contents from a local file.
        CloudBlockBlob blob = container.getBlockBlobReference(blobName);
        File source = new File(filePath);
        blob.upload(new FileInputStream(source), source.length());

        return blob;
    }

    public static void getBlobs(CloudBlobContainer container) throws IOException, StorageException {
        String filePath = "";
        for (ListBlobItem blobItem : container.listBlobs()) {
            // If the item is a blob, not a virtual directory
            if (blobItem instanceof CloudBlockBlob) {
                // Download the text
                CloudBlockBlob retrievedBlob = (CloudBlockBlob) blobItem;
                System.out.println(retrievedBlob.downloadText());
            }
        }
    }
}
