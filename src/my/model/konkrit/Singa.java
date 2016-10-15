package my.model.konkrit;

import my.model.abstrak.Carnivore;
import my.model.abstrak.Makhluk;
import my.model.abstrak.MakhlukBergerak;

/**
 * @author Ari Pratama Zhorifiandi / 13514039
 */
public class Singa extends MakhlukBergerak implements Carnivore, Runnable {
		public Thread t;
		private String threadName;
		boolean suspended = false;
		
		/**
		 * Constructor untuk singa
		 */
		//CONSTRUCTOR
		public Singa() {  	
			threadName = "Singa";
			Kekuatan = 80;
			Umur = 20;
			arah = 3;
		 }
		//DESTRUCTOR
		/**
		 * Destructor untuk singa
		 */
		protected void finalize() throws Throwable {

		}
		
		public void run() {
			//int i=0;
			while (true) {
				for(int i=0;i<deltaT();i++) {
					try {
						Thread.sleep(1000);
						synchronized(this) {
							while(suspended) {
								wait();
							}
						}
					} catch (InterruptedException e) {
						System.out.println("Thread " +  threadName + " interrupted.");
					}
				}
				move();
			}
		}
		
		public void start() {
			if (t == null) {
			 t = new Thread (this, threadName);
			 t.start ();
			}
		}
		
		public void suspend() {
			suspended = true;
		}
		
		public synchronized void resume() {
			suspended = false;
			notify();
		}
		
		/**
		 * Fungsi untuk mengeluarkan nilai deltaT
		 */
		public int deltaT() {
			return 2;
		}
		/**
		 * Fungsi untuk mengeluarkan karakter
		 */
		public char Kar() {
			return 'S';
		}
		/**
		 * Fungsi untuk mengeluarkan nilai kecepatan
		 */
		public int Kecepatan() {
			return 2;
		}
		/**
		 * Fungsi untuk mengeluarkan nilai arah
		 */
		
		public int arah() {
			return 3;
		}
		
		public void MakanDaging(Makhluk MB) {
			if ((MB.Kar() == 'G') || (MB.Kar() == 'R')) {
				Kekuatan += MB.GetKekuatan();
			}
		}	
		
		/**
		 * Driver
		 */
		//Driver kelas
		public static void main (String[] args) {
		Makhluk M = new Singa();

		System.out.println("Karakter Singa : " + M.Kar());
		System.out.println("ID Singa : " + M.GetID());
		System.out.println("Kekuatan Singa : " + M.GetKekuatan());
		System.out.println("Umur Singa : " + M.GetUmur());
		System.out.print("Posisi Singa : ");
		M.GetPosisi().Print();
		System.out.println("Set kekuatan jadi 50");
		M.SetKekuatan(50);
		System.out.println("Kekuatan Singa : " + M.GetKekuatan());

		System.out.println();

		M = new Singa();
		System.out.println("Karakter Singa : " + M.Kar());
		System.out.println("ID Singa : " + M.GetID());
		System.out.println("Kekuatan Singa : " + M.GetKekuatan());
		System.out.println("Umur Singa : " + M.GetUmur());
		System.out.print("Posisi Singa : ");
		M.GetPosisi().Print();
		System.out.println("Set umur jadi 20");
		M.SetUmur(20);
		System.out.println("Kekuatan Singa : " + M.GetUmur());

		//deltaT, kecepatan, GetStronger, move, SwitchArah belum dites
		//karena gak tau cara dynamic cast ke MakhlukBergerak
	}
}
