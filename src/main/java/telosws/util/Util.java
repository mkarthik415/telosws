package telosws.util;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import telosws.beans.Clients;
import telosws.beans.Document;
import telosws.beans.File;

import javax.servlet.ServletContext;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karthikmarupeddi on 2/8/15.
 */

@Component
public class Util {
    final org.slf4j.Logger logger = LoggerFactory.getLogger(Util.class);

    @Autowired
    ServletContext servletContext;

    public List<File> getDocumentsPath(List<Document> documentsList) throws SQLException, IOException {
        String fileName = null;
        List<File> fileList = new ArrayList<File>();
        for(Document document :documentsList)
        {
            Blob blob = document.getScanned();
            fileName = document.getFileName() ;
            InputStream in = blob.getBinaryStream();
            int fileLength = in.available();

            logger.debug("file creation location is" + System.getenv("OPENSHIFT_DATA_DIR"));

            OutputStream out = new FileOutputStream(System.getenv("OPENSHIFT_DATA_DIR")+"/"+fileName);
            byte[] buff = blob.getBytes(1,fileLength);
            File file = new File();
            file.setFileName(fileName);
            file.setUrl(null);
            fileList.add(file);
            out.write(buff);
            out.close();
        }

        logger.info("location of the file is ==================="+System.getenv("OPENSHIFT_DATA_DIR")+fileName);
        return fileList;
    }

    public static Double decimalVale(Double dValue)
    {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(dValue));
    }


    public static String formateData(Double value)
    {
        DecimalFormat noDecimal = new DecimalFormat("###.#");

        String str = Double.valueOf(value).toString();
        String format = str.substring(str.indexOf(".") + 1);

        if(format.length() ==1 && format.equals("0"))
        {
            return  noDecimal.format(value);
        }
        else
            return  Double.valueOf(value).toString();

    }

    public static String getClientName(Clients client) {
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
        return clientName;
    }
}
