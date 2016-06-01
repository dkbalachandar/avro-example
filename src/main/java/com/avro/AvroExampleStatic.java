package com.avro;


import com.avro.entity.User;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

/**
 * A Java program to demo the serialization and deserialization of Avro content
 */
public class AvroExampleStatic {

    public static void main(final String[] args) throws IOException {

        //Creating User instances with the setter, constructors
        User user1 = User.newBuilder().setId(1).setFName("Bala").setLName("Samy").build();
        User user2 = new User(2, "John", "Peter");
        User user3 = new User();
        user3.setId(3);
        user3.setFName("mike");
        user3.setLName("andy");

        //Create the tempFile
        File store = File.createTempFile("user", ".avro");

        //Serialize the user data to the temp file
        System.out.println("serializing users to temp file: " + store.getPath());
        DatumWriter<User> datumWriter = new SpecificDatumWriter<>(User.class);
        DataFileWriter<User> fw = new DataFileWriter<>(datumWriter);
        fw.create(user1.getSchema(), store);
        fw.append(user1);
        fw.append(user2);
        fw.append(user3);
        fw.close();

        // Deserialize the content from the temp file to User instance
        DatumReader<User> datumReader = new SpecificDatumReader<>(User.class);
        DataFileReader<User> fr = new DataFileReader<>(store, datumReader);
        while (fr.hasNext()) {
            System.out.println("Content::" + fr.next());
        }
        fr.close();
    }
}
