public class CSVEntry{
    String nameOfCountry;
    Float tempOfCountry;

    public CSVEntry(String cn, Float tc){
        nameOfCountry=cn;
        tempOfCountry=tc;
    }
    
    public boolean equals(CSVEntry e1, CSVEntry e2){
        boolean ans=false;
        boolean c=false;
        boolean t=false;
        if(e1.nameOfCountry.equalsIgnoreCase(e2.nameOfCountry)){
            c = true;
        }
        if(e1.tempOfCountry.equals(e2.tempOfCountry)){
            t=true;
        }
        if(c&&t){
            ans = true;
        }
        return ans;
    }
}