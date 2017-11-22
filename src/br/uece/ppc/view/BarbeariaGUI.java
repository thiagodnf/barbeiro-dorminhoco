package br.uece.ppc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import br.uece.ppc.model.Barbearia;
import br.uece.ppc.model.Barbeiro;
import br.uece.ppc.model.Cliente;

public class BarbeariaGUI extends JFrame{
	private static int QUANTIDADE_BARBEIRO = 3;
	private static int QUANTIDADE_CLIENTE = 50;
	private static int QUANTIDADE_LUGARES_DE_ESPERA = 20;
	
	private static Barbearia barbearia;
	private Barbeiro[] barbeiro;
	private Cliente[] cliente;

	private static final long serialVersionUID = 1L;	

	public BarbeariaGUI(){ }

	public void run() {		

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setMinimumSize(new Dimension(800, 600));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Barbeiro Dorminhoco - UECE - 2011.1");
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
								
				barbearia = new Barbearia(QUANTIDADE_LUGARES_DE_ESPERA,QUANTIDADE_CLIENTE);
				barbeiro = new Barbeiro[QUANTIDADE_BARBEIRO];
				cliente = new Cliente[QUANTIDADE_CLIENTE];
				
				frame.add(barbearia, BorderLayout.CENTER);
		
				for(int i=0;i<barbeiro.length;i++)
				{
					barbeiro[i] = new Barbeiro(i, barbearia);				
					frame.add(barbeiro[i], BorderLayout.CENTER);
					Thread t = new Thread(barbeiro[i]);
				    t.start();
				}			
				
				liberarClientes();
				repaint();
			}	
			
		});
	}
	
	private void liberarClientes()
	{
		for(int i=0;i<cliente.length;i++)
		{
			cliente[i] = new Cliente(i, barbearia);										
			cliente[i].start();											
		}
	}	

}
