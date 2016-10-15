import org.junit.*;
import static org.junit.Assert.*;

public class GajahTest{
	@Test
	public void test_returnKekuatan(){
		System.out.println("Test GetKekuatan()");
		Gajah S =new Gajah();
		assertTrue(S.GetKekuatan()==60);
	}
	
	@Test
	public void test_returnUmur(){
		System.out.println("Test GetUmur()");
		Gajah S =new Gajah();
		assertTrue(S.GetUmur()==30);
	}
	
	/*@Test
	public void test_returnID(){
		System.out.println("Test GetID()");
		Gajah S =new Gajah();
		assertTrue(S.GetID()==1.0);
	}
	
	@Test
	public void test_returnPosisi(){
		System.out.println("Test GetPosisi()");
		Gajah S =new Gajah();
		assertTrue(S.GetPosisi().getX()==1.0);
	}*/
	
	@Test
	public void test_SetKekuatan(){
		System.out.println("Test SetKekuatan()");
		Gajah S =new Gajah();
		S.SetKekuatan(10);
		assertTrue(S.GetKekuatan()==10);
	}
	
	@Test
	public void test_SetUmur(){
		System.out.println("Test SetUmur()");
		Gajah S =new Gajah();
		S.SetUmur(100);
		assertTrue(S.GetUmur()==100);
	}
	
	@Test
	public void test_returnArah(){
		System.out.println("Test GetArah()");
		Gajah S =new Gajah();
		assertTrue(S.GetArah()==1);
	}
	
	@Test
	public void test_returnKar(){
		System.out.println("Test Kar()");
		Gajah S =new Gajah();
		assertTrue(S.Kar()=='G');
	}
	
	@Test
	public void test_DeltaT(){
		System.out.println("Test DeltaT()");
		Gajah S =new Gajah();
		assertTrue(S.deltaT()==2);
	}
	
	@Test
	public void test_Kecepatan(){
		System.out.println("Test Kecepatan()");
		Gajah S =new Gajah();
		assertTrue(S.Kecepatan()==2);
	}
	
	@Test
	public void test_GetStronger(){
		System.out.println("Test GetStronger()");
		Pohon S =new Pohon();
		Gajah L =new Gajah();
		Gajah M =new Gajah();
		L.MakanTumbuhan(S);
		M.MakanTumbuhan(L);
		assertTrue((L.GetKekuatan()==90)&&(M.GetKekuatan()==60));
	}
	
	@Test
	public void test_SwitchArah(){
		System.out.println("Test SwitchArah()");
		Gajah S =new Gajah();
		S.SwitchArah();
		assertTrue(S.GetArah()==5);
	}
	
	/*@Test
	public void test_Move(){
		System.out.println("Test move()");
		Gajah S =new Gajah();
		S.move();
		assertTrue((S.GetPosisi().getX()==2)&&(S.GetPosisi().getY()==2));
	}*/
	
}
