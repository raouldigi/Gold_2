package bean;

public class InfoListaContratti implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    // Fields
    private int n_contratto;
    private String nome;
    private String cognome;
    private String nomedip;
    private String cognome_dip;
    private String data_assunzione;
    private String data_cessaz;
    private Integer mese;
    private Integer anno;
    private String stato;
    private String prospetto_duff;
    private String data_confacli;

    public String getData_confacli() {
        return data_confacli;
    }

    public void setData_confacli(String data_confacli) {
        this.data_confacli = data_confacli;
    }

    public String getProspetto_duff() {
        return prospetto_duff;
    }

    public void setProspetto_duff(String prospetto_duff) {
        this.prospetto_duff = prospetto_duff;
    }

    public InfoListaContratti() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getN_contratto() {
        return n_contratto;
    }

    public void setN_contratto(int n_contratto) {
        this.n_contratto = n_contratto;
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

    public String getNomedip() {
        return nomedip;
    }

    public void setNomedip(String nomedip) {
        this.nomedip = nomedip;
    }

    public String getCognome_dip() {
        return cognome_dip;
    }

    public void setCognome_dip(String cognome_dip) {
        this.cognome_dip = cognome_dip;
    }

    public String getData_assunzione() {
        return data_assunzione;
    }

    public void setData_assunzione(String data_assunzione) {
        this.data_assunzione = data_assunzione;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Integer getMese() {
        return mese;
    }

    public void setMese(Integer mese) {
        this.mese = mese;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }
    
    public String getData_cessaz() {
        return data_cessaz;
    }

    public void setData_cessaz(String data_cessaz) {
        this.data_cessaz = data_cessaz;
    }

}
