package com.avro;


import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

/**
 * A Java program to demo the serialization and deserialization of Avro content using schema file
 */
public class AvroExampleDynamic {

    public static void main(final String[] args) throws IOException {

        //Create the tempFile
        Schema schema = new Schema.Parser().parse(new File("/home/avro-example/src/main/avro/user.avsc"));
        GenericRecord user1 = new GenericData.Record(schema);
        user1.put("id", 12);
        user1.put("fName", "fn1");
        user1.put("lName", "ln1");

        GenericRecord user2 = new GenericData.Record(schema);
        user2.put("id", 13);
        user2.put("fName", "fn2");
        user2.put("lName", "ln2");

        //Serialize the user data to the temp file
        File store = File.createTempFile("user", ".avro");
        DatumWriter<GenericRecord> datumWriter = new SpecificDatumWriter<>(schema);
        DataFileWriter<GenericRecord> fw = new DataFileWriter<>(datumWriter);
        fw.create(schema, store);
        fw.append(user1);
        fw.append(user2);
        fw.close();

        // Deserialize the content from the temp file to User instance
        DatumReader<GenericRecord> datumReader = new SpecificDatumReader<>(schema);
        DataFileReader<GenericRecord> fr = new DataFileReader<>(store, datumReader);
        while (fr.hasNext()) {
            System.out.println("Content::" + fr.next());
        }
        fr.close();
    }
}
