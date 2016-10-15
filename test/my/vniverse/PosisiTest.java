import org.junit.*;
import static org.junit.Assert.*;

public class PosisiTest{
	@Test
	public void test_returnDefault(){
		System.out.println("Test Constructor Default");
		Posisi S =new Posisi();
		assertTrue((S.getX() == 0) && (S.getY() == 0));
	}
	
	@Test
	public void test_returnParameter(){
		System.out.println("Test Constructor with Parameter");
		Posisi S =new Posisi(4,5);
		assertTrue((S.getX() == 4) && (S.getY() == 5));
	}
	
	@Test
	public void test_SetX(){
		System.out.println("Test setX()");
		Posisi S =new Posisi();
		S.setX(10);
		assertTrue(S.getX()==10);
	}
	
	@Test
	public void test_SetY(){
		System.out.println("Test setY()");
		Posisi S =new Posisi();
		S.setY(10);
		assertTrue(S.getY()==10);
	}
	
	@Test
	public void test_SetPosisi(){
		System.out.println("Test SetPosisi()");
		Posisi S =new Posisi();
		S.SetPosisi(10,11);
		assertTrue((S.getX() == 10) && (S.getY() == 11));
	}
	
	@Test
	public void test_Geser(){
		System.out.println("Test Geser()");
		Posisi S =new Posisi();
		S.Geser(3,3);
		assertTrue((S.getX() == 3) && (S.getY() == 3));
	}
	
	/*@Test
	public void test_Print(){
		System.out.println("Test Print()");
		Posisi S =new Posisi();
		S.Print();
		assertTrue((S.getX() == 3) && (S.getY() == 3));
	}*/
	
}
