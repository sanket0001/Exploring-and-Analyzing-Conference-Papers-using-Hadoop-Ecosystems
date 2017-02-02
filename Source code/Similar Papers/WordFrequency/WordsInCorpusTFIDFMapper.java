package index;
 
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
 

public class WordsInCorpusTFIDFMapper extends Mapper<LongWritable, Text, Text, Text> {
 
    public WordsInCorpusTFIDFMapper() {
    }
 
    /*
     *     PRE-CONDITION: abc@5635.txt  \t  3/1500
     *     POST-CONDITION: abc, 5635.txt=3/1500
     */
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] wordAndCounters = value.toString().split("\t");
        String[] wordAndDoc = wordAndCounters[0].split("@");                 //3/1500
        context.write(new Text(wordAndDoc[0]), new Text(wordAndDoc[1] + "=" + wordAndCounters[1]));
    }
}