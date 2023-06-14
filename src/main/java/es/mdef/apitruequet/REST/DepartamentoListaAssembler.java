package es.mdef.apitruequet.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mde.acing.utils.DepartamentoImpl;
import es.mdef.apitruequet.entidades.AcuartelamientoConId;
import es.mdef.apitruequet.entidades.DepartamentoConId;

@Component
public class DepartamentoListaAssembler<T extends DepartamentoImpl>
		implements RepresentationModelAssembler<T, DepartamentoListaModel> {

	@Override
	public DepartamentoListaModel toModel(T entity) {
		DepartamentoListaModel model = new DepartamentoListaModel();
		model.setId(((DepartamentoConId) entity).getId());
		model.setAbreviatura(((DepartamentoConId) entity).getAbreviatura());
		model.setNombre(((DepartamentoConId) entity).getNombre());
		model.setAcuartelamientoN(((AcuartelamientoConId) entity.getAcuartelamiento()).getAbreviatura());
		model.setCredito(entity.getCredito());
		model.setEmail(((DepartamentoConId) entity).getEmail());
		model.setTelefono(((DepartamentoConId) entity).getTelefono());
		model.setResponsableNombre(((DepartamentoConId) entity).getResponsableNombre());
		model.setResponsableEmpleo(((DepartamentoConId) entity).getResponsableEmpleo());
		model.setDireccion(((DepartamentoConId) entity).getDireccion());
		model.setLongitud(((DepartamentoConId) entity).getLongitud());
		model.setLatitud(((DepartamentoConId) entity).getLatitud());

		int numMateriales = entity.getMaterialesOfertados() != null || entity.getMaterialesAdquiridos() != null
				? entity.getMaterialesOfertados().size() + entity.getMaterialesAdquiridos().size()
				: 0;
		model.setNumMateriales(numMateriales);

		model.add(linkTo(methodOn(AcuartelamientoController.class)
				.one(((AcuartelamientoConId) entity.getAcuartelamiento()).getId())).withRel("acuartelamiento"));
		model.add(
				linkTo(methodOn(DepartamentoController.class).materialesOfertados(((DepartamentoConId) entity).getId()))
						.withRel("materialesOfertados"));
		model.add(linkTo(
				methodOn(DepartamentoController.class).materialesAdquiridos(((DepartamentoConId) entity).getId()))
				.withRel("materialesAdquiridos"));

		return model;
	}

	public CollectionModel<DepartamentoListaModel> toCollection(List<T> lista) {
		CollectionModel<DepartamentoListaModel> collection = CollectionModel
				.of(lista.stream().map(this::toModel).collect(Collectors.toList()));
		return collection;
	}
}
