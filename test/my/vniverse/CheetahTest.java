import org.junit.*;
import static org.junit.Assert.*;

public class CheetahTest{
	@Test
	public void test_returnKekuatan(){
		System.out.println("Test GetKekuatan()");
		Cheetah S =new Cheetah();
		assertTrue(S.GetKekuatan()==60);
	}
	
	@Test
	public void test_returnUmur(){
		System.out.println("Test GetUmur()");
		Cheetah S =new Cheetah();
		assertTrue(S.GetUmur()==20);
	}
	
	/*@Test
	public void test_returnID(){
		System.out.println("Test GetID()");
		Cheetah S =new Cheetah();
		assertTrue(S.GetID()==1.0);
	}
	
	@Test
	public void test_returnPosisi(){
		System.out.println("Test GetPosisi()");
		Cheetah S =new Cheetah();
		assertTrue(S.GetPosisi().getX()==1.0);
	}*/
	
	@Test
	public void test_SetKekuatan(){
		System.out.println("Test SetKekuatan()");
		Cheetah S =new Cheetah();
		S.SetKekuatan(10);
		assertTrue(S.GetKekuatan()==10);
	}
	
	@Test
	public void test_SetUmur(){
		System.out.println("Test SetUmur()");
		Cheetah S =new Cheetah();
		S.SetUmur(100);
		assertTrue(S.GetUmur()==100);
	}
	
	@Test
	public void test_returnArah(){
		System.out.println("Test GetArah()");
		Cheetah S =new Cheetah();
		assertTrue(S.GetArah()==7);
	}
	
	@Test
	public void test_returnKar(){
		System.out.println("Test Kar()");
		Cheetah S =new Cheetah();
		assertTrue(S.Kar()=='C');
	}
	
	@Test
	public void test_DeltaT(){
		System.out.println("Test DeltaT()");
		Cheetah S =new Cheetah();
		assertTrue(S.deltaT()==1);
	}
	
	@Test
	public void test_Kecepatan(){
		System.out.println("Test Kecepatan()");
		Cheetah S =new Cheetah();
		assertTrue(S.Kecepatan()==3);
	}
	
	@Test
	public void test_GetStronger(){
		System.out.println("Test GetStronger()");
		Pohon S =new Pohon();
		Gajah G =new Gajah();
		Cheetah L =new Cheetah();
		Cheetah M =new Cheetah();
		L.MakanDaging(S);
		M.MakanDaging(G);
		assertTrue((L.GetKekuatan()==60)&&(M.GetKekuatan()==120));
	}
	
	@Test
	public void test_SwitchArah(){
		System.out.println("Test SwitchArah()");
		Cheetah S =new Cheetah();
		S.SwitchArah();
		assertTrue(S.GetArah()==3);
	}
	
	/*@Test
	public void test_Move(){
		System.out.println("Test move()");
		Cheetah S =new Cheetah();
		S.move();
		assertTrue((S.GetPosisi().getX()==2)&&(S.GetPosisi().getY()==2));
	}*/
	
}
