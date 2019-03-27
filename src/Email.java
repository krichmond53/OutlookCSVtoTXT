/*
 * The email class will be constructed from the input .csv file for each line
 * that is an actual email.
 */

public class Email {

	private String dateTime;
	private String subject;
	private String body;
	private String fromAddress;
	private String toAddress;
	private String ccAddress;
	
	public Email(String dateTime, String subject, String body, String fromAddress, String toAddress, String ccAddress) {
		super();
		this.dateTime = dateTime;
		this.subject = subject;
		this.body = body;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.ccAddress = ccAddress;
	}

	@Override
	public String toString() {
		return "Date:\t" + dateTime + "\nSub:\t" + subject + "\nFrom:\t"
				+ fromAddress + "\nTo:\t" + toAddress + "\nCC:\t" + ccAddress + "\nBody:\n" + body + "\n";
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getCcAddress() {
		return ccAddress;
	}

	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}

}

