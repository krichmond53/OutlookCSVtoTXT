import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	    
	    int num = 1;
	    for(Email e:emails) {
	    	if (!(e.getBody().trim().equals(""))) {
		    	System.out.println("Email " + num + ")\n" + e.toString());
		    	num++;
	    	}
	    }
	}
	
	/**
	 * Creates ArrayList of Email objects. Outlook's CSV output has 19 columns.  This
	 * function will only grab the subject, to/from/cc addresses, and the body of the email.
	 * @param input - Scanner object of a good file
	 * @return - List of Email objects
	 */
	public static ArrayList<Email> makeEmails(Scanner input){
		ArrayList<Email> em = new ArrayList<Email>();
		String subject = "";
		String fromAddress = "";
		String toAddress = "";
		String ccAddress = "";
		String body = "";
		
		Email newEmail = null;
		while (input.hasNextLine()){
			String thisLine = input.nextLine();
			String[] lineSplit = thisLine.split(",");
			int len = lineSplit.length;
			
			subject = lineSplit[0];
			fromAddress = lineSplit[(len-16)];
			toAddress = lineSplit[(len-13)];
			ccAddress = lineSplit[(len-10)];
			
			for(int i = 1; i<len-18; i++) {
				body += lineSplit[i];
			}

			String dateTime = getDateTimeFromBody(body);
			
			newEmail = new Email(dateTime, subject, body, fromAddress, toAddress, ccAddress);
			em.add(newEmail);
			body = "";
		}
		return em;
	}
	
	
	/**
	 * Look through the body to see if there is a date where a relative date/time can be assumed from an
	 * email reply.
	 * @param body - The body of an email
	 * @return - A relative date/time extracted from the email body
	 */
	private static String getDateTimeFromBody(String body) {
		String dt = "n/a";
		
		Pattern pattern = Pattern.compile("On (.*?)at");
		Matcher matcher = pattern.matcher(body);
		if (matcher.find())
		{
//		    System.out.println(matcher.group(1));
		    dt = matcher.group(1);
		}
		
		return dt;
	}

	/**
	 * Basic command line input 
	 * @param args - First argument should be the name of a file in the current working directory
	 * @return - name of file that user input
	 */
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
	
	/**
	 * Check to see whether a file is valid or not
	 * @param userFile - Name of the file to check for
	 * @return - Valid file
	 */
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
