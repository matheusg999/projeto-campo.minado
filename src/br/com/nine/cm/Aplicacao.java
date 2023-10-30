package br.com.nine.cm;

import br.com.nine.cm.modelo.Tabuleiro;
import br.com.nine.cm.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		new TabuleiroConsole(tabuleiro);
	}
}
