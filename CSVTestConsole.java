import java.util.*;

public class CSVTestConsole{
    public static void main(String[] args) throws Exception{
        Scanner sc_console = new Scanner(System.in);
        CSV csvfile = new CSV();
        System.out.println("Enter input file location (without double quotes):");
        String infile = sc_console.nextLine();
        System.out.println("Enter output file location (without double quotes):");
        String outfile = sc_console.nextLine();
        csvfile.setFilePath(infile);
        csvfile.setOutFilePath(outfile);
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
                System.out.println("ADDING entry to existing file..."); //input file only
                sc_console.nextLine();
                System.out.println("Please enter country name:"); 
                String countryName = sc_console.nextLine();
                System.out.println("Please enter temperature:");
                Float temperature = sc_console.nextFloat();
                boolean added = csvfile.addEntry(countryName, temperature);
                if(added){
                    System.out.println("Entry added successfully.");
                }else{
                    System.out.println(countryName + " already exists, hence can not add.");
                }
                csvfile.displayFile();
                break;

                case 2:

                System.out.println("EDITING entry in existing file...");
                System.out.println("Enter country name whose entry you want to EDIT:");
                sc_console.nextLine();
                countryName = sc_console.nextLine();
                System.out.println("Press C to edit country name or T to edit temperature");
                String editChoice = sc_console.nextLine();
                boolean edited = false;
                if(editChoice.equalsIgnoreCase("C")){
                    System.out.println("Enter updated country name:");
                    String upCountryName = sc_console.nextLine();
                    edited = csvfile.editCountryName(countryName,upCountryName);
                }else if(editChoice.equalsIgnoreCase("T")){
                    System.out.println("Enter updated temp for the country:");
                    Float upTemp = sc_console.nextFloat();
                    edited = csvfile.editTemp(countryName,upTemp);
                }else{
                    System.out.println("Invalid choice! Start editing again.");
                }
                if(edited)
                    System.out.println("Edit successful.");
                else
                    System.out.println("Edit failed.");
                csvfile.displayFile();
                break;
                
                case 3:

                System.out.println("DELETING entry from existing file...");
                System.out.println("Enter country name whose entry you want to DELETE:");
                sc_console.nextLine();
                countryName = sc_console.nextLine();
                boolean deleted = csvfile.deleteEntry(countryName);
                if(deleted)
                    System.out.println("Delete succesful.");
                else
                    System.out.println("Delete failed.");
                csvfile.displayFile();
                break;
                
                case 4:
                csvfile.displayFile();
                break;
                
                case 5:
                csvfile.sortFile();
                System.out.println("Sorted file stored on output file path.");
                break;
                
                case 6:
                csvfile.convertTemp();
                System.out.println("Conversion complete and new file stored on output file path.");
                break;
                
                case 7:
                System.out.println("EXITING...");
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

