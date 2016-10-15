package my.model.konkrit;

import my.model.abstrak.Tumbuhan;
import my.model.abstrak.Makhluk;
import my.model.abstrak.MakhlukBergerak;

public class Rumput extends Tumbuhan {
	public Rumput() {
		Kekuatan = 10;
		Umur = 50;
	}
	
	//Destructor
	protected void finalize() throws Throwable {

	}
	
	public void Grow() {
		Kekuatan = Kekuatan + 1;
	}
	
	public char Kar() {
		return 'A';
	}
}
