package projetoLogica;

import java.util.Scanner;

public class ProjetoLogica {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		for (int q = 0; q < n; q++) {
			String expressaoValoracao = in.nextLine();
			//aqui eu vou receber uma entrada que pode ser de dois tipos:
			//expressão valoração-verdade
			//{expressão_1, expressão_2, ..., expressão_n} valoração-verdade
			
		}
		//------ SAIDA -------
		//Para o n-ésimo caso (n >= 1), seu programa deve imprimir Problema #n​.
		//Cada caso deve ser separado por uma linha em branco.
		
		/*Para entradas no 1o formato:
			● Se a expressão não for legítima, seu programa deve imprimir “A palavra nao e legitima.​”
			● Se a valoração-verdade satisfaz a expressão, seu programa deve imprimir “A
			valoracao-verdade satisfaz a proposicao.”
			● Se a valoração-verdade refuta a expressão, seu programa deve imprimir “A
			valoracao-verdade nao satisfaz a proposicao.” */
		
		/*Para entradas no 2o formato:
			● Se o conjunto contiver alguma expressão não-legítima, seu programa deve imprimir “Ha
			uma palavra nao legitima no conjunto.​”
			● Se a valoração-verdade satisfaz o conjunto, seu programa deve imprimir “A
			valoracao-verdade satisfaz o conjunto.”
			● Se a valoração-verdade não satisfaz o conjunto, seu programa deve imprimir “A
			valoracao-verdade nao satisfaz o conjunto.”*/
	}

}
//As variáveis podem ser representadas por qualquer uma das 26 letras do alfabeto (maiúsculas).
//Para os operadores, são usados os seguintes: negação ​(~), conjunção ​(&), disjunção ​(v) e implicação ​(>).


//------------ COISAS IMPORTANTES ------------

/* ● Para determinar se uma expressão é legítima ou não, é obrigatório o uso da definição
indutiva de PROP (<EXPR>)​.
   ● Para determinar o valor-verdade de uma expressão, é obrigatório o uso da função de
valoração-verdade, definida pelo Teorema da Extensão Homomórfica Única.
   ● O formato da saída deve ser estritamente seguido. */
