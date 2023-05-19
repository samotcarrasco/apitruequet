package es.mdef.apitruequet.entidades;


import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("P")
public class NoInventariable2 extends Material {
	
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
	