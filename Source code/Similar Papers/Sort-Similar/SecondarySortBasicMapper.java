package sortsimilar;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SecondarySortBasicMapper extends
  	Mapper<LongWritable, Text, CompositeKeyWritable, NullWritable> {

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		if (value.toString().length() > 0) {
			String arrEmpAttributes[] = value.toString().split("\\t");
			String keyatt[] = arrEmpAttributes[0].toString().split("@");

			context.write(
					new CompositeKeyWritable(
							keyatt[1].toString(),
							( arrEmpAttributes[1].toString() + "\t"
									+ keyatt[0].toString() )), NullWritable.get());
		}

	}
}

