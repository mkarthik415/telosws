package telosws.dao;

import telosws.beans.Clients;
import telosws.beans.Document;
import telosws.beans.User;

import java.util.List;

/**
 * Created by karthikmarupeddi on 2/6/15.
 */
public interface TelosWSDao {

    public List<User> findUser(String user, String password);

    public List<Clients> findClientByName(String name);

    public List<Clients> findClientByVehicleNumber(String vehicleNumber);

    public List<Clients> findClientByPolicyNumber(String policyNumber);

    public List<Clients> findClientByPhoneNumber(String phoneNumber);

    public List<Clients> findClientByEmailId(String name);

    public List<Clients> findClientBySerialNumber(int id);

    public List<Clients> findClientByStartDate(String name);

    public List<Document> findDocumentsByClient(int id);

    public List<Document> getDocumentsById(Integer id);

    public List<Clients> findAllClients();

    //public Integer logIn(String user, String password);

    public String loadData(String location);

    public void sendEmails(String subject, Clients client);

    public  void logEmail(Clients client, String data);

    public List<Clients> getClientsForRenewals();

    public Boolean sendSMSToClient(Clients client, String templateType);

    public Boolean logSMS(Clients client,String template,String phoneNumber);

}
