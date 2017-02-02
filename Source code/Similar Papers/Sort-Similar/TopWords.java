package sortsimilar;



import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class TopWords extends Mapper<LongWritable, Text, Text, Text> {

  
	ArrayList<String> doclist = new ArrayList<String>();
	int counter = 0;
	int olddoc;
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

   
    String[] words = value.toString().split("\t");
    if(counter == 0)
    {
    	olddoc= Integer.parseInt(words[0].toString());
    	counter++;
    	context.write(new Text(words[0]), new Text(words[2]+"\t"+words[1]));
    }
    else
    {
    	if(olddoc != Integer.parseInt(words[0].toString()))
    	{
    		olddoc = Integer.parseInt(words[0].toString());
    		counter=1;
    		context.write(new Text(words[0]), new Text(words[2]+"\t"+words[1]));
    	}
    	else
    	{
    		if(counter < 50)
    		{
    			counter++;
    	    	context.write(new Text(words[0]), new Text(words[2]+"\t"+words[1]));
    		}
    	}
    }
    
  }
}
