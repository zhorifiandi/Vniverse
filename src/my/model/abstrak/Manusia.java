package my.model.abstrak;
//Kelas abstrak manusia
public abstract class Manusia extends MakhlukBergerak {
	//Constructor
	public Manusia() {

	}

	//Destructor
	protected void finalize() throws Throwable {

	}

	//Override method abstrak getstronger
	//pada kelas abstrak makhluk
	public void GetStronger (Makhluk M) {
		if ((M.Kar()!='L') && (M.Kar()!='W')) {
			Kekuatan += M.GetKekuatan();
		}
	}

	//Driver kelas tidak perlu ada karena
	//makhluk adalah kelas abstrak
}