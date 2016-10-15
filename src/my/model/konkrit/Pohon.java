package my.model.konkrit;

import my.model.abstrak.Tumbuhan;
import my.model.abstrak.Makhluk;
import my.model.abstrak.MakhlukBergerak;

/**
 * @author Praditya Raudi A. / 13514087
 */
public class Pohon extends Tumbuhan {
	/**
	 * Constructor Pohon
	 */
	public Pohon() {
		Kekuatan = 30;
		Umur = 50;
	}
	
	/**
	 * Destructor
	 */
	protected void finalize() throws Throwable {

	}
	/**
	 * Prosedur untuk menambah kekuatan Pohon
	 */
	public void Grow() {
		Kekuatan = Kekuatan + 4;
	}
	/**
	 * Fungsi untuk mengembalikan karakter
	 */
	public char Kar() {
		return 'P';
	}
}
