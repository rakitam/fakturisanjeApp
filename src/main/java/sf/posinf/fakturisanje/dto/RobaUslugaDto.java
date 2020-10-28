package sf.posinf.fakturisanje.dto;

import javax.validation.constraints.NotNull;


public class RobaUslugaDto {
	
	private Long id;
    @NotNull
	private String nazivRobeUsluge;
    @NotNull
	private String jedinicaMere;
    @NotNull
	private Long grupaRobe;
	
	public RobaUslugaDto() {
		
	}
	
	public RobaUslugaDto(String nazivRobeUsluge, String jedinicaMere, Long grupaRobe) {
		super();
		this.nazivRobeUsluge = nazivRobeUsluge;
		this.jedinicaMere = jedinicaMere;
		this.grupaRobe = grupaRobe;
	}

	public Long getId() {
		return id;
	}

	public RobaUslugaDto setId(Long id) {
		this.id = id;
		return this;
	}

	public String getNazivRobeUsluge() {
		return nazivRobeUsluge;
	}

	public RobaUslugaDto setNazivRobeUsluge(String nazivRobeUsluge) {
		this.nazivRobeUsluge = nazivRobeUsluge;
		return this;
	}

	public String getJedinicaMere() {
		return jedinicaMere;
	}

	public RobaUslugaDto setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
		return this;
	}

	public Long getGrupaRobe() {
		return grupaRobe;
	}

	public RobaUslugaDto setGrupaRobe(Long grupaRobe) {
		this.grupaRobe = grupaRobe;
		return this;
	}
	
}
