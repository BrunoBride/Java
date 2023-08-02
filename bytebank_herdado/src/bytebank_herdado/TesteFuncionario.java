package bytebank_herdado;

public class TesteFuncionario {

	public static void main(String[] args) {
		
		Autenticavel cliente = new Cliente();
		
		
 Funcionario nico = new Gerente();
 
 nico.setNome("Nico Steppat");
 nico.setCpf("123456798-0");
 nico.setSalario(2500.00);
 
 System.out.println(nico.getNome());
 System.out.println(nico.getBonificacao());
	}

}
