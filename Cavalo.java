package Clinica;
public class Cavalo extends Animal {

		private static final long serialVersionUID = 1L;

		public String alimentacao() {
			return "rac�o, aveia, alfafa";
			
		}
		public String cuidadosproprios() {	
			return "corrida, trote, cascos";
		}
		public Cavalo(String nome, String especie, String dono) {
			super(nome, especie, dono);
			this.especie = "Cavalo";			
		}
}
	
		