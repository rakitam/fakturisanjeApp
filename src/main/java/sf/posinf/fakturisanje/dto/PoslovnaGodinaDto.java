package sf.posinf.fakturisanje.dto;

import javax.validation.constraints.NotNull;

public class PoslovnaGodinaDto {

	private long id;
    @NotNull
    private int godina;
    @NotNull
    private boolean zakljucana;
    
    public PoslovnaGodinaDto() {
    	
    }

	public PoslovnaGodinaDto(long id, @NotNull int godina, @NotNull boolean zakljucana) {
		super();
		this.id = id;
		this.godina = godina;
		this.zakljucana = zakljucana;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public boolean isZakljucana() {
		return zakljucana;
	}

	public void setZakljucana(boolean zakljucana) {
		this.zakljucana = zakljucana;
	}     
}
