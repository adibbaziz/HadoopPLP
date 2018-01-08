/**
 * 
 */
package HighestTree;
import DisplayCsv.Tree;
import DisplayCsv.YearHeight;

import org.apache.hadoop.io.*;        
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author Adib
 *
 */
public class HighestTreeMapper extends Mapper<LongWritable, Text, Text, FloatWritable>{
	
	private Text type = new Text();
	

	@Override
	protected void map(LongWritable keyE, Text valE, Context context) throws IOException,InterruptedException
    {
		String line = valE.toString();
		Tree tree = new YearHeight(line);
		StringTokenizer tokenizer = new StringTokenizer(tree.espece);
		while(tokenizer.hasMoreTokens())
		{
			type.set(tokenizer.nextToken());
			context.write(type, new FloatWritable(tree.hauteur));
		}
    }

	public void run(Context context) throws IOException, InterruptedException {
		setup(context);
		while (context.nextKeyValue()) {
			map(context.getCurrentKey(), context.getCurrentValue(), context);
		}
		cleanup(context);
	}

}
