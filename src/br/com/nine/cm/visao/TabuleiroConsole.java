package br.com.nine.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.nine.cm.excecao.ExplosaoException;
import br.com.nine.cm.excecao.SairExceptions;
import br.com.nine.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	
		executarJogo();
	}
	
	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				
				System.out.println("Outra partida? (S/n) ");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				}else {
					tabuleiro.reiniciar();
				}
			}
		} catch (Exception e) {
			System.out.println("Tchau!!!");
		} finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			
			while(!tabuleiro.objetivoAlcancado()) { // o objetivo foi alcançado? NAO, entao execute.
				System.out.println(tabuleiro);      // mostre o tabuleiro
				
				String digitado = capturarValorDigitado("Digite (x, y): ");  //pegue os valores das coordenadas
				
				                                                             // a logica implementada neste bloco faz com que o usuario SÓ digite
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))    // o valor 'x' VIRGULA valor 'y', os transforme em inteiros
						.map(e -> Integer.parseInt(e.trim())).iterator();    // ignorando os espaços em branco e evitando que o usuario digite textos ou coisas do tipo.
				                                                               
				digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar"); // pegue a informação se o usuario quer abrir ou marcar
				
				if("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				}else if("2".equals(digitado)) {
					tabuleiro.alternarMarcacao(xy.next(), xy.next());
				}
			}
			
			System.out.println(tabuleiro);
			System.out.println("Voce ganhou! ");
		}catch(ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Voce perdeu! ");
		}
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairExceptions();
		}
		
		return digitado;
	}
}
