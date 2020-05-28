package org.example.persistance;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import org.example.domein.Data;

import java.io.*;

public class PersistenceManager implements Serializable{
    private final static String ENDPOINT ="https://acrememberipass.blob.core.windows.net/";
    private final static String SASTOKEN = "?sv=2019-10-10&ss=b&srt=sco&sp=rwdlacx&se=2021-03-13T02:28:11Z&st=2020-05-22T17:28:11Z&spr=https&sig=pjFT5LF5sEGrL%2F4pRkxD0BButB31lsUXHPnH7qdX0Tc%3D";
    private final static String CONTAINER = "acremember";

    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder().endpoint(ENDPOINT).sasToken(SASTOKEN).containerName(CONTAINER).buildClient();

    public static void loadDataFromAzure() throws IOException, ClassNotFoundException {
        if (blobContainer.exists()){
            BlobClient blob = blobContainer.getBlobClient("blob_data");

            if (blob.exists()){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos); // download naar eigen output

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                Object object = ois.readObject();
                if (object instanceof Data){
                    Data loadedData = (Data) object;
                    Data.setData(loadedData);}

                baos.close();
                bais.close();
            }

        }

    }

    public static void saveDataToAzure() throws IOException {
        if (!blobContainer.exists()){
            blobContainer.create();
        }

        BlobClient blob = blobContainer.getBlobClient("blob_data"); // object in bak die je wil overwriten
        Data dataToSave = Data.getData();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(dataToSave);

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);

        baos.close();
        bais.close();
    }
}
