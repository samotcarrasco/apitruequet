package es.mdef.apitruequet.entidades;


public class Inventariable extends MaterialConId implements es.mde.acing.utils.esInventariable {
	
	private String noc;
    private String numeroSerie;
	
	
    public String getNoc() {
		return noc;
	}

	public void setNoc(String noc) {
		this.noc = noc;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public TipoMaterial getTipoMaterial() {
		return TipoMaterial.Inventariable;
	}

	@Override
	public String toString() {
		return super.toString() + "MATERIAL INVENTARIABLE [TLF" + super.getNombre() +" " +  getNoc() + "]";
	}

}
	