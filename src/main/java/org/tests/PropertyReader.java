package org.tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    
    public Properties readPropertyFile() throws IOException {
        try(InputStream stream = this.getClass().getResourceAsStream("/test.properties")) {
           Properties props = new Properties();
           props.load(stream); 
           return props;
        }         
    }
    
}