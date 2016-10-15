package my.controller;

import my.model.abstrak.Posisi;

public class MatriksID {
    public class IsiSel {
        public int[] isi=new int[10];
    }
    //private final static char initChar; gak dipake?
    //private final static String initString; gak dipake?
    private IsiSel[][] Matriks;
    private final static int defHSize=10;
    private final static int defWSize=10;
    private final static int defASize=10;
    private int highestlength;

    MatriksID() {
        // Alokasikan Memori untuk Matriks
        Matriks=new IsiSel[defHSize][defWSize];
        //Inisialisasi value
        for(int i=0;i<defHSize;i++)
            for(int j=0;j<defWSize;j++) {
                Matriks[i][j]=new IsiSel();
                for(int k=0;k<defASize;k++)
                    Matriks[i][j].isi[k]=0;
                
            }
        highestlength=1;
    }
    boolean IsMatriksIDEmpty() {
        boolean cek=true;
        for(int i=0;i<defHSize && cek;i++)
            for(int j=0;j<defWSize && cek;j++)
                if(length(Matriks[i][j].isi)!=0)
                    cek=false;
        return cek;
    }
    public int[] GetArrayInt(Posisi P) {
        return Matriks[P.getX()][P.getY()].isi;
    }
    public int length(int[] AI) {
        int i=-1;
        while(AI[++i]!=0);
        return i;
    }
    void PrintArrayInt(int[] AI) {
        int i=0;
        if(AI[0]==0)
            System.out.print(" ");
        while(AI[i]!=0) {
            System.out.print(AI[i]+" ");
            i++;
        }
    }
    void Draw() {
        for(int i=0;i<defHSize;i++) {
            System.out.print("[|");
            for(int j=0;j<defWSize-1;j++) {
                System.out.print("  ");
                PrintArrayInt(Matriks[i][j].isi);
                int panjangIsi=length(Matriks[i][j].isi);
                if(panjangIsi<highestlength) {
                    int jumlah_spasi_tambahan=2*(highestlength-panjangIsi)-1;
                    for(int k=0;k<jumlah_spasi_tambahan;k++)
                        System.out.print(" ");
                }
                System.out.print("  ||");
            }
            System.out.print("  ");
            PrintArrayInt(Matriks[i][defWSize-1].isi);
            System.out.println("  |]\n");
        }
    }
    void AddMakhlukToMatriks(Posisi P, int id) {
        int[] arrInt=GetArrayInt(P);
        arrInt[length(arrInt)]=id;
        //update highest length
        int nstemp=length(arrInt);
        if(nstemp>highestlength)
            highestlength=nstemp;
    }
    int FindIndex(int[] AI, int id) {
        int i=-1;
        while(++i<defASize && AI[i]!=id);
        if(i==defASize)
            return -1; //harusnya throw
        else
            return i;
    }
    void DelMakhlukFromMatriks(Posisi P, int id) {
        assert(IsMatriksIDEmpty());
        int[] arrInt=GetArrayInt(P);
        int indeks=FindIndex(arrInt, id);
        if(indeks>=0) {
            //Geser Array ke indeks tersebut
            for(int i=indeks;i<defASize-1;i++)
                arrInt[i]=arrInt[i+1];
            arrInt[defASize-1]=0;
        } //else //Harusnya throw apa tapi belum dibikin
            //System.out.println("Didn't find Symbol");
    }
    public IsiSel[][] GetIsi() {
        return Matriks;
    }
}
