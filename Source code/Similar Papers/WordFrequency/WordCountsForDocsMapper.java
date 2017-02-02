package index;
 
import java.io.IOException;
 
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
 

public class WordCountsForDocsMapper extends Mapper<LongWritable, Text, Text, Text> {
 
    public WordCountsForDocsMapper() {
    }
 
    /*
     *     PRE-CONDITION: aa@5635.txt    1
     *                    aaron@5637   98
     *                    ab@5635.txt    3
     *
     *     POST-CONDITION: Output <"5637", "aaron=98"> pairs
     */
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] wordAndDocCounter = value.toString().split("\t");
        String[] wordAndDoc = wordAndDocCounter[0].split("@");
        context.write(new Text(wordAndDoc[1]), new Text(wordAndDoc[0] + "=" + wordAndDocCounter[1]));
    }
}