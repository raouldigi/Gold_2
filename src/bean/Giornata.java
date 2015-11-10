package bean;

import java.util.Date;


public class Giornata implements java.io.Serializable {

	// Fields
	
	private static final long serialVersionUID = 1L;
	
	private Long idfogli_presenze;
	private String lav_ord;
	private String lav_straord;
	private String ferie;
	private String perm_retrib;
	private String perm_non_retrib;
	private String malattia;
	private String assenza;
	private Long idcontratto;
	private Long idsituazione_presenze;
	private Date data_presenza;
	

	public Giornata() {
		super();
		// TODO Auto-generated constructor stub
        }   
 
	public Giornata(Long idfogli_presenze, String lav_ord, String lav_straord,
			String ferie, String perm_retrib, String perm_non_retrib, String malattia,
			String assenza, Long idcontratto, Long idsituazione_presenze) {
		super();
		this.idfogli_presenze = idfogli_presenze;
		this.lav_ord = lav_ord;
		this.lav_straord = lav_straord;
		this.ferie = ferie;
		this.perm_retrib = perm_retrib;
		this.perm_non_retrib = perm_non_retrib;
		this.malattia = malattia;
		this.assenza = assenza;
		this.idcontratto = idcontratto;
		this.idsituazione_presenze = idsituazione_presenze;
	}

	public Giornata(Long idfogli_presenze, String lav_ord, String lav_straord,
			String ferie, String perm_retrib, String perm_non_retrib, String malattia,
			String assenza, Long idcontratto, Long idsituazione_presenze,
			Date data_presenza) {
		super();
		this.idfogli_presenze = idfogli_presenze;
		this.lav_ord = lav_ord;
		this.lav_straord = lav_straord;
		this.ferie = ferie;
		this.perm_retrib = perm_retrib;
		this.perm_non_retrib = perm_non_retrib;
		this.malattia = malattia;
		this.assenza = assenza;
		this.idcontratto = idcontratto;
		this.idsituazione_presenze = idsituazione_presenze;
		this.data_presenza = data_presenza;
	}




	public Long getIdfogli_presenze() {
		return idfogli_presenze;
	}

	public void setIdfogli_presenze(Long idfogli_presenze) {
		this.idfogli_presenze = idfogli_presenze;
	}

	public String getLav_ord() {
		return lav_ord;
	}

	public void setLav_ord(String lav_ord) {
		this.lav_ord = lav_ord;
	}

	public String getLav_straord() {
		return lav_straord;
	}

	public void setLav_straord(String lav_straord) {
		this.lav_straord = lav_straord;
	}

	public String getFerie() {
		return ferie;
	}

	public void setFerie(String ferie) {
		this.ferie = ferie;
	}

	public String getPerm_retrib() {
		return perm_retrib;
	}

	public void setPerm_retrib(String perm_retrib) {
		this.perm_retrib = perm_retrib;
	}

	public String getPerm_non_retrib() {
		return perm_non_retrib;
	}

	public void setPerm_non_retrib(String perm_non_retrib) {
		this.perm_non_retrib = perm_non_retrib;
	}

	public String getMalattia() {
		return malattia;
	}

	public void setMalattia(String malattia) {
		this.malattia = malattia;
	}

	public String getAssenza() {
		return assenza;
	}

	public void setAssenza(String assenza) {
		this.assenza = assenza;
	}

	public Long getIdcontratto() {
		return idcontratto;
	}

	public void setIdcontratto(Long idcontratto) {
		this.idcontratto = idcontratto;
	}

	public Long getIdsituazione_presenze() {
		return idsituazione_presenze;
	}

	public void setIdsituazione_presenze(Long idsituazione_presenze) {
		this.idsituazione_presenze = idsituazione_presenze;
	}




	public Date getData_presenza() {
		return data_presenza;
	}

	public void setData_presenza(Date data_presenza) {
		this.data_presenza = data_presenza;
	}

}
