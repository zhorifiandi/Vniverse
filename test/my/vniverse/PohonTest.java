import org.junit.*;
import static org.junit.Assert.*;

public class PohonTest{
	@Test
	public void test_returnKekuatan(){
		System.out.println("Test GetKekuatan()");
		Pohon S =new Pohon();
		assertTrue(S.GetKekuatan()==30);
	}
	
	@Test
	public void test_returnUmur(){
		System.out.println("Test GetUmur()");
		Pohon S =new Pohon();
		assertTrue(S.GetUmur()==50);
	}
	
	/*@Test
	public void test_returnID(){
		System.out.println("Test GetID()");
		Pohon S =new Pohon();
		assertTrue(S.GetID()==1.0);
	}
	
	@Test
	public void test_returnPosisi(){
		System.out.println("Test GetPosisi()");
		Pohon S =new Pohon();
		assertTrue(S.GetPosisi().getX()==1.0);
	}*/
	
	@Test
	public void test_SetKekuatan(){
		System.out.println("Test SetKekuatan()");
		Pohon S =new Pohon();
		S.SetKekuatan(100);
		assertTrue(S.GetKekuatan()==100);
	}
	
	@Test
	public void test_SetUmur(){
		System.out.println("Test SetUmur()");
		Pohon S =new Pohon();
		S.SetUmur(100);
		assertTrue(S.GetUmur()==100);
	}
	
	@Test
	public void test_Grow(){
		System.out.println("Test Grow()");
		Pohon S =new Pohon();
		S.Grow();
		assertTrue(S.GetKekuatan()==34);
	}
	
	@Test
	public void test_returnKar(){
		System.out.println("Test Kar()");
		Pohon S =new Pohon();
		assertTrue(S.Kar()=='P');
	}
	
}
