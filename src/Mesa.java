public class Mesa {
	//Como o filósofo irá se comportar no programa.
	final int PENSANDO = 0;
	final int FAMINTO = 1;
	final int COMENDO = 2;
	//fixa o número de filósofos.
	final static int PRIMEIRO_FILOSOFO = 0;
	final static int ULTIMO_FILOSOFO = 5 - 1;
	boolean[] garfos = new boolean[5];
	int[] filosofos = new int [5];
	int[] tentativas = new int[5];
	
	public Mesa() {
		//for utilizado para inicializar os status.
		for (int i = 0; i < 5; i++) {
			//Informo que cada filósofo começa com 1 garfo.
			garfos[i] = true;
			//Todos filósofos se iniciam no status "PENSANDO".
			filosofos[i] = PENSANDO;
			//Todos iniciam com 0 tentativas de pegar o garfo.
			tentativas[i] = 0;
		}
	}
	//Aqui é onde eu seleciono o filósofo e mando ele pegar o grafo.
	//Aqui usamos o Synchronized para cada filósofo (Thread) pegar o objeto de forma individual.
	public synchronized void pegarGarfos(int filosofo) throws InterruptedException {
		//O filósofo instanciado que irá pegar o garfo já vem com a constante "FAMINTO".
		filosofos[filosofo] = FAMINTO;
		//Aqui faremos a verificação se o filosofo da esquerda ou da direita estão comendo,
		//Na primeira verificação o programa irá olhar para o último filósofo, na posição 04 do vetor
		//Já que em nosso código é dito que se o filósofo for o primeiro, será verificado o último filósofo.
		//O programa também irá olhar para o filósofo da direita, que será o na posição 01, já que o filósofo atual está na posição 0.
		//Em um primeiro momento o nosso programa não entrará nesse looping.
		while (filosofos[aEsquerda(filosofo)] == COMENDO || filosofos[aDireita(filosofo)] == COMENDO) { 
			tentativas[filosofo]++;
			wait();
		}	
	}
	public void retornarGarfos (int filosofo) {
		garfos[garfoEsquerdo(filosofo)] = true;
		garfos[garfoDireito(filosofo)] = true;
		if (filosofos[aEsquerda(filosofo)] == FAMINTO || filosofos[aDireita(filosofo)] == FAMINTO ) {
			notifyAll();
		}
		filosofos[filosofo] = PENSANDO;
	}
	//Esse método faz a verificação do filósofo a direito do filósofo atual.
	public int aDireita (int filosofo) {
		int direito;
		//Esse if verifica se o filósofo atual é o último, para assim poder olhar para o primeiro que está na posição 00.
		if (filosofo == ULTIMO_FILOSOFO) {
			direito = PRIMEIRO_FILOSOFO;
		//Caso contrário, o programa irá olhar para o filósofo a direita do atual.
		} else {
			direito = filosofo + 1;
		}
		return direito;
	}
	//Esse método faz a verificação do filósofo a esquerda do filósofo atual.
	public int aEsquerda (int filosofo) {
		int esquerdo;
		//Esse if verifica se o filósofo atual é o primeiro, para assim poder olhar para o últimoque está na posição 04.
		if (filosofo == PRIMEIRO_FILOSOFO) {
			esquerdo = ULTIMO_FILOSOFO;
		//Caso contrário, ele irá subtrair um filósofo e olhar para o que fica na posição à esquerda do atual.
		} else {
			esquerdo = filosofo - 1;
		}
		return esquerdo;
	}
	//Esse método entrega para o filósofo o garfo esquerdo.
	public int garfoEsquerdo (int filosofo) {
		int garfoEsquerdo = filosofo;
		return garfoEsquerdo;
	}
	//Esse método entrega para o filósofo o garfo esquerdo.
	public int garfoDireito (int filosofo) {
		int garfoDireito;
		//Como o último filósofo é o 04 (última posição do vetor) então ele irá olhar para o filósofo [00] com o garfo na sua direita.
		if (filosofo == ULTIMO_FILOSOFO) {
			garfoDireito = 0;
		} else {
		//Caso contrário, ele apenas pela o garfo do filósofo as au direita.
			garfoDireito = filosofo + 1;
		}
		return garfoDireito;
	}
}