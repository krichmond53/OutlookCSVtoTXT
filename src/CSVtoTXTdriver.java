import java.io.File;
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

	public static void main(String[] args) {
		// Get Outlook .csv file
		String inString = commandLine(args);		// Method for checking command line
		File goodFile = isValidFile(inString);		// Method for validating a file
		
		// Parse .csv file for relevant data
//		Subject	
//		Body	
//		
//		// In Body, search for a string that looks like a date. 
//		// Format: Sent: Tuesday, March 19, 2019 2:39:38 PM
//
//
//		From: (Name)	
//		From: (Address)	
//		From: (Type)	
//		To: (Name)	
//		To: (Address)	
//		To: (Type)	
//		CC: (Name)	
//		CC: (Address)	
//		CC: (Type)	
//		BCC: (Name)	
//		BCC: (Address)	
//		BCC: (Type)	
//		Billing Information	
//		Categories	
//		Importance	
//		Mileage	
//		Sensitivity

		


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
