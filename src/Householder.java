import java.io.IOException;
import java.util.ArrayList;

public class Householder {
	
	//public static void calcular(ArrayList<ArrayList<Double>> A){
	public static ArrayList<ArrayList<Double>> calcular (ArrayList<ArrayList<Double>> A, Retorno arquivo){
		ArrayList<ArrayList<Double>> Amodificada;
		ArrayList<ArrayList<Double>> Q, H;
		ArrayList<Double> colh;
		int n = A.size();
		
		
		H = MatrixOperations.criarIdentidade(n);
		Amodificada = A;
		//ate n-2 pois preciso deixar 3 diagonais e zerar o resto. 
		for (int h = 0; h < n-2; h++) {
			colh = new ArrayList<>();
			
			//pegar a coluna h da matriz.
			for (int i = 0; i < n; i++) {
				colh.add(i, Amodificada.get(i).get(h));
			}
			
			Q = householderMatrix(colh, h);			
			H = MatrixOperations.multiplicarMatrizes(H, Q);
			//Amodificada = QAmodificadaQ.
			Amodificada = MatrixOperations.multiplicarMatrizes(MatrixOperations.multiplicarMatrizes(Q, Amodificada), Q);
		}
		
		System.out.println("A matriz de householder encontrada foi : ");
		MatrixOperations.print(H);

		System.out.println("\nA matriz tridiagonal encontrada foi : " );
		MatrixOperations.print(Amodificada);
		
		return Amodificada; // matriz tridiagonal
	}
	
	
	public static ArrayList<ArrayList<Double>> householderMatrix(ArrayList<Double> colh, int h){
		ArrayList<Double> v = new ArrayList<>();
		ArrayList<Double> N = new ArrayList<>();
		ArrayList<Double> naux = new ArrayList<>(), n = new ArrayList<>();
		Double cv, cn, N1;
		ArrayList<ArrayList<Double>> Q, I;
		
		
		//pegando os elementos de h+1 a n da coluna.
		for (int i = h +1 ; i < colh.size(); i++) {
			v.add(colh.get(i));
		}
		cv = MatrixOperations.diseuclidiana(v);
		
		//O vetor normal vai ser v - v', onde v' é o v deitado em x e com mesmo comprimento.
		N.addAll(v);
		N1 = N.remove(0);
		if(v.get(0) > 0){
			N.add(0, N1 + cv);
		}
		else N.add(0, N1 - cv);
		

		cn = MatrixOperations.diseuclidiana(N);
		
		//dividindo pela norma.
		for (int i = 0; i < N.size(); i++) {
			naux.add(i, N.get(i)/cn);
		}
		
		//fazendo com que n tenha a dimensao correta
		for (int i = 0; i < colh.size(); i++) {
			if(i > h ){
				n.add(naux.get(i-h-1));
			}else n.add(i, (double)0);
		} 
		
		I = MatrixOperations.criarIdentidade(n.size());
		
		//Q = I - 2*(n * ntranposto) 
		Q = MatrixOperations.subMatrizes(I, MatrixOperations.matEscalar(MatrixOperations.vetorPorTransposto(n), 2));
		
		return Q; //matriz de house holder
	}
	
	public static void main(String[] args) {
		Retorno arquivo = null;
		ArrayList<ArrayList<Double>> A;
		
		try {
			arquivo = ManipuladorArquivo.leitor("/Users/israelcvidal/Documents/workspace/MNII-transformacao de similaridade/matriz.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//Criando e inicializando a matriz A
		A = arquivo.getMatriz();
		

		System.out.println("MN II - Householder \nIsrael de Castro Vidal - 370019");
		System.out.println("A Matriz utilizada foi:");
		MatrixOperations.print(A);
		System.out.println();
		
		
		
		
		Householder.calcular(A, arquivo);
		
	}
}
