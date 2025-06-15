package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Product;

public class Program
{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
			List<Product> list = new ArrayList<>();
			
			System.out.print("Digite o local do arquivo de entrada: ");
			String localDoArquivo = sc.nextLine();
			
			File arquivo = new File(localDoArquivo);
			String caminho = arquivo.getParent();
			
			boolean criarOut = new File(caminho + "//out").mkdir();
			
			String criarSummary = caminho + "//out//summary.csv";
			
			try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){
				String line = br.readLine();
				
				while(line != null) {
					String[] campos = line.split(",");
					String name = campos[0];
					double price = Double.parseDouble(campos[1]);
					int quantity = Integer.parseInt(campos[2]);
					
					list.add(new Product(name, price, quantity));
					
					line = br.readLine();
				}
				try(BufferedWriter bw = new BufferedWriter(new FileWriter(criarSummary))){
					for(Product product : list) {
						bw.write(product.getName() + "," + String.format("%.2f",product.total()));
						bw.newLine();
					}
				}catch(IOException e) {
					System.out.println("Erro para escrever o arquivo: " + e.getMessage());
				}
			}catch(IOException e) {
				System.out.println("Erro na leitura: " + e.getMessage());
			}
		
		sc.close();
	}

}	