package me.isortegah.pocs.tools.utils.settings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PropertiesFile {

    private static final Logger logger = LogManager.getLogger(PropertiesFile.class);
    private static PropertiesFile propertiesFile;

    public PropertiesFile(){}

    public static PropertiesFile getInstance(){
        if( propertiesFile == null){
            propertiesFile = new PropertiesFile();
        } else {
            logger.error("No se puede crear el objeto Properties.");
        }

        return propertiesFile;
    }

    public Properties readPropertiesFile(String path) {
        Properties props = new Properties();
        try {
            logger.info(path);
            InputStream input = null;
            input = getClass().getClassLoader().getResourceAsStream(path);

            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public Properties getProperties(){
        String file = "";
        file = ( System.getProperty("properties") != null)? System.getProperty("properties"):"default.properties";
        return readPropertiesFile("properties/"+file);
    }

}
