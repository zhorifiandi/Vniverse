package my.model.abstrak;
//import package Random untuk acak posisi awal
import java.util.Random;
//import package Point untuk posisi

//Kelas abstrak makhluk
public abstract class Makhluk {
	//Private data member
	//Static data member
	private static int jumlah_makhluk = 0;
	private static int total_makhluk = 0;
	
	//Protected data member
	protected int Umur;
	protected int ID;
	protected int Kekuatan;
	protected Posisi posisi;

	//Constructor
	public Makhluk() {
		total_makhluk++;
		jumlah_makhluk++;
		ID = total_makhluk;
	    /*Random rand = new Random();
		int x = rand.nextInt() % 10;
		int y = rand.nextInt() % 10;*/
		posisi = new Posisi();
		posisi.SetPosisi((int)(Math.random()*10), (int)(Math.random()*10));
	}

	//Destructor
	protected void finalize() throws Throwable {
		jumlah_makhluk--;
	}

	//Getter

	//Getter karakter (pure virtual/abstract method)
	public abstract char Kar();

	//Getter kekuatan
	public int GetKekuatan() {
		return Kekuatan;
	}
	
	//Getter umur
	public int GetUmur() {
		return Umur;
	}

	//Getter ID
	public int GetID() {
		return ID;
	}

	//Getter posisi
	public Posisi GetPosisi() {
		return posisi;
	}

	//Setter

	//Setter kekuatan
	public void SetKekuatan(int K) {
		Kekuatan = K;
	}

	//Setter umur
	public void SetUmur(int U) {
		Umur = U;
	}
        
        public void SetPosisi(Posisi P) {
		posisi = P;
	}

	//Driver kelas tidak perlu ada karena
	//makhluk adalah kelas abstrak
}
