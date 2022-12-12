package in.emp.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class MailAuthenticator extends Authenticator 
{
	private static Logger logger = Logger.getLogger(MailAuthenticator.class);

	public PasswordAuthentication getPasswordAuthentication()
	{
		logger.log(Level.INFO,"MailAuthenticator ::: getPasswordAuthentication() :: Method Called ");
		String username, password;
		username = System.getProperty("SMTP_HOST_USERNAME");
		password = System.getProperty("SMTP_HOST_PASSWORD");
//		System.out.println("MailAuthenticator :: Username :: " +username );
//		System.out.println("MailAuthenticator :: password :: " + password);

		return new PasswordAuthentication(username, password);
	}
}