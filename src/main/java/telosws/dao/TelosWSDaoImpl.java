package telosws.dao;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import telosws.beans.ChartResults;
import telosws.beans.Clients;
import telosws.beans.Document;
import telosws.beans.User;
import telosws.mapping.ChartResultsMapper;
import telosws.mapping.ClientMapper;
import telosws.mapping.DocumentMapping;
import telosws.mapping.UserMapper;
import telosws.util.*;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karthikmarupeddi on 2/6/15.
 */

@Component
@PropertySources(value = {@PropertySource("classpath:application.properties")})
public class TelosWSDaoImpl implements TelosWSDao,ServletContextAware {

    final org.slf4j.Logger logger = LoggerFactory.getLogger(TelosWSDaoImpl.class);

    private final static String PRIMARY_PHONE_NUMBER = "PRIMARY_PHONE_NUMBER";
    private final static String SECONDARY_PHONE_NUMBER = "SECONDARY_PHONE_NUMBER";
    private final static String renewalSMS = "RENEWAL";

    @Value( "${GET_USER_SQL}" )
    private String GET_USER_SQL;

    @Value( "${GET_CLIENT_SQL}" )
    private String GET_CLIENT_SQL;

    @Value( "${GET_DOCUMENTS}" )
    private String GET_DOCUMENTS;

    @Value( "${GET_DOCUMENTS_BY_ID}" )
    private String GET_DOCUMENTS_BY_ID;

    @Value( "${GET_VEHICLE_NUM_SQL}" )
    private String GET_VEHICLE_NUM_SQL;

    @Value( "${GET_POLICY_NUM_SQL}" )
    private String GET_POLICY_NUM_SQL;

    @Value( "${GET_PHONE_NUM_SQL}" )
    private String GET_PHONE_NUM_SQL;

    @Value( "${GET_EMAIL_ID_SQL}" )
    private String GET_EMAIL_ID_SQL;

    @Value( "${GET_SERIAL_NUM_SQL}" )
    private String GET_SERIAL_NUM_SQL;

    @Value( "${GET_ALL_EMAIL_IDS_SQL}" )
    private String GET_ALL_EMAIL_IDS_SQL;

    @Value( "${CREATE_EMAIL_LOG}" )
    private String CREATE_EMAIL_LOG;

    @Value( "${TYPES_OF_POLICIES}" )
    private String TYPES_OF_POLICIES;

    @Value( "${GET_RENEWAL_CLIENTS}" )
    private String GET_RENEWAL_CLIENTS;

    @Value( "${LOG_SMS}" )
    private String LOG_SMS;

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    MapSqlParameterSource namedParameters;
    private List<User> returnUsers = null;
    List<Clients> returnClients = null;
    List<Document> returnDocuments = null;
    List<telosws.beans.File> files = null;
    List<ChartResults> chartResultses = null;
    private ServletContext servletContext;


    @Autowired
    private ExcelHelper excelHelper;

    @Autowired
    private EmailService emailService;

    @Autowired
    public TelosWSDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Autowired
    S3CloudConnector s3CloudConnector;

    @Autowired
    SMSLane smsLane;

    @Override
    public List<User> findUser(final String user, final String password) {

        logger.debug("inside implementation method");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("username", user);
        namedParameters.addValue("password", password);

        logger.debug("before query being executed");
        try {
            returnUsers = namedParameterJdbcTemplate.query(
                    GET_USER_SQL, namedParameters, new UserMapper());
            logger.debug("After query being executed"
                    + returnUsers.get(0).getTeam());
            logger.debug("After query being executed"
                    + returnUsers.get(0).getiD());
        } catch (Exception ex) {
            logger.info("User Not Found ", ex);
            return null;
        }
        return returnUsers;
    }

    @Override
    public List<Clients> findClientByName(final String name) {


        logger.debug("inside search implementation method by name");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("clientName", name);
        try {
            returnClients = namedParameterJdbcTemplate.query(
                    GET_CLIENT_SQL, namedParameters, new ClientMapper());
            logger.debug("After query being executed "
                    + returnClients.get(0).getId() + " ID is:::::::"
                    + returnClients.get(0).getClientName()
                    + " agent name, StartDate:::"
                    + returnClients.get(0).getPolicyStartdate());


        } catch (Exception ex) {
            logger.info("Error while search client by name", ex);
            return null;
        }
        return returnClients;
    }

