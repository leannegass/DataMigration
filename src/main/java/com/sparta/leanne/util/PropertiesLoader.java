package com.sparta.leanne.util;
// Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    public static String getProperties(String key){
        Properties properties = new Properties();
        try {
            properties.load( new FileReader(("src/main/resources/database.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);


    }
}
