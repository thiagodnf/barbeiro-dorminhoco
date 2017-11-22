package br.uece.ppc.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Barbearia extends JPanel {
	
	private int TAMANHO_FILA;
		
	public ArrayList<Integer> filaAtendimento = new ArrayList<Integer>();
	public ArrayList<Integer> filaCabeloCortado = new ArrayList<Integer>();
			
	public boolean mutex_atendimento = false;
	public boolean mutex_cabeloCortado = false;
	
	private int totalClientesQueChegaram = 0;
	public  int totalClientesEsperados;		
		
	public Barbearia(int tamanhoDaFila,int quantidadeDeClientesEsperado)
	{			
		this.TAMANHO_FILA = tamanhoDaFila;
		this.totalClientesEsperados = quantidadeDeClientesEsperado;
		setSize(800, 600);		
	}
	
	public boolean colocarNaFila(int id) throws InterruptedException
	{
		boolean colocouNaFila = false;
		
		synchronized(filaAtendimento)
		{
			if(filaAtendimento.size() != TAMANHO_FILA)
			{
				filaAtendimento.add(id);
				repaint();
		        System.out.println("Cliente "+id+" na Fila.");
		        colocouNaFila = true;
			}
			
			totalClientesQueChegaram++;		
		}
		
        return colocouNaFila;
	}	
	
	public boolean foiAtendido(int id) throws InterruptedException
	{
		boolean foiAtendido = false;		
		
		synchronized (filaCabeloCortado)
		{
			if(filaCabeloCortado.contains(id))
			{
				foiAtendido = true;
				filaCabeloCortado.remove(filaCabeloCortado.indexOf(id));
				System.out.println("Cliente "+id+" Atendido e foi embora");			
			}	
			
		}		
		
		return foiAtendido;		
	}
	
	public int pegarPrimeiroClienteDaFila(int id,Barbeiro barb) throws InterruptedException
	{
		int idCliente = -1;
		
		synchronized (filaAtendimento)
		{
			if(!filaAtendimento.isEmpty())
			{			
				idCliente = filaAtendimento.remove(0);
				repaint();
				barb.idClienteAtual = idCliente;				
			}			
		}
		
		return idCliente;
	}
	
	public void marcarClienteCabeloCortado(int idCliente) throws InterruptedException
	{				
		synchronized (filaCabeloCortado)
		{
			filaCabeloCortado.add(idCliente);
		}			
	}
	
	public boolean clienteFoiEmbora(int idCliente) throws InterruptedException
	{
		boolean foiEmbora = false;
		
		synchronized (filaCabeloCortado)
		{
			if(!filaCabeloCortado.contains(idCliente))
			{
				foiEmbora = true;				
			}			
		}		
		
		return foiEmbora;
	}
	
	public boolean tiverClienteParaAtender()
	{
		boolean todoMundoAtendido = false;
		
		synchronized (filaAtendimento)
		{
			if(filaAtendimento.isEmpty() && (totalClientesEsperados == totalClientesQueChegaram) )
			{			
				todoMundoAtendido = true;				
			}			
		}			
		
		return todoMundoAtendido;		
	}
	
	protected void paintComponent(Graphics g)   
	{
//		super.paintComponents(g);
//
//		g.clearRect(0 ,120, 800, 100);
//		
//		/* Desenha os lugares da fila */
//		int altura = 120;
//		int posicao = -1;
//		for(int i=0;i<TAMANHO_FILA;i++){
//			posicao++;
//				g.setColor(Color.darkGray);
//				g.fillRect(posicao*50+posicao*10, altura, 50, 100);
//			//Image img1 = Toolkit.getDefaultToolkit().getImage("cadeira_vazia.png");
//		   // g.drawImage(img1, posicao*100, altura, this);
//			/*Faz a quebra da linha*/
//			if(i == TAMANHO_FILA/2-1){
//				altura *=2;
//				posicao = -1;
//			}	
//		}
//		
//		//Image img1 = Toolkit.getDefaultToolkit().getImage("cadeira_vazia.png");
//	    //g.drawImage(img1, 100, 10, this);
//		
//		altura = 120;
//		posicao = -1;
//		for(int i=0;i<filaAtendimento.size();i++){
//			
//			posicao++;
//		    	//g.setColor(Color.darkGray);
//		    	//g.fillRect(posicao*50+posicao*10, 120, 50, 100);
//	    	g.setFont(new Font("Ubuntu", Font.PLAIN, 20));
//	    	g.setColor(Color.white);
//	    	g.drawString(filaAtendimento.get(i).toString(),posicao*50+posicao*10, altura+30);
//	    	//Image img1 = Toolkit.getDefaultToolkit().getImage("user.png");
//		    //g.drawImage(img1, posicao*100, altura, this);
//	    	
//	    	if(i == TAMANHO_FILA/2-1){
//				altura *=2;
//				posicao = -1;
//			}
//		}
//	    g.finalize();
	    
	    super.paintComponents(g);

		g.clearRect(0 ,107+40, 800, 214);
		//g.fillRect(0 ,107+40, 800, 214);
				
		/* Desenha os lugares da fila */
		int altura = 107+40;
		int posicao = -1;
		for(int i=0;i<TAMANHO_FILA;i++){
			posicao++;
			Image img1 = Toolkit.getDefaultToolkit().getImage("images/cadeira_small.png");
			g.drawImage(img1, posicao*70, altura, this);
			/*Faz a quebra da linha*/
			if(i == TAMANHO_FILA/2-1){
				altura = 107+20;
				altura *=2;
				posicao = -1;
			}	
		}
		
		altura = 107+40;
		posicao = -1;
		for(int i=0;i<filaAtendimento.size();i++){			
			posicao++;		    
	    	Image img1 = Toolkit.getDefaultToolkit().getImage("images/user_"+filaAtendimento.get(i).toString()+".png");	    	
		    g.drawImage(img1, posicao*70, altura, this);
	    	
	    	if(i == TAMANHO_FILA/2-1){
	    		altura = 107+20;
	    		altura *=2;
				posicao = -1;
			}
		}
	    g.finalize();

    }
	
}
