/**
 * 
 */
package TreeCountByType;


import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;

/**
 * @author yassine
 *
 */
public class TreeCount extends Configured implements Tool{

	public int run(String[] args) throws Exception {

       


        Job job = Job.getInstance(getConf());
        job.setJobName("TreeCount");

        job.setJarByClass(TreeCount.class);
        job.setMapperClass(TreeCountMapper.class);
        job.setReducerClass(TreeCountReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(TextInputFormat.class);
        job.setOutputValueClass(TextOutputFormat.class);
        
       
        Path inputFilePath = new Path("data/arbres.csv");
        Path outputFilePath = new Path("data/result.txt");
        

        FileInputFormat.setInputDirRecursive(job, true);
        FileInputFormat.addInputPath(job, inputFilePath);
        FileOutputFormat.setOutputPath(job, outputFilePath);
        FileSystem fs = FileSystem.newInstance(getConf());

        if (fs.exists(outputFilePath)) {
            fs.delete(outputFilePath, true);
        }

        return job.waitForCompletion(true) ? 0: 1;
    }


    public static void main(String[] args) throws Exception {

    	TreeCount driver = new TreeCount();
        int res = ToolRunner.run(driver, args);
        System.exit(res);

    }

}