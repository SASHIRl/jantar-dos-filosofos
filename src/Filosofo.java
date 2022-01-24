//Aqui temos a nossa Thread 
public class Filosofo extends Thread {
	Mesa mesa;
	int filosofo;
	//Método contrutor da classe filósofos, recebe um
	//nome e idenfiticador como parametros.
	public Filosofo (String nome, Mesa mesadejantar, int fil) {
		super(nome);
		mesa = mesadejantar;
		filosofo = fil;
	}
	
	public void run() {
		int tempo = 0;
		while (true) {
			//'tempo' recebe um inteiro aleatório e múltiplica por 100.
			tempo = (int)(Math.random() * 10000);
			//O resultado da expressão é utilizado como tempo de pensamento do filósofo.
			//A função 'sleep' aqui é chamada pelo metodo "pensa".
			try {
				pensar(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				//Aqui temos a cereja do bolo que faz esse programa funcionar (talvez) como deveria.
				//O método getGarfos irá fazer com que o método pegarGarfos da classe Mesa seja executado!.
				//Como o método é do tipo Synchronized ele trava o garfo na mão do filósofo e como no próprio método há uma definição onde é dito que o filosofo está faminto, ele irá fazer a verificação das mesas ao lado para saber se pode pegar o garfo e poder consumir sua macarronada!
				//Caso os garfos da esquerda e da direita estejam ocupados o filosofo vai ter que aguardar, portando será chamado o método .wait(); que irá aguardar uma .notifyAll(); de algum dos filósofos que estão consumindo a macarronada.
				//Ficará registrado a quantidade de 
				getGarfos();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tempo = (int)(Math.random() *10000);
			try {
				//Aqui é onde o filósofo irá saborear a sua macarronada, para isso será necessário um tempo aleatório, seguido da informação que o filósofo fez seu consumo.
				comer(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Aqui temos a segunda cereja do bolo, onde o método returnGarfos(); irá fazer com que o método retornarGarfos(); da classe mesa seja executado.
			//Esse método irá fazer uma verificação se os filósofos (Threads) em sua direita e esquerda estão FAMINTOS, caso isso seja verdade ele irá notificar essas Threads com o método .notifyAll();.
			//O filósofo atual que acabou de retornar os garfos irá entrar em estado de "PENSANDO".
			//É importante frisar que o método retornarGarfos(); é do tipo synchronized, portando até que ele termina suas operações nenhuma outra Thread tem acesso aos seus garfos... Apenas após a notificação.
			returnGarfos();
		}
	}
	
	public void pensar (int tempo) throws InterruptedException {
		//O número aleatório gerado é utilizado aqui.
		sleep(tempo);
		System.out.println("O filósofo pensou...");
	}
	
	public void comer (int tempo) throws InterruptedException {
		sleep(tempo);
		System.out.println("O filósofo comeu");
	}
	
	public void getGarfos() throws InterruptedException {
		mesa.pegarGarfos(filosofo);
	}
	public void returnGarfos() {
		mesa.retornarGarfos(filosofo);
	}
}