import java.io.*;
import java.text.*;
import java.util.*;
public class HospitalBedAllocationQ
{
    public static void main (String []args)
    {
        try {
            DecimalFormat df = new DecimalFormat("RM000.00");
            FileReader fr = new FileReader("bedinfo.txt");
            BufferedReader br = new BufferedReader(fr);
            Scanner sc = new Scanner(System.in);
            
            //1
            Queue <Bed> bedQ = new Queue();
            String input = null;
            while ((input = br.readLine()) != null) 
            {
                StringTokenizer st = new StringTokenizer(input, ";");

                //Parse data from each line
                String bedID = st.nextToken();
                char wardType = st.nextToken().charAt(0); 
                double roomPrice = Double.parseDouble(st.nextToken());
                int numDays = Integer.parseInt(st.nextToken());
                String requestDate = st.nextToken();
                String patientID = st.nextToken();
                String patientName = st.nextToken();
                int patientAge = Integer.parseInt(st.nextToken());
                char condition = st.nextToken().charAt(0);

                //Create Patient and Bed objects
                Patient patient = new Patient(patientID, patientName, patientAge, condition);
                Bed bed = new Bed(bedID, wardType, roomPrice, numDays, requestDate, patient);

                //Enqueue the Bed object
                bedQ.enqueue(bed);
            }
            br.close();
            
            System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.printf("|\t\t\t\t\t\tBed Infomation Detail\t\t\t\t\t\t\t      |\n");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            
            Queue <Bed> tempQ = new Queue();
            Bed obj;
            while(!bedQ.isEmpty())
            {
                obj = bedQ.dequeue();
                System.out.print(obj.toString());
                tempQ.enqueue(obj);
            }
            
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            
            while(!tempQ.isEmpty())
            {
                bedQ.enqueue(tempQ.dequeue());
            }
            
            //2
            Bed HighestObj = bedQ.dequeue();
            Bed LowestObj = bedQ.dequeue();
            tempQ.enqueue(HighestObj);
            tempQ.enqueue(LowestObj);
            while(!bedQ.isEmpty())
            {
                obj = bedQ.dequeue();
                if(obj.getRoomPrice() > HighestObj.getRoomPrice())
                {
                    HighestObj = obj;
                }
                else if(obj.getRoomPrice() < LowestObj.getRoomPrice())
                {
                    LowestObj = obj;
                }
                tempQ.enqueue(obj);
            }
            
            while(!tempQ.isEmpty())
            {
                bedQ.enqueue(tempQ.dequeue());
            }
            System.out.print("\nThe Highest Room Pirce Is:RM" + HighestObj.getRoomPrice() + " " + "Where The Patient ID Is:" + HighestObj.patient.getPatientID());
            System.out.print("\nThe Lowest Room Pirce Is:RM" + LowestObj.getRoomPrice() + " " + "Where The Patient ID Is:" + LowestObj.patient.getPatientID());
            System.out.println();
            
            //3
            System.out.println();
            System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.printf("|\t\t\t\t\tUnder 40 Y/O Patient in Covid Ward\t\t\t\t\t\t      |\n");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            
            
            
            while(!bedQ.isEmpty())
            {
                obj = bedQ.dequeue();
                if(obj.getPatient().getPatientAge() < 40 && (obj.getWardType() == 'C' || obj.getWardType() == 'c'))
                    {
                        System.out.print(obj.toString());
                    }
                tempQ.enqueue(obj);
            }
            
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            
            //restore
            while(!tempQ.isEmpty())
            {
                bedQ.enqueue(tempQ.dequeue());
            }
            System.out.println();
                        
            //4
            System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.printf("|\t\t\t\t\t\tBed Infomation Detail\t\t\t\t\t\t\t      |\n");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            
            while(!bedQ.isEmpty())
            {
                obj = bedQ.dequeue();
                System.out.print(obj.toString());
                tempQ.enqueue(obj);
            }
            
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            
            while(!tempQ.isEmpty())
            {
                bedQ.enqueue(tempQ.dequeue());
            }
            
            System.out.print("\nEnter The Bed ID To Update The Room's Price: ");
            String seacrhPatientID = sc.next();
            boolean found = false;
            Bed search = null;
            while (!bedQ.isEmpty()) 
            {
                obj = bedQ.dequeue();
                if (obj.getBedID().equalsIgnoreCase(seacrhPatientID)) 
                {
                    found = true;
                    search = obj; 
                }
                tempQ.enqueue(obj); 
            }

            while (!tempQ.isEmpty()) {
                bedQ.enqueue(tempQ.dequeue());
            }
            
            double newRprice = 0.0;
            if (found) 
            {
                System.out.print("Enter the new room price to update: RM ");
                newRprice = sc.nextDouble();
                search.setRoomPrice(newRprice);
                System.out.println("\nRoom price updated successfully!");
                
                System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
                System.out.printf("|\t\t\t\t\t\tUpdated Patient Details\t\t\t\t\t\t\t      |\n");
                System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
                System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
                System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            
                System.out.print(search.toString()); 
                
                System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            
            }
            else 
            {
                System.out.println("The record does not exist!!");
            }
            
            //5
            Queue <Bed> criticalQ = new Queue();
            Queue <Bed> stableQ = new Queue();
            Queue <Bed> criticalTempQ = new Queue();
            Queue <Bed> stableTempQ = new Queue();
            while(!bedQ.isEmpty())
            {
                obj = bedQ.dequeue();
                if(obj.getPatient().getCondition() == 'C')
                {
                   criticalQ.enqueue(obj); 
                }
                else if(obj.getPatient().getCondition() == 'S')
                {
                    stableQ.enqueue(obj);
                }
                
                tempQ.enqueue(obj);
            }
            System.out.println();
            
            while (!tempQ.isEmpty()) {
                bedQ.enqueue(tempQ.dequeue());
            }
            
            // Print table headers
            System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.printf("|\t\t\t\t\tPatient With Critical Condition\t\t\t\t\t\t\t      |\n");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            while(!criticalQ.isEmpty()) 
            {
                obj = criticalQ.dequeue();
                System.out.print(obj.toString());
                criticalTempQ.enqueue(obj);
            }
    
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            
            while(!criticalTempQ.isEmpty()) 
            {
                criticalQ.enqueue(criticalTempQ.dequeue());
            }
            System.out.println();
            System.out.println();
            
            // Print table headers
            System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.printf("|\t\t\t\t\tPatient With Stable Condition\t\t\t\t\t\t\t      |\n");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            while(!stableQ.isEmpty()) 
            {
                obj = stableQ.dequeue();
                System.out.print(obj.toString());
                stableTempQ.enqueue(obj);
            }
            
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            
            while(!stableTempQ.isEmpty()) 
            {
                stableQ.enqueue(stableTempQ.dequeue());
            }
            System.out.println();
            
            //6
            criticalQ.sortRequestDate();
            System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.printf("|\t\t\t\t\t\tSorted Infomation Detail\t\t\t\t\t\t      |\n");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            while(!criticalQ.isEmpty())
            {
                obj = criticalQ.dequeue();
                System.out.print(obj.toString());
                criticalTempQ.enqueue(obj);
            }
            
            System.out.printf("|-----------------------------------------------------------------------------------------------------------------------------|\n");
            
            while(!criticalTempQ.isEmpty()) 
            {
                criticalQ.enqueue(criticalTempQ.dequeue());
            }
            System.out.println();
            
            //7
            double totalStableQ = 0.0;
            int size = 0;
            while (!stableQ.isEmpty()) {
                obj = stableQ.dequeue();
                if (obj.getWardType() == 'G') 
                {
                    totalStableQ += obj.getRoomPrice();
                    size++;
                }
                stableTempQ.enqueue(obj); // Preserve original queue state
                }
                
            while (!stableTempQ.isEmpty()) 
            {
                stableQ.enqueue(stableTempQ.dequeue());
            }
            double avg = totalStableQ/size;
            System.out.println("\nAverage room price of all patient in stableQ that stay in general ward is " + df.format(avg) );
            
            //8
            int countS = 0;
            while(!stableQ.isEmpty())
            {
                obj = stableQ.dequeue();
                if(obj.getWardType() == 'C')
                {
                    countS++;
                }
                stableTempQ.enqueue(obj);
            }
            
            while(!stableTempQ.isEmpty())
            {
                stableQ.enqueue(stableTempQ.dequeue());
            }
            System.out.println("\nThe number of patients in  stableQ that stay in COVID ward type is " + countS);
            
            //9
            double avgAge = 0.0,AgeAll = 0.0;
            while (!criticalQ.isEmpty())
            {
                obj = criticalQ.dequeue();
                AgeAll += obj.getPatient().getPatientAge(); // Accumulate total age
                criticalTempQ.enqueue(obj); // Preserve the original state of criticalQ
            }
            
            // Restore the original state of criticalQ from tempQ
            while (!criticalTempQ.isEmpty()) 
            {
                criticalQ.enqueue(criticalTempQ.dequeue());
            }

            avgAge = AgeAll/criticalQ.size();//size method
            System.out.println("\nThe average patient age in criticalLL is " + avgAge + " years old");
            
            //10
            System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.printf("|\t\t\t\t\tPatient With Critical Condition\t\t\t\t\t\t\t      |\n");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            while(!criticalQ.isEmpty()) 
            {
                obj = criticalQ.dequeue();
                System.out.print(obj.toString());
                criticalTempQ.enqueue(obj);
            }
    
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            
            while(!criticalTempQ.isEmpty()) 
            {
                criticalQ.enqueue(criticalTempQ.dequeue());
            }
            System.out.println();
            
            
            System.out.println("Enter a bed id from criticalQ to change their ward type: ");
            String SearchId = sc.next();
            boolean foundCon = false;
            Bed searchNew = null;
            while (!criticalQ.isEmpty()) 
            {
                obj = criticalQ.dequeue();
                if(obj.getBedID().equalsIgnoreCase(SearchId))
                {
                    foundCon = true;
                    searchNew = obj;
                }
                criticalTempQ.enqueue(obj);
            }
            
            while (!criticalTempQ.isEmpty()) {
                criticalQ.enqueue(criticalTempQ.dequeue());
            }
            
            if(foundCon)
            {
                
                System.out.println("Enter new ward type to update [I|G|C]: ");
                char newWard = sc.next().charAt(0);
            
                System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
                System.out.printf("|\t\t\t\t\t\tUpdated Patient Details\t\t\t\t\t\t\t      |\n");
                System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
                System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
                System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            
                searchNew.setWardType(newWard);
                System.out.print(searchNew.toString());
                System.out.printf("|-----------------------------------------------------------------------------------------------------------------------------|\n");

            }
            else
            {
                System.out.println("Record doesn't exist!!!");
            }
        } 
        catch (FileNotFoundException fnfe) 
        {
            System.out.println("File not found: " + fnfe.getMessage());
        } 
        catch (IOException io)
        {
            System.out.println("Error reading file: " + io.getMessage());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
