import java.util.ArrayList;

public class Retorno {
	private ArrayList<ArrayList<Double>> matriz;
	private ArrayList<Double> vetor, autovetor;
	private Double tolerancia, autovalor;
	
	public Retorno(ArrayList<ArrayList<Double>> matriz, ArrayList<Double> vetor, Double tolerancia){
		setMatriz(matriz);
		setVetor(vetor);
		setTolerancia(tolerancia);
	}
	
	public Retorno(Double autovalor, ArrayList<Double> autovetor){
		setAutovalor(autovalor);
		setAutovetor(autovetor);
	}

	public ArrayList<ArrayList<Double>> getMatriz() {
		return matriz;
	}

	public void setMatriz(ArrayList<ArrayList<Double>> matriz) {
		this.matriz = matriz;
	}
	
	public ArrayList<Double> getVetor() {
		return vetor;
	}

	public void setVetor(ArrayList<Double> vetor) {
		this.vetor = vetor;
	}

	public Double getTolerancia() {
		return tolerancia;
	}

	public void setTolerancia(Double tolerancia) {
		this.tolerancia = tolerancia;
	}

	public ArrayList<Double> getAutovetor() {
		return autovetor;
	}

	public void setAutovetor(ArrayList<Double> autovetor) {
		this.autovetor = autovetor;
	}

	public Double getAutovalor() {
		return autovalor;
	}

	public void setAutovalor(Double autovalor) {
		this.autovalor = autovalor;
	}

	
}
