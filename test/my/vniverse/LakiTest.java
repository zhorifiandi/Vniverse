import org.junit.*;
import static org.junit.Assert.*;

public class LakiTest{
	@Test
	public void test_returnKekuatan(){
		System.out.println("Test GetKekuatan()");
		Laki S =new Laki();
		assertTrue(S.GetKekuatan()==100);
	}
	
	@Test
	public void test_returnUmur(){
		System.out.println("Test GetUmur()");
		Laki S =new Laki();
		assertTrue(S.GetUmur()==40);
	}
	
	/*@Test
	public void test_returnID(){
		System.out.println("Test GetID()");
		Laki S =new Laki();
		assertTrue(S.GetID()==1.0);
	}
	
	@Test
	public void test_returnPosisi(){
		System.out.println("Test GetPosisi()");
		Laki S =new Laki();
		assertTrue(S.GetPosisi().getX()==1.0);
	}*/
	
	@Test
	public void test_SetKekuatan(){
		System.out.println("Test SetKekuatan()");
		Laki S =new Laki();
		S.SetKekuatan(10);
		assertTrue(S.GetKekuatan()==10);
	}
	
	@Test
	public void test_SetUmur(){
		System.out.println("Test SetUmur()");
		Laki S =new Laki();
		S.SetUmur(100);
		assertTrue(S.GetUmur()==100);
	}
	
	@Test
	public void test_returnArah(){
		System.out.println("Test GetArah()");
		Laki S =new Laki();
		assertTrue(S.GetArah()==2);
	}
	
	@Test
	public void test_returnKar(){
		System.out.println("Test Kar()");
		Laki S =new Laki();
		assertTrue(S.Kar()=='L');
	}
	
	@Test
	public void test_DeltaT(){
		System.out.println("Test DeltaT()");
		Laki S =new Laki();
		assertTrue(S.deltaT()==2);
	}
	
	@Test
	public void test_Kecepatan(){
		System.out.println("Test Kecepatan()");
		Laki S =new Laki();
		assertTrue(S.Kecepatan()==2);
	}
	
	@Test
	public void test_GetStronger(){
		System.out.println("Test GetStronger()");
		Pohon S =new Pohon();
		Laki L =new Laki();
		Laki M =new Laki();
		L.GetStronger(S);
		M.GetStronger(L);
		assertTrue((L.GetKekuatan()==130)&&(M.GetKekuatan()==100));
	}
	
	@Test
	public void test_SwitchArah(){
		System.out.println("Test SwitchArah()");
		Laki S =new Laki();
		S.SwitchArah();
		assertTrue(S.GetArah()==6);
	}
	
	/*@Test
	public void test_Move(){
		System.out.println("Test move()");
		Laki S =new Laki();
		S.move();
		assertTrue((S.GetPosisi().getX()==2)&&(S.GetPosisi().getY()==2));
	}*/
	
}
