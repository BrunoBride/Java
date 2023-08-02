package bytebank_herdado;

//contrato autenticavel
public abstract interface Autenticavel {

	public abstract void setSenha(int senha);

	public abstract boolean autentica(int senha);
}
