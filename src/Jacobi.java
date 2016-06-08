import java.io.IOException;
import java.util.ArrayList;

public class Jacobi {
	public static ArrayList<ArrayList<Double>>calcular (ArrayList<ArrayList<Double>> A, double tolerancia){
		ArrayList<ArrayList<Double>> J, Abarra, Jij;
		
		J = MatrixOperations.criarIdentidade(A.size());
		Abarra = A;
		
		
		while(MatrixOperations.normaMatrizSemDiagonal(Abarra) > tolerancia){
			for (int j = 0; j < A.size() - 1; j++) {
				for (int i = j+1; i < A.size(); i++) {
					Jij = jacobiMatrix(A.size(), i, j, Abarra.get(j).get(j), Abarra.get(i).get(j), Abarra.get(i).get(i));
					//Abarra = Jt.Abarra.J
					Abarra = MatrixOperations.multiplicarMatrizes(MatrixOperations.transpor(Jij),MatrixOperations.multiplicarMatrizes(Abarra,Jij));
					//acumulando os J.
					J = MatrixOperations.multiplicarMatrizes(J, Jij);
				}
				//System.out.println(j);
			}
		}
		
		System.out.println("A matriz diagonal obtida foi: ");
		MatrixOperations.print(Abarra);
		System.out.println("\nA matriz de jacobi obtida foi: ");
		MatrixOperations.print(J);
	
		System.out.println("\nOs autovalores e autovetores obtidos foram:\n");
		ArrayList<Double> autovalores = MatrixOperations.diagonalParaVetor(Abarra);
		
		for (int i = 0; i < A.size(); i++) {
			System.out.println("Autovalor("+ i + ") = " + autovalores.get(i));
			System.out.println("Autovetor("+ i + ") = " + MatrixOperations.pegarColuna(J, i));
			System.out.println();
		}
		return Abarra;
		
		
	}
	
	public static ArrayList<ArrayList<Double>> jacobiMatrix(int n, int i, int j, Double ajj, Double aij, Double aii) {
		ArrayList<ArrayList<Double>> J = MatrixOperations.criarIdentidade(n);
		double teta = (Math.atan( (2*aij)/(ajj-aii) ))/2;
		
		J.get(j).remove(j);
		J.get(j).add(j, Math.cos(teta));
		
		J.get(j).remove(i);
		J.get(j).add(i, -(Math.sin(teta)));
		
		J.get(i).remove(j);
		J.get(i).add(j, Math.sin(teta));
		
		J.get(i).remove(i);
		J.get(i).add(i, Math.cos(teta));
		
		//System.out.println(J);
		return J;
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
		System.out.println("A Matriz utilizada foi:" + A);
		
		calcular(A, arquivo.getTolerancia());
		
		
		
	}
	
	
	
}
