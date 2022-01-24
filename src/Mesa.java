public class Mesa {
	//Como o fil�sofo ir� se comportar no programa.
	final int PENSANDO = 0;
	final int FAMINTO = 1;
	final int COMENDO = 2;
	//fixa o n�mero de fil�sofos.
	final static int PRIMEIRO_FILOSOFO = 0;
	final static int ULTIMO_FILOSOFO = 5 - 1;
	boolean[] garfos = new boolean[5];
	int[] filosofos = new int [5];
	int[] tentativas = new int[5];
	
	public Mesa() {
		//for utilizado para inicializar os status.
		for (int i = 0; i < 5; i++) {
			//Informo que cada fil�sofo come�a com 1 garfo.
			garfos[i] = true;
			//Todos fil�sofos se iniciam no status "PENSANDO".
			filosofos[i] = PENSANDO;
			//Todos iniciam com 0 tentativas de pegar o garfo.
			tentativas[i] = 0;
		}
	}
	//Aqui � onde eu seleciono o fil�sofo e mando ele pegar o grafo.
	//Aqui usamos o Synchronized para cada fil�sofo (Thread) pegar o objeto de forma individual.
	public synchronized void pegarGarfos(int filosofo) throws InterruptedException {
		//O fil�sofo instanciado que ir� pegar o garfo j� vem com a constante "FAMINTO".
		filosofos[filosofo] = FAMINTO;
		//Aqui faremos a verifica��o se o filosofo da esquerda ou da direita est�o comendo,
		//Na primeira verifica��o o programa ir� olhar para o �ltimo fil�sofo, na posi��o 04 do vetor
		//J� que em nosso c�digo � dito que se o fil�sofo for o primeiro, ser� verificado o �ltimo fil�sofo.
		//O programa tamb�m ir� olhar para o fil�sofo da direita, que ser� o na posi��o 01, j� que o fil�sofo atual est� na posi��o 0.
		//Em um primeiro momento o nosso programa n�o entrar� nesse looping.
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
	//Esse m�todo faz a verifica��o do fil�sofo a direito do fil�sofo atual.
	public int aDireita (int filosofo) {
		int direito;
		//Esse if verifica se o fil�sofo atual � o �ltimo, para assim poder olhar para o primeiro que est� na posi��o 00.
		if (filosofo == ULTIMO_FILOSOFO) {
			direito = PRIMEIRO_FILOSOFO;
		//Caso contr�rio, o programa ir� olhar para o fil�sofo a direita do atual.
		} else {
			direito = filosofo + 1;
		}
		return direito;
	}
	//Esse m�todo faz a verifica��o do fil�sofo a esquerda do fil�sofo atual.
	public int aEsquerda (int filosofo) {
		int esquerdo;
		//Esse if verifica se o fil�sofo atual � o primeiro, para assim poder olhar para o �ltimoque est� na posi��o 04.
		if (filosofo == PRIMEIRO_FILOSOFO) {
			esquerdo = ULTIMO_FILOSOFO;
		//Caso contr�rio, ele ir� subtrair um fil�sofo e olhar para o que fica na posi��o � esquerda do atual.
		} else {
			esquerdo = filosofo - 1;
		}
		return esquerdo;
	}
	//Esse m�todo entrega para o fil�sofo o garfo esquerdo.
	public int garfoEsquerdo (int filosofo) {
		int garfoEsquerdo = filosofo;
		return garfoEsquerdo;
	}
	//Esse m�todo entrega para o fil�sofo o garfo esquerdo.
	public int garfoDireito (int filosofo) {
		int garfoDireito;
		//Como o �ltimo fil�sofo � o 04 (�ltima posi��o do vetor) ent�o ele ir� olhar para o fil�sofo [00] com o garfo na sua direita.
		if (filosofo == ULTIMO_FILOSOFO) {
			garfoDireito = 0;
		} else {
		//Caso contr�rio, ele apenas pela o garfo do fil�sofo as au direita.
			garfoDireito = filosofo + 1;
		}
		return garfoDireito;
	}
}