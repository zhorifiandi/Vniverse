/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.controller;

import my.view.Area;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import my.model.abstrak.Makhluk;
import my.model.abstrak.MakhlukBergerak;
import my.model.abstrak.Posisi;
import my.model.abstrak.Tumbuhan;
import my.model.konkrit.Cheetah;
import my.model.konkrit.Gajah;
import my.model.konkrit.Laki;
import my.model.konkrit.Rusa;
import my.model.konkrit.Singa;
import my.model.konkrit.Wanita;
import my.model.konkrit.Pohon;
import my.model.konkrit.Rumput;


/**
 *
 * @author user-ari
 */
public class VniverseUI extends javax.swing.JFrame implements KeyListener  {
    /**
     * Default icon
     */
    int linecounter = 0;
    private final ImageIcon defaulticon = new ImageIcon(getClass().getResource("img/oop-empty.png"));
    private final ImageIcon singaicon = new ImageIcon(getClass().getResource("img/oop-singa.png"));
    private final ImageIcon cheetahicon = new ImageIcon(getClass().getResource("img/oop-cheetah.png"));
    private final ImageIcon gajahicon = new ImageIcon(getClass().getResource("img/oop-gajah.png"));
    private final ImageIcon rusaicon = new ImageIcon(getClass().getResource("img/oop-rusa.png"));
    private final ImageIcon lakiicon = new ImageIcon(getClass().getResource("img/oop-laki.png"));
    private final ImageIcon wanitaicon = new ImageIcon(getClass().getResource("img/oop-wanita.png"));
    private final ImageIcon pohonicon = new ImageIcon(getClass().getResource("img/oop-pohon.png"));
    private final ImageIcon rumputicon = new ImageIcon(getClass().getResource("img/oop-rumput.png"));
    private final ImageIcon battleicon = new ImageIcon(getClass().getResource("img/oop-battle.png"));
    private final ImageIcon level1 = new ImageIcon(getClass().getResource("img/oop-level1.png"));
    private final ImageIcon level2 = new ImageIcon(getClass().getResource("img/oop-level2.png"));
    private final ImageIcon level3 = new ImageIcon(getClass().getResource("img/oop-level3.png"));        
    /**
     * Controller atributes
     */
    private static Area Dunia;
    private static MatriksID MID;
    private boolean game = false;
    private boolean loopOto = true;
    private boolean isGameOver = false;
    private int IDPlayer = 0;
    private int levelPlayer = 1;
    private int eaten = 0;
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
    /**
     * Creates new form VniverseUI
     */
    public VniverseUI() {
        initComponents();
        initBox();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        //Controller
        Dunia = new Area();
	MID = new MatriksID();
    }
    
    /**
    * Mengembalikan Area
    */
    Area GetArea() {
	return Dunia;
    }
		
    /**
     *	Mengembalikan Matriks ID
     */
    MatriksID GetMID() {
	return MID;
    }
    
