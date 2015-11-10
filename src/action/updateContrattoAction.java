/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import bean.InfoContratto;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.AnagraficheDao;
import dao.DipendenteDao;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author gminardi
 */
public class updateContrattoAction extends ActionSupport {

    private String idcontratto;
    private String nome;
    private String cognome;
    private String nomeddl;
    private String cognomeddl;
    private String indirizzolav;
    private String cap;
    private String citta;
    private String data_assunz;
    private String data_cessaz;
    private String giorno_riposo;
    private String prospetto_duff;
    private String tipo_contratto;
    private String categoria;
    private String livello;

    public void validate() {
       if ((citta != null) && (citta.trim().equalsIgnoreCase(""))) {
            addFieldError("citta", "* Inserisci la città");
        }
        if ((data_assunz != null) && (data_assunz.trim().equalsIgnoreCase(""))) {
            addFieldError("data_assunz", "* Inserisci la Data di Assunzione");
        }
        if ((prospetto_duff != null) && (prospetto_duff.trim().equalsIgnoreCase(""))) {
            addFieldError("prospetto_duff", "* Inserisci la descrizione");
        }

        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            idcontratto = request.getParameter("idcontratto");

            DipendenteDao contrDao = new DipendenteDao();
            InfoContratto c = new InfoContratto();
            c = (InfoContratto) contrDao.getContrattobyID(idcontratto);

           request.setAttribute("contratto", c);

        } catch (Exception e) {

        }

    }

    public String execute() {

        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date data_ass = format.parse(data_assunz);
//            Date data_cess = format.parse(data_cessaz);
            Date data_cess = null;

            if (data_cessaz != null && !data_cessaz.isEmpty()) {
                data_cess = format.parse(data_cessaz);
            }

            boolean b = AnagraficheDao.updateContratto(indirizzolav, cap, citta, data_ass, data_cess, giorno_riposo, prospetto_duff, tipo_contratto, categoria, livello, idcontratto);
            if (!b) {
                return SUCCESS;
            } else {
                return ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String getIdcontratto() {
        return idcontratto;
    }

    public void setIdcontratto(String idcontratto) {
        this.idcontratto = idcontratto;
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

    public String getNomeddl() {
        return nomeddl;
    }

    public void setNomeddl(String nomeddl) {
        this.nomeddl = nomeddl;
    }

    public String getCognomeddl() {
        return cognomeddl;
    }

    public void setCognomeddl(String cognomeddl) {
        this.cognomeddl = cognomeddl;
    }

    public String getIndirizzolav() {
        return indirizzolav;
    }

    public void setIndirizzolav(String indirizzolav) {
        this.indirizzolav = indirizzolav;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getData_assunz() {
        return data_assunz;
    }

    public void setData_assunz(String data_assunz) {
        this.data_assunz = data_assunz;
    }

    public String getData_cessaz() {
        return data_cessaz;
    }

    public void setData_cessaz(String data_cessaz) {
        this.data_cessaz = data_cessaz;
    }

    public String getGiorno_riposo() {
        return giorno_riposo;
    }

    public void setGiorno_riposo(String giorno_riposo) {
        this.giorno_riposo = giorno_riposo;
    }

    public String getProspetto_duff() {
        return prospetto_duff;
    }

    public void setProspetto_duff(String prospetto_duff) {
        this.prospetto_duff = prospetto_duff;
    }

    public String getTipo_contratto() {
        return tipo_contratto;
    }

    public void setTipo_contratto(String tipo_contratto) {
        this.tipo_contratto = tipo_contratto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLivello() {
        return livello;
    }

    public void setLivello(String livello) {
        this.livello = livello;
    }

}
