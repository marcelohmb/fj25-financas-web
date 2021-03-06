package br.com.caelum.financas.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Categoria;

@Stateless
public class CategoriaDao implements Serializable{


	private static final long serialVersionUID = 1L;

	@Inject //@PersistenceContext
	private EntityManager manager;
	
	public Categoria procura(Integer id){
		return this.manager.find(Categoria.class, id);
	}
	
	public List<Categoria> lista(){
		return this.manager.createQuery("select c from Categoria c", Categoria.class)
				.getResultList();
	}
}
