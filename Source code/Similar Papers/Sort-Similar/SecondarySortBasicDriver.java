package sortsimilar;





import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class SecondarySortBasicDriver extends Configured implements Tool {

	private static final String INPUT_PATH = "3-tf-idf";
	private static final String OUTPUT_PATH = "sort-result";
	private static final String OUTPUT_PATH1 = "sort-top";
	private static final String OUTPUT_PATH2 = "top-similar-papers";
	 
    // where to read the data from.
   // private static final String INPUT_PATH = "3-tf-idf/part-r-00000";
  @Override
	public int run(String[] args) throws Exception {

	  
	  Configuration conf = getConf();

		Job job1 = new Job(conf);
		job1.setJobName("Secondary sort example");

		job1.setJarByClass(SecondarySortBasicDriver.class);
		FileInputFormat.setInputPaths(job1, new Path(INPUT_PATH));
		FileOutputFormat.setOutputPath(job1, new Path(OUTPUT_PATH));

		job1.setMapperClass(SecondarySortBasicMapper.class);
		job1.setMapOutputKeyClass(CompositeKeyWritable.class);
		job1.setMapOutputValueClass(NullWritable.class);
		job1.setPartitionerClass(SecondarySortBasicPartitioner.class);
		job1.setSortComparatorClass(SecondarySortBasicCompKeySortComparator.class);
		job1.setGroupingComparatorClass(SecondarySortBasicGroupingComparator.class);
		job1.setReducerClass(SecondarySortBasicReducer.class);
		job1.setOutputKeyClass(CompositeKeyWritable.class);
		job1.setOutputValueClass(NullWritable.class);

		//job.setNumReduceTasks(8);

		job1.waitForCompletion(true);
				
		Job job2 = new Job();
		job2.setJobName("Top 50 words");
		  job2.setJarByClass(SecondarySortBasicDriver.class);

		  job2.setMapperClass(TopWords.class);
		  job2.setReducerClass(TopWordsReducer.class);

		  job2.setOutputKeyClass(Text.class);
		  job2.setOutputValueClass(Text.class);

		  FileInputFormat.setInputPaths(job2, new Path(OUTPUT_PATH));
		  FileOutputFormat.setOutputPath(job2, new Path(OUTPUT_PATH1));
		  
		  job2.waitForCompletion(true);
			
			Job job3 = new Job();
			job3.setJobName("Top Similar Papers");
			  job3.setJarByClass(SecondarySortBasicDriver.class);

			  job3.setMapperClass(SimilarMapper.class);
			  job3.setMapOutputKeyClass(DoubleWritable.class);
			  job3.setMapOutputValueClass(Text.class);
			  job3.setSortComparatorClass(SimilarComparator.class);
			  job3.setReducerClass(SimilarReducer.class);

			  job3.setOutputKeyClass(Text.class);
			  job3.setOutputValueClass(DoubleWritable.class);

			  FileInputFormat.setInputPaths(job3, new Path(OUTPUT_PATH1));
			  FileOutputFormat.setOutputPath(job3, new Path(OUTPUT_PATH2));
		  
		  
		  
		  boolean success = job3.waitForCompletion(true);
			return success ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Configuration(),
				new SecondarySortBasicDriver(), args);
		System.exit(exitCode);
	}
}
