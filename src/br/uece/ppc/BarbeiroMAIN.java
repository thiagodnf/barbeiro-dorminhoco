/**
 * Barbeiro Dorminhoco
 * Projeto da Disciplina
 * Disciplina: Programação Paralela e Concorrente
 * Semestre: 2011.1
 */

package br.uece.ppc;

import br.uece.ppc.view.BarbeariaGUI;

public class BarbeiroMAIN {
	
	/** Funcao principal do programa
	 * @param args Parametros usado na execucao
	 */
	public static void main(String[] args) throws InterruptedException
	{
		BarbeariaGUI barbearia = new BarbeariaGUI();
		barbearia.run();
		
//		JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setTitle("BarbeiroMAIN");
//		
//		
//		Barbearia content = new Barbearia();
//		content.setPreferredSize(content.getSize());
//		frame.add(content, BorderLayout.CENTER);
//		frame.pack();
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);
		
//		Barbearia fila = new Barbearia();
//		
//		Cliente[] cliente = new Cliente[QUANTIDADE_CLIENTE];
//		Barbeiro[] barbeiro = new Barbeiro[QUANTIDADE_BARBEIRO];
//		
//		for(int i=0;i<barbeiro.length;i++)
//		{
//			barbeiro[i] = new Barbeiro(i, fila);				
//			barbeiro[i].start();
//		}
//		
//		for(int i=0;i<cliente.length;i++)
//		{
//			cliente[i] = new Cliente(i, fila);
//			cliente[i].start();
//		}
	
		
		
	}

}
