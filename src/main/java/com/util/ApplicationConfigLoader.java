package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ApplicationConfigLoader {

    public static Properties loadConfig(){
        Properties props = new Properties();

        try(FileInputStream file = new FileInputStream("src/main/java/com/alura/jdbc/resources/application.properties")){
            props.load(file);
        }catch (FileNotFoundException e) {
            System.out.println("file .properties not found");
        }catch (IOException e) {
            System.out.println("Exception reading properties file");
        }
        return props;
    }
}
