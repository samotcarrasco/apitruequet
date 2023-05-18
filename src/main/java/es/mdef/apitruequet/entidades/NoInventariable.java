package es.mdef.apitruequet.entidades;



public class NoInventariable extends Material  implements es.mde.acing.utils.esNoInvetnariable{
	
	private int bonificacion;
    


	public int getBonificacion() {
		return bonificacion;
	}



	public void setBonificacion(int bonificacion) {
		this.bonificacion = bonificacion;
	}

	public TipoMaterial getTipoMaterial() {
		return TipoMaterial.noInventariable;
	}

	@Override
	public String toString() {
		return super.toString() + "MATERIAL INVENTARIABLE [TLF" + super.getNombre() +" " +  getBonificacion() + "]";
	}

}
	