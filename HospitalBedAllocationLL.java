import java.util.*;
import java.io.*;
import java.text.*;
public class HospitalBedAllocationLL
{ 
    public static void main ( String [] args ) 
    {
        try {
            Scanner sc = new Scanner ( System.in);
            DecimalFormat df = new DecimalFormat("RM000.00");
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
            
            FileReader fr = new FileReader("bedinfo.txt");
            BufferedReader br = new BufferedReader(fr);
            
            LinkedList <Bed> BedLL = new LinkedList();
            
            String input = null;
    
            //Reading and parsing the file
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
    
                //Add Bed and patient infomation inside linkedList
                BedLL.insertAtBack(bed);
            }
            br.close();
            
            /**
             * List of processing
             */
            
            //Store Bed objects into bedLL and display the information in tabular format.
            Bed obj  = null;
            obj = BedLL.getFirst();
            // Print table headers
            System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.printf("|\t\t\t\t\t\tBed Infomation Detail\t\t\t\t\t\t\t      |\n");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
                
            while(obj != null)
            {
                System.out.print(obj.toString());
                obj = BedLL.getNext();
            }
            System.out.printf("|-----------------------------------------------------------------------------------------------------------------------------|\n");
    
            
            //Find the highest room price and the lowest room price in bedLL then display the price and the patient id.
            Bed hobj = BedLL.getFirst();
            Bed lobj = BedLL.getFirst();
            obj = BedLL.getFirst();
            while ( obj != null)
            {
                
                if(obj.getRoomPrice() > hobj.getRoomPrice())
                {
                    hobj = obj;
                }
                else if (obj.getRoomPrice() < lobj.getRoomPrice())
                {
                    lobj = obj;
                }
                
                obj = BedLL.getNext();
            }
            
            
            System.out.println("\nHighest room price is Rm" + hobj.getRoomPrice() + " where patient id is " + hobj.getPatient().getPatientID());
            System.out.println("\nLowest room price is Rm" + lobj.getRoomPrice() + " where patient id is " + lobj.getPatient().getPatientID());
            System.out.println();
            
            //3
            System.out.println();
            System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.printf("|\t\t\t\t\tUnder 40 Y/O Patient in Covid Ward\t\t\t\t\t\t      |\n");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
                
            obj = BedLL.getFirst();
            while (obj != null)
            {
                if ( obj.getWardType() == 'C')
                {
                    if ( obj.getPatient().getPatientAge() < 40 )
                    {
                        System.out.print(obj.toString());
                    }
                }
                
                obj = BedLL.getNext();
            }
            System.out.printf("|-----------------------------------------------------------------------------------------------------------------------------|\n");
            System.out.println();
            
            // Print table headers
            //4
            System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.printf("|\t\t\t\t\t\tBed Infomation Detail\t\t\t\t\t\t\t      |\n");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
                
            obj = BedLL.getFirst();
            while(obj != null)
            {
                System.out.print(obj.toString());
                obj = BedLL.getNext();
            }
            System.out.printf("|-----------------------------------------------------------------------------------------------------------------------------|\n");
            
            System.out.print("\nEnter The Bed ID To Update The Room's Price: ");
            String Search = sc.nextLine();
            boolean found = false;
            obj = BedLL.getFirst();
            while(obj != null)
            {
                if(obj.getBedID().equalsIgnoreCase(Search))
                {
                    found = true;
                    break;
                }
                else
                {
                    found = false;
                }
                obj = BedLL.getNext();
            }
            
            if(found)
            {
                System.out.print("Enter the new room price to update: RM ");
                double price = sc.nextDouble();
                System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
                System.out.printf("|\t\t\t\t\t\tUpdated Patient Details\t\t\t\t\t\t\t      |\n");
                System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
                System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
                System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            
                obj.setRoomPrice(price);
                System.out.print(obj.toString());
                System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            }
            else
            {
                System.out.println("Record doesn't exist!!");
            }
            
            //5            
            LinkedList <Bed> criticalLL = new LinkedList();
            LinkedList <Bed> stableLL = new LinkedList();
            obj = BedLL.getFirst();
            while (obj != null)
            {
                if(obj.getPatient().getCondition() == 'C')
                {
                    criticalLL.insertAtBack(obj);
                }
                else if (obj.getPatient().getCondition() == 'S')
                {
                    stableLL.insertAtBack(obj);
                }
                obj = BedLL.getNext();
            }
            
            System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.printf("|\t\t\t\t\tPatient With Critical Condition\t\t\t\t\t\t\t      |\n");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            obj = criticalLL.getFirst();
            while(obj != null)
            {
                System.out.print(obj.toString());
                obj = criticalLL.getNext();
            }
            System.out.printf("|-----------------------------------------------------------------------------------------------------------------------------|\n");
            
            System.out.println();
            
            System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.printf("|\t\t\t\t\tPatient With Stable Condition\t\t\t\t\t\t\t      |\n");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            obj = stableLL.getFirst();
            while(obj != null)
            {
                System.out.print(obj.toString());
                obj = stableLL.getNext();
            }
            System.out.printf("|-----------------------------------------------------------------------------------------------------------------------------|\n");
            
            //6
            criticalLL.sortRequestDate();
            System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.printf("|\t\t\t\t\t\tSorted Infomation Detail\t\t\t\t\t\t      |\n");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            obj = criticalLL.getFirst();
            while(obj != null)
            {
                System.out.print(obj.toString());
                obj = criticalLL.getNext();
            }
            System.out.printf("|-----------------------------------------------------------------------------------------------------------------------------|\n");
            
            
            //7
            double avg = 0.0, total = 0.0;
            int count = 0;
            obj = stableLL.getFirst();
            while(obj != null)
            {   
                if(obj.getWardType() == 'G')
                {
                    total += obj.getRoomPrice();
                    count += 1;
                }
                obj = stableLL.getNext();
            }
            
            avg = total / count;
            
            System.out.println("\n\nAverage room price of all patient in stableLL that stay in general ward is " + df.format(avg) );
            
            //8
            int num = 0;
            obj = stableLL.getFirst();
            while( obj != null)
            {
                if(obj.getWardType() == 'C')
                {
                    num++;
                }
                obj = stableLL.getNext();
            }
            System.out.println("\nThe number of patients in  stableLL that stay in COVID ward type is " + num);
            
            //9
            double avgAge = 0.0,AgeAll = 0.0;
            obj = criticalLL.getFirst();
            while(obj != null)
            {
                AgeAll += obj.getPatient().getPatientAge();
                obj = criticalLL.getNext();
            }
            
            avgAge = AgeAll / criticalLL.size();
            System.out.println("The average patient age in criticalLL is " + avgAge + " years old");
            
            //10
            System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.printf("|\t\t\t\t\tPatient With Critical Condition\t\t\t\t\t\t\t      |\n");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
            System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
            obj = criticalLL.getFirst();
            while(obj != null)
            {
                System.out.print(obj.toString());
                obj = criticalLL.getNext();
            }
            System.out.printf("|-----------------------------------------------------------------------------------------------------------------------------|\n");
            
            System.out.print("\nEnter a Bed id from criticalLL to change their ward type: ");
            String SearchId = sc.next();
            boolean foundCon = false;
            obj = criticalLL.getFirst();
            
            while ( obj != null ) 
            {
                if(obj.getBedID().equalsIgnoreCase(SearchId))
                {
                    foundCon = true;
                    break;
                }
                else 
                {
                    foundCon = false;
                }
                obj = criticalLL.getNext();
            }
            
            if(foundCon)
            {
                
                System.out.print("Enter new ward type to update [I|G|C]: ");
                char newWard = sc.next().charAt(0);
                
                System.out.printf("+-----------------------------------------------------------------------------------------------------------------------------+\n");
                System.out.printf("|\t\t\t\t\t\tUpdated Patient Details\t\t\t\t\t\t\t      |\n");
                System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
                System.out.printf("| %-10s | %-8s | %-10s | %-3s | %-14s | %-10s | %-20s | %-9s | %-10s |\n","BedID", "WardType", "RoomPrice", "NumDays", "RequestDate", "PatientID", "PatientName", "PatientAge", "Condition");
                System.out.printf("+------------+----------+------------+---------+----------------+------------+----------------------+------------+------------+\n");
                
                obj.setWardType(newWard);
                System.out.print(obj.toString());
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
