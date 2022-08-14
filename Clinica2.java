package Clinica;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Clinica1 {

	private ArrayList<Animal> animais;

	public Clinica1( ) {
		this.animais = new ArrayList<Animal>();
	}
	public void adicionaAnimal(Animal ani) {
		this.animais.add(ani);
	}
	public void listarAnimais() {
		for(Animal ani:animais) {
			System.out.println(ani.toString());
		}
		System.out.println("Total = " + this.animais.size() + " animais listados com sucesso!\n");
	}	
	public void excluirAnimal(Animal ani) {
		if (this.animais.contains(ani)) {
			this.animais.remove(ani);
			System.out.println("[Animal " + ani.toString() + "excluido com sucesso!]\n");
		}
		else
			System.out.println("Animal inexistente!\n");
	}
	public void excluirAnimais() {
		animais.clear();
		System.out.println("Animais excluidos com sucesso!\n");
	}
	public void gravarAnimais()  {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\animais.dat"));
			for(Animal ani:animais) {
				outputStream.writeObject(ani);
			}
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (outputStream != null ) {
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void recuperarAnimais() {
		ObjectInputStream inputStream = null;
		try {
			inputStream	= new ObjectInputStream (new FileInputStream ("c:\\temp\\animais.dat"));
			Object obj = null;
			while((obj = inputStream.readObject ()) != null) {
				if (obj instanceof Cavalo)  
					this.animais.add((Cavalo)obj);
				else if (obj instanceof Cachorro)  
					this.animais.add((Cachorro)obj);
				else if (obj instanceof Passaro)  
					this.animais.add((Passaro)obj);
			}
		}catch (EOFException ex) {     // when EOF is reached
			System.out.println ("End of file reached");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (inputStream != null ) {
					inputStream.close();
					System.out.println("Animais recuperados com sucesso!\n");
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		Clinica1 cli  = new Clinica1();

		Cavalo pe_de_pano = new Cavalo("P� de Pano",   "Puro Sangue", "Ana");
		Cavalo Alasao = new Cavalo("Alas�o", "Manga larga", "Ana");
		Cachorro  Jordan = new Cachorro ("Jordan","Border Collie", "Carlos");
		Cachorro  Lico = new Cachorro ("Lico", "Liasa", "Carlos");
		Passaro  Piu_piu = new Passaro ("Piu Piu","strigilatus", "Valdir");
		Passaro  pica_pau = new Passaro ("Pica Pau", "Pica Pau", "Valdir");
		
		
		cli.adicionaAnimal(pe_de_pano);
		cli.adicionaAnimal(Alasao);
		cli.adicionaAnimal(Jordan);
		cli.adicionaAnimal(Lico);
		cli.adicionaAnimal(Piu_piu);
		cli.adicionaAnimal(pica_pau);
		cli.listarAnimais();
		cli.gravarAnimais();
		cli.excluirAnimal(pe_de_pano);
		cli.listarAnimais();
		cli.excluirAnimais();
		cli.listarAnimais();
		cli.recuperarAnimais();
		cli.listarAnimais();
	}
}
