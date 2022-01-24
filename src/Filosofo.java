//Aqui temos a nossa Thread 
public class Filosofo extends Thread {
	Mesa mesa;
	int filosofo;
	//M�todo contrutor da classe fil�sofos, recebe um
	//nome e idenfiticador como parametros.
	public Filosofo (String nome, Mesa mesadejantar, int fil) {
		super(nome);
		mesa = mesadejantar;
		filosofo = fil;
	}
	
	public void run() {
		int tempo = 0;
		while (true) {
			//'tempo' recebe um inteiro aleat�rio e m�ltiplica por 100.
			tempo = (int)(Math.random() * 10000);
			//O resultado da express�o � utilizado como tempo de pensamento do fil�sofo.
			//A fun��o 'sleep' aqui � chamada pelo metodo "pensa".
			try {
				pensar(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				//Aqui temos a cereja do bolo que faz esse programa funcionar (talvez) como deveria.
				//O m�todo getGarfos ir� fazer com que o m�todo pegarGarfos da classe Mesa seja executado!.
				//Como o m�todo � do tipo Synchronized ele trava o garfo na m�o do fil�sofo e como no pr�prio m�todo h� uma defini��o onde � dito que o filosofo est� faminto, ele ir� fazer a verifica��o das mesas ao lado para saber se pode pegar o garfo e poder consumir sua macarronada!
				//Caso os garfos da esquerda e da direita estejam ocupados o filosofo vai ter que aguardar, portando ser� chamado o m�todo .wait(); que ir� aguardar uma .notifyAll(); de algum dos fil�sofos que est�o consumindo a macarronada.
				//Ficar� registrado a quantidade de 
				getGarfos();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tempo = (int)(Math.random() *10000);
			try {
				//Aqui � onde o fil�sofo ir� saborear a sua macarronada, para isso ser� necess�rio um tempo aleat�rio, seguido da informa��o que o fil�sofo fez seu consumo.
				comer(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Aqui temos a segunda cereja do bolo, onde o m�todo returnGarfos(); ir� fazer com que o m�todo retornarGarfos(); da classe mesa seja executado.
			//Esse m�todo ir� fazer uma verifica��o se os fil�sofos (Threads) em sua direita e esquerda est�o FAMINTOS, caso isso seja verdade ele ir� notificar essas Threads com o m�todo .notifyAll();.
			//O fil�sofo atual que acabou de retornar os garfos ir� entrar em estado de "PENSANDO".
			//� importante frisar que o m�todo retornarGarfos(); � do tipo synchronized, portando at� que ele termina suas opera��es nenhuma outra Thread tem acesso aos seus garfos... Apenas ap�s a notifica��o.
			returnGarfos();
		}
	}
	
	public void pensar (int tempo) throws InterruptedException {
		//O n�mero aleat�rio gerado � utilizado aqui.
		sleep(tempo);
		System.out.println("O fil�sofo pensou...");
	}
	
	public void comer (int tempo) throws InterruptedException {
		sleep(tempo);
		System.out.println("O fil�sofo comeu");
	}
	
	public void getGarfos() throws InterruptedException {
		mesa.pegarGarfos(filosofo);
	}
	public void returnGarfos() {
		mesa.retornarGarfos(filosofo);
	}
}