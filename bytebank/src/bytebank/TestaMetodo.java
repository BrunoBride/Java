package bytebank;

public class TestaMetodo {
	public static void main(String[] args) {
		Conta contaDoPaulo = new Conta();
		contaDoPaulo.saldo = 100;
		System.out.println("Saldo anterior: " + contaDoPaulo.saldo);
		contaDoPaulo.deposita(50);
		System.out.println("Saldo atual: " + contaDoPaulo.saldo);
		
		        
        boolean conseguiuRetirar = contaDoPaulo.saca(20);
        System.out.println(contaDoPaulo.saldo);
        System.out.println(conseguiuRetirar);
	}
}