package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import bean.Dipendente;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.AnagraficheDao;
import dao.DipendenteDao;

public class AnagraficaDipeAction extends ActionSupport {

    private Integer idanagrafica_dipe;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private String cellulare;

    public void validate() {
        if ((nome != null) && (nome.trim().equalsIgnoreCase(""))) {
            addFieldError("nome", "* Inserisci il nome");
        }
        if ((cognome != null) && (cognome.trim().equalsIgnoreCase(""))) {
            addFieldError("cognome", "* Inserisci il cognome");
        }

        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            DipendenteDao dip = new DipendenteDao();
            ArrayList<Dipendente> d = new ArrayList<Dipendente>();
            d = (ArrayList<Dipendente>) dip.getDipendenteList();
            request.setAttribute("dipendenti", d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String execute() {
        try {
//            Dipendente dip = new Dipendente();
//            
//            dip.setIdanagrafica_dipe(idanagrafica_dipe);
//            dip.setNome(nome.trim());
//            dip.setCognome(cognome.trim());
//            dip.setIndirizzo(indirizzo.trim());
//            
//            dip.setTelefono(telefono.trim());
//            dip.setCellulare(cellulare.trim());

            if (!AnagraficheDao.addDipendente(nome, cognome, indirizzo, telefono, cellulare)) {
                return SUCCESS; // RIGA MODIFICATA
            }
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

//    public String execute() {
//        try {
//            
//            if (!AnagraficheDao.addDipendente(nome, cognome, indirizzo, telefono, cell)) {
//                return SUCCESS; // RIGA MODIFICATA
//            }
//            return ERROR;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ERROR;
//        }
//
//    }
    public String editDipendente() {

        return SUCCESS;
    }

    public String okEditDipendente() throws Exception {
        try {
//            Dipendente dp = new Dipendente(idanagrafica_dipe, nome, cognome, indirizzo, telefono, cellulare);
            if (!AnagraficheDao.updateDipendente(nome, cognome, indirizzo, telefono, cellulare, idanagrafica_dipe)) {
                return SUCCESS; // RIGA MODIFICATA
            }
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getIdanagrafica_dipe() {
        return idanagrafica_dipe;
    }

    public void setIdanagrafica_dipe(Integer idanagrafica_dipe) {
        this.idanagrafica_dipe = idanagrafica_dipe;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

}
