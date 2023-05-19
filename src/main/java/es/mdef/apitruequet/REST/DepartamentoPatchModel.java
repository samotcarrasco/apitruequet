package es.mdef.apitruequet.REST;



import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@Relation(collectionRelation = "departamentos")
public class DepartamentoPatchModel extends RepresentationModel<CategoriaModel>{
	
	private int credito;
	
	public int getCredito() {
		return this.credito;
	}
	
		
	
}
