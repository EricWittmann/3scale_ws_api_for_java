package threescale.v3.api.impl;

import java.io.InputStream;
import java.util.Properties;

import threescale.v3.api.ServerAccessor;

public abstract class AbstractServerAccessor implements ServerAccessor {
	
	protected Properties props;
	protected String pluginHeaderValue;
	protected String defaultVersion = "3.1";
	
    public AbstractServerAccessor() {
    	props = new Properties();
        try {
        	InputStream in = DefaultServerAccessor.class.getClassLoader().getResourceAsStream("props.properties");
        	if (in == null) {
        		System.out.println("props.properties not found");
        	} else {
        		props.load(in);
        		defaultVersion = props.getProperty(ServerAccessor.MAVEN_PROJECT_VERSION);
        	}
        } catch (Exception e) {
        	System.out.println(e);
        }   	
		pluginHeaderValue = ServerAccessor.X_3SCALE_USER_CLIENT_HEADER_JAVA_PLUGIN + defaultVersion;
    }

	public String getPluginHeaderValue() {
		return pluginHeaderValue;
	}

}
