import java.io.IOException;

public class CSVTestLib {
    public static void main(String[] args) throws IOException{
        CSV csvfile = new CSV();
        String infile = "/Users/cb-it-01-1959/Documents/assign-1-imp/asg1_sample.csv";
        String outfile = "/Users/cb-it-01-1959/Documents/assign-1-imp/asg1_sample_write.csv";
        csvfile.setFilePath(infile);
        csvfile.setOutFilePath(outfile);

        //add entry
        String countryName = "Egypt";
        Float temperature = 21.0f;
        csvfile.addEntry(countryName, temperature);

        //edit country name
        countryName="Fiji";
        String upCountryName = "Fizi";
        csvfile.editCountryName(countryName,upCountryName);
        
        //edit temp
        Float upTemp = 14.8f;
        csvfile.editTemp(countryName,upTemp);

        //delete entry
        countryName="ussr";
        csvfile.deleteEntry(countryName);

        //diplay file
        csvfile.displayFile();

        // csvfile.sortFile();
        csvfile.convertTemp();

    }
}
