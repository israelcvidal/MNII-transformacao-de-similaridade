import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class ManipuladorArquivo {

	public static Retorno leitor(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String str;
		Integer n;
		Double tolerancia;
		StringTokenizer tkn;
		ArrayList<ArrayList<Double>> matriz = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> vetor = new ArrayList<Double>();
		
		//pegando o n da matriz nxn
		str = buffRead.readLine();
		n = Integer.parseInt(str);
		
		//criando a matriz nxn
		for (int i = 0; i < n; i++) {
			matriz.add(new ArrayList<Double>());
		}
		
		//lendo a matriz do arquivo
		for (int i = 0; i < n; i++) {
			str = buffRead.readLine();
			tkn = new StringTokenizer(str, "\t");
			for (int j = 0; j < n; j++) {
				matriz.get(i).add(Double.parseDouble(tkn.nextToken()));
			}
		}
		
		//lendo o vetor inicial do arquivo.
		for (int i = 0; i < n; i++) {
			str = buffRead.readLine();
			vetor.add(Double.parseDouble(str));
		}
		
		//lendo a tolerancia do arquivo
		
		str = buffRead.readLine();
		tolerancia = Double.parseDouble(str);
		
		//Setando o retorno
		Retorno ret = new Retorno(matriz, vetor, tolerancia);
		
		buffRead.close();
		
		return ret;
	}

	public static void escritor(String path, Retorno ret) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		
		buffWrite.write("Autovalor obtido = " + ret.getAutovalor() + "\nAutovetor obtido = " + ret.getAutovetor().toString());
		
		buffWrite.close();
	}
	
	public static void escritor(String path, ArrayList<Double> mi, ArrayList<Double> autovalor, ArrayList<ArrayList<Double>> autovetor) throws IOException{
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		
		for (int i = 0; i < autovalor.size(); i++) {
			buffWrite.write("Mi("+i+") = " + mi.get(i) +"\nAutovalor("+ i +") = " + autovalor.get(i) + "\nAutovetor("+i +") = "+  autovalor.get(i).toString() + "\n---\n");
		}
		
		buffWrite.close();
	}
	
}

