package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.GerenteDao;
import br.com.caelum.financas.modelo.Gerente;

@Named
@ViewScoped
public class GerentesBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	public Gerente getGerente() {
		return this.gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}

	@Inject
	private GerenteDao gerenteDao;
	
	private List<Gerente> gerentes;
	
	private Gerente gerente = new Gerente();

	private void limpaFormularioDoJSF() {
		this.gerente = new Gerente();
	}
	
	public List<Gerente> getGerentes(){
		if(this.gerentes == null){
			this.gerentes = gerenteDao.lista();
		}
		
		return this.gerentes;
	}
	
	public void grava(){
		if (this.gerente.getId()==null) {
			gerenteDao.adiciona(this.gerente);
		}
		else
		{
			gerenteDao.altera(this.gerente);
		}
		this.gerentes = gerenteDao.lista();
		
		limpaFormularioDoJSF();
	}
	
	public void remove(){
		gerenteDao.remove(this.gerente);
		
		this.gerentes = gerenteDao.lista();
		limpaFormularioDoJSF();
	}

}
