
 
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
 

public class DatasetsByDocMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
 
    public DatasetsByDocMapper() {
    }
 
    
    private static Set<String> Datasets;
 
    static {
    	Datasets = new HashSet<String>();
    	Datasets.add("MINST"); Datasets.add("CIFAR-100");Datasets.add("CIFAR-10");Datasets.add("Caltech-101");Datasets.add("CURVES");Datasets.add("dots");
    	Datasets.add("ImageNet"); Datasets.add("UCI");Datasets.add("PASCAL");Datasets.add("faces");Datasets.add("Protein");Datasets.add("CIFAR-100");Datasets.add("VGG");
    	Datasets.add("Pascal VOC");Datasets.add("adult");Datasets.add("votes");Datasets.add("twitter");Datasets.add("MovieLens");Datasets.add("street");Datasets.add("Alzheimer's");
    	Datasets.add("TIMIT");Datasets.add("SVHN");	Datasets.add("newsgroups");Datasets.add("diabetes");Datasets.add("TreeBank");Datasets.add("HES");Datasets.add("TEXT8");Datasets.add("tweet");
    	Datasets.add("NORB");
    }
 
   
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // Compile all the words using regex
        Pattern p = Pattern.compile("\\s");
        String[] words = p.split(value.toString());
       
 
        // Get the name of the file from the input split in the context
        String fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
 
        // build the values and write <k,v> pairs through the context
        StringBuilder valueBuilder = new StringBuilder();
       for(String s :words)
       {
    	   
       
          if  (Datasets.contains(s)) {
       
            
            valueBuilder.append(s);
            valueBuilder.append("@");
            valueBuilder.append(fileName);
            
            // emit the partial <k,v>
            context.write(new Text(valueBuilder.toString()), new IntWritable(1));
            valueBuilder.setLength(0);
            }
        }
    }
}