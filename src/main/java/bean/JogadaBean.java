package bean;

import static util.MessageUtil.addErrorMessage;
import static util.MessageUtil.addInfoMessage;
import static util.MessageUtil.addWarningMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;

import dao.JogadaDAO;
import entidade.Jogada;

@ManagedBean
public class JogadaBean 
{
	private Jogada jogada = new Jogada();
	private List<Jogada> listaJogada;
	private Jogada jogadaUnica;
	
	public String salvar() 
	{
		try{
			List<String> jogadasPossiveis = Arrays.asList("Pedra", "Papel", "Tesoura");
			
			jogada.setJogada1(jogadasPossiveis.get(new Random().nextInt(jogadasPossiveis.size())));
			jogada.setJogada2(jogadasPossiveis.get(new Random().nextInt(jogadasPossiveis.size())));
			
			System.out.println(jogada.getJogada1() +" - "+ jogada.getJogada2());
			if(jogada.getJogada1() == jogada.getJogada2()){
				jogada.setResultado("Empate");
			}else if(jogada.getJogada1() == "Pedra" && jogada.getJogada2() == "Papel" ) {
				jogada.setResultado("Jogador 2 ganhou!");
			}else if(jogada.getJogada1() == "Pedra" && jogada.getJogada2() == "Tesoura" ) {
				jogada.setResultado("Jogador 1 ganhou!");
			}else if(jogada.getJogada2() == "Pedra" && jogada.getJogada1() == "Papel" ) {
				jogada.setResultado("Jogador 1 ganhou!");
			}else if(jogada.getJogada2() == "Pedra" && jogada.getJogada1() == "Tesoura" ) {
				jogada.setResultado("Jogador 1 ganhou!");
			}else if(jogada.getJogada1() == "Tesoura" && jogada.getJogada2() == "Papel"){
				jogada.setResultado("Jogador 1 ganhou!");
			}else if(jogada.getJogada1() == "Papel" && jogada.getJogada2() == "Tesoura"){
				jogada.setResultado("Jogador 2 ganhou!");
			}else {
				System.out.println("nenhuma opção encontrada");
				System.out.println("jogada.getJogada2()"+jogada.getJogada2()+"jogada.getJogada1()"+jogada.getJogada1());
			}

			JogadaDAO.salvar(jogada);
			
			if(jogada.getResultado() == "Empate") {
				addWarningMessage(jogada.getResultado(), "Opa! Vamo mais uma?");
			}else {
				addInfoMessage(jogada.getResultado(),"Hoje é seu dia de sorte!");
			}
				
			jogada = new Jogada();
		}catch (Exception e) {
			System.out.println(e);
			addErrorMessage("Erro", "Erro ao salvar jogada.");
		}
		
		return null;
	}
	
	public String editarJogada() 
	{
		try {
			System.out.println("Editar");
			System.out.println(jogadaUnica);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e);
		}
		
		
		return null;
	}
	
	public String excluir() 
	{
		try {
			JogadaDAO.excluir(getJogadaUnica());
		} catch (Exception e) {
			System.out.println(e);
			addErrorMessage("Erro", "Erro ao excluir jogada.");
		}
		return null;
	}

	public Jogada getJogada() {
		return jogada;
	}

	public void setJogada(Jogada jogada) {
		this.jogada = jogada;
	}
	
	public List<Jogada> getListaJogada() {
		if (listaJogada == null) {
			listaJogada = JogadaDAO.listar();
		}
		return listaJogada;
	}

	public Jogada getJogadaUnica() {
		return jogadaUnica;
	}

	public void setJogadaUnica(Jogada jogadaUnica) {
		this.jogadaUnica = jogadaUnica;
	}
}
