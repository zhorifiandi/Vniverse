package my.model.container;

import my.model.abstrak.Makhluk;

//Import library vector yang disediakan Java
import java.util.Vector;
import my.model.konkrit.Singa;

public class ListMakhluk {
	//private data member listmakhluk
	//menggunakan elemen infotype
	//dan struktur data vector
	private Vector<Infotype> Vec;

	//Constructor
	public ListMakhluk() {
		Vec = new Vector<Infotype>();
	}

	//Destructor
	protected void finalize() throws Throwable {
		delAll();
		Dealocate();
	}

	//Method untuk mengecek apakah list kosong
	public boolean isEmpty() {
		return ((nEff() == 0) || (nbElmt()==0));
	}

	//Method yang mengembalikan jumlah elemen list
	public int nbElmt() {
		return Vec.size();
	}

	//Method yang mengembalikan jumlah efektif elemen list
	//yaitu elemen yang belum di-delete
	public int nEff() {
		int jumlah = 0;
		for (int i=1; i<=nbElmt(); i++) {
			if(!GetElmt(i).GetDel()) {
				jumlah++;
			}
		}
		return jumlah;
	}

	//Method untuk mengembalikan elemen list pada
	//indeks tertentu
	public Infotype GetElmt(int idx) {
    	return Vec.elementAt(idx-1);
	}

	//Method untuk menambahkan makhluk ke list
	//pada indeks sesuai ID makhluk
	public void Insert(Makhluk MK) {
		Infotype info = new Infotype();
		info.SetMakhluk(MK);
		Vec.add(info);
	}

	//Method untuk menghapus makhluk dari list
	//berdasarkan ID makhluk
	public void Delete(Makhluk M) {
		GetElmt(M.GetID()).SetDel(true);
	}

	//Method untuk menghapus makhluk dari list
	//berdasarkan indeks
	public void Delete(int indeks) {
		GetElmt(indeks).SetDel(true);
	}

	//Method untuk menghapus seluruh elemen
	public void delAll() {
		for (int i=0; i<nbElmt(); i++) {
			GetElmt(i).SetDel(true);
		}
	}	

	//Method untuk mendealokasi list
	public void Dealocate() {
		Vec.clear()	;
	}

	//Driver kelas
	public static void main (String[] args) {
		ListMakhluk LM = new ListMakhluk();
		
		LM.Insert(new Singa());
		System.out.println(LM.GetElmt(1).GetMakhluk().Kar());

		/*if (LM.isEmpty()) {
			System.out.println("List masih kosong");
		} else {
			System.out.println("List sudah terisi");
		}

		System.out.println();

		LM.Insert(new Laki());
		LM.Insert(new Wanita());
		LM.Insert(new Wanita());
		LM.Insert(new Laki());
		System.out.println("Ukuran list : " + LM.nbElmt());
		System.out.println("Ukuran list efektif : " + LM.nEff());
		for (int i=0; i<LM.nbElmt(); i++) {
			if (!LM.GetElmt(i).GetDel()) {
				System.out.println(LM.GetElmt(i).GetMakhluk().Kar());
			}
		}

		System.out.println();

		LM.Delete(1);
		LM.Delete(2);
		System.out.println("Ukuran list setelah wanita dihapus : " + LM.nbElmt());
		System.out.println("Ukuran list efektif setelah wanita dihapus : " + LM.nEff());
		for (int i=0; i<LM.nbElmt(); i++) {
			if (!LM.GetElmt(i).GetDel()) {
				System.out.println(LM.GetElmt(i).GetMakhluk().Kar());
			}
		}

		System.out.println();

		System.out.println("Delete semua elemen");
		LM.delAll();
		System.out.println("Ukuran list setelah delete semua elemen : " + LM.nbElmt());
		System.out.println("Ukuran list efektif setelah delete semua elemen : " + LM.nEff());

		System.out.println();

		System.out.println("Dealokasi list");
		LM.Dealocate();
		System.out.println("Ukuran list setelah dealokasi : " + LM.nbElmt());
		System.out.println("Ukuran list efektif setelah dealokasi : " + LM.nEff());

		if (LM.isEmpty()) {
			System.out.println("List sudah kosong");
		} else {	
			System.out.println("List masih terisi");
		}*/
	}
}
