
A Java program to demo the serialization and deserialization of Avro content

1. Checkout the repo
2. The avro schema file is located in the  src\main\avro folder. Generate the Java file(s) by running the below command

     java -jar avro-tools-1.7.7.jar compile schema PROJECT_DIR/avro/src/main/avro/ PROJECT_DIR/avro/src/main/java

  [Download avro-tools-1.7.7.jar from the website and replace PROJECT_DIR with the proper path]

  3. Then run either AvroExampleDynamic.java or AvroExampleStatic.java and check the output





