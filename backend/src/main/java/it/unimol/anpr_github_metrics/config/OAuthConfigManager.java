package it.unimol.anpr_github_metrics.config;

import it.unimol.anpr_github_metrics.services.MissingConfValueException;

import java.io.*;
import java.util.Properties;

public class OAuthConfigManager {
    public static final String KEY_CLIENT_ID = "client_id";
    public static final String KEY_CLIENT_SECRET = "client_secret";
    public static final String KEY_REDIRECT_URI = "redirect_uri";

    private static OAuthConfigManager instance = null;

    protected OAuthConfigManager() {
        // Exists only to defeat instantiation.
    }

    public static OAuthConfigManager getInstance() {
        if (instance == null) {
            instance = new OAuthConfigManager();
        }
        return instance;
    }

    public void setLoginConf(String clientId, String clientSecret, String redirectUri) {
        try {
            Properties properties = new Properties();
            properties.setProperty(KEY_CLIENT_ID, clientId);
            properties.setProperty(KEY_CLIENT_SECRET, clientSecret);
            properties.setProperty(KEY_REDIRECT_URI, redirectUri);

            File file = new File("/anpr_login_conf.xml");
            FileOutputStream fileOut = new FileOutputStream(file);
            properties.storeToXML(fileOut, "OAuth Github login settings");
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) throws MissingConfValueException{
        String value = "";

        try {
            File file = new File("/anpr_login_conf.xml");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.loadFromXML(fileInput);
            fileInput.close();

            value = properties.getProperty(key);

            if(value == null) {
                throw new MissingConfValueException();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

}


