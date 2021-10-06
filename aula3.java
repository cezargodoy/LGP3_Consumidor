public class Main {
	public static volatile Object lock = new Object();
	public static volatile int produtos = 0;
	
	public static void main(String[] args) {
		Produtor p1 = new Produtor(1);
		p1.start();
		Consumidor c1 = new Consumidor(1);
		c1.start();
	}
}
class Produtor extends Thread {
	int id = 0;
	Produtor(int novoId) {
		this.id = novoId;
	}
	public void run() {
		for(int i = 0; i < 100; i ++ ) {			
			synchronized( Main.lock )
			{	
				if(Main.produtos < 100)
					Main.produtos = Main.produtos + 1;
				System.out.println("\nProdutor " + id + "; estoque = " + Main.produtos);
			}
		}
	}
}
class Consumidor extends Thread {
	int id = 0;
	Consumidor(int novoId) {
		this.id = novoId;
	}
	public void run() {
		for(int i = 0; i < 100; i++) {			
			synchronized( Main.lock )
			{	
				System.out.println("\nConsumidor " + id + "; estoque = " + Main.produtos);
				if(Main.produtos > 0)
					Main.produtos = Main.produtos - 1;
			}
		}
	}
}
