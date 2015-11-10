package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ResourceBundle;

public class DownloadFileAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    public InputStream pdfDocument;
    private String destPath;
    private String filename;
    private String fileToDownload;

    public String execute() throws Exception {
        filename = fileToDownload;
        ResourceBundle rb = ResourceBundle.getBundle("globali");
        destPath = rb.getString("destPath");
            
        fileToDownload = destPath + fileToDownload;

        //Get it from file system
        File file = new File(fileToDownload);
        pdfDocument = new FileInputStream(file);

        return Action.SUCCESS;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileToDownload() {
        return fileToDownload;
    }

    public void setFileToDownload(String fileToDownload) {
        this.fileToDownload = fileToDownload;
    }

}
