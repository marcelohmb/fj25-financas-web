package br.com.caelum.financas.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;

@Named
@RequestScoped
public class MovimentacoesDaContaBean {

	private List<Movimentacao> movimentacoes;
	private Conta conta = new Conta();
	
	@Inject
	private MovimentacaoDao dao;
	
	public void lista() {
		this.movimentacoes = dao.listaTodasMovimentacoes(this.conta);
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public Conta getConta() {
		return this.conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
}
