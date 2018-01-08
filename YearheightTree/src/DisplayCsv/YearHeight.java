/**
 * 
 */
package DisplayCsv;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
/**
 * @author yassine
 *
 */
public class YearHeight extends Tree {

	public YearHeight(String line) {
		super(line);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Path filePath = Paths.get(System.getProperty("user.dir"), "data", "arbres.csv");
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		InputStream in = new BufferedInputStream(new FileInputStream(filePath.toString()));
		
		try{
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			
			String line = br.readLine();
			line = br.readLine();
			
			while (line !=null){
				Tree tree = new YearHeight(line);
				tree.displayYearHeight();
				line = br.readLine();
			}
			
		}
		finally{
			in.close();
			fs.close();
			
		}
 
		
		
	}

}

