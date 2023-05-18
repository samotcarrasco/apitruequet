package es.mdef.apitruequet.entidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class Departamento extends es.mde.acing.utils.DepartamentoL{
	
	private Long id;
	
			

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

		@Override
	public String toString() {
		return "Departamento [id=" + getId() + "]";
	}
	
}