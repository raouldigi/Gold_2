package bean;

public class User implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    // Fields
    private Long id;
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String email;
    private String tipoUtente;

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(Long id, String nome, String cognome, String username,
            String password, String email, String tipoUtente) {
        super();
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tipoUtente = tipoUtente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoUtente() {
        return tipoUtente;
    }

    public void setTipoUtente(String tipoUtente) {
        this.tipoUtente = tipoUtente;
    }

}
