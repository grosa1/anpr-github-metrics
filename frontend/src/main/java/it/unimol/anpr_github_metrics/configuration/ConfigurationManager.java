package it.unimol.anpr_github_metrics.configuration;

import com.mashape.unirest.http.Unirest;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * @author Simone Scalabrino.
 */
public class ConfigurationManager extends FileBasedConfigurationManager {

    private static ConfigurationManager instance;

    public static ConfigurationManager getInstance() throws IOException {
        if (instance == null) {
            instance = new ConfigurationManager();
        }

        return instance;
    }
    private ConfigurationManager() throws IOException {
        super("config.properties");

        Unirest.setObjectMapper(new com.mashape.unirest.http.ObjectMapper() {
            private ObjectMapper jacksonObjectMapper
                    = new ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public String getBackendEndpoint() {
        return this.configuration.getProperty("backend.endpoint");
    }
}