    @Override
    public List<Clients> findClientByVehicleNumber(String vehicleNumber) {
        logger.debug("inside search implementation method by vehicle number");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("vehicleNumber", vehicleNumber);
        try {
            returnClients = namedParameterJdbcTemplate.query(
                    GET_VEHICLE_NUM_SQL, namedParameters, new ClientMapper());
            logger.debug("After query being executed "
                    + returnClients.get(0).getId() + " ID is:::::::"
                    + returnClients.get(0).getClientName()
                    + " agent name, StartDate:::"
                    + returnClients.get(0).getPolicyStartdate());
        } catch (Exception ex) {
            logger.info("Error while search client by vehicle", ex);
            return null;
        }
        return returnClients;
    }

    @Override
    public List<Clients> findClientByPolicyNumber(String policyNumber) {
        logger.debug("inside search implementation method by policy number");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("policyNumber", policyNumber);
        try {
            returnClients = namedParameterJdbcTemplate.query(
                    GET_POLICY_NUM_SQL, namedParameters, new ClientMapper());
            logger.debug("After query being executed "
                    + returnClients.get(0).getId() + " ID is:::::::"
                    + returnClients.get(0).getClientName()
                    + " agent name, StartDate:::"
                    + returnClients.get(0).getPolicyStartdate());
        } catch (Exception ex) {
            logger.info("Error while search client by policy number", ex);
            return null;
        }
        return returnClients;
    }

    @Override
    public List<Clients> findClientByPhoneNumber(String phoneNumber) {
        logger.debug("inside search implementation method by phone number");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("phoneNumber", phoneNumber);
        try {
            returnClients = namedParameterJdbcTemplate.query(
                    GET_PHONE_NUM_SQL, namedParameters, new ClientMapper());
            logger.debug("After query being executed "
                    + returnClients.get(0).getId() + " ID is:::::::"
                    + returnClients.get(0).getClientName()
                    + " agent name, StartDate:::"
                    + returnClients.get(0).getPolicyStartdate());
        } catch (Exception ex) {
            logger.info("Error while search client by phone number", ex);
            return null;
        }
        return returnClients;
    }

    @Override
    public List<Clients> findClientByEmailId(String emailId) {
        logger.debug("inside search implementation method by email id");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("emailId", emailId);
        try {
            returnClients = namedParameterJdbcTemplate.query(
                    GET_EMAIL_ID_SQL, namedParameters, new ClientMapper());
            logger.debug("After query being executed "
                    + returnClients.get(0).getId() + " ID is:::::::"
                    + returnClients.get(0).getClientName()
                    + " agent name, StartDate:::"
                    + returnClients.get(0).getPolicyStartdate());
        } catch (Exception ex) {
            logger.info("Error while search client by name", ex);
            return null;
        }
        return returnClients;
    }

    @Override
    public List<Clients> findClientBySerialNumber(int id) {
        logger.debug("inside search implementation method by serial number");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        try {
            returnClients = namedParameterJdbcTemplate.query(
                    GET_SERIAL_NUM_SQL, namedParameters, new ClientMapper());
            logger.debug("After query being executed "
                    + returnClients.get(0).getId() + " ID is:::::::"
                    + returnClients.get(0).getClientName()
                    + " agent name, StartDate:::"
                    + returnClients.get(0).getPolicyStartdate());
        } catch (Exception ex) {
            logger.info("Error while search client by serial number", ex);
            return null;
        }
        return returnClients;
    }

    @Override
    public List<Clients> findClientByStartDate(String name) {
        logger.debug("inside search implementation method by start date");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("clientName", name);
        try {
            returnClients = namedParameterJdbcTemplate.query(
                    GET_CLIENT_SQL, namedParameters, new ClientMapper());
            logger.debug("After query being executed "
                    + returnClients.get(0).getId() + " ID is::::::"
                    + returnClients.get(0).getClientName()
                    + " agent name, StartDate:::"
                    + returnClients.get(0).getPolicyStartdate());
        } catch (Exception ex) {
            logger.info("Error while search client by start date", ex);
            return null;
        }
        return returnClients;
    }

