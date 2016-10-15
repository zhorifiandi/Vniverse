import org.junit.*;
import static org.junit.Assert.*;

public class AreaTest{
	@Test
	public void test_returnIsAreaEmpty(){
		System.out.println("Test IsAreaEmpty");
		Area S =new Area();
		assertTrue((S.IsAreaEmpty()==true));
	}
	
	@Test
	public void test_returnIsNeededToShrink(){
		System.out.println("Test IsNeededToShrink");
		Area S =new Area();
		assertTrue(S.IsNeededToShrink() == true);
	}
	
	@Test
	public void test_returnVector(){
		System.out.println("Test AddMakhlukToMatriks()");
		Area S = new Area();
		Singa M = new Singa();
		
		assertTrue(true==S.GetVector_Makhluk().isEmpty());
	}
	
}
