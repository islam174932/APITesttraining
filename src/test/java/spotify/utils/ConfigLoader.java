package spotify.utils;

import java.util.Properties;

public class ConfigLoader {
    private static Properties properties ;
    private static ConfigLoader configloader ;

    private ConfigLoader() {
        properties = PropertyUtils.readProperties("C:\\Users\\User\\MySpotifyApiProject\\src\\test\\resources\\config.properties");

    }

    public static ConfigLoader getInstance() {
        if (configloader == null){
            configloader = new ConfigLoader();
        }
        return configloader;
    }
    public static String getClientId() {
        return properties.getProperty("client_id");
    }

    public static String getClientSecret() {
        return properties.getProperty("client_secret");
    }

    public static String getRefreshToken() {
        return properties.getProperty("refresh_token");
    }

    public  String getGrantType() {
        return properties.getProperty("grant_type");
    }

    public String getUserId() {
        return properties.getProperty("user_id");
    }
}
