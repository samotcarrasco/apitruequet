package es.mdef.apitruequet.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mde.acing.utils.Categoria;
import es.mdef.apitruequet.entidades.CategoriaConId;

@Component
public class CategoriaListaAssembler<T extends Categoria>
		implements RepresentationModelAssembler<T, CategoriaListaModel> {

	@Override
	public CategoriaListaModel toModel(T entity) {
		CategoriaListaModel model = new CategoriaListaModel();
		model.setCategoria(entity.getCategoria());
		model.setDescripcion(entity.getDescripcion());
		model.setId(((CategoriaConId) entity).getId());
		model.setMinMilis(entity.getMinMilis());
		model.setMaxMilis(entity.getMaxMilis());
		if (entity.getCategoriaPadre() != null) {
			model.setGrupo(entity.getCategoriaPadre().getCategoria());
		}
		int numMateriales = entity.getMateriales() != null ? entity.getMateriales().size() : 0;
		model.setNumMateriales(numMateriales);

		model.add(linkTo(methodOn(CategoriaController.class).one(((CategoriaConId) entity).getId())).withSelfRel());
		if (entity.getCategoriaPadre() != null) {
			model.add(linkTo(
					methodOn(CategoriaController.class).one(((CategoriaConId) entity.getCategoriaPadre()).getId()))
					.withRel("categoriaPadre"));
		}
		return model;
	}

	public CollectionModel<CategoriaListaModel> toCollection(List<T> lista) {
		CollectionModel<CategoriaListaModel> collection = CollectionModel
				.of(lista.stream().map(this::toModel).collect(Collectors.toList()));
		return collection;
	}
}
