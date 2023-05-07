package es.mdef.apitruequet.REST;



import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.apitruequet.entidades.Departamento.TipoAcuartelamiento;
import es.mdef.apitruequet.entidades.Departamento.TipoEmpleo;



@Relation(collectionRelation = "departamentos")
public class DepartamentoPatchModel extends RepresentationModel<CategoriaModel>{
	
	private int credito;
	
	public int getCredito() {
		return this.credito;
	}
	
		
	
}
