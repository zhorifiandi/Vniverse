import org.junit.*;
import static org.junit.Assert.*;

public class WanitaTest{
	@Test
	public void test_returnKekuatan(){
		System.out.println("Test GetKekuatan()");
		Wanita S =new Wanita();
		assertTrue(S.GetKekuatan()==40);
	}
	
	@Test
	public void test_returnUmur(){
		System.out.println("Test GetUmur()");
		Wanita S =new Wanita();
		assertTrue(S.GetUmur()==40);
	}
	
	/*@Test
	public void test_returnID(){
		System.out.println("Test GetID()");
		Wanita S =new Wanita();
		assertTrue(S.GetID()==1.0);
	}
	
	@Test
	public void test_returnPosisi(){
		System.out.println("Test GetPosisi()");
		Wanita S =new Wanita();
		assertTrue(S.GetPosisi().getX()==1.0);
	}*/
	
	@Test
	public void test_SetKekuatan(){
		System.out.println("Test SetKekuatan()");
		Wanita S =new Wanita();
		S.SetKekuatan(10);
		assertTrue(S.GetKekuatan()==10);
	}
	
	@Test
	public void test_SetUmur(){
		System.out.println("Test SetUmur()");
		Wanita S =new Wanita();
		S.SetUmur(100);
		assertTrue(S.GetUmur()==100);
	}
	
	@Test
	public void test_returnArah(){
		System.out.println("Test GetArah()");
		Wanita S =new Wanita();
		assertTrue(S.GetArah()==6);
	}
	
	@Test
	public void test_returnKar(){
		System.out.println("Test Kar()");
		Wanita S =new Wanita();
		assertTrue(S.Kar()=='W');
	}
	
	@Test
	public void test_DeltaT(){
		System.out.println("Test DeltaT()");
		Wanita S =new Wanita();
		assertTrue(S.deltaT()==3);
	}
	
	@Test
	public void test_Kecepatan(){
		System.out.println("Test Kecepatan()");
		Wanita S =new Wanita();
		assertTrue(S.Kecepatan()==1);
	}
	
	@Test
	public void test_GetStronger(){
		System.out.println("Test GetStronger()");
		Pohon S =new Pohon();
		Wanita L =new Wanita();
		Wanita M =new Wanita();
		L.GetStronger(S);
		M.GetStronger(L);
		assertTrue((L.GetKekuatan()==70)&&(M.GetKekuatan()==40));
	}
	
	@Test
	public void test_SwitchArah(){
		System.out.println("Test SwitchArah()");
		Wanita S =new Wanita();
		S.SwitchArah();
		assertTrue(S.GetArah()==2);
	}
	
	/*@Test
	public void test_Move(){
		System.out.println("Test move()");
		Wanita S =new Wanita();
		S.move();
		assertTrue((S.GetPosisi().getX()==2)&&(S.GetPosisi().getY()==2));
	}*/
	
}
