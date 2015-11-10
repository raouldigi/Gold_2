package bean;


public class Contratto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	// Fields
	private Long id;
	private String data_assunz;
	private String data_cessaz;
	private int giorno_riposo;
	private String prospetto_duff;


	public Contratto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contratto(Long id, String data_assunz, String data_cessaz,
			int giorno_riposo, String prospetto_duff) {
		super();
		this.id = id;
		this.data_assunz = data_assunz;
		this.data_cessaz = data_cessaz;
		this.giorno_riposo = giorno_riposo;
		this.prospetto_duff = prospetto_duff;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getGiorno_riposo() {
		return giorno_riposo;
	}

	public void setGiorno_riposo(int giorno_riposo) {
		this.giorno_riposo = giorno_riposo;
	}

	public String getProspetto_duff() {
		return prospetto_duff;
	}

	public void setProspetto_duff(String prospetto_duff) {
		this.prospetto_duff = prospetto_duff;
	}
	
	
	
}
