package bytebank_herdado;

public class SistemaInterno {
	private int senha = 222;
	
	public void autentica(Autenticavel fa) {
	boolean autenticou = fa.autentica(this.senha);
	if(autenticou) {
		System.out.println("Pode etrar no sistema!");
	}else {
		System.out.println("Não pode etrar no sistema!");
	}
	}

}
