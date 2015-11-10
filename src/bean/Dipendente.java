package bean;

public class Dipendente implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    // Fields
    //private Long id;
    private Integer idanagrafica_dipe;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private String cellulare;
    private int idcontratto;

    public Dipendente() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Dipendente(Integer idanagrafica_dipe, String nome, String cognome, String indirizzo,
            String telefono, String cellulare, int idcontratto) {
        super();
        //this.id = id;
        this.idanagrafica_dipe= idanagrafica_dipe;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.cellulare = cellulare;
        this.setIdcontratto(idcontratto);
    }

    public Integer getIdanagrafica_dipe() {
        return idanagrafica_dipe;
    }

    public void setIdanagrafica_dipe(Integer idanagrafica_dipe) {
        this.idanagrafica_dipe = idanagrafica_dipe;
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

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

//	public String getEmail() {
//		return email;
//	}
//
//
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
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

    public int getIdcontratto() {
        return idcontratto;
    }

    public void setIdcontratto(int idcontratto) {
        this.idcontratto = idcontratto;
    }

}
