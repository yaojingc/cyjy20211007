package nc.utils.encode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
	private static Properties prop = new Properties();
	  static{
	    try {
	    	InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream("config.properties");
	      prop.load(in);
	    } catch (FileNotFoundException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	  }
	  public static String getValue(String key){
	    return (String) prop.get(key);
	  }
}
