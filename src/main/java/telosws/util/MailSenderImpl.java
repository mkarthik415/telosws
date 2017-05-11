package telosws.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import telosws.beans.Clients;
import telosws.beans.Document;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by karthikmarupeddi on 3/8/15.
 */

@Component
public class MailSenderImpl implements SendMails{

    @Autowired
    private JavaMailSender javaMailSender;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

    private String disclamerMessage = "Please do not reply to this message. Replies to this message are routed to an unmonitored mailbox. If you have any questions, please contact Telos Risk Management & Insurance Broking Services (P) Ltd at teloshyd@gmail.com .";



    public void send(Clients clients)  {

        final org.slf4j.Logger logger = LoggerFactory.getLogger(MailSenderImpl.class);

        String clientName;
        StringBuffer clientNameBuffer= new StringBuffer(clients.getClientName());
        if(clientNameBuffer.substring(0,4).equalsIgnoreCase("M/S.") )
        {
            clientNameBuffer.replace(0,4,"");
            clientName =clientNameBuffer.toString();
        }
        else
        {
            clientName = clients.getClientName();
        }


        String reply ="Telos Risk Management and Insurance Broking Services (P) Limited,<br/>" +
                "2nd floor, Status Plaza, Opp.: Greenlands Guest House, Begumpet,<br/>" +
                "Hyderabad - 500016<br/>" +
                "E Mail: teloshyd@gmail.com";

        String regards ="<br/><br/><br/>With regards,<br/>" +
                "<br/>" +
                "M. Narasimha Rao & M.R.G. Raju<br/>" +
                "Telos Risk Management and Insurance Broking Services (P) Limited,<br/>" +
                "2nd floor, Status Plaza, Opp.: Greenlands Guest House, Begumpet,<br/>" +
                "Hyderabad - 500016.  Tel- 040-66776677  TeleFax-040-23416770 mobile 9848021211<br/>" +
                "Website: www.telosrisk.com<br/><br/><br/><br/>";



        String bodyHtml = "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "<title>ManDate</title>\n" +
                "</head>\n" +
                "\t<body>\n" +
                "\t\n" +
                "\t<p>Dear Customer,</p>\n" +
                "\t\t\t<p>\n" +
                "\t\t\t\tRe.: Change of Mail ID's A/c. M/s. Telos Risk Management & Insurance Broking Services Pvt. Ltd.</p>\n" +
                "\t\t\t\t<p>This refers to the above we bring to your notice that we have changed our Mail IDS w.e.f. 08-07-2015 as mentioned below:</p>\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t<p>1)\tFor general quotes (other than motor) and other matters, send mails to mail@teloshyd.com</p>\n" +
                "\t\t\t\t<p>2)\tFor motor quotes and related queries, send mails to  motor@teloshyd.com</p>\n" +
                "\t\t\t\t<p>3)\tFor all claims and related matters, send mails to claims@teloshyd.com</p>\n" +
                "\t\t\t\t<p>Please make a note of the same in your records.</p>\n" +
                "\t\t\t\t\n" +  regards +
                "\t\n" +
                "\t</body>\n" +
                "</html>";
        try{

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper;

            helper = new MimeMessageHelper(message, true); // true indicates
            // multipart message
            helper.setSubject("Change of Mail ID's");
            helper.setTo(clients.getEmail());
            helper.setFrom("teloshyd@gmail.com");
            helper.setText(bodyHtml, true); // true indicates html
            // continue using helper object for more functionalities like adding attachments, etc.
            logger.info("emails being sent are"+clients.getEmail());
            javaMailSender.send(message);
        }
        catch (javax.mail.internet.AddressException e) {
            System.out.println("incorrect mailid:"+clients.getEmail());
            logger.info("this method is not able to find any files due to an exception", e);
        }
        catch (javax.mail.MessagingException e) {
            System.out.println("incorrect mailid:"+clients.getEmail());
            logger.info("this method is not able to find any files due to an exception", e);
        }
        catch (Exception e) {
            System.out.println("incorrect mailid:"+clients.getEmail());
            logger.info("this method is not able to find any files due to an  exception", e);
        }

    }
}
