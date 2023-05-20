package es.mdef.apitruequet.entidades;



public class NoInventariable extends MaterialConId  implements es.mde.acing.utils.esNoInvetnariable{
	
	private int bonificacion;
    
	public TipoMaterial getTipoMaterial() {
		return TipoMaterial.noInventariable;
	}

	public int getBonificacion() {
		return bonificacion;
	}



	public void setBonificacion(int bonificacion) {
		this.bonificacion = bonificacion;
	}



	@Override
	public String toString() {
		return super.toString() + "MATERIAL INVENTARIABLE [TLF" + super.getNombre() +" " +  getBonificacion() + "]";
	}

}
	