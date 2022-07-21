//Nama: Derick Chainatra
//NIM: 03081210031
//Soal No. 3
//Keterangan: Membuat class util untuk read file dan init data
package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import khs.Dosen;
import khs.Mahasiswa;
import khs.Matakuliah;
import khs.Term;

public class Util {
    static String fileDosen = "D:\\Kuliah\\Semester Akselerasi 1 2\\Pemrograman Berbasis Object\\UAS Copy\\UTS-main\\src\\text\\InfoDosen.txt";
    static String fileMahasiswa = "D:\\Kuliah\\Semester Akselerasi 1 2\\Pemrograman Berbasis Object\\UAS Copy\\UTS-main\\src\\text\\InfoMahasiswa.txt";
    static String fileMatkul = "D:\\Kuliah\\Semester Akselerasi 1 2\\Pemrograman Berbasis Object\\UAS Copy\\UTS-main\\src\\text\\InfoMatkul.txt";
    static String fileTerm = "D:\\Kuliah\\Semester Akselerasi 1 2\\Pemrograman Berbasis Object\\UAS Copy\\UTS-main\\src\\text\\InfoTerm.txt";

    public static ArrayList<Dosen> initDosen(ArrayList<Dosen> dosen){
        String data;
        FileReader fin = null;
        try{
            fin = new FileReader(fileDosen);
            BufferedReader br = new BufferedReader(fin);
            do{
                data = br.readLine();
                if(data!=null){
                    String str[] = data.split(",");
                    String dosenID = str[0];
                    String namaDosen = str[1];
                    int tahunMasuk = Integer.parseInt(str[2]);
                    String pendidikan = str[3];
                    dosen.add(new Dosen(dosenID, namaDosen, tahunMasuk, pendidikan));
                }
            }while(data!=null);
        }catch(FileNotFoundException e){
            System.out.println("File tidak ditemukan! Coba periksa kembali "+ fileDosen);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }finally{
            try{
                fin.close();
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        return dosen;
    }

    public static ArrayList<Mahasiswa> initMahasiswa(ArrayList<Mahasiswa> mahasiswa){
        String data;
        FileReader fin = null;
        try{
            fin = new FileReader(fileMahasiswa);
            BufferedReader br = new BufferedReader(fin);
            do{
                data = br.readLine();
                if(data!=null){
                    String str[] = data.split(",");
                    String studentID = str[0];
                    String namaMahasiswa = str[1];
                    String jurusan = str[2];
                    int tahunMasuk = Integer.parseInt(str[3]);  
                    mahasiswa.add(new Mahasiswa(studentID, namaMahasiswa, jurusan, tahunMasuk));
                }
            }while(data!=null);
        }catch(FileNotFoundException e){
            System.out.println("File tidak ditemukan! Coba periksa kembali "+ fileMahasiswa);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }finally{
            try{
                fin.close();
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        return mahasiswa;
    }

    public static ArrayList<Matakuliah> initMatkul(ArrayList<Matakuliah> matkul){
        String data;
        FileReader fin = null;
        try{
            fin = new FileReader(fileMatkul);
            BufferedReader br = new BufferedReader(fin);
            do{
                data = br.readLine();
                if(data!=null){
                    String str[] = data.split(",");
                    String kodeMatkul = str[0];
                    String namaMatkul = str[1];
                    int sks = Integer.parseInt(str[2]);
                    matkul.add(new Matakuliah(kodeMatkul, namaMatkul, sks));
                }
            }while(data!=null);
        }catch(FileNotFoundException e){
            System.out.println("File tidak ditemukan! Coba periksa kembali "+ fileMatkul);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }finally{
            try{
                fin.close();
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        return matkul;
    }

    public static ArrayList<Term> initTerm(ArrayList<Term> term){
        String data;
        FileReader fin = null;
        try{
            fin = new FileReader(fileTerm);
            BufferedReader br = new BufferedReader(fin);
            do{
                data = br.readLine();
                if(data!=null){
                    String str[] = data.split(",");
                    String kodeTerm = str[0];
                    int tahunAjaran = Integer.parseInt(str[1]);
                    String semester = str[2];
                    String ket = str[3];
                    String kets = str[4];
                    term.add(new Term(kodeTerm, tahunAjaran, semester, ket, kets));
                }
            }while(data!=null);
        }catch(FileNotFoundException e){
            System.out.println("File tidak ditemukan! Coba periksa kembali "+ fileTerm);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }finally{
            try{
                fin.close();
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        return term;
    }

}
