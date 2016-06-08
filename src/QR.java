import java.io.IOException;
import java.util.ArrayList;

public class QR {
	public static void calcular (ArrayList<ArrayList<Double>> A, double tolerancia){
		ArrayList<ArrayList<Double>> Q, A1;
		RetornoQR QR = null;
		Q = MatrixOperations.criarIdentidade(A.size());
		A1 = A;
		
		while(MatrixOperations.normaMatrizSemDiagonal(A1) > tolerancia){
			QR = obterQR(A);
			Q = MatrixOperations.multiplicarMatrizes(Q, QR.getQ()); 
			A1 = MatrixOperations.multiplicarMatrizes(QR.getR(), QR.getQ());
			A = A1;
		}
		
		ArrayList<Double> autovalores = MatrixOperations.diagonalParaVetor(A);
		System.out.println();
		for (int i = 0; i < A.size(); i++) {
			System.out.println("Autovalor("+ i + ") = " + autovalores.get(i));
			System.out.println("Autovetor("+ i + ") = " + MatrixOperations.pegarColuna(Q, i));
			System.out.println();
		}
		
	}
	
	
	//Decompoe A em QR 
	private static RetornoQR obterQR(ArrayList<ArrayList<Double>> A) {
		ArrayList<ArrayList<Double>> Qij = new ArrayList<>(), Abarra = null , Q = MatrixOperations.criarIdentidade(A.size());
		
		for (int j = 0; j < A.size() - 1; j++) {
			for (int i = j+1; i < A.size(); i++) {
				Qij = montar_Qij( A.get(i).get(j), A.get(j).get(j), i, j, A.size());
				Abarra = MatrixOperations.multiplicarMatrizes(MatrixOperations.transpor(Qij), A);
				Q = MatrixOperations.multiplicarMatrizes(Q, Qij); 
				A = Abarra;
			}
		}
		return new RetornoQR(Q, A);
	}

	//Calcula a matriz de reflexao para zerar os elementos abaixo da diagonal
	private static ArrayList<ArrayList<Double>> montar_Qij(double Aij, double Ajj, int i, int j, int size) {
		ArrayList<ArrayList<Double>> Q = MatrixOperations.criarIdentidade(size);
		double teta;
		if (Ajj != 0 ){
			teta = Math.atan(Aij/Ajj);
		}
		else teta = Math.PI/2;
		
		Q.get(j).remove(j);
		Q.get(j).add(j, Math.cos(teta));
		
		Q.get(j).remove(i);
		Q.get(j).add(i, -(Math.sin(teta)));
		
		Q.get(i).remove(j);
		Q.get(i).add(j, Math.sin(teta));
		
		Q.get(i).remove(i);
		Q.get(i).add(i, Math.cos(teta));
		
		return Q;
	}

	public static void main(String[] args) {
		ArrayList<ArrayList<Double>> A;;
		Retorno arquivo = null;
		
		try {
			arquivo = ManipuladorArquivo.leitor("/Users/israelcvidal/Documents/workspace/MNII - transformacao de similaridade/matriz.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		A = arquivo.getMatriz();
		System.out.println("MN II - Jacobi \nIsrael de Castro Vidal - 370019");
		System.out.println("A Matriz utilizada foi:" );
		MatrixOperations.print(A);
		
		calcular(A, arquivo.getTolerancia());
		
		
		
	}
	
}
