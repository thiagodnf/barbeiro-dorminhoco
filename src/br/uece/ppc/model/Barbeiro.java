package br.uece.ppc.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
import br.uece.ppc.util.PseudoRandom;

@SuppressWarnings("serial")
public class Barbeiro extends JPanel implements Runnable {
		
	public int id;
	public Barbearia barbearia;
	public int totalClientesAtendidos = 0;
	public int idClienteAtual = -1;

		
	public Barbeiro(int id, Barbearia barbearia)
	{
		this.setName("Barbeiro_"+id);
		this.barbearia = barbearia;
		this.id = id;
		setSize(800, 600);
	}
	
	public void run()
	{		
		while(!barbearia.tiverClienteParaAtender())
		{			
			try
			{				
				idClienteAtual = barbearia.pegarPrimeiroClienteDaFila(id,this);
				
				if(idClienteAtual != -1)
				{
					System.out.println("Barbeiro::#"+ (id+1)+" - Cortando: Cliente "+idClienteAtual);
					repaint();
					Thread.sleep(PseudoRandom.randInt(1, 6)*1000);	
					System.out.println("Barbeiro "+ (id+1)+" Terminou: Cliente: "+idClienteAtual);
					
					barbearia.marcarClienteCabeloCortado(idClienteAtual);
					
					while(!barbearia.clienteFoiEmbora(idClienteAtual)){}
					
					totalClientesAtendidos++;
					idClienteAtual = -1;
					repaint();
				}
			}
			catch (InterruptedException e1)
			{
				System.out.println(e1.getMessage());
				e1.printStackTrace();
			}	
		}
		idClienteAtual = -1;
		repaint();
		System.out.println("Barbeiro "+(id+1)+" foi embora e atendeu "+ totalClientesAtendidos+"!");
		
	}
	
	protected void paintComponent(Graphics g)  
	{
		super.paintComponents(g);
       
		//g.setColor(Color.darkGray);
    	//g.fillRect(id*70+id*40+250, 0, 70, 107);
    	//g.setFont(new Font("Ubuntu", Font.PLAIN, 20));
    	
    	Image img1;
    	if(idClienteAtual != -1){
    		img1 = Toolkit.getDefaultToolkit().getImage("images/user_"+idClienteAtual+".png");
    	}else{    		
    		img1 = Toolkit.getDefaultToolkit().getImage("images/cadeira_small.png");			    		
    	}
    	
    	g.clearRect(id*70+id*40+250, 0, 70, 107);
    	g.drawImage(img1, id*70+id*40+250,0, this);
    	g.finalize();
    }
	
				
}
