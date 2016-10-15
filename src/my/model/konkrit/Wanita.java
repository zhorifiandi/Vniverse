package my.model.konkrit;

import my.model.abstrak.Manusia;
import my.model.abstrak.Makhluk;
import my.model.abstrak.MakhlukBergerak;

//Kelas non-abstrak wanita
public class Wanita extends Manusia implements Runnable {
	//Constructor
	public Thread t;
	private String threadName;
	boolean suspended = false;
	public Wanita() {
		threadName = "Wanita";
		Kekuatan = 40;
		Umur = 40;
		arah = 6;
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
		return 'W';
	}

	//Override method abstrak deltaT
	//pada kelas abstrak MakhlukBergerak
	public int deltaT() {
		return 3;
	}

	//Override method abstrak Kecepatan
	//pada kelas abstrak MakhlukBergerak
	public int Kecepatan() {
		return 1;
	}

	//Driver kelas
	public static void main (String[] args) {
		Makhluk M = new Wanita();

		System.out.println("Karakter wanita : " + M.Kar());
		System.out.println("ID wanita : " + M.GetID());
		System.out.println("Kekuatan wanita : " + M.GetKekuatan());
		System.out.println("Umur wanita : " + M.GetUmur());
		System.out.print("Posisi wanita : ");
		M.GetPosisi().Print();
		System.out.println("Set kekuatan jadi 50");
		M.SetKekuatan(50);
		System.out.println("Kekuatan wanita : " + M.GetKekuatan());

		System.out.println();

		M = new Wanita();
		System.out.println("Karakter wanita : " + M.Kar());
		System.out.println("ID wanita : " + M.GetID());
		System.out.println("Kekuatan wanita : " + M.GetKekuatan());
		System.out.println("Umur wanita : " + M.GetUmur());
		System.out.print("Posisi wanita : ");
		M.GetPosisi().Print();
		System.out.println("Set umur jadi 20");
		M.SetUmur(20);
		System.out.println("Kekuatan wanita : " + M.GetUmur());
	}
}	
