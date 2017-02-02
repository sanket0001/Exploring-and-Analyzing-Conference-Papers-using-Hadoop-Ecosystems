package country;

 
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
 

public class CountryDriver extends Configured implements Tool {
 
    // where to put the data in hdfs when we're done
	private static final String INPUT_PATH = "input";
    private static final String OUTPUT_PATH1 = "Countryinpapers";
    private static final String OUTPUT_PATH2 = "Countrycounts";
    
 
    // where to read the data from.
    
 
    public int run(String[] args) throws Exception {
 
        Configuration conf = getConf();
        Job job1 = new Job(conf, "Country Count By Document");
 
        job1.setJarByClass(CountryDriver.class);
        job1.setMapperClass(CountryByDocMapper.class);
        job1.setReducerClass(CountryByDocReducer.class);
        job1.setCombinerClass(CountryByDocReducer.class);
 
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(IntWritable.class);
 
        FileInputFormat.addInputPath(job1, new Path(INPUT_PATH));
        FileOutputFormat.setOutputPath(job1, new Path(OUTPUT_PATH1));
        job1.waitForCompletion(true);
        
        Job job2 = new Job(conf, "Country Counts");
        
        job2.setJarByClass(CountryDriver.class);
        job2.setMapperClass(CountryCountMapper.class);
        job2.setReducerClass(CountryCountReducer.class);
 
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(IntWritable.class);
 
        FileInputFormat.addInputPath(job2, new Path(OUTPUT_PATH1));
        FileOutputFormat.setOutputPath(job2, new Path(OUTPUT_PATH2));
 
        return job2.waitForCompletion(true) ? 0 : 1;
 
        
    }
 
    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new CountryDriver(), args);
        System.exit(res);
    }
}
