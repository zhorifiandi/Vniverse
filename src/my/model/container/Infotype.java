package my.model.container;

import my.model.abstrak.Makhluk;

//Import package util untuk mekanisme I/O
import java.util.*;

//Kelas non-abstrak yang menyatakan struktur data Infotype
public class Infotype {
	//private data member
	private Makhluk M;
	private boolean Del;

	//Constructor
	public Infotype() {
		Del = false;
	}

	//Destructor
	protected void finalize() throws Throwable {

	}

	//Getter

	//Getter Makhluk
	public Makhluk GetMakhluk() {
		return M;
	}

	//Getter Del
	public boolean GetDel() {
		return Del;
	}

	//Setter

	//Setter Makhluk
	public void SetMakhluk(Makhluk MK) {
		M = MK;
	}

	//Setter Del
	public void SetDel(boolean bool) {
		Del = bool;
	}

	//Driver kelas
	public static void main (String[] args) {
		Infotype IF = new Infotype();
		System.out.println("Inisiasi Del : " + IF.GetDel());
		System.out.println("Set Del menjadi true");
		IF.SetDel(true);
		System.out.println("Del baru : " + IF.GetDel());
	}
}