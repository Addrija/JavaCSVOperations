import java.io.*;
import java.util.*;
// import java.util.Scanner;  


public class CSVTestDrive{
    public static void main(String[] args) throws Exception{
        Scanner sc_console = new Scanner(System.in);
        CSV csvfile = new CSV();
        System.out.println("Enter input file location (without double quotes):");
        String infile = sc_console.nextLine();
        System.out.println("Enter output file location (without double quotes):");
        String outfile = sc_console.nextLine();
        csvfile.setInFile(infile);
        csvfile.setOutFile(outfile);
        while(true){

            System.out.println("Select the operation to perform:");
            System.out.println("1. Add entry");
            System.out.println("2. Edit entry");
            System.out.println("3. Delete entry");
            System.out.println("4. Display file");
            System.out.println("5. Sort file on country names");
            System.out.println("6. Convert C to F");
            System.out.println("7. Exit ");
            System.out.println();

            int choice = sc_console.nextInt();
            System.out.println("\n--------------------------------");

            switch(choice){
                case 1:
                csvfile.addEntry(sc_console);
                csvfile.displayFile();
                break;

                case 2:
                csvfile.editEntry(sc_console);
                csvfile.displayFile();
                break;
                
                case 3:
                csvfile.deleteEntry(sc_console);
                csvfile.displayFile();
                break;
                
                case 4:
                csvfile.displayFile();
                break;
                
                case 5:
                csvfile.sortFile();
                csvfile.displayFile();
                break;
                
                case 6:
                csvfile.convertTemp();
                csvfile.displayFile();
                break;
                
                case 7:
                csvfile.closeFile();
                csvfile.displayFile();
                break;
                
                default:
                System.out.println("Please enter a valid choice");
                
            }
            System.out.println("--------------------------------\n");
            
            if(choice==7){
                break;
            }
        }
        System.out.println("Exit successful!");
        sc_console.close();

    }
}


class CSV{
    String inFilePath;
    String outFilePath;
    File inFile;
    File outFile;
    Scanner inFile_scanner;  
    // HashMap<String, Float> map = new HashMap<>();
    LinkedHashMap<String, Float> map = new LinkedHashMap<>();


    private void readFileIntoMap() throws Exception{
        FileReader fr = new FileReader(inFilePath);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        line = br.readLine();
        while(line!=null){
            String[] splitline = line.split(",");
            map.put(splitline[0],Float.valueOf(splitline[1]));
            line = br.readLine();
        }
        // System.out.println(map);
        br.close();

        // FileWriter fw = new FileWriter(inFilePath,true);
        // BufferedWriter bw = new BufferedWriter(fw);
        // PrintWriter pw = new PrintWriter(bw);
        // pw.println("");
        // pw.close();
    }

    private void writeMapToFile() throws Exception{
        FileWriter fw = new FileWriter(inFilePath);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Country name, Temperature (C)");

        for(String k:map.keySet()){
            String writeString = "\n" + k + "," + Float.toString(map.get(k));
            bw.write(writeString);
        }
        bw.close();
    }

    private void writeToInFile(String country, Float temperature) throws Exception{
        FileWriter fw = new FileWriter(inFilePath,true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        pw.println("");
        String completeString = country+","+Float.toString(temperature);
        pw.print(completeString);
        pw.close();
    }
    private Float getTempInFarenheit(Float tempC){
        return 32 + (tempC*1.8f);
    }
    private void addTuple(String countryName, Float temperature) throws Exception{
        //adds the tuple to inFile
        map.put(countryName, temperature);
        writeToInFile(countryName,temperature);
    }
    public void setInFile(String inpath) throws Exception{
        inFilePath = inpath;
        inFile = new File(inFilePath);
        inFile_scanner = new Scanner(inFilePath);
        readFileIntoMap();
    }
    public void setOutFile(String outpath){
        outFilePath = outpath;
        outFile = new File(outFilePath);
    }

    
    public void sortFile() throws Exception{
        System.out.println("\nsorting file...");
        ArrayList<String> mapKeys = new ArrayList<String>(map.keySet());
        Collections.sort(mapKeys);
        String unit = "C";

        FileWriter fw = new FileWriter(outFilePath);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Country name, Temperature ("+unit+")\n");

        for(String k:mapKeys){
            String completeString = k+","+Float.toString(map.get(k))+"\n";
            bw.write(completeString);
        }
        bw.close();
        System.out.println("Sorted file saved to output file location");
    }

    public void convertTemp() throws Exception{
        System.out.println("\nconverting temperature to farenheit...");
        // HashMap<String,Float> fMap = new HashMap<>();
        LinkedHashMap<String,Float> fMap = new LinkedHashMap<>();
        ArrayList<String> mapKeys = new ArrayList<String>(map.keySet());
        Collections.sort(mapKeys);

        for(String k:mapKeys){
            fMap.put(k, getTempInFarenheit(map.get(k)));
        }
        FileWriter fw = new FileWriter(outFilePath);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Country name, Temperature (C),Temperature (F)\n");
        

        for(String k:mapKeys){
            String completeString = k+ "," + Float.toString(map.get(k)) + "," + Float.toString(fMap.get(k)) + "\n";
            bw.write(completeString);
        }
        bw.close();
        System.out.println("Conversion done. Changes saved to output file location");

    }

    public void addEntry(Scanner sc_console) throws Exception{
        System.out.println("Adding entry to existing file..."); //input file only
        //The try-with-resources statement ensures that each resource is closed at the end of the statement.
        sc_console.nextLine();
        System.out.println("Please enter country name:"); 
        String countyName = sc_console.nextLine();
        System.out.println("Please enter temperature:");
        Float temprature = sc_console.nextFloat();
        if(map.containsKey(countyName)){
            System.out.println("This country already exists. Please use edit option to update it.");
        }else{
            addTuple(countyName,temprature);
            System.out.println("Entry added successfully.");
        }
    }

    public void editEntry(Scanner sc_console) throws Exception{
        System.out.println("Editting entry in existing file...");
        System.out.println("Enter country name whose entry you want to EDIT:");
        sc_console.nextLine();
        String countryName = sc_console.nextLine();
        System.out.println("Press C to edit country name or T to edit temperature");
        String editChoice = sc_console.nextLine();
        System.out.println(editChoice);
        if(editChoice.equals("C")){
            System.out.println("Enter updated country name:");
            String upCountryName = sc_console.nextLine();
            Float temp = map.get(countryName);
            map.remove(countryName);
            map.put(upCountryName, temp);
        }else if(editChoice.equals("T")){
            System.out.println("Enter updated temp for the country:");
            Float upTemp = sc_console.nextFloat();
            map.remove(countryName);
            map.put(countryName, upTemp);
        }
        writeMapToFile();
    }

    public void deleteEntry(Scanner sc_console) throws Exception{
        System.out.println("deleting entry from existing file...");
        System.out.println("Enter country name whose entry you want to DELETE:");
        sc_console.nextLine();
        String countryName = sc_console.nextLine();
        if(map.containsKey(countryName)){
            map.remove(countryName);
            writeMapToFile();
        }else{
            System.out.println(countryName + " does not exist, hence can't be deleted.");
        }
    }

    public void displayFile() throws Exception{
        System.out.println("\n\nVIEW FILE");
        FileReader fr = new FileReader(inFilePath);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        line = br.readLine();
        while(line!=null){
            System.out.println(line);
            line = br.readLine();
        }
        br.close();
    }

    public void closeFile(){
        System.out.println("file closed...");
    }
}
