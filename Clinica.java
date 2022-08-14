package Clinica;	

	import java.io.EOFException;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.ObjectInputStream;
	import java.io.ObjectOutputStream;
	import java.util.ArrayList;

	import javax.swing.JOptionPane;

	public class Clinica {
		private ArrayList<Animal> Animais;

		public Clinica() {
			this.Animais = new ArrayList<Animal>();
		}
		public String[] leValores (String [] dadosIn){
			String [] dadosOut = new String [dadosIn.length];

			for (int i = 0; i < dadosIn.length; i++)
				dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

			return dadosOut;
		}
		public Cavalo leCavalo (){

			String [] valores = new String [3];
			String [] nomeVal = {"Nome", "Especie", "Dono"};
			valores = leValores (nomeVal);

			String especie = this.retornainteiro(valores[1]);

			Cavalo cavalo = new Cavalo (valores[0],especie,valores[2]);
			return cavalo;
		}
		private String retornainteiro(String string) {
			// TODO Auto-generated method stub
			return null;
		}
		public Cachorro leCachorro (){

			String [] valores = new String [3];
			String [] nomeVal = {"Nome", "Especie", "Dono"};
			valores = leValores (nomeVal);		

			Cachorro cachorro = new Cachorro (valores[0],valores[1],valores[2]);
			return cachorro;
		}
		public Passaro lePassaro (){

			String [] valores = new String [3];
			String [] nomeVal = {"Nome", "Especie", "Dono"};
			valores = leValores (nomeVal);		

			Passaro passaro = new Passaro (valores[0],valores[1],valores[2]);
			return passaro;
		}	
		public void salvaanimais (ArrayList<Animal> animais){
			ObjectOutputStream outputStream = null;
			try {
				outputStream = new ObjectOutputStream 
						(new FileOutputStream("c:\\temp\\Clinica.dados"));
				for (int i=0; i < animais.size(); i++)
					outputStream.writeObject(animais.get(i));
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(null,"Imposs�vel criar arquivo!");
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {  //Close the ObjectOutputStream
				try {
					if (outputStream != null) {
						outputStream.flush();
						outputStream.close();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		@SuppressWarnings("finally")
		public ArrayList<Animal> recuperaanimais (){
			ArrayList<Animal> animaisTemp = new ArrayList<Animal>();

			ObjectInputStream inputStream = null;

			try {	
				inputStream = new ObjectInputStream
						(new FileInputStream("c:\\temp\\Clinica.dados"));
				Object obj = null;
				while ((obj = inputStream.readObject()) != null) {
					if (obj instanceof Animal) {
						animaisTemp.add((Animal) obj);
					}   
				}          
			} catch (EOFException ex) { // when EOF is reached
				System.out.println("Fim de arquivo.");
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(null,"Arquivo com Clinica N�O existe!");
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {  //Close the ObjectInputStream
				try {
					if (inputStream != null) {
						inputStream.close();
					}
				} catch (final IOException ex) {
					ex.printStackTrace();
				}
				return animaisTemp;
			}
		}
		public void menuClinica (){

			String menu = "";
			String entrada;
			int    opc1, opc2;

			do {
				menu = "Controle Clinica\n" +
						"Op��es:\n" + 
						"1. Entrar Animais\n" +
						"2. Exibir Animais\n" +
						"3. Limpar Animais\n" +
						"4. Gravar Animais\n" +
						"5. Recuperar Animais\n" +
						"9. Sair";
				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc1 = this.retornaInteiro(entrada);

				switch (opc1) {
				case 1:// Entrar dados
					menu = "Entrada de Animais \n" +
							"Op��es:\n" + 
							"1. Cavalo\n" +
							"2. Cachorro\n"+
							"3. Passaro\n";

					entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

					switch (opc2){
					case 1: Animais.add((Animal)leCavalo());
					break;
					case 2: Animais.add((Animal)leCachorro());
					break;
					case 3: Animais.add((Animal)lePassaro());
					break;
					default: 
						JOptionPane.showMessageDialog(null,"Animal  para entrada N�O escolhido!");
					}
					break;
				case 2: // Exibir dados
					if (Animais.size() == 0) {
						JOptionPane.showMessageDialog(null,"Entre com animais primeiramente");
						break;
					}
					String dados = "";
					for (int i=0; i < Animais.size(); i++)	{
						dados += Animais.get(i).toString() + "---------------\n";
					}
					JOptionPane.showMessageDialog(null,dados);
					break;
				case 3: // Limpar Dados
					if (Animais.size() == 0) {
						JOptionPane.showMessageDialog(null,"Entre com animais primeiramente");
						break;
					}
					Animais.clear();
					JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
					break;
				case 4: // Grava Dados
					if (Animais.size() == 0) {
						JOptionPane.showMessageDialog(null,"Entre com animais primeiramente");
						break;
					}
					salvaanimais(Animais);
					JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
					break;
				case 5: // Recupera Dados
					Animais = recuperaanimais();
					if (Animais.size() == 0) {
						JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
						break;
					}
					JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
					break;
				case 9:
					JOptionPane.showMessageDialog(null,"Fim do aplicativo CLINICA");
					break;
				}
			} while (opc1 != 9);
		}
		private int retornaInteiro(String entrada) {
			// TODO Auto-generated method stub
			return 0;
		}
		public static void main (String [] args){

			Clinica pet = new Clinica ();
			pet.menuClinica();
		}
	}


