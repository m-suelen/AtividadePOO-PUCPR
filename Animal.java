package Clinica;	

	import java.io.Serializable;

	public abstract class Animal  implements Serializable {

		private static final long serialVersionUID = 1L;
		private   String nome;
		protected   String especie;
		protected String dono;
		
		public Animal(String nome, String especie, String dono) {
			this.nome = nome;
			this.especie = especie;
			this.dono = dono;
		}
		public String toString() {
			String retorno = "";
			retorno += "Nome: "     + this.nome     + "\n";
			retorno += "especie: "     + this.especie     + "\n";
			retorno += "dono: "  + this.dono  + "\n";
			retorno += "alimenta��o: "  + alimentacao()        + "\n";
			
			return retorno;
		}
		public abstract String alimentacao();
		public abstract String cuidadosproprios();
	}
	


