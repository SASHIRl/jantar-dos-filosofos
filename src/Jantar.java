//Dentro da classe Jantar que � o nosso Main fa�o a instancia��o da classe Mesa.
//Conforme o m�todo construtor da classe mesa, todos os fil�sofos ir�o iniciar pensando, com 05 garfos na mesa e com nenhuma tentativa de pega-los.
public class Jantar {	
	public static void main(String[] args) {
		Mesa mesa = new Mesa();
		//Aqui s�o criadas as 5 threads que representam nossos 5 fil�sofos.
		for (int filosofo = 0; filosofo < 5; filosofo++) {
			new Filosofo("Filosofo: " + filosofo, mesa, filosofo).start();
		}
	}
}