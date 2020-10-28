package sf.posinf.fakturisanje.dto;

import com.sun.istack.NotNull;

public class MestoDto {

	private long id;
    @NotNull
	private String naziv;
    @NotNull
	private int postanskiBroj;
    @NotNull
	private String drzava;

	
	public MestoDto() { }
	
	public MestoDto(String naziv, int postanskiBroj, String drzava) {
		super();
		this.naziv = naziv;
		this.postanskiBroj = postanskiBroj;
		this.drzava = drzava;

	}

	public long getId() {
		return id;
	}

	public MestoDto setId(long id) {
		this.id = id;
		return this;
	}

	public String getNaziv() {
		return naziv;
	}

	public MestoDto setNaziv(String naziv) {
		this.naziv = naziv;
		return this;
	}

	public int getPostanskiBroj() {
		return postanskiBroj;
	}

	public MestoDto setPostanskiBroj(int postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
		return this;
	}

	public String getDrzava() {
		return drzava;
	}

	public MestoDto setDrzava(String drzava) {
		this.drzava = drzava;
		return this;
	}
}
