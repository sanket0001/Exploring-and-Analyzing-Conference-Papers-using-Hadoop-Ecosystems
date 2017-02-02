package country;

 
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
 

public class CountryByDocMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
 
    public CountryByDocMapper() {
    }
 
    private static Set<String> Datasets;
 
    static {
    	Datasets = new HashSet<String>();
    	//Datasets.add("University");Datasets.add("Lab");Datasets.add("Institution");Datasets.add("Google");
    	//Datasets.add("Twitter");Datasets.add("Facebook");Datasets.add("Microsoft");
    	Datasets.add("Afghanistan");
    	Datasets.add("Albania");
    	Datasets.add("Algeria");
    	Datasets.add("Andorra");
    	Datasets.add("Angola");
    	Datasets.add("Antigua and Barbuda");
    	Datasets.add("Argentina");
    	Datasets.add("Armenia");
    	Datasets.add("Australia");
    	Datasets.add("Austria");
    	Datasets.add("Azerbaijan");
    	Datasets.add("Bahamas");
    	Datasets.add("Bahrain");
    	Datasets.add("Bangladesh");
    	Datasets.add("Barbados");
    	Datasets.add("Belarus");
    	Datasets.add("Belgium");
    	Datasets.add("Belize");
    	Datasets.add("Benin");
    	Datasets.add("Bhutan");
    	Datasets.add("Bolivia");
    	Datasets.add("Bosnia and Herzegovina");
    	Datasets.add("Botswana");
    	Datasets.add("Brazil");
    	Datasets.add("Brunei");
    	Datasets.add("Bulgaria");
    	Datasets.add("Burkina Faso");
    	Datasets.add("Burundi");
    	Datasets.add("Cabo Verde");
    	Datasets.add("Cambodia");
    	Datasets.add("Cameroon");
    	Datasets.add("Canada");
    	Datasets.add("Central African Republic");
    	Datasets.add("CAR");
    	Datasets.add("Chad");
    	Datasets.add("Chile");
    	Datasets.add("China");
    	Datasets.add("Colombia");
    	Datasets.add("Comoros");
    	Datasets.add("Democratic Republic of the Congo");
    	Datasets.add("Republic of the Congo");
    	Datasets.add("Costa Rica");
    	Datasets.add("Cote d'Ivoire");
    	Datasets.add("Croatia");
    	Datasets.add("Cuba");
    	Datasets.add("Cyprus");
    	Datasets.add("Czech Republic");
    	Datasets.add("Denmark");
    	Datasets.add("Djibouti");
    	Datasets.add("Dominica");
    	Datasets.add("Dominican Republic");
    	Datasets.add("Ecuador");
    	Datasets.add("Egypt");
    	Datasets.add("El Salvador");
    	Datasets.add("Equatorial Guinea");
    	Datasets.add("Eritrea");
    	Datasets.add("Estonia");
    	Datasets.add("Ethiopia");
    	Datasets.add("Fiji");
    	Datasets.add("Finland");
    	Datasets.add("France");
    	Datasets.add("Gabon");
    	Datasets.add("Gambia");
    	Datasets.add("Georgia");
    	Datasets.add("Germany");
    	Datasets.add("Ghana");
    	Datasets.add("Greece");
    	Datasets.add("Grenada");
    	Datasets.add("Guatemala");
    	Datasets.add("Guinea");
    	Datasets.add("Guinea-Bissau");
    	Datasets.add("Guyana");
    	Datasets.add("Haiti");
    	Datasets.add("Honduras");
    	Datasets.add("Hungary");
    	Datasets.add("Iceland");
    	Datasets.add("India");
    	Datasets.add("Indonesia");
    	Datasets.add("Iran");
    	Datasets.add("Iraq");
    	Datasets.add("Ireland");
    	Datasets.add("Israel");
    	Datasets.add("Italy");
    	Datasets.add("Jamaica");
    	Datasets.add("Japan");
    	Datasets.add("Jordan");
    	Datasets.add("Kazakhstan");
    	Datasets.add("Kenya");
    	Datasets.add("Kiribati");
    	Datasets.add("Kosovo");
    	Datasets.add("Kuwait");
    	Datasets.add("Kyrgyzstan");
    	Datasets.add("Laos");
    	Datasets.add("Latvia");
    	Datasets.add("Lebanon");
    	Datasets.add("Lesotho");
    	Datasets.add("Liberia");
    	Datasets.add("Libya");
    	Datasets.add("Liechtenstein");
    	Datasets.add("Lithuania");
    	Datasets.add("Luxembourg");
    	Datasets.add("Macedonia");
    	Datasets.add("Madagascar");
    	Datasets.add("Malawi");
    	Datasets.add("Malaysia");
    	Datasets.add("Maldives");
    	Datasets.add("Mali");
    	Datasets.add("Malta");
    	Datasets.add("Marshall Islands");
    	Datasets.add("Mauritania");
    	Datasets.add("Mauritius");
    	Datasets.add("Mexico");
    	Datasets.add("Micronesia");
    	Datasets.add("Moldova");
    	Datasets.add("Monaco");
    	Datasets.add("Mongolia");
    	Datasets.add("Montenegro");
    	Datasets.add("Morocco");
    	Datasets.add("Mozambique");
    	Datasets.add("Myanmar");
    	Datasets.add("Burma");
    	Datasets.add("Namibia");
    	Datasets.add("Nauru");
    	Datasets.add("Nepal");
    	Datasets.add("Netherlands");
    	Datasets.add("New Zealand");
    	Datasets.add("Nicaragua");
    	Datasets.add("Niger");
    	Datasets.add("Nigeria");
    	Datasets.add("North Korea");
    	Datasets.add("Norway");
    	Datasets.add("Oman");
    	Datasets.add("Pakistan");
    	Datasets.add("Palau");
    	Datasets.add("Palestine");
    	Datasets.add("Panama");
    	Datasets.add("Papua New Guinea");
    	Datasets.add("Paraguay");
    	Datasets.add("Peru");
    	Datasets.add("Philippines");
    	Datasets.add("Poland");
    	Datasets.add("Portugal");
    	Datasets.add("Qatar");
    	Datasets.add("Romania");
    	Datasets.add("Russia");
    	Datasets.add("Rwanda");
    	Datasets.add("Saint Kitts and Nevis");
    	Datasets.add("Saint Lucia");
    	Datasets.add("Saint Vincent and the Grenadines");
    	Datasets.add("Samoa");
    	Datasets.add("San Marino");
    	Datasets.add("Sao Tome and Principe");
    	Datasets.add("Saudi Arabia");
    	Datasets.add("Senegal");
    	Datasets.add("Serbia");
    	Datasets.add("Seychelles");
    	Datasets.add("Sierra Leone");
    	Datasets.add("Singapore");
    	Datasets.add("Slovakia");
    	Datasets.add("Slovenia");
    	Datasets.add("Solomon Islands");
    	Datasets.add("Somalia");
    	Datasets.add("South Africa");
    	Datasets.add("South Korea");
    	Datasets.add("South Sudan");
    	Datasets.add("Spain");
    	Datasets.add("Sri Lanka");
    	Datasets.add("Sudan");
    	Datasets.add("Suriname");
    	Datasets.add("Swaziland");
    	Datasets.add("Sweden");
    	Datasets.add("Switzerland");
    	Datasets.add("Syria");
    	Datasets.add("Taiwan");
    	Datasets.add("Tajikistan");
    	Datasets.add("Tanzania");
    	Datasets.add("Thailand");
    	Datasets.add("Timor-Leste");
    	Datasets.add("Togo");
    	Datasets.add("Tonga");
    	Datasets.add("Trinidad and Tobago");
    	Datasets.add("Tunisia");
    	Datasets.add("Turkey");
    	Datasets.add("Turkmenistan");
    	Datasets.add("Tuvalu");
    	Datasets.add("Uganda");
    	Datasets.add("Ukraine");
    	Datasets.add("United Arab Emirates");
    	Datasets.add("UAE");
    	Datasets.add("United Kingdom");
    	Datasets.add("UK");
    	Datasets.add("United States of America");
    	Datasets.add("USA");
    	Datasets.add("Uruguay");
    	Datasets.add("Uzbekistan");
    	Datasets.add("Vanuatu");
    	Datasets.add("Vatican City");
    	Datasets.add("Venezuela");
    	Datasets.add("Vietnam");
    	Datasets.add("Yemen");
    	Datasets.add("Zambia");
    	Datasets.add("Zimbabwe");
    	/*Datasets.add("University of California System,US");
    	Datasets.add("Harvard University,MA,USA");
    	Datasets.add("Massachusetts Institute of Technology,MA,USA");
    	Datasets.add("Johns Hopkins University,Baltimore,USA");
    	Datasets.add("University of California, San Francisco,USA");
    	Datasets.add("Stanford University,USA");
    	Datasets.add("Northwestern University,USA");
    	Datasets.add("University of Cambridge,UK");
    	Datasets.add("University of Oxford,UK");
    	Datasets.add("MAX PLANCK SOCIETY,USA");
    	Datasets.add("University of WASHINGTON,USA");
    	Datasets.add("University of California,Berkely,USA");
    	Datasets.add("University of Wisconsin,USA");
    	Datasets.add("University of California,Los Angeles,USA");
    	Datasets.add("Alabama");
    	Datasets.add("Alaska");
    	Datasets.add("Arizona");
    	Datasets.add("Arkansas");
    	Datasets.add("California");
    	Datasets.add("Colorado");
    	Datasets.add("Connecticut");
    	Datasets.add("Delaware");
    	Datasets.add("Florida");
    	Datasets.add("Georgia");
    	Datasets.add("Hawaii");
    	Datasets.add("Idaho");
    	Datasets.add("Illinois Indiana");
    	Datasets.add("Iowa");
    	Datasets.add("Kansas");
    	Datasets.add("Kentucky");
    	Datasets.add("Louisiana");
    	Datasets.add("Maine");
    	Datasets.add("Maryland");
    	Datasets.add("Massachusetts");
    	Datasets.add("Michigan");
    	Datasets.add("Minnesota");
    	Datasets.add("Mississippi");
    	Datasets.add("Missouri");
    	Datasets.add("Montana Nebraska");
    	Datasets.add("Nevada");
    	Datasets.add("New Hampshire");
    	Datasets.add("New Jersey");
    	Datasets.add("New Mexico");
    	Datasets.add("New York");
    	Datasets.add("North Carolina");
    	Datasets.add("North Dakota");
    	Datasets.add("Ohio");
    	Datasets.add("Oklahoma");
    	Datasets.add("Oregon");
    	Datasets.add("Pennsylvania Rhode Island");
    	Datasets.add("South Carolina");
    	Datasets.add("South Dakota");
    	Datasets.add("Tennessee");
    	Datasets.add("Texas");
    	Datasets.add("Utah");
    	Datasets.add("Vermont");
    	Datasets.add("Virginia");
    	Datasets.add("Washington");
    	Datasets.add("West Virginia");
    	Datasets.add("Wisconsin");
    	Datasets.add("Wyoming");*/

    }
 
    
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // Compile all the words using regex
        Pattern p = Pattern.compile("\\s");
        String[] words = p.split(value.toString());
       // Matcher m = p.matcher(value.toString());
 
        // Get the name of the file from the input split in the context
        String fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
 
        // build the values and write <k,v> pairs through the context
        StringBuilder valueBuilder = new StringBuilder();
       for(String s :words)
       {
    	   if(s.equalsIgnoreCase("Abstract"))
    	   {
    		   break;
    	   }
    	   else
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
}