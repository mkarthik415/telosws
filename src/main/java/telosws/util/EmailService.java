package telosws.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import telosws.beans.Clients;
import telosws.beans.Document;
import telosws.dao.TelosWSDaoImpl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

/**
 * Created by karthikmarupeddi on 9/11/15.
 */
@Component
public class EmailService implements EmailServiceInterf {


    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private TelosWSDaoImpl daoImpl;

    @Value("${google.p12.path}")
    private String mypath;

    @Value("${google.serviceAccount.id}")
    private String googleServiceAccountId;

    @Value("${spring.mail.from}")
    private String googleOriginMailAddress;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    JavaMailSender javaMailSender;

    /**
     * View and manage your mail.
     */
    private static final String MAIL_GOOGLE_COM = "https://mail.google.com/";

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");

    final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendEmails(String subject,Clients client) throws IOException, InterruptedException, GeneralSecurityException, MessagingException {


        List<Document> totalDocuments = daoImpl.searchDocumentsByClient(client);

        MimeMessage email = createMimeMessage("mkarthik415@gmail.com", googleOriginMailAddress, subject, getMessageForRenewals(client), totalDocuments);

//        Message message = createMessageWithEmail(email);
        javaMailSender.send(email);
//        message = gmailService.users().messages().send(googleOriginMailAddress, message).execute();

        logger.debug("Message id: " + email.getSubject());
        logger.debug(email.getSubject());

        daoImpl.logEmail(client, email.getSubject());

        if (totalDocuments != null && totalDocuments.size() >= 0) {
            for (Document file : totalDocuments) {
                if (!StringUtils.containsIgnoreCase(file.getFileName(), "MANDATE")) {
                    FileUtils.deleteQuietly(new File("temp/"+file.getFileName()));
                }
            }
        }

    }


    private static MimeMessage createMimeMessage(String to, String from, String subject, String body, List<Document> files) {
        MimeMessage message;
        message = null;
        try {
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props, null);
            message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(javax.mail.Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Send the actual HTML message, as big as you like
            message.setText(body,null,"html");

            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = null;

            if (files != null && files.size() >= 0) {
//                FileUtils.deleteQuietly(tempFile);
                for (Document file : files) {
                    if (!StringUtils.containsIgnoreCase(file.getFileName(), "MANDATE")) {
                        //Attach files to the message
                         messageBodyPart = new MimeBodyPart();

                        //create tempfile
                        File tempFile = new File("temp/"+file.getFileName());
                        InputStream in = file.getScanned().getBinaryStream();
                        OutputStream outputStream = new FileOutputStream(tempFile);
                        IOUtils.copy(in, outputStream);

                        DataSource source = new FileDataSource(tempFile);
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(file.getFileName());
                        multipart.addBodyPart(messageBodyPart);
                        message.setContent(multipart);
                    }
                }
            }
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html");
            messageBodyPart.setHeader("Content-Type", "text/html; charset=\"UTF-8\"");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);


        } catch (MessagingException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return message;
    }

    public String getMessageForRenewals(Clients client)
    {
        try{
            String clientName;
            StringBuffer clientNameBuffer= new StringBuffer(client.getClientName());
            if(clientNameBuffer.substring(0,4).equalsIgnoreCase("M/S.") )
            {
                clientNameBuffer.replace(0,4,"");
                clientName =clientNameBuffer.toString();
            }
            else
            {
                clientName = client.getClientName();
            }

            Context context = new Context();
            context.setVariable("department", client.getDepartment());
            context.setVariable("policyNumber", client.getPolicyNumber() );
            context.setVariable("clientName", clientName);
            context.setVariable("clientPolicyEndDate", SIMPLE_DATE_FORMAT.format(client.getPolicyEndDate()));

            if(client.getRenewalAmount() != null && client.getRenewalCompany() != null && client.getRenewalAmount() > 0.0)
            {
                context.setVariable("isRenewalAmount", "true");
                context.setVariable("renewalAmount", client.getRenewalAmount().toString());
                context.setVariable("renewalCompany", client.getRenewalCompany());
            }
            return templateEngine.process("renewal",context);
        }
        catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }
}
