package me.isortegah.pocs.tools.utils.settings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Config {

    private static final Logger logger = LogManager.getLogger(Config.class);
    private static Config config;
    private Properties propTest;
    private HashMap<String,String> paramMap = new HashMap<String,String>();

    private Config(){
        propTest = new PropertiesFile().getProperties();
    }

    public static Config getInstance() {
        if( config == null ) {
            config = new Config();
        }
        return config;
    }

    public void load() {
        Iterator<String> params = getParameters().iterator();
        while( params.hasNext()){
            String param = params.next();
            paramMap.put(param,propTest.getProperty(param,""));
        }
    }

    private Set<String> getParameters(){
        Set<String> params = new HashSet<>(getListOfParams());
        return params;
    }

    private List<String> getListOfParams(){
        return Arrays.asList(
                "headless",
                "remoteWebDriver",
                "urlHub"
        );
    }

    public Map<String, String> getParamsConfig(){
        return paramMap;
    }

}
