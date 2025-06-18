public class Bed 
{
    private String bedID;       //unique identifier for the bed
    private char wardType;      //‘G’ for general, ‘C’ for covid, ‘I’ for ICU
    private double roomPrice;   //price for room per day
    private int numDays;        //Number of days stayed
    private String requestDate; //date for requesting for bed
    public Patient patient;    //this is composite object use to search either patient is suitable

    public Bed(String bedID, char wardType, double roomPrice, int numDays, String requestDate, Patient patient) 
    {
        this.bedID = bedID;
        this.wardType = wardType;
        this.roomPrice = roomPrice;
        this.numDays = numDays;
        this.requestDate = requestDate;
        this.patient = patient;
    }
    
    public void setAll(String bedID, char wardType, double roomPrice, int numDays, String requestDate, Patient patient) 
    {
        this.bedID = bedID;
        this.wardType = wardType;
        this.roomPrice = roomPrice;
        this.numDays = numDays;
        this.requestDate = requestDate;
        this.patient = patient;
    }
    
    public void setBedID(String bedID)
    {
        this.bedID = bedID;
    }
    
    public void setWardType(char wardType)
    {
        this.wardType = wardType;
    }
    
    public void setRoomPrice(double roomPrice)
    {
        this.roomPrice = roomPrice;
    }
    
    public void setNumDays(int numDays)
    {
        this.numDays = numDays;
    }
    
    public void setRequestDate(String requestDate)
    {
        this.requestDate = requestDate;
    }
    
    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }
    
    public String getBedID()
    {
        return bedID;
    }
    
    public char getWardType()
    {
        return wardType;
    }
    
    public double getRoomPrice()
    {
        return roomPrice;
    }
    
    public int getNumDays()
    {
        return numDays;
    }
    
    public String getRequestDate()
    {
        return requestDate;
    }
    
    public Patient getPatient()
    {
        return patient;
    }
    
    public String toString() 
    {
        return String.format("| %-10s | %-8s | RM%-8.2f | %-7d | %-14s | %-10s | %-20s | %-10d | %-10c |\n",  bedID,  wardType, roomPrice, numDays,  requestDate, patient.getPatientID(), patient.getPatientName(), patient.getPatientAge(), patient.getCondition());
    }
}
