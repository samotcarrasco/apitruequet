package es.mdef.apitruequet.REST;


import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;



@Relation(itemRelation ="material")
public class FechaEntregaModel extends RepresentationModel<FechaEntregaModel>{
	
	
	private LocalDate fechaEntrega;

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	
	
}
