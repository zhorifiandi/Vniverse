import org.junit.*;
import static org.junit.Assert.*;

public class MatriksIDTest{
	@Test
	public void test_returnAdd(){
		System.out.println("Test Constructor Add");
		MatriksID S =new MatriksID();
		Singa M = new Singa();
		S.AddMakhlukToMatriks(M.GetPosisi(),M.GetID());
		assertTrue((S.GetArrayInt(M.GetPosisi())[0]==M.GetID()));
	}
	
	@Test
	public void test_returnAdd2(){
		System.out.println("Test Constructor Add");
		MatriksID S =new MatriksID();
		Singa M = new Singa();
		S.AddMakhlukToMatriks(M.GetPosisi(),M.GetID());
		S.FindIndex(S.GetArrayInt(M.GetPosisi()),0);
		assertTrue((S.GetArrayInt(M.GetPosisi())[0]==M.GetID()));
	}
	
	@Test
	public void test_returnDel(){
		System.out.println("Test Constructor Del");
		MatriksID S =new MatriksID();
		Singa M = new Singa();
		S.AddMakhlukToMatriks(M.GetPosisi(),M.GetID());
		S.DelMakhlukFromMatriks(M.GetPosisi(),M.GetID());
		assertTrue(10!=S.FindIndex(S.GetArrayInt(M.GetPosisi()),M.GetID()));
	}
	
}
