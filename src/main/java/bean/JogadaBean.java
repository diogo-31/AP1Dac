package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import dao.JogadaDAO;
import entidade.Jogada;

@ManagedBean
public class JogadaBean 
{
	private Jogada jogada = new Jogada();
	private List<Jogada> listaJogada;
	
	public String salvar() 
	{
		try{
			jogada.setJogada1("Pedra");
			jogada.setJogada2("Papel");
			
			if(jogada.getJogada1() == jogada.getJogada2())
				jogada.setResultado("Empate");
			
			
			JogadaDAO.salvar(jogada);
			
			
			
			jogada = new Jogada();
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	public String editar() 
	{
		System.out.println("Editar");
		
		return null;
	}
	
	public String excluir(String idJogada) 
	{
		System.out.println("excluir");
		System.out.println(idJogada);
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

	public void setListaJogada(List<Jogada> listaJogada) {
		this.listaJogada = listaJogada;
	}
}
