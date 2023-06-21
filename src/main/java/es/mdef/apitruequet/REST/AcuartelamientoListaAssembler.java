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
import es.mdef.apitruequet.entidades.AcuartelamientoConId;
import es.mdef.apitruequet.entidades.DepartamentoConId;

@Component
public class AcuartelamientoListaAssembler<T extends Acuartelamiento>
		implements RepresentationModelAssembler<T, AcuartelamientoModel> {

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

		int numMateriales = 0;
		if (entity.getDepartamentos() != null) {
			for (Departamento departamento : entity.getDepartamentos()) {
				numMateriales += ((DepartamentoConId) departamento).getNumMateriales();
			}
		}

		int numDptos = entity.getDepartamentos() != null ? entity.getDepartamentos().size() : 0;

		model.setNumDepartamentos(numDptos);
		model.setNumMateriales(numMateriales);
		model.add(linkTo(methodOn(AcuartelamientoController.class).one(((AcuartelamientoConId) entity).getId()))
				.withSelfRel());
		model.add(
				linkTo(methodOn(AcuartelamientoController.class).departamentos(((AcuartelamientoConId) entity).getId()))
						.withRel("departamentos"));

		return model;
	}

	public CollectionModel<AcuartelamientoModel> toCollection(List<T> lista) {
		CollectionModel<AcuartelamientoModel> collection = CollectionModel
				.of(lista.stream().map(this::toModel).collect(Collectors.toList()));
		return collection;
	}
}
