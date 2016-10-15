package my.model.konkrit;

import my.model.abstrak.Carnivore;
import my.model.abstrak.Makhluk;
import my.model.abstrak.MakhlukBergerak;

/**
 * @author Ari Pratama Zhorifiandi / 13514039
 */
public class Cheetah extends MakhlukBergerak implements Carnivore, Runnable {
		
		public Thread t;
		private String threadName;
		boolean suspended = false;
		
		//CONSTRUCTOR
		/**
		 * Constructor untuk kelas Cheetah
		 */		 
		public Cheetah() {  
			threadName = "Cheetah";
			Kekuatan = 60;
			Umur = 20;
			arah = 7;
		 }
		//DESTRUCTOR
		/**
		 * Destructor untuk kelas Cheetah
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
			return 1;
		}
		/**
		 * Fungsi untuk mengeluarkan karakter
		 */
		public char Kar() {
			return 'C';
		}
		/**
		 * Fungsi untuk mengeluarkan nilai kecepatan
		 */	
		public int Kecepatan() {
			return 3;
		}
		/**
		 * Fungsi untuk mengeluarkan nilai arah
		 */
		public int arah() {
			return 7;
		}
		
		public void MakanDaging(Makhluk MB) {
			if ((MB.Kar() == 'G') || (MB.Kar() == 'R')) {
				Kekuatan += MB.GetKekuatan();
			}
		}	
		
		//Driver kelas
	public static void main (String[] args) {
		Makhluk M = new Cheetah();

		System.out.println("Karakter Cheetah : " + M.Kar());
		System.out.println("ID Cheetah : " + M.GetID());
		System.out.println("Kekuatan Cheetah : " + M.GetKekuatan());
		System.out.println("Umur Cheetah : " + M.GetUmur());
		System.out.print("Posisi Cheetah : ");
		M.GetPosisi().Print();
		System.out.println("Set kekuatan jadi 50");
		M.SetKekuatan(50);
		System.out.println("Kekuatan Cheetah : " + M.GetKekuatan());

		System.out.println();

		M = new Cheetah();
		System.out.println("Karakter Cheetah : " + M.Kar());
		System.out.println("ID Cheetah : " + M.GetID());
		System.out.println("Kekuatan Cheetah : " + M.GetKekuatan());
		System.out.println("Umur Cheetah : " + M.GetUmur());
		System.out.print("Posisi Cheetah : ");
		M.GetPosisi().Print();
		System.out.println("Set umur jadi 20");
		M.SetUmur(20);
		System.out.println("Kekuatan Cheetah : " + M.GetUmur());

		//deltaT, kecepatan, GetStronger, move, SwitchArah belum dites
		//karena gak tau cara dynamic cast ke MakhlukBergerak
	}
}
