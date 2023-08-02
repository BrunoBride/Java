package bytebank_herdado;

public class ControleBonificacao {

	private double soma;
	
	public void registra(Funcionario f) {
        this.soma += f.getBonificacao();
    }

	public double getSoma() {
		return soma;
	}

	public void setSoma(double soma) {
		this.soma = soma;
	}

}
