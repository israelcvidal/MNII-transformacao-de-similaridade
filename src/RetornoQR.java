import java.util.ArrayList;

public class RetornoQR {
	private ArrayList<ArrayList<Double>> Q; 
	private ArrayList<ArrayList<Double>> R;
	
	RetornoQR(ArrayList<ArrayList<Double>> Q, ArrayList<ArrayList<Double>> R){
		this.Q = Q;
		this.R = R;
	}
	
	public ArrayList<ArrayList<Double>> getR() {
		return R;
	}
	public void setR(ArrayList<ArrayList<Double>> r) {
		R = r;
	}
	public ArrayList<ArrayList<Double>> getQ() {
		return Q;
	}
	public void setQ(ArrayList<ArrayList<Double>> q) {
		Q = q;
	}
	
	
}
