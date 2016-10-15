package my.view;



import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import my.model.abstrak.Posisi;
import my.model.container.ListMakhluk;
/**
 * @author Praditya Raudi A. / 13514087
 */
public class Area {
	private static final String initstring = " ";
	private static final char initchar = ' ';
	//DEFAULT SIZE OF MATRIKS
	/**
	 * Default Size Of Matrix
	 */
	private static final int defaultheightsize = 10;
	private static final int defaultwidthsize = 10;
	//ATRIBUTE
	/**
	 * Matriks of String
	 * ListMakhluk
	 */
	private String[][] Matriks; //MATRIX
	private int highestlength; //HIGHEST LENGTH OF STRING ELEMENT
	private ListMakhluk Vector_Makhluk;
	
	/**
	 * Constructor Area
	 */
	public Area() {
		Matriks = new String[defaultheightsize][defaultwidthsize];
		
		for (int i=0; i < defaultheightsize; ++i){
			for (int j=0 ; j < defaultwidthsize ; ++j){
				Matriks[i][j] = initstring;
			}
		}
		highestlength = initstring.length();
		Vector_Makhluk = new ListMakhluk();
	}
	/**
	 * Prosedur untuk memperbesar matriks
	 */
	public void GrowMatriks() {
		for (int i=0; i < defaultheightsize; ++i){
			for (int j=0 ; j < defaultwidthsize ; ++j){
				String stemp = Matriks[i][j];
				//Jika panjang string kurang dari highestlength, tambah char spasi (' ') pada head (string[0])
				if (highestlength > stemp.length()) {
					//tambahkan spasi hingga panjangnya sama dengan highestlength
					for (int k=stemp.length(); k < highestlength ; ++k)
					{
						stemp = stemp + " ";
					}
					Matriks[i][j] = stemp;
				}
			}
		}
	}
	/**
	 * Prosedur untuk memperkecil matriks
	 */
	public void ShrinkMatriks() {
		for (int i=0; i < defaultheightsize; ++i){
			for (int j=0 ; j < defaultwidthsize ; ++j){
				String stemp = Matriks[i][j].substring(0,Matriks[i][j].length()-1);
				Matriks[i][j] = stemp;
			}
		}
	}
	/**
	 * Fungsi untuk memeriksa apakah matriks kosong
	 */
	public boolean IsAreaEmpty() {
		boolean cek = true;
		for (int i=0; i < defaultheightsize; ++i){
			for (int j=0 ; j < defaultwidthsize ; ++j){
				String stemp = Matriks[i][j];
				int length = stemp.length();
				if (length != 1 || stemp.toCharArray()[0] != initchar){
					cek = false;
					break;
				}
			}
		}
		return cek;
	}
	/** 
	 * Fungsi untuk memeriksa apakah matriks perlu diperkecil
	 */
	public boolean IsNeededToShrink() {
		boolean cek = true;
		for (int i=0; i < defaultheightsize; ++i){
			for (int j=0 ; j < defaultwidthsize ; ++j){
				String stemp = Matriks[i][j];
				int last = stemp.length() - 1;
				if (last+1 > 0 && stemp.toCharArray()[last] != initchar ){
					cek = false;
					break;
				}
			}
		}
		return cek;
	}

	/**
	 * Prosedur untuk menambahkan makhluk ke dalam matriks
	 */
	public void AddMakhlukToMatriks(Posisi P, int ID) {
		String stemp = Matriks[P.getX()][P.getY()];	
		String stemp1 = "";
	
		char symbol = Vector_Makhluk.GetElmt(ID).GetMakhluk().Kar();
		stemp = symbol + stemp;
		if (stemp.toCharArray()[highestlength] == ' '){
			stemp1 = stemp.substring(0,stemp.length()-1);
		}
		else {
			stemp1 = stemp;
		}
		Matriks[P.getX()][P.getY()] = stemp1;
		//update highest length
		int nstemp = stemp1.length();
		if (nstemp > highestlength) {
			highestlength = nstemp;
			GrowMatriks();
		}
	}
	/**
	 * Prosedur untuk menghapus makhluk dari matriks
	 */
	public void DelMakhlukFromMatriks(Posisi P, int ID) {
		if (IsAreaEmpty()){
			//Harusnya throw apa tapi belum dibikin
		}
		else if ((P.getX()>9) || (P.getY()>9) || (P.getX()<0) || (P.getY()<0)) {
			System.out.println("Didn't find Symbol" + Vector_Makhluk.GetElmt(ID).GetMakhluk().Kar());
			System.out.println(P.getX()+" "+P.getY());
		}
		else
		{
			int posisi_x = P.getX();
			int posisi_y = P.getY();
			String symbol = "";
			symbol += Vector_Makhluk.GetElmt(ID).GetMakhluk().Kar();
			String str1 = Matriks[posisi_x][posisi_y];
			String str2 = str1.replaceFirst(symbol,initstring);
			
			Matriks[posisi_x][posisi_y] = str2;
			//Shrink
			if (IsNeededToShrink()) {
				ShrinkMatriks();
				highestlength--;
			}
		}
	}
	/**
	 * Getter List Makhluk
	 */
	public ListMakhluk GetVector_Makhluk() {
		return Vector_Makhluk;
	}

        public void DrawFile (String output) {		
		try {
			PrintWriter writer = new PrintWriter(output, "UTF-8");
			for (int i=0; i < defaultheightsize; ++i){
				writer.print("[| ");
				int jumlahsel = 0;
				for (int j=0 ; j < defaultwidthsize-1 ; ++j){
					String stemp = Matriks[i][j];
					writer.print("  " +stemp+ "  ||");
				}
				String stemp = Matriks[i][defaultwidthsize-1];
				writer.print("  " +stemp+ "  ");
				writer.println(" |]\n");
			}
			writer.close();
                        System.out.println("asjdsaidjiasjdsjai File Saved");
		}
		catch (UnsupportedEncodingException ex) {
			System.out.println("Input tidak dapat ditulis");
		}
		catch (FileNotFoundException ex) {
			System.out.println("File tidak ditemukan");
		}
	}
}
