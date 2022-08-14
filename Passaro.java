package Clinica;

public class Passaro extends Animal {

		private static final long serialVersionUID = 1L;

		public String alimentacao() {
			return "alpiste, frutas";
			
		}
		public String cuidadosproprios() {	
			return "abrigo, est�mulos";
		}
		public Passaro(String nome, String especie, String dono) {
			super(nome, especie, dono);
			this.especie = "Passaro";			
		}
}
	
		

