package sortsimilar;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class SimilarReducer extends Reducer<DoubleWritable, Text, Text, DoubleWritable> {

  int count = 5;
  
 
  public void reduce(DoubleWritable key, Text values, Context context)
			throws IOException, InterruptedException {
		
		
		for(int i = 0; i< count;i++ )
		{
			context.write(values, key);
		}
	  
		
	}
}