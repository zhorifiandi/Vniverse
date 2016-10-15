package my.model.konkrit;

import my.model.abstrak.Herbivora;
import my.model.abstrak.Makhluk;
import my.model.abstrak.MakhlukBergerak;

//Kelas non-abstrak makhluk bergerak
public class Rusa extends MakhlukBergerak implements Herbivora, Runnable {
    //Constructor
    public Thread t;
    private String threadName;
    boolean suspended = false;
    public Rusa() {
		threadName = "Rusa";
        Kekuatan = 40;
        Umur = 30;
        arah = 5;
	}
    
    public void run() {
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
        return 'R';
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
    /*public static void main (String[] args) {
        Makhluk R1 = new Rusa();
        Rusa R2 = new Rusa();
        
        System.out.println(R1.Kar());
        ((Rusa)R1).start();
        R2.start();
        try {
			Thread.sleep(3000);
			((Rusa)R1).suspend();
			System.out.println("Thread interrupted.");
			((Rusa)R1).resume();   
        
			Thread.sleep(1000);
			((Rusa)R1).suspend();
			((Rusa)R1).resume();
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted.");
		}
		 
        //R2.suspend();   

        //deltaT, kecepatan, GetStronger, move, SwitchArah belum dites
        //karena gak tau cara dynamic cast ke MakhlukBergerak
    }*/
}
