import java.io.*;
import java.util.*;

public class CSV{
    String filePath;
    String outFilePath;
    File currFile;
    File outFile;
    ArrayList<CSVEntry> fileDataStruct = new ArrayList<>();

    private void readFileInArrayList() throws IOException{
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        line = br.readLine();
        while(line!=null){
            String[] splitline = line.split(",");
            CSVEntry curr = new CSVEntry(splitline[0],Float.valueOf(splitline[1])); 
            fileDataStruct.add(curr);
            line = br.readLine();
        }
        br.close();
    }

    private void writeArrayListToFile(String pathOfFile, ArrayList<CSVEntry> ArrayListObject, String tempUnit) throws IOException{
        FileWriter fw = new FileWriter(pathOfFile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Country name, Temperature ("+tempUnit+")");

        for(CSVEntry entry:ArrayListObject){
            String writeString = "\n" + entry.nameOfCountry + "," + Float.toString(entry.tempOfCountry);
            bw.write(writeString);
        }
        bw.close();
    }

    private int getIndexByCountry(String name){
        int index = -1;
        int n = fileDataStruct.size();
        for(int i=0;i<n;i++){
            if(fileDataStruct.get(i).nameOfCountry.equalsIgnoreCase(name)){
                index=i;
                break;
            }
        }
        return index;
    }

    private Float getTempInFarenheit(Float tempC){
        return 32 + (tempC*1.8f);
    }

    public void setFilePath(String path) throws IOException{
        filePath = path;
        currFile = new File(filePath);
        readFileInArrayList();
    }

    public void setOutFilePath(String outpath){
        outFilePath = outpath;
        outFile = new File(outFilePath);
    }

    public boolean addEntry(String countryName, Float tempC) throws IOException{
        CSVEntry entry = new CSVEntry(countryName, tempC);
        boolean added = false;
        int index = getIndexByCountry(countryName);
        if(index==-1){
            fileDataStruct.add(entry);
            writeArrayListToFile(filePath,fileDataStruct,"C");
            added=true;
        }
        return added;
    }

    public boolean editCountryName(String countryName, String upCountryName) throws IOException{
        boolean edited=false;
        int index = getIndexByCountry(countryName);
        if(index!=-1){
            CSVEntry entry = new CSVEntry(upCountryName, fileDataStruct.get(index).tempOfCountry);
            fileDataStruct.set(index, entry);
            writeArrayListToFile(filePath,fileDataStruct,"C");
            edited=true;
        }
        return edited;
    }

    public boolean editTemp(String countryName,Float upTemp) throws IOException{
        boolean edited=false;
        int index = getIndexByCountry(countryName);
        if(index!=-1){
            CSVEntry entry = new CSVEntry(countryName, upTemp);
            fileDataStruct.set(index, entry);
            writeArrayListToFile(filePath,fileDataStruct,"C");
            edited=true;
        }
        return edited;
    }

    public boolean deleteEntry(String countryName) throws IOException{
        boolean deleted=false;
        int index = getIndexByCountry(countryName);
        if(index!=-1){
            fileDataStruct.remove(index);
            writeArrayListToFile(filePath,fileDataStruct,"C");
            deleted=true;
        }
        return deleted;
    }

    public void displayFile() throws IOException{
        System.out.println("\n\nVIEW FILE");
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        line = br.readLine();
        while(line!=null){
            System.out.println(line);
            line = br.readLine();
        }
        br.close();
    }

    public void sortFile() throws IOException{
        ArrayList<CSVEntry> sortedArrayList = new ArrayList<>(fileDataStruct);
        sortedArrayList.sort((o1, o2)-> o1.nameOfCountry.toLowerCase().compareTo(o2.nameOfCountry.toLowerCase()));
        writeArrayListToFile(outFilePath,sortedArrayList,"C");
    }

    public void convertTemp() throws IOException{
        ArrayList<CSVEntry> convertedFileDataStruct = new ArrayList<>();
        for(CSVEntry entry: fileDataStruct){
            CSVEntry newEntry = new CSVEntry(entry.nameOfCountry, getTempInFarenheit(entry.tempOfCountry));
            convertedFileDataStruct.add(newEntry);
        }
        writeArrayListToFile(outFilePath,convertedFileDataStruct,"F");
    }
}
