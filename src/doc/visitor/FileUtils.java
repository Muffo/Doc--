package doc.visitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {
	
	FileUtils copyResource(String resource, String destinationPath) {

		InputStream is = this.getClass().getResourceAsStream(resource);
		File f=new File(destinationPath);
	    OutputStream os;
		try {
			os = new FileOutputStream(f);
			byte buf[]=new byte[1024];
			int len;
			    
			while((len=is.read(buf))>0)
			 	os.write(buf,0,len);
			
			os.close();
			is.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
		return this;
	}

	public static void copyAllResourcesTo(String path) {
		new FileUtils().copyResource("/resources/docStyle.css", path + "/docStyle.css")
						.copyResource("/resources/logo.png", path + "/logo.png")
						.copyResource("/resources/author.png", path + "/author.png")
						.copyResource("/resources/list.png", path + "/list.png");
	}

}
