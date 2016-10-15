import org.junit.*;
import static org.junit.Assert.*;

public class RusaTest{
	@Test
	public void test_returnKekuatan(){
		System.out.println("Test GetKekuatan()");
		Rusa S =new Rusa();
		assertTrue(S.GetKekuatan()==40);
	}
	
	@Test
	public void test_returnUmur(){
		System.out.println("Test GetUmur()");
		Rusa S =new Rusa();
		assertTrue(S.GetUmur()==30);
	}
	
	
	@Test
	public void test_SetKekuatan(){
		System.out.println("Test SetKekuatan()");
		Rusa S =new Rusa();
		S.SetKekuatan(10);
		assertTrue(S.GetKekuatan()==10);
	}
	
	@Test
	public void test_SetUmur(){
		System.out.println("Test SetUmur()");
		Rusa S =new Rusa();
		S.SetUmur(100);
		assertTrue(S.GetUmur()==100);
	}
	
	@Test
	public void test_returnArah(){
		System.out.println("Test GetArah()");
		Rusa S =new Rusa();
		assertTrue(S.GetArah()==5);
	}
	
	@Test
	public void test_returnKar(){
		System.out.println("Test Kar()");
		Rusa S =new Rusa();
		assertTrue(S.Kar()=='R');
	}
	
	@Test
	public void test_DeltaT(){
		System.out.println("Test DeltaT()");
		Rusa S =new Rusa();
		assertTrue(S.deltaT()==2);
	}
	
	@Test
	public void test_Kecepatan(){
		System.out.println("Test Kecepatan()");
		Rusa S =new Rusa();
		assertTrue(S.Kecepatan()==2);
	}
	
	@Test
	public void test_GetStronger(){
		System.out.println("Test GetStronger()");
		Pohon S =new Pohon();
		Rusa L =new Rusa();
		Rusa M =new Rusa();
		L.MakanTumbuhan(S);
		M.MakanTumbuhan(L);
		assertTrue((L.GetKekuatan()==70)&&(M.GetKekuatan()==40));
	}
	
	@Test
	public void test_SwitchArah(){
		System.out.println("Test SwitchArah()");
		Rusa S =new Rusa();
		S.SwitchArah();
		assertTrue(S.GetArah()==1);
	}
	
	/*@Test
	public void test_Move(){
		System.out.println("Test move()");
		Rusa S =new Rusa();
		S.move();
		assertTrue((S.GetPosisi().getX()==2)&&(S.GetPosisi().getY()==2));
	}*/
	
}
