package telosws.util;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

/**
 * Created by karthikmarupeddi on 6/1/15.
 */

@Component
@PropertySources(value = {@PropertySource("classpath:application.properties")})
public class S3CloudConnector {

    final org.slf4j.Logger logger = LoggerFactory.getLogger(S3CloudConnector.class);

    @Value( "${S3_ACCESS_KEY}" )
    private String S3_ACCESS_KEY;

    @Value( "${S3_SECRET_KEY}" )
    private String S3_SECRET_KEY;

    private  AmazonS3 s3Connection;

    public AmazonS3 getS3Connection() {
        return s3Connection;
    }

    public void setS3Connection(AmazonS3 s3Connection) {
        this.s3Connection = s3Connection;
    }


    public void getConnection()
    {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(S3_ACCESS_KEY, S3_SECRET_KEY);
        AmazonS3 s3Client = new AmazonS3Client(awsCreds);
        //AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());
        setS3Connection(s3Client);

    }

    public String getBuckets()
    {
        return getS3Connection().getBucketLocation("teloshyd");
    }
}
