package telosws.beans;

import java.sql.Blob;

/**
 * Created by karthikmarupeddi on 2/8/15.
 */
public class Document {

    private Integer id;
    private String fileName;
    private String fileDescption;
    private Blob scanned;
    private String url;

    public Document(Integer id, String fileName, String fileDescption, Blob scanned) {
        this.id = id;
        this.fileName = fileName;
        this.fileDescption = fileDescption;
        this.scanned = scanned;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDescption() {
        return fileDescption;
    }

    public void setFileDescption(String fileDescption) {
        this.fileDescption = fileDescption;
    }

    public Blob getScanned() {
        return scanned;
    }

    public void setScanned(Blob scanned) {
        this.scanned = scanned;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
