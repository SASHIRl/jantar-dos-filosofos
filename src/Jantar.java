//Dentro da classe Jantar que é o nosso Main faço a instanciação da classe Mesa.
//Conforme o método construtor da classe mesa, todos os filósofos irão iniciar pensando, com 05 garfos na mesa e com nenhuma tentativa de pega-los.
public class Jantar {	
	public static void main(String[] args) {
		Mesa mesa = new Mesa();
		//Aqui são criadas as 5 threads que representam nossos 5 filósofos.
		for (int filosofo = 0; filosofo < 5; filosofo++) {
			new Filosofo("Filosofo: " + filosofo, mesa, filosofo).start();
		}
	}
}