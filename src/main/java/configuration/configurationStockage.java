package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configurationStockage 
{
	
	
	public String getType_stockage() throws IOException
	{
		InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
		     Properties  props = new Properties();
	          props.load(input);
           String type = props.getProperty("type_stockage");
	          return  type ;
	
			

	
}
}
