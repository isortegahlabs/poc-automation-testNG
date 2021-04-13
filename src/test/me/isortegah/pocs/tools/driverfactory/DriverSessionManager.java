package me.isortegah.pocs.tools.driverfactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DriverSessionManager {
    private static final Logger logger = LogManager.getLogger(DriverSessionManager.class);
    private DriverSessionManager(){}
    private static DriverSessionManager instance = new DriverSessionManager();
    private HashMap<String, RemoteWebDriver> driverMap = new HashMap<>();

    public static DriverSessionManager getInstance(){
        return instance;
    }

    public synchronized void setDriverSession(String sessionId, RemoteWebDriver rwd){
        logger.info("**--> SetDriverSession: " + rwd.getSessionId());
        driverMap.put( sessionId , rwd);
        logger.info("**--> Size: " + driverMap.size());
    }

    public RemoteWebDriver getDriverSession(String sessionID){
        return driverMap.get(sessionID);
    }

    public void logginDriverMap(){
        Iterator<Map.Entry<String, RemoteWebDriver>> it = driverMap.entrySet().iterator();
        logger.info("^^^Size: --> " + driverMap.size());
        while(it.hasNext()){
            Map.Entry<String, RemoteWebDriver> item = it.next();
            logger.info("*****--> SessionId: " + item.getKey() + " ID " + it.next().getValue().getSessionId());
        }
    }

}
