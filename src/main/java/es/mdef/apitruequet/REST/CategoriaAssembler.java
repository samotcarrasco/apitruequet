package es.mdef.apitruequet.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import es.mdef.apitruequet.entidades.CategoriaConId;

@Component
public class CategoriaAssembler implements RepresentationModelAssembler<CategoriaConId, CategoriaModel> {

	@Override
	public CategoriaModel toModel(CategoriaConId entity) {
		CategoriaModel model = new CategoriaModel();
		model.setCategoria(entity.getCategoria());
		model.setDescripcion(entity.getDescripcion());
		model.setId(((CategoriaConId) entity).getId());
		if (entity.getCategoriaPadre() != null) {
			model.setGrupo(entity.getCategoriaPadre().getCategoria());
		}
		int minMilis = entity.getMinMilis() > 0 ? entity.getMinMilis() : 0;
		model.setMinMilis(minMilis);
		int maxMilis = entity.getMaxMilis() > 0 ? entity.getMaxMilis() : 0;
		model.setMaxMilis(maxMilis);

		// devolvemos el numero de materiales que tiene la categoria
		//int numMateriales = entity.getMateriales() != null ? entity.getMateriales().size() : 0;

		model.setNumMateriales(entity.getNumMateriales());

		model.add(linkTo(methodOn(CategoriaController.class).one(((CategoriaConId) entity).getId())).withSelfRel(),
				linkTo(methodOn(CategoriaController.class).materialesDeCategoria(entity.getId()))
						.withRel("materiales"));

		if (entity.getCategoriaPadre() != null) {
			model.add(linkTo(
					methodOn(CategoriaController.class).one(((CategoriaConId) entity.getCategoriaPadre()).getId()))
					.withRel("categoriaPadre"));
		}
		return model;
	}

	public CategoriaConId toEntity(CategoriaPostModel model) {
		CategoriaConId categoria = new CategoriaConId();
		categoria.setCategoria(model.getCategoria());
		categoria.setDescripcion(model.getDescripcion());
		categoria.setCategoriaPadre(model.getCategoriaPadre());
		categoria.setMinMilis(model.getMinMilis());
		categoria.setMaxMilis(model.getMaxMilis());
		categoria.setNumMateriales(0);
		return categoria;
	}
}
