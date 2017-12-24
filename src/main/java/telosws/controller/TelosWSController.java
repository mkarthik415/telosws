package telosws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telosws.beans.Clients;
import telosws.beans.Document;
import telosws.beans.Greeting;
import telosws.beans.User;
import telosws.dao.TelosWSDaoImpl;
import telosws.util.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by karthikmarupeddi on 2/5/15.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TelosWSController {


    @Autowired
    TelosWSDaoImpl  daoImpl;

    @Autowired
    Util util;

    private static final String template = "Hello  world, %s!";
    private final AtomicLong counter = new AtomicLong();


    @RequestMapping("/check")
    public Greeting check(String name) {

        String clientName = daoImpl.findClientByName(name).get(0).getClientName();
        return new Greeting(counter.incrementAndGet(),
                String.format(clientName, clientName));
    }

    @RequestMapping("/angularjs")
    public Greeting check() {
        String clientName = daoImpl.findClientByName("karthik").get(0).getClientName();
        return new Greeting(counter.incrementAndGet(),
                String.format(clientName, clientName));
    }


    @RequestMapping("/findUser")
    public List<User> findUser(String userName, String password) {

        return daoImpl.findUser(userName, password);

    }


    @RequestMapping("/byName")
    public List<Clients> byName(String name) {

        return daoImpl.findClientByName(name);

    }

    @RequestMapping("/byVehicleNumber")
    public List<Clients> byVehicleNumber(String vehicleNumber) {

        return daoImpl.findClientByVehicleNumber(vehicleNumber);

    }

    @RequestMapping("/startDate")
    public List<Clients> startDate(String name) {

        return daoImpl.findClientByName(name);

    }

    @RequestMapping("/byPolicyNumber")
    public List<Clients> byPolicyNumber(String policyNumber) {

        return daoImpl.findClientByPolicyNumber(policyNumber);

    }

    @RequestMapping("/byPhoneNumber")
    public List<Clients> byPhoneNumber(String phoneNumber) {

        return daoImpl.findClientByPhoneNumber(phoneNumber);

    }

    @RequestMapping("/byEmailId")
    public List<Clients> byEmailId(String emailId) {

        return daoImpl.findClientByEmailId(emailId);

    }

    @RequestMapping("/bySerialNumber")
    public List<Clients> bySerialNumber(int serialNumber) {

        return daoImpl.findClientBySerialNumber(serialNumber);

    }

    @RequestMapping("/sendEMails")
    public List<Clients> findAllClients() {

        return daoImpl.findAllClients();

    }

    @RequestMapping("/documentsByClient")
    public List<Document> getDocuments(int id)
    {

        return daoImpl.findDocumentsByClient(id);


    }

    @RequestMapping("/documentsById")
    public List<Document> getDocumentsById(int id)
    {

        return daoImpl.getDocumentsById(id);


    }

//    @RequestMapping("/login")
//    public Integer  login(String username, String password) {
//
//        return daoImpl.logIn(username,password);
//
//    }



    @RequestMapping("/update")
    public String getUpdateStatments(String location)
    {

        String documentsList = daoImpl.loadData(location);
        return documentsList;

    }

    @RequestMapping("/echo")
    public String echoName(String name)  {

        try
        {

            //URL resourceUrl = getClass().getResource("/static");
//            File file = new File("/var/lib/openshift/54d6bc064382ec06910000bd/app-root/repo/src/main/resources/static/text.xml");
//            OutputStream outputStream = new FileOutputStream(file);
            return "hi hi hi hi "+name ;
        }
        catch (Exception e)
        {
            return "Exception"+e;
        }
    }


    @RequestMapping("/mail")
    public Boolean sendEmail(String subject, String data)
    {
        daoImpl.sendEmails(subject,daoImpl.findClientByName("sravani rampay").get(0));
        return false;
    }

    @RequestMapping("/getChartTypeOfPolicies")
    public List<telosws.beans.ChartResults> getChartTypeOfPolicies(Integer fromYear, Integer toYear)
    {

        return daoImpl.resultForTypesOfPolicies(fromYear,toYear);


    }

    @RequestMapping("/getClientsForRenewal")
    public List<Clients> getClientsForRenewal()
    {
        return daoImpl.getClientsForRenewals();
    }


    @RequestMapping("/getSMSAndEMailsRenewals")
    public Boolean getSMSAndEMailsRenewals()
    {
         return daoImpl.sendSMSAndEMailForRenewal();
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

   /* @Scheduled(fixedRate = 5000, zone = "Asia/Kolkata")*/
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
//        sendEmail(null, null);
    }

}
