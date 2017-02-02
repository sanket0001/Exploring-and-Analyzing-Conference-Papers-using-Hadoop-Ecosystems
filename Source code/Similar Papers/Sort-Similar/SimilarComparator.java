package sortsimilar;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SimilarComparator extends WritableComparator {
	
	

	protected SimilarComparator() {
		super(DoubleWritable.class, true);
	}

	/**
	 * Need to implement our sorting mechanism.
	 */
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		DoubleWritable key1 = (DoubleWritable) a;
		DoubleWritable key2 = (DoubleWritable) b;

		// Implemet sorting in descending order
		int result = key1.get() < key2.get() ? 1 : key1.get() == key2.get() ? 0 : -1;
		return result;
	}
}