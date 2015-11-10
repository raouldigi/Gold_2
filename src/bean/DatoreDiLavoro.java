package bean;

public class DatoreDiLavoro implements java.io.Serializable {

    // Fields
    private static final long serialVersionUID = 1L;

    private Integer idanagrafica_ddl;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String zona;
    private String telefono;
    private String cellulare;
    private String email;
    private String userid;
    private String password;
    private String tipo_utente;

    public DatoreDiLavoro() {
        super();
        // TODO Auto-generated constructor stub
    }

    public DatoreDiLavoro(Integer idanagrafica_ddl, String nome, String cognome, String indirizzo, String zona, String telefono, String cellulare, String userid, String password, String email, String tipo_utente) {

        super();
        this.idanagrafica_ddl = idanagrafica_ddl;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.zona = zona;
        this.telefono = telefono;
        this.cellulare = cellulare;
        this.userid = userid;
        this.password = password;
        this.email = email;
        this.tipo_utente=tipo_utente;
    }

    public Integer getIdanagrafica_ddl() {
        return idanagrafica_ddl;
    }

    public void setIdanagrafica_ddl(Integer idanagrafica_ddl) {
        this.idanagrafica_ddl = idanagrafica_ddl;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo_utente() {
        return tipo_utente;
    }

    public void setTipo_utente(String tipo_utente) {
        this.tipo_utente = tipo_utente;
    }

    /**
     * Returns the full name of the user in the format: surname name
     */
    public String getFullName() {
        StringBuffer result = new StringBuffer();
        if (cognome != null && cognome.length() > 0) {
            result.append(cognome);
        }
        if (nome != null && nome.length() > 0) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(nome);
        }
        return result.toString();
    }

}
