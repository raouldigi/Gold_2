package action;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

//import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
//import javax.swing.JDialog;
//import javax.swing.JOptionPane;

import bean.FoglioPresenze;

import com.opensymphony.xwork2.ActionSupport;
import common.RandomString;

import common.Utility;
import dao.FoglioPresenzeDao;
import dao.UpdateFileDao;
import java.util.ResourceBundle;

public class UploadFile extends ActionSupport implements com.opensymphony.xwork2.ValidationAware, ServletRequestAware {

    private static final long serialVersionUID = 1L;

    private File file1;
    private String file1ContentType;
    private String file1FileName;

    private File file2;
    private String file2ContentType;
    private String file2FileName;

    private File file3;
    private String file3ContentType;
    private String file3FileName;

    private File myFile;
    private String myFileContentType;
    private String myFileFileName;

    private String destPath, accessType;

    private HttpServletRequest servletRequest;

    public String execute() {
        
       
        try {
            HttpServletRequest request = ServletActionContext.getRequest();

            HashMap<String, Object> map = (HashMap<String, Object>) Utility.setAttributeForJspFoglioPresenze(request,accessType);

            FoglioPresenze foglio = (FoglioPresenze) map.get("foglioPresenze");
            
            String contratto = (String) map.get("contratto");
            Integer anno = (Integer) map.get("anno");
            Integer mese = (Integer) map.get("mese");
            
            RandomString rs = new RandomString(12);
            if (file1 != null) {
                myFile = file1;
                String codiceRandom = rs.nextString();
                myFileFileName = contratto + "_" + codiceRandom + "_" + file1FileName;
                myFileContentType = file1ContentType;
            } else if (file2 != null) {
                myFile = file2;
                String codiceRandom = rs.nextString();
                myFileFileName = contratto + "_" + codiceRandom + "_" +  file2FileName;
                myFileContentType = file2ContentType;
            } else if (file3 != null) {
                myFile = file3;
                String codiceRandom = rs.nextString();
                myFileFileName = contratto + "_" + codiceRandom + "_" +  file3FileName;
                myFileContentType = file3ContentType;
            } else {
    			//        	JDialog msg = new JDialog();
                //			msg.setAlwaysOnTop(true);
                //            JOptionPane.showMessageDialog(msg, "Errore durante il caricamento del file: dimensione o/e estensione non permesse!!", "Error",
                //                    JOptionPane.ERROR_MESSAGE);

                request.setAttribute("error_message", "Errore durante il caricamento del file: dimensione o/e estensione non permesse!!");

                return (String) map.get("return");
            }

    		// Stoppando tomcat e ripubblicando si perde la dir con i file. Bisognerebbe utilizzare una dir esterna al progetto.
            // Ma in tal caso inserire il path assoluto
//            destPath = servletRequest.getSession().getServletContext().getRealPath("/goldfileDir/"); // tomcatXX/.../goldfileDir
            
            ResourceBundle rb = ResourceBundle.getBundle("globali");
//            destPath = rb.getString("destPath").replace("\\", "\\\\");
            destPath = rb.getString("destPath");
            // System.out.println("Server path:" + destPath);

    		      //      System.out.println("Src File name: " + myFile);
            //      System.out.println("Dst File name: " + myFileFileName);
            File destFile = new File(destPath, myFileFileName);
            myFile.mkdirs();
            FileUtils.copyFile(myFile, destFile);

            if (file1 != null) {
                UpdateFileDao.insertUploadFile(foglio.getIdSituazione_presenze(), myFileFileName, "allegato1");
            } else if (file2 != null) {
//              UpdateFileDao.insertUploadFile(foglio.getIdSituazione_presenze(), destFile.getAbsolutePath().replace("\\", "//"), "allegato2");
                UpdateFileDao.insertUploadFile(foglio.getIdSituazione_presenze(), myFileFileName, "allegato2");
            } else if (file3 != null) {
                UpdateFileDao.insertUploadFile(foglio.getIdSituazione_presenze(), myFileFileName, "allegato3");
                // Inserimento busta paga effettuato --> update data conferma ACLI
                if ("acli".equalsIgnoreCase((String) map.get("return"))) {
                    FoglioPresenzeDao.aggiornaDataConfermaAcli(foglio.getIdSituazione_presenze());
                }
            }

//            String contratto = (String) map.get("contratto");
//            Integer anno = (Integer) map.get("anno");
//            Integer mese = (Integer) map.get("mese");


            String noteBusta = request.getParameter("note_busta");
            String osservazioni = request.getParameter("osservazioni_foglio");
            FoglioPresenzeDao.aggiornaNoteFoglio(foglio.getIdSituazione_presenze(), noteBusta, osservazioni);

    		// Dopo aver aggiornato l'allegato ricarico il foglio aggiornato in request
            // Dopo aver aggiornato le note ricarico il foglio aggiornato in request
            foglio = FoglioPresenzeDao.getFoglioPresenze(Integer.parseInt(contratto), anno, mese);
            request.setAttribute("foglioPresenze", foglio);
    		/////////////////

    		//		JDialog msg = new JDialog();
            //		msg.setAlwaysOnTop(true);
            //        JOptionPane.showMessageDialog(msg, "File caricato Correttamente.", "Information",
            //                JOptionPane.INFORMATION_MESSAGE);
            request.setAttribute("message", "File caricato Correttamente.");

            return (String) map.get("return");

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public File getFile1() {
        return file1;
    }

    public void setFile1(File file1) {
        this.file1 = file1;
    }

    public String getFile1ContentType() {
        return file1ContentType;
    }

    public void setFile1ContentType(String file1ContentType) {
        this.file1ContentType = file1ContentType;
    }

    public String getFile1FileName() {
        return file1FileName;
    }

    public void setFile1FileName(String file1FileName) {
        this.file1FileName = file1FileName;
    }

    /////
    public File getFile2() {
        return file2;
    }

    public void setFile2(File file2) {
        this.file2 = file2;
    }

    public String getFile2ContentType() {
        return file2ContentType;
    }

    public void setFile2ContentType(String file2ContentType) {
        this.file2ContentType = file2ContentType;
    }

    public String getFile2FileName() {
        return file2FileName;
    }

    public void setFile2FileName(String file2FileName) {
        this.file2FileName = file2FileName;
    }

    ////
    public File getFile3() {
        return file3;
    }

    public void setFile3(File file3) {
        this.file3 = file3;
    }

    public String getFile3ContentType() {
        return file3ContentType;
    }

    public void setFile3ContentType(String file3ContentType) {
        this.file3ContentType = file3ContentType;
    }

    public String getFile3FileName() {
        return file3FileName;
    }

    public void setFile3FileName(String file3FileName) {
        this.file3FileName = file3FileName;
    }

//	public File getMyFile() {
//		return myFile;
//	}
//	public void setMyFile(File myFile) {
//		this.myFile = myFile;
//	}
//	public String getMyFileContentType() {
//		return myFileContentType;
//	}
//	public void setMyFileContentType(String myFileContentType) {
//		this.myFileContentType = myFileContentType;
//	}
//	public String getMyFileFileName() {
//		return myFileFileName;
//	}
//	public void setMyFileFileName(String myFileFileName) {
//		this.myFileFileName = myFileFileName;
//	}
    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
}
