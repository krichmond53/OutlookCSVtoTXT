import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Exporting emails from Outlook can be done either into a .pst or .csv.  .pst files
 * can only be opened from within Outlook, which does no good if the person you wish
 * to send the email to does not use outlook.  .csv files are not as human-readable and
 * the export does not contain a date column.
 * 
 * This program is to take transform an Outlook .csv into a .txt file that includes a
 * relative date based on the first date which appears in an email.  This will also be
 * a more human readable format.
 */
public class CSVtoTXTdriver {

	public static void main(String[] args) throws FileNotFoundException {
		// Get Outlook .csv file
		String inString = commandLine(args);			// Method for checking command line
		File goodFile = isValidFile(inString);			// Method for validating a file
		
	    Scanner readIt = new Scanner(goodFile);			// Create a scanner to read the file
	    ArrayList<Email> emails = makeEmails(readIt);		// Method to make ArrayList of emails from the scanner
    
	    
		// Parse .csv file for relevant data


	}
	
	public static ArrayList<Email> makeEmails(Scanner input){
		ArrayList<Email> em = new ArrayList<Email>();
		while (input.hasNextLine()){
			String thisLine = input.nextLine();
//			System.out.println("This line: " + thisLine);
			String[] lineSplit = thisLine.split("\",\"");
			int count = 1;
			for (String ls : lineSplit)
			{
				System.out.println(count + ") " + ls);
				count++;
			}
			
			
//			Scanner getNums = new Scanner(thisLine);
//			int jobNumber = 0;
//			int executeTime = 0;
//			int entryTime = 0;
//			int jobPriority = 0;
//			boolean badInfo = false;
//			
//			if (getNums.hasNextInt()) jobNumber = getNums.nextInt();		// Next integer will be job number
//			else badInfo = true;
//			if (jobNumber < 1) badInfo = true;
//			if (!j.isEmpty() && (jobNumber <= j.get(j.size()-1).getNumber())) badInfo = true;
//			
//			if (getNums.hasNextInt()) executeTime = getNums.nextInt();		// Next integer will be execute time
//			else badInfo = true;
//			if (executeTime < 1) badInfo = true;
//
//			if (getNums.hasNextInt()) entryTime = getNums.nextInt();		// Next integer will be entry time
//			else badInfo = true;
//			if (entryTime < 1) badInfo = true;
////			if (!j.isEmpty()) System.out.println(j.get(j.size()-1).getEntry());
//			if (!j.isEmpty() && entryTime <= j.get(j.size()-1).getEntry()) badInfo = true;
//				
//			if (getNums.hasNextInt()) jobPriority = getNums.nextInt();		// Next number will be job priority
//			else badInfo = true;
//			if (jobPriority < 1 || jobPriority > 4) badInfo = true;
//			
//			if (getNums.hasNext())	badInfo = true;							// If there's more info, it is invalid
//		
//			if (!badInfo) {													// When there's no bad information....
//				Job work = new Job(jobNumber, executeTime, entryTime, jobPriority);			// ...create a new job
//				j.add(work);
//			} else System.out.println("ERROR: Invalid infomation on line >>> " + thisLine + " <<<");
//		getNums.close();
		}
		return em;
	}
	
	public static String commandLine(String[] args){
	    String s;
	    Scanner keyIn;
		if (args.length > 0) {					// Accept a command line argument
	    	s = args[0];						// Create a new inFile that is valid
	    } else {								// If no command line argument
	    	keyIn = new Scanner(System.in);
		    System.out.println("Please enter the file name to read from: ");
		   s = keyIn.nextLine();				// Create a new inFIle that is valid
	    }
		return s;
	}
	
	
	public static File isValidFile(String userFile)
	{
		File inFile = new File(userFile);
	
		while(!inFile.exists() || inFile.isDirectory())
		{ 
			System.out.println("Please enter a valid file name or Q to quit.");
			Scanner in = new Scanner(System.in);
			String validFile = in.nextLine();
			if(validFile.equalsIgnoreCase("q"))
			{   
				System.out.println("\nProgram terminated by user.");
				in.close();
				return null;
			} 
			else inFile = new File(validFile);
			if (inFile.exists()) in.close();
		}
	  return inFile;
	}
}
