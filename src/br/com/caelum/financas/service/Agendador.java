package br.com.caelum.financas.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import br.com.caelum.financas.dao.ContaDao;

//@Stateless
@Singleton
@AccessTimeout(unit = TimeUnit.SECONDS, value=5)
public class Agendador {

	@EJB
	private ContaDao contaDao;
	
	private static int totalCriado;
	
	@Resource
	private TimerService timerService;
	
	public void agenda(String expressaoMinutos, String expressaoSegundos){
		ScheduleExpression expression = new ScheduleExpression();
		expression.hour("*");
		expression.minute(expressaoMinutos);
		expression.second(expressaoSegundos);
		
		TimerConfig config = new TimerConfig();
		config.setInfo(expression.toString());
		config.setPersistent(false);
		
		this.timerService.createCalendarTimer(expression, config);
		
		System.out.println("Agendamento: " + expression);
	}

	@Timeout
	public void verificacaoPeriodicaSeHaNovasContas(Timer timer){
		System.out.println(timer.getInfo());
		System.out.println(contaDao.lista()); 
	} 
	
	
	public void executa() {
		System.out.printf("%d instancias criadas %n", totalCriado);

		// simulando demora de 4s na execucao
		try {
			System.out.printf("Executando %s %n", this);
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
	}
	
	@PostConstruct
	void posConstrucao(){
		System.out.println("criando agendador");
		totalCriado++;
	}

	@PreDestroy
	void preDestruicao(){
		System.out.println("destruindo agendador");
	}
}
