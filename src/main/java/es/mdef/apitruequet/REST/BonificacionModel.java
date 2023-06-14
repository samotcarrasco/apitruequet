package es.mdef.apitruequet.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(itemRelation = "material")
public class BonificacionModel extends RepresentationModel<BonificacionModel> {

	private int bonificacion;

	public int getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(int bonificacion) {
		this.bonificacion = bonificacion;
	}

	@Override
	public String toString() {
		return "Bonificacion";
	}

}
