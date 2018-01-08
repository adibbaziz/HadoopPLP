/**
 * 
 */
package HighestTree;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.FloatWritable;


/**
 * @author Adib
 *
 */
public class HighestTreeReducer extends Reducer<Text, FloatWritable, Text, FloatWritable>{
	
	private FloatWritable highestTree = new FloatWritable();

    @Override
    public void reduce(final Text key, final Iterable<FloatWritable> values,
            final Context context) throws IOException, InterruptedException {

        Iterator<FloatWritable> iterator = values.iterator();
        
        float maxHeight = 0;
        while (iterator.hasNext()) {
        	float height = iterator.next().get();
        	if(height > maxHeight){
        		maxHeight = height;
        	}
        }

        highestTree.set(maxHeight);
        context.write(key, highestTree);
    }

}
