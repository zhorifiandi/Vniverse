import org.junit.*;
import static org.junit.Assert.*;

public class ListMakhlukTest{
	@Test
	public void test_returnIsEmpty(){
		System.out.println("Test Constructor IsEmpty");
		ListMakhluk S =new ListMakhluk();
		assertTrue((S.isEmpty()==true));
	}
	
	@Test
	public void test_returnnbElmt(){
		System.out.println("Test nbElmt");
		ListMakhluk S =new ListMakhluk();
		assertTrue(S.nbElmt() == 0);
	}
	
	@Test
	public void test_returnnEff(){
		System.out.println("Test nEff()");
		ListMakhluk S =new ListMakhluk();
		assertTrue(S.nEff()==0);
	}
	
	@Test
	public void test_GetElmt(){
		System.out.println("Test GetElmt()");
		ListMakhluk S =new ListMakhluk();
		Singa M = new Singa();
		S.Insert(M);
		assertTrue(S.GetElmt(0).GetDel()==false);
	}
	
	@Test
	public void test_Insert(){
		System.out.println("Test Insert()");
		ListMakhluk S =new ListMakhluk();
		Makhluk M = new Laki();
		S.Insert(M);
		assertTrue(S.nEff() == 1);
	}
	
	/*@Test
	public void test_DeleteM(){
		System.out.println("Test Delete(Makhluk)");
		ListMakhluk S =new ListMakhluk();
		Makhluk M = new Laki();
		S.Insert(M);
		S.Delete(M);
		System.out.println(M.GetID());
		assertTrue(S.nbElmt() != 1);
	}
	
	@Test
	public void test_DeleteI(){
		System.out.println("Test Delete(indeks)");
		ListMakhluk S =new ListMakhluk();
		Makhluk M = new ();
		S.Insert(M);
		S.Delete(M.GetID());
		assertTrue(S.nEff() == 0);
	}*/
	
	@Test
	public void test_delAll(){
		System.out.println("Test delAll()");
		ListMakhluk S =new ListMakhluk();
		Singa M = new Singa();
		S.delAll();
		assertTrue(S.nEff() == 0);
	}
	
	/*@Test
	public void test_Dealocate(){
		System.out.println("Test Dealocate()");
		ListMakhluk S =new ListMakhluk();
		Singa M = new Singa();
		S.Dealocate();
		assertTrue(S.nEff() == 0);
	}*/
	
}
