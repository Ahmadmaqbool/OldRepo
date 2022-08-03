package seleniumProgram;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFile {
 
    public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("./data/Property.property.txt");
		Properties p=new Properties();
		p.load(fis);
		String url=p.getProperty("url");
		String un=p.getProperty("username");
		String pw=p.getProperty("password");
		
		System.out.println(url);
		System.out.println(un);
		System.out.println(pw);
		
		
		
	}
}
