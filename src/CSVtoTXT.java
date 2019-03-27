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
public class CSVtoTXT {

	public static void main(String[] args) throws FileNotFoundException {

		// Get Outlook .csv file
		String inString = commandLine(args);			// Method for checking command line
		File goodFile = isValidFile(inString);			// Method for validating a file
		
	    Scanner readIt = new Scanner(goodFile);			// Create a scanner to read the file
		
	    // Parse .csv file for relevant data
	    ArrayList<Email> emails = makeEmails(readIt);		// Method to make ArrayList of emails from the scanner

	}
	
	public static ArrayList<Email> makeEmails(Scanner input){
		ArrayList<Email> em = new ArrayList<Email>();
		String body = "";

		while (input.hasNextLine()){
			String thisLine = input.nextLine();
			String[] lineSplit = thisLine.split(",");
			String subject = "";
			String fromAddress = "";
			String toAddress = "";
			String ccAddress = "";
			
			int len = lineSplit.length;
			
			// If there are 7 items in the array and the next array doesn't have 7, then add the next array
			// strings to the previous body.
			System.out.println("Array size: " + len);

			if (len == 7) {
				System.out.println("Body: " + body);

				subject = lineSplit[1];
				fromAddress = lineSplit[2];
				toAddress = lineSplit[3];
				ccAddress = lineSplit[4];
				body = lineSplit[6];
				System.out.println("Subject: " + subject + "\nFrom: " + fromAddress + "\nTo " 
						+ toAddress + "\nCC: " + ccAddress + "\n");
			} else if (len < 7) {
				for (String s : lineSplit) {
					body += s + "\n";
				}
			}

			
//			Email newEmail = Email(dateTime, subject, body, fromAddress, toAddress, ccAddress);
//			newEmail = new Email("defaultTime", lineSplit[1], body, lineSplit[2], lineSplit[3], lineSplit[4]);
//			newEmail.toString();
			
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
