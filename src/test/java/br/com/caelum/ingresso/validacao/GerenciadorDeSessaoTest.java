package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessaoTest {
	private Filme filme;
	private Sala sala;
	private Sessao sessaoDasDez;
	private Sessao sessaoDasTreze;
	
	@Before
	public void preparaSessoes() {
		this.filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", BigDecimal.ONE);
		this.sala = new Sala("Sala 3D", BigDecimal.TEN);
		
		this.sessaoDasDez = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		this.sessaoDasTreze = new Sessao(LocalTime.parse("13:00:00"), sala, filme);
		
	}

	

	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorSessao gerenciador = new GerenciadorSessao(sessoes);
		
		Assert.assertFalse(gerenciador.cabe(sessaoDasDez));
		
	}
	
	@Test
	public void garanteQueNaoDevePermitirTerminandoDentroDoHorarioDeSessaoExistente() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		Sessao sessao = new Sessao(sessaoDasDez.getHorario().minusHours(1), sala, filme);
		GerenciadorSessao gerenciador = new GerenciadorSessao(sessoes);
		
		Assert.assertFalse(gerenciador.cabe(sessao));
		
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessaoIniciandDentroDoHorarioDeUmaSessaoExistente() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		Sessao sessao = new Sessao(sessaoDasTreze.getHorario().minusHours(2), sala, filme);
		GerenciadorSessao gerenciador = new GerenciadorSessao(sessoes);
		
		Assert.assertFalse(gerenciador.cabe(sessao));
		
	}




}
