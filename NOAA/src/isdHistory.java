import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
/**
 * @author yassine
 *
 */
public class isdHistory {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Path filePath = Paths.get(System.getProperty("user.dir"), "data", "isd-history.txt");
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		InputStream in = new BufferedInputStream(new FileInputStream(filePath.toString()));
		
		try{
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			
			String line = br.readLine();
			
			int i = 0;
			while(i<22){
				line = br.readLine();
				i++;
			}
			
			while (line !=null){
				String USAF = line.substring(0, 6);
				String station_name = line.substring(13, 42);
				if(!station_name.isEmpty()){
					StringBuilder sb = new StringBuilder();
					List<String> list = new ArrayList<String>(Arrays.asList(station_name.split(" ")));
					if(list.size() != 0){
						for(int j=0; j<list.size()-1;j++){
							sb.append(list.get(j));
							sb.append(" ");
						}
						sb.append(list.get(list.size()-1));
						station_name = sb.toString();
					}
				}
				String CTRY = line.substring(43, 45);
				String elevation = line.substring(74, 81);
				System.out.printf("USAF: %s, STATION NAME: %s, COUNTRY: %s, ELEVATION: %s %n",USAF,station_name,CTRY,elevation);
		    	line = br.readLine();
			}
		}
		finally{
			in.close();
			fs.close();
		}
 
		
		
	}


}
