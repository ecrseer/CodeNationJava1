package br.com.codenation;

import br.com.codenation.entidades.Jogador;
import br.com.codenation.entidades.Time;
import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;


public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private ArrayList<Jogador> jogadores = new ArrayList<>();
	private ArrayList<Time> tims = new ArrayList<>();

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
							String corUniformeSecundario) {
		if(tims.stream().anyMatch(tms -> tms.getId().equals(id)))
			throw new IdentificadorUtilizadoException();

		tims.add(new Time(id,nome,dataCriacao,corUniformePrincipal,corUniformeSecundario));

		tims.stream().forEach(ttt->System.out.println(ttt.getNome()));
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento,
							   Integer nivelHabilidade, BigDecimal salario) {

		if(jogadores.stream().anyMatch((jgs) -> jgs.getId().equals(id)))
			throw new IdentificadorUtilizadoException();

		if(tims.stream().noneMatch((tms) -> tms.getId().equals(idTime)))
			throw new TimeNaoEncontradoException();

		jogadores.add(new Jogador(id,idTime,nome,dataNascimento,nivelHabilidade,salario));
	}

	public void definirCapitao(Long idJogador) {

		buscarTime(buscarJoga(idJogador).getIdTime()).setCapitao(idJogador);


	}

	public Long buscarCapitaoDoTime(Long idTime) {

		if(tims.stream().filter(tms -> tms.getId().equals(idTime))
				.anyMatch(cap -> cap.getId().equals(null)))
			throw new CapitaoNaoInformadoException();

		return buscarTime(idTime).getCapitao();
	}

	public String buscarNomeJogador(Long idJogador) {

		if(jogadores.stream().noneMatch(jgs -> jgs.getId().equals(idJogador)))
			throw new JogadorNaoEncontradoException();

		return buscarJoga(idJogador).getNome();
	}

	public String buscarNomeTime(Long idTime) {

		if(tims.stream().noneMatch(tms -> tms.getId().equals(idTime)))
			throw new TimeNaoEncontradoException();

		return buscarTime(idTime).getNome();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {

		if(tims.stream().noneMatch(tms -> tms.getId().equals(idTime)))
			throw new TimeNaoEncontradoException();

		List<Long> dotimme = new ArrayList<>();

			dotimme = jogadores.stream().filter(jgs -> jgs.getIdTime().equals(idTime))
					.map(jgs -> jgs.getId())
					.collect(Collectors.toList());

			return dotimme;


	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	public Long buscarJogadorMaisVelho(Long idTime) {

		//Verifica se time Existe
		buscarTime(idTime);

		Comparator<Jogador> comparando = Comparator.comparing(Jogador::getDataNascimento)
				.thenComparing(Jogador::getId).reversed();

		//desempate Comparator<Jogador> comparandoId = Comparator.comparing(Jogador::getId).reversed();

		Jogador jgs = jogadores.stream().max(comparando).get();

		return jgs.getId();
	}

	public List<Long> buscarTimes() {
		List<Long> idTimes = new ArrayList<>();

		idTimes = tims.stream().mapToLong(tms -> tms.getId()).boxed().collect(Collectors.toList());


		return idTimes;
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		buscarTime(idTime);

		Comparator<Jogador> comparandoSal = Comparator.comparing(Jogador::getSalario);
		List<Jogador> dotimme = new ArrayList<>();

		dotimme = jogadores.stream().filter(jgs -> jgs.getIdTime().equals(idTime))
				.collect(Collectors.toList());

    return dotimme.stream().max(comparandoSal).get().getId();

	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {

		Jogador jog;

			jog = jogadores.stream().filter(jg -> jg.getId().equals(idJogador))
					.findFirst().orElseThrow(JogadorNaoEncontradoException::new);

			return jog.getSalario();

	}

	public List<Long> buscarTopJogadores(Integer top) {
		Comparator<Jogador> habilidoso = Comparator
				.comparingInt(Jogador::getNivelHabilidade)
				.thenComparingLong(Jogador::getIdTime)
				.reversed();

		throw new UnsupportedOperationException();
	}

	protected Jogador buscarJoga(Long id){
		return jogadores.stream().filter(jgs -> jgs.getId().equals(id)).findFirst().
				orElseThrow(JogadorNaoEncontradoException::new);
	}
	protected Time buscarTime(Long id){
		return tims.stream().filter(tms -> tms.getId().equals(id)).
				findFirst().orElseThrow(TimeNaoEncontradoException::new);
	}

}
