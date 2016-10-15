package my.model.konkrit;

import my.model.abstrak.Herbivora;
import my.model.abstrak.Makhluk;
import my.model.abstrak.MakhlukBergerak;

//Kelas non-abstrak makhluk bergerak
public class Gajah extends MakhlukBergerak implements Herbivora, Runnable {
    
    //Constructor
    public Thread t;
	private String threadName;
	boolean suspended = false;
    public Gajah() {
		threadName = "Gajah";
        Kekuatan = 60;
        Umur = 30;
        arah = 1;
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
        return 'G';
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

    public void MakanTumbuhan(Makhluk MB) {
        if((MB.Kar() == 'P') || (MB.Kar() == 'A')) {
            Kekuatan += MB.GetKekuatan();
        }
    }

    //Driver kelas
    public static void main (String[] args) {
        Makhluk M = new Gajah();

        System.out.println("Karakter gajah : " + M.Kar());
        System.out.println("ID gajah : " + M.GetID());
        System.out.println("Kekuatan gajah : " + M.GetKekuatan());
        System.out.println("Umur gajah : " + M.GetUmur());
        System.out.print("Posisi gajah : ");
        M.GetPosisi().Print();
        System.out.println("Set kekuatan jadi 50");
        M.SetKekuatan(50);
        System.out.println("Kekuatan gajah : " + M.GetKekuatan());

        System.out.println();

        M = new Gajah();
        System.out.println("Karakter gajah : " + M.Kar());
        System.out.println("ID gajah : " + M.GetID());
        System.out.println("Kekuatan gajah : " + M.GetKekuatan());
        System.out.println("Umur gajah : " + M.GetUmur());
        System.out.print("Posisi gajah : ");
        M.GetPosisi().Print();
        System.out.println("Set umur jadi 20");
        M.SetUmur(20);
        System.out.println("Kekuatan gajah : " + M.GetUmur());

        //deltaT, kecepatan, GetStronger, move, SwitchArah belum dites
        //karena gak tau cara dynamic cast ke MakhlukBergerak
    }
}
