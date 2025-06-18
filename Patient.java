public class Patient 
{
    private String patientID; //unique identifier for the patient
    private String patientName; //Name of the patient
    private int patientAge; //Age of the patient
    private char condition; //‘C’ for critical and ‘S’ for stable

    public Patient(String patientID, String patientName, int patientAge, char condition) 
    {
        this.patientID = patientID;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.condition = condition;
    }
    
    public void setAll(String patientID, String patientName, int patientAge, char condition) 
    {
        this.patientID = patientID;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.condition = condition;
    }
    
    public void setPatientID(String patientID)
    {
        this.patientID = patientID;
    }
    
    public void setPatientName(String patientName)
    {
        this.patientName = patientName;
    }
    
    public void setPatientAge(int patientAge)
    {
        this.patientAge = patientAge;
    }
    
    public void setCondition(char condition)
    {
        this.condition = condition;
    }
    
    public String getPatientID()
    {
        return patientID;
    }
    
    public String getPatientName()
    {
        return patientName;
    }
    
    public int getPatientAge()
    {
        return patientAge;
    }
    
    public char getCondition()
    {
        return condition;
    }

    public String toString() 
    {
        return String.format( "| %-10s | %-20s | %-10d | %-10c |", patientID, patientName, patientAge, condition);
    }
}