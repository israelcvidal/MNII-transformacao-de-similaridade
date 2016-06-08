import java.io.IOException;
import java.util.ArrayList;

public class PotenciaInversa {
	
	public static Retorno resolver(ArrayList<ArrayList<Double>> A, ArrayList<Double> vk1, double tolerancia) {
		double lambidak, lambidak1;
		ArrayList<Double> vk, phik; 
		Retorno ret;


		//Criando os vetores
		vk = new ArrayList<Double>();
		phik = new ArrayList<Double>();
				
		//autovalor inicial
		lambidak1 = 0;

		do{
			phik.removeAll(phik);
			lambidak = lambidak1;
			
			vk = vk1;
			//phik = normalizacao do vetor k
			for (int i = 0; i < vk.size(); i++) {
				phik.add(i, vk.get(i)/MatrixOperations.diseuclidiana(vk) );
			}
			
			
			//atualizando o vk atual
			vk1 = MatrixOperations.lu(A, phik);
			
			//lambidak1
			lambidak1 = MatrixOperations.prodescalar(phik, vk1);
			
		}while(Math.abs( (lambidak1-lambidak)/lambidak1) > tolerancia);
		
		ret = new Retorno(1/lambidak1, phik);

		return ret;
	}
	
	
	
	public static void main(String[] args) {
		ArrayList< ArrayList<Double> > A = new ArrayList<ArrayList<Double>>();
		Retorno arquivo = null;
		ArrayList<Double> vk1;
		Retorno ret;
				
		try {
			arquivo = ManipuladorArquivo.leitor("/Users/israelcvidal/Documents/workspace/MNII-potencia/matriz.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//Criando e inicializando a matriz A
		A = arquivo.getMatriz();
		
		// criando e inicializando autovetor inicial
		vk1 = arquivo.getVetor();
		
	
		System.out.println("MN II - Autovalores e Autovetores \nIsrael de Castro Vidal - 370019");
		System.out.println("Metodo da Potencia Regular");
		System.out.println("A Matriz utilizada foi:" + A);
		System.out.println("O vetor inicial foi: " + arquivo.getVetor());
		
		
		ret = PotenciaInversa.resolver(A, vk1, arquivo.getTolerancia());
		
		System.out.println("autovalor = " + ret.getAutovalor());
		System.out.println("autoveto = " + ret.getAutovetor());
		
		
		
		
		try {
			ManipuladorArquivo.escritor("/Users/israelcvidal/Documents/workspace/MNII-potencia/resultado.txt", ret);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}