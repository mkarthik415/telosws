package telosws.util;

import telosws.beans.Clients;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Created by karthikmarupeddi on 9/12/15.
 */
public interface EmailServiceInterf {

    public void sendEmails(String subject, Clients client) throws IOException, InterruptedException, GeneralSecurityException, MessagingException;
}
