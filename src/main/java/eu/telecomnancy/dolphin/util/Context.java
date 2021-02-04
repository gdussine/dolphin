package eu.telecomnancy.dolphin.util;

import java.io.IOException;
import java.util.Properties;

public class Context {

    private static Context instance;

    public final String USER_AUTH_NAME, USER_AUTH_PASS, PTF_DATE_START, PTF_DATE_STOP, PTF_NAME;
    public final int PTF_ID, PTF_NB_MIN, PTF_NB_MAX, API_SHARPE_ID;
    public final double PTF_NAV_MIN, PTF_NAV_MAX;

    private Context(){
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("context.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        USER_AUTH_NAME = properties.getProperty("user.auth.name", "");
        USER_AUTH_PASS = properties.getProperty("user.auth.pass", "");
        PTF_DATE_START = properties.getProperty("ptf.date.start", "2016-06-01");
        PTF_DATE_STOP = properties.getProperty("ptf.date.stop", "2020-01-01");
        PTF_ID = Integer.parseInt(properties.getProperty("ptf.id", "1839"));
        PTF_NAME = properties.getProperty("ptf.name", "PTF");
        PTF_NB_MIN = Integer.parseInt(properties.getProperty("ptf.nb.min","20"));
        PTF_NB_MAX = Integer.parseInt(properties.getProperty("ptf.nb.max", "40"));
        PTF_NAV_MIN = Double.parseDouble(properties.getProperty("ptf.nav.min","0.01"));
        PTF_NAV_MAX = Double.parseDouble(properties.getProperty("ptf.nav.max","0.1"));
        API_SHARPE_ID = Integer.parseInt(properties.getProperty("api.sharpe.id"));
    }

    @Override
    public String toString() {
        return "Context{" +
                "USER_AUTH_NAME='" + USER_AUTH_NAME + '\'' +
                ", USER_AUTH_PASS='" + USER_AUTH_PASS + '\'' +
                ", PTF_DATE_START='" + PTF_DATE_START + '\'' +
                ", PTF_DATE_STOP='" + PTF_DATE_STOP + '\'' +
                ", PTF_ID=" + PTF_ID +
                ", PTF_NB_MIN=" + PTF_NB_MIN +
                ", PTF_NB_MAX=" + PTF_NB_MAX +
                ", API_SHARPE_ID=" + API_SHARPE_ID +
                ", PTF_NAV_MIN=" + PTF_NAV_MIN +
                ", PTF_NAV_MAX=" + PTF_NAV_MAX +
                '}';
    }

    public static Context get(){
        if(instance == null)
            instance = new Context();
        return instance;
    }

    public void log(){
        System.out.println(this);
    }


}
