package bean;

public class InfoContratto implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    // Fields
    private String idcontratto;
    private String nomeddl;
    private String cognomeddl;
    private String nome;
    private String cognome;
    private String indirizzolav;
    private String data_assunz;
    private String data_cessaz;
    private String prospetto_duff;
    private Integer giorno_riposo;
    private String categoria;
    private String tipo_contratto;
    private String livello;
    private String citta;


     public InfoContratto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getIdcontratto() {
        return idcontratto;
    }

    public void setIdcontratto(String idcontratto) {
        this.idcontratto = idcontratto;
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

    public String getIndirizzolav() {
        return indirizzolav;
    }

    public void setIndirizzolav(String indirizzolav) {
        this.indirizzolav = indirizzolav;
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

    public String getProspetto_duff() {
        return prospetto_duff;
    }

    public void setProspetto_duff(String prospetto_duff) {
        this.prospetto_duff = prospetto_duff;
    }

    public Integer getGiorno_riposo() {
        return giorno_riposo;
    }

    public void setGiorno_riposo(Integer giorno_riposo) {
        this.giorno_riposo = giorno_riposo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo_contratto() {
        return tipo_contratto;
    }

    public void setTipo_contratto(String tipo_contratto) {
        this.tipo_contratto = tipo_contratto;
    }

    public String getLivello() {
        return livello;
    }

    public void setLivello(String livello) {
        this.livello = livello;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    
}
