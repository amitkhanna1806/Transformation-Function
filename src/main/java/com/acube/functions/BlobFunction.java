//package com.acube.functions;
//
//import com.microsoft.azure.serverless.functions.annotation.*;
//
//import static com.acube.functions.Utility.STORAGE_CONNECTION_STRING_COSMOS;
//
//public class BlobFunction {
//
//
//    @FunctionName("BlobFunction")
//    @StorageAccount("acube")
//    public static void copy(@BlobTrigger(name = "acube", path = "acube/{name}") String content) {
//        DBReaderWriter.blobToCosmosSync(content);
//    }
//
//}
////        @BlobTrigger(name = "blob", dataType ="blobTrigger",
////        path = "acube/data/meta/HandsetDeviceMetadataM3/handset_device_metadata_m3.csv.gz",
////        connection = "DefaultEndpointsProtocol=https;" +
////                "AccountName=acube;AccountKey=d3ogxHhcfajnM6GmTGRrbvoSujAF686e5OjqxtcJDnTHCQnlIP4VnYe1UrOmzFBaAuzIJsoFi5oPPXj3otdTZw==;" +
////                "TableEndpoint=https://acube.table.cosmosdb.azure.com:443/;") )
//
//
