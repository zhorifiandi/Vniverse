import org.junit.*;
import static org.junit.Assert.*;

public class RumputTest{
	@Test
	public void test_returnKekuatan(){
		System.out.println("Test GetKekuatan()");
		Rumput S =new Rumput();
		assertTrue(S.GetKekuatan()==10);
	}
	
	@Test
	public void test_returnUmur(){
		System.out.println("Test GetUmur()");
		Rumput S =new Rumput();
		assertTrue(S.GetUmur()==50);
	}
	
	/*@Test
	public void test_returnID(){
		System.out.println("Test GetID()");
		Rumput S =new Rumput();
		assertTrue(S.GetID()==1.0);
	}
	
	@Test
	public void test_returnPosisi(){
		System.out.println("Test GetPosisi()");
		Rumput S =new Rumput();
		assertTrue(S.GetPosisi().getX()==1.0);
	}*/
	
	@Test
	public void test_SetKekuatan(){
		System.out.println("Test SetKekuatan()");
		Rumput S =new Rumput();
		S.SetKekuatan(100);
		assertTrue(S.GetKekuatan()==100);
	}
	
	@Test
	public void test_SetUmur(){
		System.out.println("Test SetUmur()");
		Rumput S =new Rumput();
		S.SetUmur(100);
		assertTrue(S.GetUmur()==100);
	}
	
	@Test
	public void test_Grow(){
		System.out.println("Test Grow()");
		Rumput S =new Rumput();
		S.Grow();
		assertTrue(S.GetKekuatan()==11);
	}
	
	@Test
	public void test_returnKar(){
		System.out.println("Test Kar()");
		Rumput S =new Rumput();
		assertTrue(S.Kar()=='A');
	}
	
}
