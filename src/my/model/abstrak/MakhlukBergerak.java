package my.model.abstrak;
//Kelas abstrak makhluk bergerak
public abstract class MakhlukBergerak extends Makhluk {
	//Protected data member
	protected int arah;

	//Constructor 
	public MakhlukBergerak() {

	}

	//Destructor
	protected void finalize() throws Throwable {

	}

	//Getter arah
	public int GetArah() {
		return arah;
	}

	//Method abstrak yang mengembalikan nilai
	//interval pergerakan
	public abstract int deltaT();

	//Method abstrak yang mengembalikan nilai
	//jauhnya pergerakan makhluk (jauhnya perpindahan posisi)
	public abstract int Kecepatan();

	//Method untuk mengubah posisi makhluk (bergerak)
	public void move() {
		
		int t = Kecepatan();
		switch (arah) {
			case 1 	:	posisi.Geser(0,t); 
					  	break;
			case 2 	: 	posisi.Geser(t,t); 
						break;
			case 3 	: 	posisi.Geser(t,0); 
						break;
			case 4 	: 	posisi.Geser(t,t*(-1)); 
						break;
			case 5 	: 	posisi.Geser(0,t*(-1)); 
						break;
			case 6 	: 	posisi.Geser(t*(-1),t*(-1)); 
						break;
			case 7 	: 	posisi.Geser(t*(-1),0); 
						break;
			case 8 	: 	posisi.Geser(t*(-1),t); 
						break;
			default : 	break;
		}
	}

	//Method untuk membalik arah makhluk
	public void SwitchArah() {
		switch (arah) {
		case 1 :	arah = 5; 
					break;
		case 2 :	arah = 6; 
					break;
		case 3 :	arah = 7; 
					break;
		case 4 :	arah = 8; 
					break;
		case 5 :	arah = 1; 
					break;
		case 6 :	arah = 2; 
					break;
		case 7 :	arah = 3; 
					break;
		case 8 :	arah = 4; 
					break;
		default :	break;
		}
	}

	//Driver kelas tidak perlu ada karena
	//makhluk adalah kelas abstrak
}
