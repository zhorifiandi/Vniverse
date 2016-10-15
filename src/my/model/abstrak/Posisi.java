package my.model.abstrak;
//import package Point
import java.awt.Point;

//Kelas non-abstrak Point untuk menentukan lokasi
public class Posisi {
	//private data member
	Point P;

	//Constructor
	public Posisi() {
		P = new Point();
	}

	//Constructor dengan parameter
	public Posisi(int a, int b) {
		P = new Point(a,b);
	}

	//Destructor
	protected void finalize() throws Throwable {

	}

	//Getter 

	//Getter absis
	public int getX() {
		return P.x;
	}

	//Getter ordinat
	public int getY() {
		return P.y;
	}

	//Getter point
	public Point getPoint() {
		return P;
	}

	//Setter

	//Setter absis
	public void setX(int a) {
		P.x = a;
	}

	//Setter ordinat
	public void setY(int b) {
		P.y = b;
	}

	//Setter posisi
	public void SetPosisi(int a, int b) {
		setX(a);
		setY(b);
	}

	//Method untuk menggeser posisi
	public void Geser(int dx, int dy) {
		P.translate(dx, dy);
	}

	//Method untuk output posisi
	public void Print() {
		System.out.println("<" + getX() + "," + getY() + ">");
	}

	//Driver kelas
	public static void main (String[] args) {
		Posisi P = new Posisi();
		System.out.print("Posisi awal : ");
		P.Print();
		System.out.println("Set posisi menjadi <-8,6>");
		P.SetPosisi(-8,6);
		System.out.print("Posisi akhir : ");
		P.Print();

		System.out.println();

		P = new Posisi(-1,3);
		System.out.print("Posisi awal : ");
		P.Print();
		System.out.println("Geser sejauh <4,-4>");
		P.Geser(4,-4);
		System.out.print("Posisi akhir : ");
		P.Print();
	}
}