    @Override
    public List<Document> findDocumentsByClient(int id) {
        logger.debug("inside search implementation method of find documents for client with his ID");
        MapSqlParameterSource searchClientParameters = new MapSqlParameterSource();
        searchClientParameters.addValue("clientId", id);
        logger.debug("before documents search query being executed for client id-----" + id);
        try {
            returnDocuments = namedParameterJdbcTemplate.query(
                    GET_DOCUMENTS, searchClientParameters,
                    new DocumentMapping());

            return returnDocuments;
        } catch (Exception ex) {
            logger.info("this method is not able to find any files due to an exception",ex);
            return null;
        }
    }

    @Override
    public List<Document> getDocumentsById(Integer id) {
        logger.debug("inside search implementation method of find documents for client with his ID");
        MapSqlParameterSource searchClientParameters = new MapSqlParameterSource();
        searchClientParameters.addValue("clientId", id);
        logger.debug("before documents search query being executed for client id-----" + id);
        try {
            returnDocuments = namedParameterJdbcTemplate.query(
                    GET_DOCUMENTS, searchClientParameters,
                    new DocumentMapping());

            return returnDocuments;
        } catch (Exception ex) {
            logger.info("this method is not able to find any files due to an exception",ex);
            return null;
        }
    }

    @Override
    public List<Clients> findAllClients() {
        logger.debug("inside search implementation method to all email ids");
        try {
            returnClients = namedParameterJdbcTemplate.query(
                    TYPES_OF_POLICIES,new ChartResultsMapper());

            logger.debug("After query being executed "
                    + returnClients.get(0).getId() + " ID is:::::::::"
                    + returnClients.get(0).getClientName()
                    + " agent name, StartDate:::"
                    + returnClients.get(0).getPolicyStartdate());
        } catch (Exception ex) {
            logger.info("Error while finding all the clients", ex);
            return null;
        }
        return returnClients;
    }


    @Override
    public String loadData(String location) {

        String status = excelHelper.ExcelCountroller();

        return status;
    }


    @Override
    public void sendEmails(String subject, Clients client) {
        try {
            emailService.sendEmails(subject,client);

        } catch (IOException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        } catch (MessagingException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        }

    }



    @Override
    public  void logEmail(Clients client, String data) {

        logger.debug("inside implemntation method right befor updating DB when email was sent");
        try {
            namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("address", client.getEmail());
            namedParameters.addValue("clientId", client.getId());
            namedParameters.addValue("message", data);
            namedParameters.addValue("userId", 100);
        } catch (Exception e) {
            logger.debug( "named parameters issue " + e.toString());
        }
        logger.debug("before query being executed");
        try {

            namedParameterJdbcTemplate.update(CREATE_EMAIL_LOG,
                    namedParameters);
        } catch (DuplicateKeyException e) {
            logger.debug(
                    "After query being executed exception found  " + e);

        } catch (Exception e) {
            logger.debug(
                    "After query being executed exception found  " + e);

        }
    }

    public Boolean sendSMSAndEMailForRenewal() {
        Boolean status = false;
        logger.info("Right before sms and emails are send for renewals");
        returnClients = findClientByEmailId("mkarthik415@gmail.com");
//        returnClients = getClientsForRenewals();
        if(!returnClients.isEmpty())
        {
            for(Clients client : returnClients)
            {
//                Boolean smsStatus = sendSMSToClient(client,renewalSMS);
                Boolean smsStatus = true;
                logger.info("SMS Sent Status  "+smsStatus);
                if(client.getEmail()!= null)
                {
                    sendEmails("Policy Renewal Notice",client);
                    if(smsStatus)
                    {
                        status = true;
                    }
                }
            }
        }
        return status;
    }


    public List<Document> searchDocumentsByClient(Clients client) {
        logger.debug("inside search implementation method of find documents for client with his ID");
        MapSqlParameterSource searchClientParameters = new MapSqlParameterSource();
        searchClientParameters.addValue("clientId", client.getId());
        logger.debug("before documents search query being executed for client id-----" + client.getId());
        files = new ArrayList<telosws.beans.File>();
        try {
            returnDocuments = namedParameterJdbcTemplate.query(
                    GET_DOCUMENTS, searchClientParameters,
                    new DocumentMapping());
            logger.debug("before documents are------" + returnDocuments.get(0).getFileName());
        } catch (Exception ex) {
            logger.info("this method is not able to find any files due to an exception", ex);
            return null;
        }
        return returnDocuments;
    }


