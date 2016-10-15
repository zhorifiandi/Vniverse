package my.model.konkrit;

import my.model.abstrak.Manusia;
import my.model.abstrak.Makhluk;
import my.model.abstrak.MakhlukBergerak;

//Kelas non-abstrak manusia
public class Laki extends Manusia implements Runnable {
	//Constructor
	public Thread t;
	private String threadName;
	boolean suspended = false;
	public Laki() {
		threadName = "Laki";
		Kekuatan = 100;
		Umur = 40;
		arah = 2;
	}

	//Destructor
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

	//Override method abstrak Kar
	//pada kelas abstrak Makhluk
	public char Kar() {
		return 'L';
	}

	//Override method abstrak deltaT
	//pada kelas abstrak MakhlukBergerak
	public int deltaT() {
		return 2;
	}

	//Override method abstrak Kecepatan
	//pada kelas abstrak MakhlukBergerak
	public int Kecepatan() {
		return 2;
	}

	//Driver kelas
	public static void main (String[] args) {
		Makhluk M = new Laki();

		System.out.println("Karakter laki : " + M.Kar());
		System.out.println("ID laki : " + M.GetID());
		System.out.println("Kekuatan laki : " + M.GetKekuatan());
		System.out.println("Umur laki : " + M.GetUmur());
		System.out.print("Posisi laki : ");
		M.GetPosisi().Print();
		System.out.println("Set kekuatan jadi 50");
		M.SetKekuatan(50);
		System.out.println("Kekuatan laki : " + M.GetKekuatan());

		System.out.println();

		M = new Laki();
		System.out.println("Karakter laki : " + M.Kar());
		System.out.println("ID laki : " + M.GetID());
		System.out.println("Kekuatan laki : " + M.GetKekuatan());
		System.out.println("Umur laki : " + M.GetUmur());
		System.out.print("Posisi laki : ");
		M.GetPosisi().Print();
		System.out.println("Set umur jadi 20");
		M.SetUmur(20);
		System.out.println("Kekuatan laki : " + M.GetUmur());

		//deltaT, kecepatan, GetStronger, move, SwitchArah belum dites
		//karena gak tau cara dynamic cast ke MakhlukBergerak
	}
}
