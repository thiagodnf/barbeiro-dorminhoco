package br.uece.ppc.model;

import br.uece.ppc.util.PseudoRandom;

public class Cliente extends Thread  {
	
	private Barbearia fila;
	private int id;
		
	public Cliente(int id, Barbearia fila)
	{
		this.setName("Cliente_"+id);
		this.fila = fila;
		this.id = id;		
	}	
	
	public void run()
	{	
		try 
		{			
			/*Simula o tempo de chegada do cliente na barbearia*/
			Thread.sleep(PseudoRandom.randInt(1, 20)*1000);
						
			if(fila.colocarNaFila(id))
			{	
				while(!fila.foiAtendido(id)){}				
			}
			else
			{
				System.out.println("Cliente "+ id +" foi embora! Motivo: Lotado!");
			}			
		}
		catch(InterruptedException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