    /**
     * Untuk start game
     */
    void GameStart() {
        int pos1 = 0, pos2 = 0;
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                if(MID.GetIsi()[i][j].isi.length==0){
                    pos1 = i;
                    pos2 = j;
                    break;
                }
            }
        }
        
        Laki player = new Laki();
        Posisi P = new Posisi(pos1,pos2);
        player.SetPosisi(P);        
        IDPlayer = player.GetID();
        InsMakhluk(player);
        AddMakhlukToMatriks(player);
    }

    /**
    * Untuk menjalankan program secara otomatis
    * @param input , char
    */
    synchronized void Otomatis() throws Exception {   
	//rencana kalo keylistener uda ada, kalo lagi mencet sesuatu loop jadi false, terus ngejalanin prosedur laen
	//diawal prosedur atau akhir keylistener bikin semua suspend dulu kaya line 102-112
	//for (int j = 0; j<1; j++)
        if (loopOto == true) {
            for (int i=1;i<=Dunia.GetVector_Makhluk().nbElmt();i++) {
                if (i != IDPlayer) {
                    if (!Dunia.GetVector_Makhluk().GetElmt(i).GetDel()) {		
                        if ((Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar() != 'A') && (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar() != 'P')) {
                            MakhlukBergerak S = (MakhlukBergerak) (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk());	
                            DelMakhlukFromMatriks(S);
			}
                    }
                    switch (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar()) {
			case 'R': ((Rusa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).resume();break;
			case 'G': ((Gajah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).resume();break;
			case 'S': ((Singa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).resume();break;
			case 'C': ((Cheetah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).resume();break;
			case 'L': ((Laki)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).resume();break;
			case 'W': ((Wanita)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).resume();break;
                        default:break;
                    }
                }
            }                    
            try {       
		Thread.sleep(10);
            } catch (InterruptedException e) {
		System.out.println("Thread interrupted.");
            }                                                      
            for (int i=1;i<=Dunia.GetVector_Makhluk().nbElmt();i++) {
                if (i != IDPlayer) {
                    switch (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar()) {
			case 'R': ((Rusa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
			case 'G': ((Gajah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
                        case 'S': ((Singa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
			case 'C': ((Cheetah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
			case 'L': ((Laki)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
			case 'W': ((Wanita)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
			default:break;
                    }
                }
            }
                            	
            for (int i=1;i<=Dunia.GetVector_Makhluk().nbElmt();i++) {
                if (i != IDPlayer) {
                    if (!Dunia.GetVector_Makhluk().GetElmt(i).GetDel()) {	
                        if ((Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar() != 'A') && (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar() != 'P')) {
                            MakhlukBergerak S = (MakhlukBergerak) (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk());			
                            if (KenaUjung(S.GetID())) {
				S.SwitchArah();
                            }
                            AddMakhlukToMatriks(S);
                        }
                    }
                }
            }            
            InteraksiAntarMakhluk();
            if (game == false) {
                KurangiUmur();
            }
            MinimalDuaTiapRas();
            StatusMakhluk();
            if (KondisiBerhenti()) {
		System.out.println("Semua makhluk mati");
		System.exit(0);
            }                    
        }
    }
    
    /**
    * Untuk menjalankan program secara step by step 
    * @param input , char
    */
    void StepByStep() throws Exception {  
        for (int i=1;i<=Dunia.GetVector_Makhluk().nbElmt();i++) {
            if (!Dunia.GetVector_Makhluk().GetElmt(i).GetDel()) {				
                if ((Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar() != 'A') && (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar() != 'P')) {
                    MakhlukBergerak S = (MakhlukBergerak) (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk());	
                    DelMakhlukFromMatriks(S); 
                }
            }
            switch (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar()) {
                case 'R': ((Rusa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).resume();break;
		case 'G': ((Gajah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).resume();break;
		case 'S': ((Singa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).resume();break;
		case 'C': ((Cheetah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).resume();break;
		case 'L': ((Laki)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).resume();break;
		case 'W': ((Wanita)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).resume();break;
		default:break;
            }
	}                         
        try {       
            Thread.sleep(10);
	} catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
	}
                                                                    
        for (int i=1;i<=Dunia.GetVector_Makhluk().nbElmt();i++) {
            switch (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar()) {
            	case 'R': ((Rusa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
		case 'G': ((Gajah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
		case 'S': ((Singa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
		case 'C': ((Cheetah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
		case 'L': ((Laki)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
		case 'W': ((Wanita)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
		default:break;
            }
	}
                            	
	for (int i=1;i<=Dunia.GetVector_Makhluk().nbElmt();i++) {
            if (!Dunia.GetVector_Makhluk().GetElmt(i).GetDel()) {				
		if ((Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar() != 'A') && (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar() != 'P')) {
                    MakhlukBergerak S = (MakhlukBergerak) (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk());			
                    if (KenaUjung(S.GetID())) {
                        S.SwitchArah();
                    }
                    AddMakhlukToMatriks(S);
		}
            }
	}
                    
	InteraksiAntarMakhluk();
	KurangiUmur();
        MinimalDuaTiapRas();
	StatusMakhluk();
	if (KondisiBerhenti()) {
            System.out.println("Semua makhluk mati");
            System.exit(0);
	}
    }                      
    /**
     * Untuk mengatasi ketiadaan makhluk saat game
     */
    public void MinimalDuaTiapRas () {
        int jumlahMinimal = 4;
        int P = 0, A = 0, G = 0, R = 0, S = 0, C = 0, L = 0, W = 0;
            for (int i=1; i<=Dunia.GetVector_Makhluk().nbElmt(); i++) {
                    if (!Dunia.GetVector_Makhluk().GetElmt(i).GetDel()) { 
                            switch (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar()) {
                                    case 'P' : P++; break;
                                    case 'A' : A++; break;
                                    case 'G' : G++; break;
                                    case 'R' : R++; break;
                                    case 'S' : S++; break;
                                    case 'C' : C++; break;
                                    case 'L' : L++; break;
                                    case 'W' : W++; break;
                            }
                    }
            }

        int jumlahManusia = L+W-1;
        int jumlahHewan = G+R+S+C;
        int jumlahTumbuhan = P+A;

        if (jumlahManusia < jumlahMinimal) {
            System.out.println("Masuk manusia");
            int manusiaDitambah = jumlahMinimal - jumlahManusia;
            for (int i=0; i<manusiaDitambah; i++) {
                Makhluk M;
                if (game == true) {
                    do {
                        M = new Laki();
                    } while (M.GetPosisi()==Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk().GetPosisi());
                }
                else {
                    M = new Laki();
                }
                InsMakhluk(M);
                AddMakhlukToMatriks(M);
            }
        }

        if (jumlahTumbuhan < jumlahMinimal) {
            System.out.println("Masuk tumbuhan");
            int tumbuhanDitambah = jumlahMinimal - jumlahTumbuhan;
            for (int i=0; i<tumbuhanDitambah; i++) {
                Makhluk M;
                if (game == true) {
                    do {
                        M = new Pohon();
                    } while (M.GetPosisi()==Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk().GetPosisi());
                }
                else {
                    M = new Pohon();
                }
                InsMakhluk(M);
                AddMakhlukToMatriks(M);
            }
        }

        if (jumlahHewan < jumlahMinimal) {
            System.out.println("Masuk hewan");
            int hewanDitambah = jumlahMinimal - jumlahHewan;
            for (int i=0; i<hewanDitambah; i++) {
                Makhluk M;
                if (game == true) {
                    do {
                        M = new Cheetah();
                    } while (M.GetPosisi()==Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk().GetPosisi());
                }
                else {
                    M = new Cheetah();
                }
                InsMakhluk(M);
                AddMakhlukToMatriks(M);
            }
        }
    }

    
    /**
     * Untuk membuat makhluk secara random 
     */	
    void BuatMakhlukRandom() {
        //srand(time(NULL));
        //Random rand = new Random();
        int angkaRandom = (int)(Math.random() * 10) + 10; 
        //int angkaRandom = 1;
        for (int i=0;i<angkaRandom;i++) {
            //srand(i);
            int randomMakhluk = (int)(Math.random() * 9); 
            switch (randomMakhluk) {
                case 0: { 
                    Makhluk S = new Gajah(); 
                    InsMakhluk(S);
                    AddMakhlukToMatriks(S);
                    break; 
                }
                case 1: { 
                    Makhluk S = new Rusa(); 
                    InsMakhluk(S);
                    AddMakhlukToMatriks(S); 
                    break; 
                }
                case 2: { 
                    Makhluk S = new Singa(); 
                    InsMakhluk(S);
                    AddMakhlukToMatriks(S); 
                    break; 
                }
                case 3: { 
                    Makhluk S = new Cheetah(); 
                    InsMakhluk(S);
                    AddMakhlukToMatriks(S);
                    break; 
                } 
                case 4: { 
                    Makhluk S = new Pohon(); 
                    InsMakhluk(S);
                    AddMakhlukToMatriks(S);
                    break; 
                }
                case 5: { 
                    Makhluk S = new Rumput(); 
                    InsMakhluk(S);
                    AddMakhlukToMatriks(S);
                    break; 
                }
                case 6: { 
                    Makhluk S = new Laki(); 
                    InsMakhluk(S);
                    AddMakhlukToMatriks(S); 
                    break; 
                }
                case 7: { 
                    Makhluk S = new Wanita(); 
                    InsMakhluk(S);
                    AddMakhlukToMatriks(S);
                    break; 
                }
                default : break;
            }
        }
        //Mulai gerak
        for (int i=2;i<=Dunia.GetVector_Makhluk().nbElmt();i++) {
            switch (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar()) {
                case 'R': ((Rusa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).start();break;
                case 'G': ((Gajah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).start();break;
                case 'S': ((Singa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).start();break;
                case 'C': ((Cheetah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).start();break;
                case 'L': ((Laki)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).start();break;
                case 'W': ((Wanita)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).start();break;
                default:break;
            }
        }
        for (int i=1;i<=Dunia.GetVector_Makhluk().nbElmt();i++) {
            switch (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar()) {
                case 'R': ((Rusa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
                case 'G': ((Gajah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
                case 'S': ((Singa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
                case 'C': ((Cheetah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
                case 'L': ((Laki)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
                case 'W': ((Wanita)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
                default:break;
            }
        }
    }


    /**
     * Fungsi untuk mengembalikan boolean apakah menabrak ujung atau tidak
     * @param ID, integer
     */
    boolean KenaUjung(int ID) {
        Posisi P = Dunia.GetVector_Makhluk().GetElmt(ID).GetMakhluk().GetPosisi();
        //Posisi P;
        if (( (P.getX()<0) || (P.getX()>9) ) || ( (P.getY()<0) || (P.getY()>9) )) {
            if (P.getX()<0) {
                P.SetPosisi(0, P.getY());
            }
            if (P.getX()>9) {
                P.SetPosisi(9, P.getY());
            }
            if (P.getY()<0) {
                    P.SetPosisi(P.getX(), 0);
            }
            if (P.getY()>9) {
                    P.SetPosisi(P.getX(), 9);
            }
            //Dunia.GetVector_Makhluk().GetElmt(ID).M->GetPosisi().SetPoint(P.getX(), P.getY());
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Prosedur untuk menambahkan makhluk ke matriks
     * @param S, makhluk
     */
    void AddMakhlukToMatriks(Makhluk S) {
        if (S.GetID() != IDPlayer) {
            MID.AddMakhlukToMatriks(S.GetPosisi(), S.GetID());
        }
        Dunia.AddMakhlukToMatriks(S.GetPosisi(), S.GetID());
        if (S.GetID() == IDPlayer) {
            if (levelPlayer == 1) {
                setLabel(S.GetPosisi().getX(),S.GetPosisi().getY(),level1);
                jLabel101.setText("Now, You're Level 1");
                jLabel102.setText("You only can eat Tree and Grass");
            }
            else if (levelPlayer == 2) {
                setLabel(S.GetPosisi().getX(),S.GetPosisi().getY(),level2);
                jLabel101.setText("Now, You're Level 2");
                jLabel102.setText("You only can eat Animals");
            } 
            else if (levelPlayer == 3) {
                setLabel(S.GetPosisi().getX(),S.GetPosisi().getY(),level3);
                jLabel101.setText("CANIBAL TIME!!!");
                jLabel102.setText("You only can eat Man or Woman");
            } 
        }
        else if (MID.length(MID.GetArrayInt(S.GetPosisi())) > 1)
            setLabel(S.GetPosisi().getX(),S.GetPosisi().getY(),battleicon);
        else {
            if (S.Kar() == 'S')
                setLabel(S.GetPosisi().getX(),S.GetPosisi().getY(),singaicon);
            else if (S.Kar() == 'C')
                setLabel(S.GetPosisi().getX(),S.GetPosisi().getY(),cheetahicon);
            else if (S.Kar() == 'G')
                setLabel(S.GetPosisi().getX(),S.GetPosisi().getY(),gajahicon);
            else if (S.Kar() == 'R')
                setLabel(S.GetPosisi().getX(),S.GetPosisi().getY(),rusaicon);
            else if (S.Kar() == 'L')
                setLabel(S.GetPosisi().getX(),S.GetPosisi().getY(),lakiicon);
            else if (S.Kar() == 'W')
                setLabel(S.GetPosisi().getX(),S.GetPosisi().getY(),wanitaicon);
            else if (S.Kar() == 'P')
                setLabel(S.GetPosisi().getX(),S.GetPosisi().getY(),pohonicon);
            else if (S.Kar() == 'A')
                setLabel(S.GetPosisi().getX(),S.GetPosisi().getY(),rumputicon);
        }
    }
    /**
     * Prosedur untuk menghapus makhluk dari matriks
     * @param S, makhluk
     */
    void DelMakhlukFromMatriks(Makhluk S) {
        Dunia.DelMakhlukFromMatriks(S.GetPosisi(), S.GetID());				
        MID.DelMakhlukFromMatriks(S.GetPosisi(), S.GetID());
        setLabel(S.GetPosisi().getX(),S.GetPosisi().getY(),defaulticon);
    }

    /**
     * Prosedur untuk menghapus makhluk dari vektor
     * @param S, makhluk
     */
    void DelMakhluk(Makhluk S) {
        Dunia.GetVector_Makhluk().Delete(S.GetID());
    }


    /**
     * Prosedur untuk memasukan makhluk ke vektor
     * @param S, makhluk
     */
    void InsMakhluk(Makhluk S) {
        Dunia.GetVector_Makhluk().Insert(S);
    }

    /**
     * Fungsi untuk mengeluarkan nilai boolean apakah semua makhluk adalah tumbuhan atau tidak
     */
    boolean IsAllTanaman() {
        for (int i=1; i<=Dunia.GetVector_Makhluk().nbElmt(); i++) {
            if (!Dunia.GetVector_Makhluk().GetElmt(i).GetDel()) { 
                if ((Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar()!='P') && (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar()!='A')) {
                    return false;
                }				 
            }
        }
        return true;
    }
    
    /**
     * Fungsi untuk mengeluarkan nilai boolean true apabila jumlah makhluk kurang dari 10
     */
    boolean JumlahMakhlukSedikit() {
        return Dunia.GetVector_Makhluk().nEff() < 10;
    }
    
    /**
     * Fungsi untuk mengeluarkan nilai kekuatan terkecil
     * @param P, posisi
     */
    int KekuatanTerkecil(Posisi P) {
        int[] ArrayInteraksi = MID.GetArrayInt(P);
        int IDMin = ArrayInteraksi[0];
        for (int i=1; i<MID.length(ArrayInteraksi); i++) {
            int ID = ArrayInteraksi[i];
            if (Dunia.GetVector_Makhluk().GetElmt(ID).GetMakhluk().GetKekuatan()<Dunia.GetVector_Makhluk().GetElmt(IDMin).GetMakhluk().GetKekuatan()) {
                IDMin = i;
            }
        }
        return IDMin;
    }
    
    /**
     * Prosedur untuk mengatur interaksi antar makhluk
     */
    void InteraksiAntarMakhluk() {
        for (int i=0; i<=9; i++) {
            for (int j=0; j<=9; j++) {
                Posisi P = new Posisi();
                P.SetPosisi(i,j);
                if (MID.length(MID.GetArrayInt(P))>1) {
                    int IDMin = KekuatanTerkecil(P);
                    //GetStronger dan Grow
                    for (int k=0;k<MID.length(MID.GetArrayInt(P));k++) {
                        if ((Dunia.GetVector_Makhluk().GetElmt(MID.GetArrayInt(P)[k]).GetMakhluk().Kar()=='P') || (Dunia.GetVector_Makhluk().GetElmt(MID.GetArrayInt(P)[k]).GetMakhluk()).Kar()=='A') {
                            Tumbuhan T = (Tumbuhan) (Dunia.GetVector_Makhluk().GetElmt(MID.GetArrayInt(P)[k]).GetMakhluk());
                            T.Grow();
                        } 
                        else{
                            Makhluk S = (Dunia.GetVector_Makhluk().GetElmt(MID.GetArrayInt(P)[k]).GetMakhluk());
                            Makhluk lawan=Dunia.GetVector_Makhluk().GetElmt(IDMin).GetMakhluk();
                            switch(S.Kar()) {
                                case 'G' : Gajah G=(Gajah)S; G.MakanTumbuhan(lawan); break;
                                case 'R' : Rusa R=(Rusa)S; R.MakanTumbuhan(lawan); break;
                                case 'C' : Cheetah C=(Cheetah)S; C.MakanDaging(lawan); break;
                                case 'S' : Singa Si=(Singa)S; Si.MakanDaging(lawan); break;
                                case 'L' : Laki L=(Laki)S; L.GetStronger(lawan); break;
                                case 'W' : Wanita W=(Wanita)S; W.GetStronger(lawan); break;
                                default : break;
                            }
                        }
                    }
                    System.out.print("Makhluk ");
                    System.out.print(Dunia.GetVector_Makhluk().GetElmt(IDMin).GetMakhluk().Kar());
                    System.out.print(" pada posisi <");
                    System.out.print(P.getX());
                    System.out.print(",");
                    System.out.print(P.getY());
                    System.out.print("> kalah saing");
                    //cout << "Makhluk " << Dunia.GetVector_Makhluk().GetElmt(IDMin).GetMakhluk().Kar() << " pada posisi <" << P.getX() << "," << P.getY() << "> kalah saing" << endl;
                    DelMakhlukFromMatriks(Dunia.GetVector_Makhluk().GetElmt(IDMin).GetMakhluk());
                    DelMakhluk(Dunia.GetVector_Makhluk().GetElmt(IDMin).GetMakhluk());				
                }
            }
        }
    }
    
    /**
     * Untuk menangani interaksi makhluk dengan player
     */
     void InteraksiPlayer() {
        Makhluk player = Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk();
        Posisi posPlayer = player.GetPosisi();
        int n = MID.length(MID.GetArrayInt(posPlayer));
        if(n>0) { //ada sesuatu
            for(int i=0;i<n;i++) {
                Makhluk lawan = Dunia.GetVector_Makhluk().GetElmt(MID.GetArrayInt(posPlayer)[i]).GetMakhluk();
                char karLawan = lawan.Kar();
                if((levelPlayer==1 && (karLawan=='P' || karLawan=='A')) ||
                   (levelPlayer==2 && (karLawan=='G' || karLawan=='R' || karLawan=='S' || karLawan=='C')) ||
                   (levelPlayer==3 && (karLawan=='L' || karLawan=='W'))) {
                    if(++eaten>=2) {
                        DelMakhluk(lawan);
                        DelMakhlukFromMatriks(lawan);
                        eaten=0;
                        if(++levelPlayer==4) {
                            System.out.println("YOU WIN");
                            jLabel101.setText("YOU WIN!!");
                            jLabel102.setText("You're the real Predator!");
                            loopOto = false;
                            game = false;
                        }
                    }
                    break;
                } else {
                    System.out.println("GAME OVER");
                    jLabel101.setText("GAME OVER");
                    jLabel102.setText("Sorry, try again next time");
                    loopOto = false;
                    game = false;
                    isGameOver = true;
                }
            }
            System.out.println("Level "+levelPlayer+", sudah makan "+eaten);
        }
    }

    /**
     * Prosedur untuk mengurangi umur
     */
    void KurangiUmur() {
        for (int i=1; i<=Dunia.GetVector_Makhluk().nbElmt(); i++) {
            if (i != IDPlayer) {
                if (!Dunia.GetVector_Makhluk().GetElmt(i).GetDel()) { 
                    Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().SetUmur(Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().GetUmur()-1);
                    if (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().GetUmur() <= 0) {
                        System.out.print("Makhluk dengan ID ");
                        System.out.print(Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().GetID());
                        System.out.println(" mati karena sudah tua");
                        DelMakhlukFromMatriks(Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk());
                        DelMakhluk(Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk());				
                    }
                }
            }
        }
    }


    /**
     * Fungsi untuk mengeluarkan boolean true apabila makhluk telah habis
     */
    boolean MakhlukHabis() {
        return Dunia.GetVector_Makhluk().nEff()==0;
    }

    /**
     * Fungsi untuk mengeluarkan boolean true apabila makhluk telah habis
     */
    boolean KondisiBerhenti() {
        if (MakhlukHabis()) {
            return true;
        } else {
            return false;
        }
    } 

    /**
     * Prosedur untuk menyimpan dunia
     */
    void Save(String output) {
        System.out.println("Writing to disk...");
        Dunia.DrawFile(output);
        System.out.println("Done");
    }

    /**
     * Proseduru untuk menampilkan status makhluk
     */
    void StatusMakhluk() {
        int P = 0, A = 0, G = 0, R = 0, S = 0, C = 0, L = 0, W = 0;
        for (int i=1; i<=Dunia.GetVector_Makhluk().nbElmt(); i++) {
            if (!Dunia.GetVector_Makhluk().GetElmt(i).GetDel()) { 
                switch (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar()) {
                    case 'P' : P++; break;
                    case 'A' : A++; break;
                    case 'G' : G++; break;
                    case 'R' : R++; break;
                    case 'S' : S++; break;
                    case 'C' : C++; break;
                    case 'L' : L++; break;
                    case 'W' : W++; break;
                }
            }
        }
        System.out.println("Hewan : " + (G+R+S+C));
        System.out.println("Manusia : " + (L+W));
        System.out.println("Tumbuhan : " + (P+A));
        /*
        System.out.print("P:");	System.out.print(P); System.out.print(";");
        System.out.print("A:");	System.out.print(A); System.out.print(";");
        System.out.print("G:");	System.out.print(G); System.out.print(";");
        System.out.print("R:");	System.out.print(R); System.out.print(";");
        System.out.print("S:");	System.out.print(S); System.out.print(";");
        System.out.print("C:");	System.out.print(C); System.out.print(";");
        System.out.print("L:");	System.out.print(L); System.out.print(";");
        System.out.print("W:");	System.out.print(W); System.out.print(";");
        //cout << "Total:" << Dunia.GetVector_Makhluk().nEff() << endl;

        System.out.print("Total:");	System.out.println( P+A+G+R+S+C+L+W);
        System.out.println( "P : Pohon" );
        System.out.println("A : Alang-alang");
        System.out.println("G : Gajah");
        System.out.println("R : Rusa");
        System.out.println("S : Singa");
        System.out.println("C : Cheetah"); 
        System.out.println("L : Laki");
        System.out.println("W : Wanita");
        System.out.println();

        System.out.println("Hotkey : ");
        System.out.println("s : pause and save" );
        System.out.println("b : mode otomatis");
        System.out.println("n : mode step-by-step");
        System.out.println("a : tambah makhluk");
        System.out.println("q : quit"); 
        if ((P+A+G+R+S+C+L+W)==0) {
                System.out.println("Semua makhluk mati");
                System.exit(0);
        }
        */
    }
    
    /**
     * SetLabel dengan icon
     * @param i
     * @param j
     * @param input
     */
    public void setLabel(int i, int j, ImageIcon input){
        switch (i) {
            case 0 :{
               switch (j) {
                    case 0 :{
                       jLabel1.setIcon(input);
                       break; 
                    } 
                    case 1 : {
                       jLabel2.setIcon(input);
                       break; 
                        }
                    case 2 :{
                       jLabel3.setIcon(input);
                       break; 
                    }
                    case 3 : {
                       jLabel4.setIcon(input);
                       break; 
                    }
                    case 4 :{
                        jLabel5.setIcon(input);
                       break; 
                    }
                    case 5 : {
                        jLabel6.setIcon(input);
                       break; 
                        }
                    case 6 :{
                        jLabel7.setIcon(input);
                       break; 
                    }
                    case 7 : {
                        jLabel8.setIcon(input);
                       break; 
                        }
                    case 8 :{
                        jLabel9.setIcon(input);
                       break; 
                    }
                    case 9 : {
                        jLabel10.setIcon(input);
                       break; 
                        }
                }
                break; 
            } 
            case 1 : {
                switch (j) {
                    case 0 :{
                       jLabel11.setIcon(input);
                       break; 
                    } 
                    case 1 : {
                        jLabel12.setIcon(input);
                       break; 
                        }
                    case 2 :{
                        jLabel13.setIcon(input);
                       break; 
                    }
                    case 3 : {
                       jLabel14.setIcon(input);
                       
                       break; 
                        }
                    case 4 :{
                       jLabel15.setIcon(input);
                        break; 
                    }
                    case 5 : {
                        jLabel16.setIcon(input);
                       break; 
                        }
                    case 6 :{
                        jLabel17.setIcon(input);
                       break; 
                    }
                    case 7 : {
                        jLabel18.setIcon(input);
                       break; 
                        }
                    case 8 :{
                        jLabel19.setIcon(input);
                       break; 
                    }
                    case 9 : {
                        jLabel20.setIcon(input);
                       break; 
                        }
                }
               break; 
                }
            case 2 :{
               switch (j) {
                    case 0 :{
                        jLabel21.setIcon(input);
                       break; 
                    } 
                    case 1 : {
                        jLabel22.setIcon(input);
                       break; 
                        }
                    case 2 :{
                        jLabel23.setIcon(input);
                       break; 
                    }
                    case 3 : {
                        jLabel24.setIcon(input);
                       break; 
                        }
                    case 4 :{
                        jLabel25.setIcon(input);
                       break; 
                    }
                    case 5 : {
                        jLabel26.setIcon(input);
                       break; 
                        }
                    case 6 :{
                        jLabel27.setIcon(input);
                       break; 
                    }
                    case 7 : {
                       jLabel28.setIcon(input);
                        break; 
                        }
                    case 8 :{
                        jLabel29.setIcon(input);
                       break; 
                    }
                    case 9 : {
                        jLabel30.setIcon(input);
                       break; 
                        }
                }   
                break; 
            }
            case 3 : {
                switch (j) {
                    case 0 :{
                        jLabel31.setIcon(input);
                       break; 
                    } 
                    case 1 : {
                        jLabel32.setIcon(input);
                       break; 
                        }
                    case 2 :{
                        jLabel33.setIcon(input);
                       break; 
                    }
                    case 3 : {
                        jLabel34.setIcon(input);
                       break; 
                        }
                    case 4 :{
                        jLabel35.setIcon(input);
                       break; 
                    }
                    case 5 : {
                        jLabel36.setIcon(input);
                       break; 
                        }
                    case 6 :{
                        jLabel37.setIcon(input);
                       break; 
                    }
                    case 7 : {
                        jLabel38.setIcon(input);
                       break; 
                        }
                    case 8 :{
                        jLabel39.setIcon(input);
                       break; 
                    }
                    case 9 : {
                        jLabel40.setIcon(input);
                       break; 
                        }
                }
               break; 
                }
            case 4 :{
                switch (j) {
                    case 0 :{
                        jLabel41.setIcon(input);
                       break; 
                    } 
                    case 1 : {
                        jLabel42.setIcon(input);
                       break; 
                        }
                    case 2 :{
                        jLabel43.setIcon(input);
                       break; 
                    }
                    case 3 : {
                        jLabel44.setIcon(input);
                       break; 
                        }
                    case 4 :{
                        jLabel45.setIcon(input);
                       break; 
                    }
                    case 5 : {
                        jLabel46.setIcon(input);
                       break; 
                        }
                    case 6 :{
                        jLabel47.setIcon(input);
                       break; 
                    }
                    case 7 : {
                        jLabel48.setIcon(input);
                       break; 
                        }
                    case 8 :{
                        jLabel49.setIcon(input);
                       break; 
                    }
                    case 9 : {
                       jLabel50.setIcon(input); 
                       break; 
                        }
                }
               break; 
            }
            case 5 : {
                switch (j) {
                    case 0 :{
                        jLabel51.setIcon(input);
                       break; 
                    } 
                    case 1 : {
                        jLabel52.setIcon(input);
                       break; 
                        }
                    case 2 :{
                        jLabel53.setIcon(input);
                       break; 
                    }
                    case 3 : {
                        jLabel54.setIcon(input);
                       break; 
                        }
                    case 4 :{
                        jLabel55.setIcon(input);
                       break; 
                    }
                    case 5 : {
                        jLabel56.setIcon(input);
                       break; 
                        }
                    case 6 :{
                        jLabel57.setIcon(input);
                       break; 
                    }
                    case 7 : {
                        jLabel58.setIcon(input);
                       break; 
                        }
                    case 8 :{
                        jLabel59.setIcon(input);
                       break; 
                    }
                    case 9 : {
                        jLabel60.setIcon(input);
                       break; 
                        }
                }
               break; 
                }
            case 6 :{
                switch (j) {
                    case 0 :{
                        jLabel61.setIcon(input);
                       break; 
                    } 
                    case 1 : {
                        jLabel62.setIcon(input);
                       break; 
                        }
                    case 2 :{
                        jLabel63.setIcon(input);
                       break; 
                    }
                    case 3 : {
                        jLabel64.setIcon(input);
                       break; 
                        }
                    case 4 :{
                        jLabel65.setIcon(input);
                       break; 
                    }
                    case 5 : {
                        jLabel66.setIcon(input);
                       break; 
                        }
                    case 6 :{
                        jLabel67.setIcon(input);
                       break; 
                    }
                    case 7 : {
                        jLabel68.setIcon(input);
                       break; 
                        }
                    case 8 :{
                        jLabel69.setIcon(input);
                       break; 
                    }
                    case 9 : {
                        jLabel70.setIcon(input);
                       break; 
                        }
                }
               break; 
            }
            case 7 : {
                switch (j) {
                    case 0 :{
                        jLabel71.setIcon(input);
                       break; 
                    } 
                    case 1 : {
                        jLabel72.setIcon(input);
                       break; 
                        }
                    case 2 :{
                        jLabel73.setIcon(input);
                       break; 
                    }
                    case 3 : {
                        jLabel74.setIcon(input);
                       break; 
                        }
                    case 4 :{
                        jLabel75.setIcon(input);
                       break; 
                    }
                    case 5 : {
                        jLabel76.setIcon(input);
                       break; 
                        }
                    case 6 :{
                        jLabel77.setIcon(input);
                       break; 
                    }
                    case 7 : {
                        jLabel78.setIcon(input);
                       break; 
                        }
                    case 8 :{
                        jLabel79.setIcon(input);
                       break; 
                    }
                    case 9 : {
                        jLabel80.setIcon(input);
                       break; 
                        }
                }
               break; 
                }
            case 8 :{
                switch (j) {
                    case 0 :{
                        jLabel81.setIcon(input);
                       break; 
                    } 
                    case 1 : {
                        jLabel82.setIcon(input);
                       break; 
                        }
                    case 2 :{
                        jLabel83.setIcon(input);
                       break; 
                    }
                    case 3 : {
                        jLabel84.setIcon(input);
                       break; 
                        }
                    case 4 :{
                        jLabel85.setIcon(input);
                       break; 
                    }
                    case 5 : {
                        jLabel86.setIcon(input);
                       break; 
                        }
                    case 6 :{
                        jLabel87.setIcon(input);
                       break; 
                    }
                    case 7 : {
                        jLabel88.setIcon(input);
                       break; 
                        }
                    case 8 :{
                        jLabel89.setIcon(input);
                       break; 
                    }
                    case 9 : {
                        jLabel90.setIcon(input);
                       break; 
                        }
                }
               break; 
            }
            case 9 : {
                switch (j) {
                    case 0 :{
                        jLabel91.setIcon(input);
                       break; 
                    } 
                    case 1 : {
                        jLabel92.setIcon(input);
                       break; 
                        }
                    case 2 :{
                        jLabel93.setIcon(input);
                       break; 
                    }
                    case 3 : {
                        jLabel94.setIcon(input);
                       break; 
                        }
                    case 4 :{
                        jLabel95.setIcon(input);
                       break; 
                    }
                    case 5 : {
                        jLabel96.setIcon(input);
                       break; 
                        }
                    case 6 :{
                        jLabel97.setIcon(input);
                       break; 
                    }
                    case 7 : {
                        jLabel98.setIcon(input);
                       break; 
                        }
                    case 8 :{
                        jLabel99.setIcon(input);
                       break; 
                    }
                    case 9 : {
                        jLabel100.setIcon(input);
                       break; 
                        }
                }
               break; 
                }
        }
    }
    
    /**
     * For init box
     */
    private void initBox() {
        jLabel102.setText("");
        jLabel1.setIcon(defaulticon);
        jLabel2.setIcon(defaulticon);
        jLabel3.setIcon(defaulticon);
        jLabel4.setIcon(defaulticon);
        jLabel5.setIcon(defaulticon);
        jLabel6.setIcon(defaulticon);
        jLabel7.setIcon(defaulticon);
        jLabel8.setIcon(defaulticon);
        jLabel9.setIcon(defaulticon);
        jLabel10.setIcon(defaulticon);
        jLabel11.setIcon(defaulticon);
        jLabel12.setIcon(defaulticon);
        jLabel13.setIcon(defaulticon);
        jLabel14.setIcon(defaulticon);
        jLabel15.setIcon(defaulticon);
        jLabel16.setIcon(defaulticon);
        jLabel17.setIcon(defaulticon);
        jLabel18.setIcon(defaulticon);
        jLabel19.setIcon(defaulticon);
        jLabel20.setIcon(defaulticon);
        jLabel21.setIcon(defaulticon);
        jLabel22.setIcon(defaulticon);
        jLabel23.setIcon(defaulticon);
        jLabel24.setIcon(defaulticon);
        jLabel25.setIcon(defaulticon);
        jLabel26.setIcon(defaulticon);
        jLabel27.setIcon(defaulticon);
        jLabel28.setIcon(defaulticon);
        jLabel29.setIcon(defaulticon);
        jLabel30.setIcon(defaulticon);
        jLabel31.setIcon(defaulticon);
        jLabel32.setIcon(defaulticon);
        jLabel33.setIcon(defaulticon);
        jLabel34.setIcon(defaulticon);
        jLabel35.setIcon(defaulticon);
        jLabel36.setIcon(defaulticon);
        jLabel37.setIcon(defaulticon);
        jLabel38.setIcon(defaulticon);
        jLabel39.setIcon(defaulticon);
        jLabel40.setIcon(defaulticon);
        jLabel41.setIcon(defaulticon);
        jLabel42.setIcon(defaulticon);
        jLabel43.setIcon(defaulticon);
        jLabel44.setIcon(defaulticon);
        jLabel45.setIcon(defaulticon);
        jLabel46.setIcon(defaulticon);
        jLabel47.setIcon(defaulticon);
        jLabel48.setIcon(defaulticon);
        jLabel49.setIcon(defaulticon);
        jLabel50.setIcon(defaulticon);
        jLabel51.setIcon(defaulticon);
        jLabel52.setIcon(defaulticon);
        jLabel53.setIcon(defaulticon);
        jLabel54.setIcon(defaulticon);
        jLabel55.setIcon(defaulticon);
        jLabel56.setIcon(defaulticon);
        jLabel57.setIcon(defaulticon);
        jLabel58.setIcon(defaulticon);
        jLabel59.setIcon(defaulticon);
        jLabel60.setIcon(defaulticon);
        jLabel61.setIcon(defaulticon);
        jLabel62.setIcon(defaulticon);
        jLabel63.setIcon(defaulticon);
        jLabel64.setIcon(defaulticon);
        jLabel65.setIcon(defaulticon);
        jLabel66.setIcon(defaulticon);
        jLabel67.setIcon(defaulticon);
        jLabel68.setIcon(defaulticon);
        jLabel69.setIcon(defaulticon);
        jLabel70.setIcon(defaulticon);
        jLabel71.setIcon(defaulticon);
        jLabel72.setIcon(defaulticon);
        jLabel73.setIcon(defaulticon);
        jLabel74.setIcon(defaulticon);
        jLabel75.setIcon(defaulticon);
        jLabel76.setIcon(defaulticon);
        jLabel77.setIcon(defaulticon);
        jLabel78.setIcon(defaulticon);
        jLabel79.setIcon(defaulticon);
        jLabel80.setIcon(defaulticon);
        jLabel81.setIcon(defaulticon);
        jLabel82.setIcon(defaulticon);
        jLabel83.setIcon(defaulticon);
        jLabel84.setIcon(defaulticon);
        jLabel85.setIcon(defaulticon);
        jLabel86.setIcon(defaulticon);
        jLabel87.setIcon(defaulticon);
        jLabel88.setIcon(defaulticon);
        jLabel89.setIcon(defaulticon);
        jLabel90.setIcon(defaulticon);
        jLabel91.setIcon(defaulticon);
        jLabel92.setIcon(defaulticon);
        jLabel93.setIcon(defaulticon);
        jLabel94.setIcon(defaulticon);
        jLabel95.setIcon(defaulticon);
        jLabel96.setIcon(defaulticon);
        jLabel97.setIcon(defaulticon);
        jLabel98.setIcon(defaulticon);
        jLabel99.setIcon(defaulticon);
        jLabel100.setIcon(defaulticon);
        jLabel1.setText("");
        jLabel2.setText("");
        jLabel3.setText("");
        jLabel4.setText("");
        jLabel5.setText("");
        jLabel6.setText("");
        jLabel7.setText("");
        jLabel8.setText("");
        jLabel9.setText("");
        jLabel10.setText("");
        jLabel11.setText("");
        jLabel12.setText("");
        jLabel13.setText("");
        jLabel14.setText("");
        jLabel15.setText("");
        jLabel16.setText("");
        jLabel17.setText("");
        jLabel18.setText("");
        jLabel19.setText("");
        jLabel20.setText("");
        jLabel21.setText("");
        jLabel22.setText("");
        jLabel23.setText("");
        jLabel24.setText("");
        jLabel25.setText("");
        jLabel26.setText("");
        jLabel27.setText("");
        jLabel28.setText("");
        jLabel29.setText("");
        jLabel30.setText("");
        jLabel31.setText("");
        jLabel32.setText("");
        jLabel33.setText("");
        jLabel34.setText("");
        jLabel35.setText("");
        jLabel36.setText("");
        jLabel37.setText("");
        jLabel38.setText("");
        jLabel39.setText("");
        jLabel40.setText("");
        jLabel41.setText("");
        jLabel42.setText("");
        jLabel43.setText("");
        jLabel44.setText("");
        jLabel45.setText("");
        jLabel46.setText("");
        jLabel47.setText("");
        jLabel48.setText("");
        jLabel49.setText("");
        jLabel50.setText("");
        jLabel51.setText("");
        jLabel52.setText("");
        jLabel53.setText("");
        jLabel54.setText("");
        jLabel55.setText("");
        jLabel56.setText("");
        jLabel57.setText("");
        jLabel58.setText("");
        jLabel59.setText("");
        jLabel60.setText("");
        jLabel61.setText("");
        jLabel62.setText("");
        jLabel63.setText("");
        jLabel64.setText("");
        jLabel65.setText("");
        jLabel66.setText("");
        jLabel67.setText("");
        jLabel68.setText("");
        jLabel69.setText("");
        jLabel70.setText("");
        jLabel71.setText("");
        jLabel72.setText("");
        jLabel73.setText("");
        jLabel74.setText("");
        jLabel75.setText("");
        jLabel76.setText("");
        jLabel77.setText("");
        jLabel78.setText("");
        jLabel79.setText("");
        jLabel80.setText("");
        jLabel81.setText("");
        jLabel82.setText("");
        jLabel83.setText("");
        jLabel84.setText("");
        jLabel85.setText("");
        jLabel86.setText("");
        jLabel87.setText("");
        jLabel88.setText("");
        jLabel89.setText("");
        jLabel90.setText("");
        jLabel91.setText("");
        jLabel92.setText("");
        jLabel93.setText("");
        jLabel94.setText("");
        jLabel95.setText("");
        jLabel96.setText("");
        jLabel97.setText("");
        jLabel98.setText("");
        jLabel99.setText("");
        jLabel100.setText("");
    }
    /**
     * 
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("jLabel1");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("jLabel1");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("jLabel1");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("jLabel1");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("jLabel1");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("jLabel1");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("jLabel1");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("jLabel1");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("jLabel1");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("jLabel1");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("jLabel1");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("jLabel1");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("jLabel1");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("jLabel1");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("jLabel1");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("jLabel1");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("jLabel1");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("jLabel1");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("jLabel1");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("jLabel1");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("jLabel1");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("jLabel1");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("jLabel1");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("jLabel1");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("jLabel1");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("jLabel1");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("jLabel1");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("jLabel1");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("jLabel1");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("jLabel1");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("jLabel1");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("jLabel1");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("jLabel1");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("jLabel1");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("jLabel1");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("jLabel1");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("jLabel1");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("jLabel1");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("jLabel1");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("jLabel1");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("jLabel1");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("jLabel1");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("jLabel1");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("jLabel1");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("jLabel1");

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("jLabel1");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("jLabel1");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("jLabel1");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("jLabel1");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("jLabel1");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("jLabel1");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("jLabel1");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("jLabel1");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("jLabel1");

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("jLabel1");

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("jLabel1");

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("jLabel1");

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("jLabel1");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("jLabel1");

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("jLabel1");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("jLabel1");

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("jLabel1");

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("jLabel1");

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("jLabel1");

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("jLabel1");

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("jLabel1");

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("jLabel1");

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("jLabel1");

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("jLabel1");

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("jLabel1");

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("jLabel1");

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("jLabel1");

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setText("jLabel1");

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("jLabel1");

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setText("jLabel1");

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setText("jLabel1");

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("jLabel1");

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("jLabel1");

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("jLabel1");

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("jLabel1");

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("jLabel1");

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setText("jLabel1");

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("jLabel1");

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setText("jLabel1");

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setText("jLabel1");

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(255, 255, 255));
        jLabel96.setText("jLabel1");

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setText("jLabel1");

        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(255, 255, 255));
        jLabel98.setText("jLabel1");

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setText("jLabel1");

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(255, 255, 255));
        jLabel100.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel40))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel43)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel44)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel46)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel49)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel50))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel71)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel72)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel73)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel74)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel75)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel76)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel77)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel78)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel79)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel80))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel91)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel92)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel93)
                                    .addComponent(jLabel3))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel94)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel95)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel96)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel97)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel98))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel99))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel100)
                                    .addComponent(jLabel10)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel53)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel56)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel58)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel59)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel60))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel61)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel62)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel63)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel64)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel65)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel66)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel67)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel68)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel69)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel70))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel81)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel82)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel83)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel84)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel85)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel86)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel87)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel88)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel89)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel90)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47)
                    .addComponent(jLabel48)
                    .addComponent(jLabel49)
                    .addComponent(jLabel50))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jLabel52)
                    .addComponent(jLabel53)
                    .addComponent(jLabel54)
                    .addComponent(jLabel55)
                    .addComponent(jLabel56)
                    .addComponent(jLabel57)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jLabel62)
                    .addComponent(jLabel63)
                    .addComponent(jLabel64)
                    .addComponent(jLabel65)
                    .addComponent(jLabel66)
                    .addComponent(jLabel67)
                    .addComponent(jLabel68)
                    .addComponent(jLabel69)
                    .addComponent(jLabel70))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(jLabel72)
                    .addComponent(jLabel73)
                    .addComponent(jLabel74)
                    .addComponent(jLabel75)
                    .addComponent(jLabel76)
                    .addComponent(jLabel77)
                    .addComponent(jLabel78)
                    .addComponent(jLabel79)
                    .addComponent(jLabel80))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(jLabel83)
                    .addComponent(jLabel84)
                    .addComponent(jLabel85)
                    .addComponent(jLabel86)
                    .addComponent(jLabel87)
                    .addComponent(jLabel88)
                    .addComponent(jLabel89)
                    .addComponent(jLabel90)
                    .addComponent(jLabel81))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel92)
                    .addComponent(jLabel93)
                    .addComponent(jLabel94)
                    .addComponent(jLabel95)
                    .addComponent(jLabel96)
                    .addComponent(jLabel97)
                    .addComponent(jLabel98)
                    .addComponent(jLabel99)
                    .addComponent(jLabel100)
                    .addComponent(jLabel91))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel101.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel101.setText("Press 'G' to Start The Game!");

        jLabel102.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel102.setText("jLabel102");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel101)
                    .addComponent(jLabel102))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jLabel101)
                .addGap(18, 18, 18)
                .addComponent(jLabel102)
                .addGap(276, 276, 276))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
    * Untuk membuat makhluk secara random 
    */	
    void BuatMakhlukRandomGame() {
        //srand(time(NULL));
        //Random rand = new Random();

        int angkaRandom = (int)(Math.random() * 10) + 10; 
        //int angkaRandom = 1;
        for (int i=0;i<angkaRandom;i++) {
            //srand(i);
            int randomMakhluk = (int)(Math.random() * 9); 
            switch (randomMakhluk) {
                case 0: { 
                        Makhluk S;
                        do {
                            S = new Gajah();
                        } while (S.GetPosisi()==Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk().GetPosisi()); 
                        InsMakhluk(S);
                        AddMakhlukToMatriks(S);
                        break; 
                }
                case 1: { 
                        Makhluk S;
                        do {
                            S = new Rusa();
                        } while (S.GetPosisi()==Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk().GetPosisi()); 
                        InsMakhluk(S);
                        AddMakhlukToMatriks(S); 
                        break; 
                }
                case 2: { 
                        Makhluk S;
                        do {
                            S = new Singa();
                        } while (S.GetPosisi()==Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk().GetPosisi()); 
                        InsMakhluk(S);
                        AddMakhlukToMatriks(S); 
                        break; 
                }
                case 3: { 
                        Makhluk S;
                        do {
                            S = new Cheetah();
                        } while (S.GetPosisi()==Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk().GetPosisi()); 
                        InsMakhluk(S);
                        AddMakhlukToMatriks(S);
                        break; 
                } 
                case 4: { 
                        Makhluk S;
                        do {
                            S = new Pohon();
                        } while (S.GetPosisi()==Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk().GetPosisi()); 
                        InsMakhluk(S);
                        AddMakhlukToMatriks(S);
                        break; 
                }
                case 5: { 
                        Makhluk S;
                        do {
                            S = new Rumput();
                        } while (S.GetPosisi()==Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk().GetPosisi()); 
                        InsMakhluk(S);
                        AddMakhlukToMatriks(S);
                        break; 
                }
                case 6: { 
                        Makhluk S;
                        do {
                            S = new Laki();
                        } while (S.GetPosisi()==Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk().GetPosisi()); 
                        InsMakhluk(S);
                        AddMakhlukToMatriks(S); 
                        break; 
                }
                case 7: { 
                        Makhluk S;
                        do {
                            S = new Wanita();
                        } while (S.GetPosisi()==Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk().GetPosisi()); 
                        InsMakhluk(S);
                        AddMakhlukToMatriks(S);
                        break; 
                }
                default : break;
            }
        }

            //Mulai gerak

        for (int i=2;i<=Dunia.GetVector_Makhluk().nbElmt();i++) {
            switch (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar()) {
                case 'R': ((Rusa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).start();break;
                case 'G': ((Gajah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).start();break;
                case 'S': ((Singa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).start();break;
                case 'C': ((Cheetah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).start();break;
                case 'L': ((Laki)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).start();break;
                case 'W': ((Wanita)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).start();break;
                default:break;
            }
        }
        for (int i=1;i<=Dunia.GetVector_Makhluk().nbElmt();i++) {
            switch (Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk().Kar()) {
                case 'R': ((Rusa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
                case 'G': ((Gajah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
                case 'S': ((Singa)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
                case 'C': ((Cheetah)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
                case 'L': ((Laki)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
                case 'W': ((Wanita)Dunia.GetVector_Makhluk().GetElmt(i).GetMakhluk()).suspend();break;
                default:break;
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VniverseUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VniverseUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VniverseUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VniverseUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {        
                final VniverseUI V = new VniverseUI();
                
                javax.swing.Timer t = new javax.swing.Timer(1000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            V.Otomatis();
                        } catch (Exception ex) {
                            Logger.getLogger(VniverseUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                V.BuatMakhlukRandom();
                V.setTitle("V-niverse");
                V.setResizable(false);
                //V.setSize(600, 600);
                //V.setMinimumSize(new Dimension(600, 600));
                V.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                V.pack();
                V.setVisible(true);
                t.start();
                //V.BuatMakhlukRandom();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        linecounter++;
        System.out.println(linecounter + " : typed");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        linecounter++;
        System.out.println(linecounter + " : pressed = " + e.getKeyChar());
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_S) {
             Save("output.txt"); 
        }
        else if (code == KeyEvent.VK_O) {
             if (!isGameOver){
                loopOto = true;
                game = false;
             }
        }
        else if (code == KeyEvent.VK_G) {
            if (!isGameOver){
                loopOto = false;
                game = true;
                GameStart();
                BuatMakhlukRandomGame();
                loopOto = true;
                jLabel101.setText("Game Start!!");
            }
        }
        else if (code == KeyEvent.VK_A) {
            if (!isGameOver) {
                if (!game) {
                    BuatMakhlukRandom();
                }
            }
        }
        else if (code == KeyEvent.VK_B) {
             if (!isGameOver){
                if (!game) {
                    loopOto = false;
                    game = false;
                    try {
                        StepByStep();
                    } catch (Exception ex) {
                        Logger.getLogger(VniverseUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
             }
        }
        else if (code == KeyEvent.VK_UP) {
            //loopOto = true;
            if (game) {
                Makhluk Player = Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk();
                DelMakhlukFromMatriks(Player);
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VniverseUI.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                Player.GetPosisi().Geser(-1,0);
                KenaUjung(IDPlayer);
                AddMakhlukToMatriks(Player);
                InteraksiPlayer();
            }
        }
        
        else if (code == KeyEvent.VK_DOWN) {
            //loopOto = true;
            if (game) {
                Makhluk Player = Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk();
                DelMakhlukFromMatriks(Player);
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VniverseUI.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                Player.GetPosisi().Geser(1,0);
                KenaUjung(IDPlayer);
                AddMakhlukToMatriks(Player);
                InteraksiPlayer();
            }
        }
        
        else if (code == KeyEvent.VK_LEFT) {
            //loopOto = true;
            if (game) {
                Makhluk Player = Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk();
                DelMakhlukFromMatriks(Player);
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VniverseUI.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                Player.GetPosisi().Geser(0,-1);
                KenaUjung(IDPlayer);
                AddMakhlukToMatriks(Player);
                InteraksiPlayer();
            }
        }
        
        else if (code == KeyEvent.VK_RIGHT) {
            //loopOto = true;
            if (game) {
                Makhluk Player = Dunia.GetVector_Makhluk().GetElmt(IDPlayer).GetMakhluk();
                DelMakhlukFromMatriks(Player);
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VniverseUI.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                Player.GetPosisi().Geser(0,1);
                KenaUjung(IDPlayer);
                AddMakhlukToMatriks(Player);
                InteraksiPlayer();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
