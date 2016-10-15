import org.junit.*;
import static org.junit.Assert.*;

public class SingaTest{
	@Test
	public void test_returnKekuatan(){
		System.out.println("Test GetKekuatan()");
		Singa S =new Singa();
		assertTrue(S.GetKekuatan()==80);
	}
	
	@Test
	public void test_returnUmur(){
		System.out.println("Test GetUmur()");
		Singa S =new Singa();
		assertTrue(S.GetUmur()==20);
	}
	
	/*@Test
	public void test_returnID(){
		System.out.println("Test GetID()");
		Singa S =new Singa();
		assertTrue(S.GetID()==1.0);
	}
	
	@Test
	public void test_returnPosisi(){
		System.out.println("Test GetPosisi()");
		Singa S =new Singa();
		assertTrue(S.GetPosisi().getX()==1.0);
	}*/
	
	@Test
	public void test_SetKekuatan(){
		System.out.println("Test SetKekuatan()");
		Singa S =new Singa();
		S.SetKekuatan(10);
		assertTrue(S.GetKekuatan()==10);
	}
	
	@Test
	public void test_SetUmur(){
		System.out.println("Test SetUmur()");
		Singa S =new Singa();
		S.SetUmur(100);
		assertTrue(S.GetUmur()==100);
	}
	
	@Test
	public void test_returnArah(){
		System.out.println("Test GetArah()");
		Singa S =new Singa();
		assertTrue(S.GetArah()==3);
	}
	
	@Test
	public void test_returnKar(){
		System.out.println("Test Kar()");
		Singa S =new Singa();
		assertTrue(S.Kar()=='S');
	}
	
	@Test
	public void test_DeltaT(){
		System.out.println("Test DeltaT()");
		Singa S =new Singa();
		assertTrue(S.deltaT()==2);
	}
	
	@Test
	public void test_Kecepatan(){
		System.out.println("Test Kecepatan()");
		Singa S =new Singa();
		assertTrue(S.Kecepatan()==2);
	}
	
	@Test
	public void test_GetStronger(){
		System.out.println("Test GetStronger()");
		Pohon S =new Pohon();
		Gajah G =new Gajah();
		Singa L =new Singa();
		Singa M =new Singa();
		L.MakanDaging(S);
		M.MakanDaging(G);
		assertTrue((L.GetKekuatan()==80)&&(M.GetKekuatan()==140));
	}
	
	@Test
	public void test_SwitchArah(){
		System.out.println("Test SwitchArah()");
		Singa S =new Singa();
		S.SwitchArah();
		assertTrue(S.GetArah()==7);
	}
	
	/*@Test
	public void test_Move(){
		System.out.println("Test move()");
		Singa S =new Singa();
		S.move();
		assertTrue((S.GetPosisi().getX()==2)&&(S.GetPosisi().getY()==2));
	}*/
	
}