    @Override
    public List<Clients> getClientsForRenewals() {
        try {
            returnClients = jdbcTemplate.query(GET_RENEWAL_CLIENTS, new ClientMapper());
        } catch (Exception e) {
            logger.debug(
                    "After query being executed exception found  " + e);
            return null;
        }
        return returnClients;
    }

    @Override
    public Boolean sendSMSToClient(Clients client, String templateType) {
        Boolean responseByFirstPhoneNumber = false;
        Boolean responseBySecondaryPhoneNUmber = false;
        Boolean success = false;

        if (client.getPhoneNumber() != null) {

            String response = smsLane.SMSSender(client.getPhoneNumber(),
                    templateType, client);
            if (response.subSequence(17, 29).equals(client.getPhoneNumber())) {
                responseByFirstPhoneNumber = true;
                this.logSMS(client, templateType, PRIMARY_PHONE_NUMBER);
            }
            else
                responseByFirstPhoneNumber = false;
        }
        if (client.getSecondaryPhoneNumber() != null) {
            String response = smsLane.SMSSender(
                    client.getSecondaryPhoneNumber(), templateType, client);
            if (response.subSequence(17, 29).equals(
                    client.getSecondaryPhoneNumber()) ) {
                responseBySecondaryPhoneNUmber = true;
                this.logSMS(client, templateType, SECONDARY_PHONE_NUMBER);
            }
            else
                responseBySecondaryPhoneNUmber = false;
        }

        if (responseByFirstPhoneNumber || responseBySecondaryPhoneNUmber) {
            success = true;
            return success;
        } else {

            return success;
        }
    }

    @Override
    public Boolean logSMS(Clients client, String template, String phoneNumber) {
        namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("clientId",client.getId() );
        namedParameters.addValue("template",template );
        if(phoneNumber.equals(PRIMARY_PHONE_NUMBER))
        {
            namedParameters.addValue("phoneNumber", client.getPhoneNumber() );
        }
        else if(phoneNumber.equals(SECONDARY_PHONE_NUMBER))
        {
            namedParameters.addValue("phoneNumber", client.getSecondaryPhoneNumber() );
        }
        namedParameters.addValue("userId", "connect2telos" );
        logger.debug("before logging sms  being executed");
        try{

            namedParameterJdbcTemplate.update(
                    LOG_SMS, namedParameters);
        }
        catch(Exception ex)
        {
            logger.info("Exception when trying to update DB " + ex.toString());
            return false;
        }
        return true;
    }



//    @Override
//    public Integer logIn(String user, String password) {
//        StoredProcedureCall storedProcedureCall = new StoredProcedureCall(
//                jdbcTemplate, "xu_security.validate_login_password");
//        SqlParameter idParam = new SqlParameter("i_user_name", Types.VARCHAR);
//        SqlParameter passwordParam = new SqlParameter("i_user_password", Types.VARCHAR);
//        SqlOutParameter outParam = new SqlOutParameter("o_user_id", Types.INTEGER);
//        SqlParameter[] paramArray = { idParam,passwordParam,outParam };
//        storedProcedureCall.setParameters(paramArray);
//        storedProcedureCall.compile();
//        // Call stored procedure
//        Map storedProcResult = storedProcedureCall.execute(1);
//        System.out.println(storedProcResult);
//        return storedProcResult.size();
//    }



    public List<telosws.beans.ChartResults> resultForTypesOfPolicies(Integer fromYear, Integer toYear)
    {

        logger.debug("inside implemntation method right before getting the chart results for type of policies");
        try {
            namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("fromYear", fromYear);
            namedParameters.addValue("toYear", toYear);
        } catch (Exception e) {
            logger.debug( "named parameters issue " + e.toString());
        }
        logger.debug("before query being executed");
        try {

            chartResultses = namedParameterJdbcTemplate.query(
                    TYPES_OF_POLICIES,namedParameters,new ChartResultsMapper());
        } catch (DuplicateKeyException e) {
            logger.debug(
                    "After query being executed exception found  " + e);

        } catch (Exception e) {
            logger.debug(
                    "After query being executed exception found  " + e);

        }

        return  chartResultses;


    }


    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


}
