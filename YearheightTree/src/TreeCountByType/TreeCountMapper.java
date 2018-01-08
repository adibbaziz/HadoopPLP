/**
 * 
 */
package TreeCountByType;
import DisplayCsv.Tree;
import DisplayCsv.YearHeight;

import org.apache.hadoop.io.*;        
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author yassine
 *
 */
public class TreeCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	

	@Override
	protected void map(LongWritable keyE, Text valE, Context context) throws IOException,InterruptedException
    {
		String line = valE.toString();
		Tree tree = new YearHeight(line);
		StringTokenizer tokenizer = new StringTokenizer(tree.espece);
		while(tokenizer.hasMoreTokens())
		{
			word.set(tokenizer.nextToken());
			context.write(word, one);
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

