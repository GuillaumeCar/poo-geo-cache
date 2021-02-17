package configuration;

import lombok.Data;

@Data
public class GeneralConfiguration {
    // Change here your parameters
    public static final String mongoDatabaseName = "geocache";
    public static final String mongoDatabaseHost = "localhost";
    public static final int mongoDatabasePort = 27017;
    public static final String hibernateConfigurationLocation = "hibernate.cfg.xml";
}
