package index;
 
import java.io.IOException;
 
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
 

public class WordFrequenceInDocReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
 
    public WordFrequenceInDocReducer() {
    }
 
    /*
     *      PRE-CONDITION: receive a list of <"word@filename",[1, 1, 1, ...]> pairs
     *        <"abc@a.txt", [1, 1]>
     *
     *      POST-CONDITION: emit the output a single key-value where the sum of the occurrences.
     *        <"abc@a.txt", 2>
     */
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
 
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        //write the key and the adjusted value (removing the last comma)
        context.write(key, new IntWritable(sum));
    }
}