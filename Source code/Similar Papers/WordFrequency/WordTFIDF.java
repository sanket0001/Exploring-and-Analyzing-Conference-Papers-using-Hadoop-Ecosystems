package index;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class WordTFIDF extends Configured implements Tool {

	
	private static final String INPUT_PATH = "input";
 private static final String  OUTPUT_PATH1 = "1-word-freq";
 
 private static final String OUTPUT_PATH2 = "2-word-counts";
 
 private static final String  OUTPUT_PATH = "word-tf-idf";
 


 @Override
 public int run(String[] args) throws Exception {
  /*
   * Job 1
   */
  Configuration conf = getConf();
  FileSystem fs = FileSystem.get(conf);
  Path inputPath = new Path("input");
 fs = inputPath.getFileSystem(conf);
  FileStatus[] stat = fs.listStatus(inputPath);
  
  
  Job job1 = new Job(conf, "Word Frequence In Document");
  job1.setJarByClass(WordTFIDF.class);

  job1.setMapperClass(WordFrequenceInDocMapper.class);
  job1.setReducerClass(WordFrequenceInDocReducer.class);
  job1.setCombinerClass(WordFrequenceInDocReducer.class);
  
  job1.setOutputKeyClass(Text.class);
  job1.setOutputValueClass(IntWritable.class);

  FileInputFormat.addInputPath(job1, new Path(INPUT_PATH));
  FileOutputFormat.setOutputPath(job1, new Path(OUTPUT_PATH1));

  
  job1.waitForCompletion(true);

  /*
   * Job 2
   */
  
  Job job2 = new Job(conf, "Words Counts");
  job2.setJarByClass(WordTFIDF.class);

  job2.setMapperClass(WordCountsForDocsMapper.class);
  job2.setReducerClass(WordCountsForDocsReducer.class);

  job2.setOutputKeyClass(Text.class);
  job2.setOutputValueClass(Text.class);

  FileInputFormat.addInputPath(job2, new Path(OUTPUT_PATH1));
  FileOutputFormat.setOutputPath(job2, new Path(OUTPUT_PATH2));
  
  job2.waitForCompletion(true);

  Job job3 = new Job(conf, "TF-IDF");

  job3.setJarByClass(WordTFIDF.class);
  
  
  job3.setMapperClass(WordCountsForDocsMapper.class);
  job3.setReducerClass(WordCountsForDocsReducer.class);

  job3.setOutputKeyClass(Text.class);
  job3.setOutputValueClass(Text.class);

  FileInputFormat.addInputPath(job3, new Path(OUTPUT_PATH2));
  FileOutputFormat.setOutputPath(job3, new Path(OUTPUT_PATH));
 
  
  job3.setJobName(String.valueOf(stat.length));
  
  return job3.waitForCompletion(true) ? 0 : 1;
 }

 /**
  * Method Name: main Return type: none Purpose:Read the arguments from
  * command line and run the Job till completion
  * 
  */
 public static void main(String[] args) throws Exception {
  // TODO Auto-generated method stub
	 int res = ToolRunner.run(new Configuration(), new WordTFIDF(), args);
     System.exit(res);
 }
}