package es.mdef.apitruequet.entidades;
import es.mde.acing.utils.MaterialImpl.TipoMaterial;

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
		return "NoInventariable [bonificacion=" + bonificacion + "]";
	}

	

}
	