package es.mdef.apitruequet.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mde.acing.utils.Acuartelamiento;
import es.mde.acing.utils.Departamento;
import es.mde.acing.utils.Unidad;
import es.mde.acing.utils.UnidadImpl;
import es.mdef.apitruequet.entidades.AcuartelamientoConId;
import es.mdef.apitruequet.entidades.DepartamentoConId;


@Component
public class AcuartelamientoListaAssembler <T extends Acuartelamiento> implements RepresentationModelAssembler<T, AcuartelamientoModel>{

	
	@Override
	public AcuartelamientoModel toModel(T entity) {
		AcuartelamientoModel model = new AcuartelamientoModel();
		model.setId(((AcuartelamientoConId) entity).getId());
		model.setAbreviatura(((AcuartelamientoConId) entity).getAbreviatura());
		model.setNombre(((AcuartelamientoConId) entity).getNombre());
		model.setEmail(((AcuartelamientoConId) entity).getEmail());
		model.setTelefono(((AcuartelamientoConId) entity).getTelefono());
		model.setResponsableNombre(((AcuartelamientoConId) entity).getResponsableNombre());
		model.setResponsableEmpleo(((AcuartelamientoConId) entity).getResponsableEmpleo());
		model.setDireccion(((AcuartelamientoConId) entity).getDireccion());
		model.setLongitud(((AcuartelamientoConId) entity).getLongitud());
		model.setLatitud(((AcuartelamientoConId) entity).getLatitud());
		
//		int numMateriales = 0;
//		System.out.println("materiales" + numMateriales);
//		if (entity.getDepartamentos() != null) {
//			for (Departamento departamento : entity.getDepartamentos()) {
//		    numMateriales += departamento.getMaterialesOfertados().size() +  departamento.getMaterialesAdquiridos().size();
//			}
//		}
		
		//int numDptos = entity.getDepartamentos().size();

		model.setNumDepartamentos(0);
		model.setNumMateriales(0);
		model.add(linkTo(methodOn(AcuartelamientoController.class).one(((AcuartelamientoConId) entity).getId())).withSelfRel());
		model.add(linkTo(methodOn(AcuartelamientoController.class).departamentos(((AcuartelamientoConId) entity).getId())).withRel("departamentos"));
				
		return model;
	}
	
	

	public CollectionModel<AcuartelamientoModel> toCollection(List<T> lista) {
		CollectionModel<AcuartelamientoModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);		
		return collection;
	}
}
