package projeto_logica;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProjetoLogica {
	public static void main(String[] args) throws IOException {
		try {
			Scanner in = new Scanner(new FileReader("Entrada.in"));
			FileWriter out = new FileWriter("Saida.out");
			Map<Character, Boolean> valoresverdade = new HashMap<>();

			int n = in.nextInt(); // pega o numero de linhas
			in.nextLine();

			for (int q = 1; q <= n; q++) { // lê todas as linhas
				
				out.write("Problema #" + q + "\n");
				String line = in.nextLine();
				int i;

				for (i = 0; i < line.length() && (line.charAt(i) != '1' && line.charAt(i) != '0'); i++) {
				} // separar a expressao da valoração

				if (i == line.length()) { //caso não tenham as valorações na entrada
					if (line.charAt(0) == '{') {
						out.write("Ha uma palavra nao legitima no conjunto.\n\n");
					} 
					else {
						out.write("A palavra nao e legitima.\n\n");
					}
				} 
				else {
					String expressao = line.substring(0, (i - 1));
					String valoracao = line.substring(i, (line.length()));

					String[] valoracoesAux = valoracao.split(" "); // faz um array de int com as valorações
					boolean[] valoracoes = new boolean[valoracoesAux.length];
					char[] variaveis = new char[valoracoesAux.length];

					for (int u = 0; u < valoracoes.length; u++) { //passa as valorações pra boolean e salva no array
						int aux = Integer.parseInt(valoracoesAux[u]);
						if (aux == 0) {
							valoracoes[u] = false;
						} else {
							valoracoes[u] = true;
						}
					}
					int indexA = 0;
					for (int o = 0; o < expressao.length(); o++) { // faz a correspondencia das variaveis e sua valoração no hashmap
																	
						if (expressao.charAt(o) >= 'A' && expressao.charAt(o) <= 'Z' && indexA < variaveis.length) {
							if (funcaoA(expressao.charAt(o), variaveis, 0)) {
								variaveis[indexA] = expressao.charAt(o); //salva as variaveis em um array pra chechar se já tá salvo
								valoresverdade.put(variaveis[indexA], valoracoes[indexA]);
								indexA++; 
							}
						}
					}
					if (expressao.charAt(0) == '{') { // conjunto de expressões
						String splitarExpressoes = expressao.substring(1, expressao.length() - 1);
						String[] expressoes = splitarExpressoes.split(", ");

						int cont = 0;
						for (int h = 0; h < expressoes.length; h++) {
							boolean legit = isLegit(expressoes[h]);
							if (!legit) {
								out.write("Ha uma palavra nao legitima no conjunto.\n\n");
								h = expressoes.length;
							} 
							else {
								boolean satisfaz = satisfies(valoresverdade, expressoes[h]);
								if (!satisfaz) {
									cont++;
								} 
								if (h == expressoes.length - 1 && cont > 0) {
									out.write("A valoracao-verdade nao satisfaz o conjunto.\n\n");
								}
								else if (satisfaz && h == expressoes.length - 1) {
									out.write("A valoracao-verdade satisfaz o conjunto.\n\n");
								}
							}
						}
					} 
					else { // Apenas uma expressão
						boolean legit = isLegit(expressao);
						if (!legit) {
							out.write("A palavra nao e legitima.\n\n");
						} 
						else {
							boolean satisfaz = satisfies(valoresverdade, expressao);
							if (satisfaz) {
								out.write("A valoracao-verdade satisfaz a proposicao.\n\n");
							} 
							else {
								out.write("A valoracao-verdade nao satisfaz a proposicao.\n\n");
							}
						}
					}
				}
			}
			out.close();
			in.close();
		} 
		catch (IOException e) {
		}
	}

	static boolean funcaoA(char charAt, char[] variaveis, int indexA) {
		if (variaveis.length - 1 == indexA && variaveis[indexA] != charAt) {
			return true;
		} 
		else if (variaveis[indexA] == charAt) {
			return false;
		}
		return funcaoA(charAt, variaveis, indexA + 1);
	}

	static boolean isLegit(String exp) {
		if (exp.length() == 1 && exp.charAt(0) >= 'A' && exp.charAt(0) <= 'Z')
			return true;

		int s = 0;
		int f = exp.length() - 1;

		if (exp.charAt(s) == '(' && exp.charAt(f) == ')' && exp.charAt(s + 1) == '~' && s + 2 < f)
			return isLegit(exp.substring(s + 2, f));

		else if (exp.charAt(s) == '(' && exp.charAt(f) == ')') {
			int operatorIndex = findOperator(exp);
			if (operatorIndex != -1 && exp.charAt(operatorIndex - 1) == ' ' && exp.charAt(operatorIndex + 1) == ' ')
				return isLegit(exp.substring(s + 1, operatorIndex - 1)) && isLegit(exp.substring(operatorIndex + 2, f));
		}
		return false;
	}

	static int findOperator(String exp) {
		int numParenthesis = 0;
		for (int i = 0; i < exp.length(); i++) {

			if (exp.charAt(i) == '(')
				numParenthesis++;
			
			else if (exp.charAt(i) == ')')
				numParenthesis--;

			if ((exp.charAt(i) == '>' || exp.charAt(i) == '&' || exp.charAt(i) == 'v' || exp.charAt(i) == '~') && numParenthesis == 1)
				return i;
		}
		return -1;
	}

	static boolean satisfies(Map<Character, Boolean> valoresverdade, String exp) {

		if (exp.length() == 1)
			return valoresverdade.get(exp.charAt(0));

		int s = 0;
		int f = exp.length() - 1;
		int operatorIndex = findOperator(exp);
		switch (exp.charAt(operatorIndex)) {

		case '~':
			return !satisfies(valoresverdade, exp.substring(operatorIndex + 1, f));
		case '&':
			return satisfies(valoresverdade, exp.substring(s + 1, operatorIndex - 1))
					&& satisfies(valoresverdade, exp.substring(operatorIndex + 2, f));
		case 'v':
			return satisfies(valoresverdade, exp.substring(s + 1, operatorIndex - 1))
					|| satisfies(valoresverdade, exp.substring(operatorIndex + 2, f));
		case '>':
			return !satisfies(valoresverdade, exp.substring(s + 1, operatorIndex - 1))
					|| satisfies(valoresverdade, exp.substring(operatorIndex + 2, f));
		default:
			return true;
		}
	}
}