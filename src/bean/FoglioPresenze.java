package bean;

import java.util.Date;


public class FoglioPresenze implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	// Fields
	
	private Long idSituazione_presenze;
	private int mese;
	private int anno;
	private String stato;
	private Date data_conferma_ddl;
	private Date data_conferma_acli;
	private int cud;
	private String osservazioni;
	private String noteBusta;
	private String[] allegati;
	private Long idcontratto;

	public FoglioPresenze() {
		super();
		// TODO Auto-generated constructor stub
        }
	

	public int getMese() {
		return mese;
	}

	public void setMese(int mese) {
		this.mese = mese;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String string) {
		this.stato = string;
	}

	public Date getData_conferma_ddl() {
		return data_conferma_ddl;
	}

	public void setData_conferma_ddl(Date data_conferma_ddl) {
		this.data_conferma_ddl = data_conferma_ddl;
	}

	public Date getData_conferma_acli() {
		return data_conferma_acli;
	}

	public void setData_conferma_acli(Date data_conferma_acli) {
		this.data_conferma_acli = data_conferma_acli;
	}

	public int getCud() {
		return cud;
	}

	public void setCud(int cud) {
		this.cud = cud;
	}

	public String getOsservazioni() {
		return osservazioni;
	}

	public void setOsservazioni(String osservazioni) {
		this.osservazioni = osservazioni;
	}

	public Long getIdcontratto() {
		return idcontratto;
	}

	public void setIdcontratto(Long idcontratto) {
		this.idcontratto = idcontratto;
	}



	public Long getIdSituazione_presenze() {
		return idSituazione_presenze;
	}



	public void setIdSituazione_presenze(Long idSituazione_presenze) {
		this.idSituazione_presenze = idSituazione_presenze;
	}



	public String getNoteBusta() {
		return noteBusta;
	}



	public void setNoteBusta(String noteBusta) {
		this.noteBusta = noteBusta;
	}



	public String[] getAllegati() {
		return allegati;
	}



	public void setAllegati(String[] allegati) {
		if (allegati!=null && allegati.length>0){
			for (int i = 0; i < allegati.length; i++) {
				String fileName= "< VUOTO >";
				if(allegati[i]!=null){
					int len = allegati[i].split("//").length - 1;
					fileName = ((String []) allegati[i].split("//"))[len];
//					fileName = allegati[i];
				}
				allegati[i]= fileName;
			}
		}
		this.allegati = allegati;
	}   
        
        
	

}
