package sortsimilar;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class SimilarMapper extends Mapper<LongWritable, Text, DoubleWritable, Text> {

	HashMap<String, Double> map1 = new HashMap<String, Double>();
    
    String key,Key1;
    int docex = 5635;
    int olddoc,newdoc;
    int counts =0;
    double result,tempresult;
    Set<String> sample = new HashSet<String>();
    Set<String> compdoc = new HashSet<String>();
 
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

   

		if (value.toString().length() > 0) {
			String words[] = value.toString().split("\\t");
			newdoc = Integer.parseInt(words[0].toString());
            if (docex == newdoc)
            {
                sample.add(words[1].toString());
            }
            else
            {
                    counts++;
                    compdoc.add(words[1].toString());
                    
                if(counts == 50)
                {
                    olddoc = Integer.parseInt(words[0].toString());
                    Set<String> union = new HashSet<String>();
                    union.addAll(compdoc);
                    union.addAll(sample);
                    Set<String> intersect = new HashSet<String>();
                    intersect.addAll(sample);
                    intersect.retainAll(compdoc);
                   // print("union", union);
                   // print("intersection", intersect);
                   // System.out.println(union.size());
                    //System.out.println(intersect.size());
                    tempresult = (double)intersect.size()/(double)union.size();
                    result = Math.round(tempresult * 10000.0) / 10000.0;
                   //map1.put(words[0], result);
                    
                    counts = 0;
                    compdoc.clear();
                    union.clear();
                    intersect.clear();
                    context.write(new DoubleWritable(result), new Text(words[0]));
                }
                else
                {
                    
                    
                }
                
            }
            
        }
		
        
        
      }
    

}