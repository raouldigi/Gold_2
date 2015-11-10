package action;

import bean.DatoreDiLavoro;
import bean.Dipendente;
import bean.InfoContratto;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.opensymphony.xwork2.ActionSupport;

import dao.AnagraficheDao;
import dao.DipendenteDao;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class ContrattoAction extends ActionSupport {

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
    private String email; 
    private String cognomedip;
    private String nomedip;
    public String getCognomedip() {
		return cognomedip;
	}

	public void setCognomedip(String cognomedip) {
		this.cognomedip = cognomedip;
	}

	public String getNomedip() {
		return nomedip;
	}

	public void setNomedip(String nomedip) {
		this.nomedip = nomedip;
	}

	private String cognome_dip;
    private String nome_dip;
    private ArrayList<String> sceltaCitta;
    AnagraficheDao AnaDao = new AnagraficheDao();


    
    @Override
    public void validate() {
        if ((nome != null) && (nome.trim().equalsIgnoreCase(""))) {
            addFieldError("nome", "* Inserisci il nome (anche solo una parte)");
        }
        if ((cognome != null) && (cognome.trim().equalsIgnoreCase(""))) {
            addFieldError("cognome", "* Inserisci il cognome");
        }
        if ((nomeddl != null) && (nomeddl.trim().equalsIgnoreCase(""))) {
            addFieldError("nomeddl", "* Inserisci il nome (anche solo una parte)");
        }
        if ((cognomeddl != null) && (cognomeddl.trim().equalsIgnoreCase(""))) {
            addFieldError("cognomeddl", "* Inserisci il cognome");
        }
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
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date assunzione = format.parse(data_assunz);

//            if (!data_cessaz.equalsIgnoreCase("")) {
//                Date cessazione = format.parse(data_cessaz);
//            }
            System.out.println(nome_dip);
            System.out.println(cognome_dip);
            System.out.println(nome);
            System.out.println(cognome);
            System.out.println(indirizzolav);
            System.out.println(citta);
            System.out.println(assunzione);
            System.out.println(giorno_riposo);
            System.out.println(prospetto_duff);
            System.out.println( tipo_contratto);
            System.out.println(categoria);
            System.out.println(livello);
            boolean b = AnagraficheDao.addContratto(nome_dip, cognome_dip, nome, cognome, indirizzolav, cap, citta, assunzione, giorno_riposo, prospetto_duff, tipo_contratto, categoria, livello);
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

    public String viewContratto() {

        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            idcontratto = request.getParameter("n_contratto").trim();

            DipendenteDao contrDao = new DipendenteDao();

//            ArrayList<InfoListaContratti> c = new ArrayList<InfoListaContratti>();
            InfoContratto c = new InfoContratto();
            c = (InfoContratto) contrDao.getContrattobyID(idcontratto);
//            c = (ArrayList<InfoListaContratti>) dip.getListContratti();
            request.setAttribute("contratto", c);

            return SUCCESS;

        } catch (Exception e) {
            return ERROR;
        }
    }

    public String sceltaDatore() {
        
        ListeDdlDipeAction liste = new ListeDdlDipeAction();
        liste.getTabellaDatori();
        
        return SUCCESS;
        
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public ArrayList<String> getSceltaCitta() {
		sceltaCitta = new ArrayList<String>();
		sceltaCitta = AnaDao.getCitta();
		return sceltaCitta;
	}

	public void setSceltaCitta(ArrayList<String> sceltaCitta) {
		this.sceltaCitta = sceltaCitta;
	}

	public String getCognome_dip() {
		return cognome_dip;
	}

	public void setCognome_dip(String cognome_dip) {
		this.cognome_dip = cognome_dip;
	}

	public String getNome_dip() {
		return nome_dip;
	}

	public void setNome_dip(String nome_dip) {
		this.nome_dip = nome_dip;
	}

}
