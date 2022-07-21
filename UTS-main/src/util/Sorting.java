//Nama: Derick Chainatra
//NIM: 03081210031
//Keterangan: Membuat class sorting yang berisi fungsi bubble sort
package util;
import java.util.ArrayList;
import java.util.Collections;

import khs.Dosen;
import khs.Mahasiswa;
import khs.Matakuliah;
import khs.Term;

public class Sorting {
    public static void bubbleSortMahasiswa(ArrayList<Mahasiswa> arr, String pil){
        int size = arr.size();

        for (int i = 0; i<size-1; i++) {
            for (int j = 0; j<size-i-1; j++){
                int id1 = Integer.parseInt(arr.get(j).getStudentID());
                int id2 = Integer.parseInt(arr.get(j+1).getStudentID());
                if(id1>id2){
                    Mahasiswa temp = arr.get(j);
                    arr.set(j, arr.get(j+1));
                    arr.set(j+1, temp);
                }
            }    
        }
        if(pil.equals("2")){
            Collections.reverse(arr);
        }
    }

    public static void bubbleSortDosen(ArrayList<Dosen> arr, String pil){
        int size = arr.size();

        for (int i = 0; i<size-1; i++) {
            for (int j = 0; j<size-i-1; j++){
                int id1 = Integer.parseInt(arr.get(j).getDosenId());
                int id2 = Integer.parseInt(arr.get(j+1).getDosenId());
                if(id1>id2){
                    Dosen temp = arr.get(j);
                    arr.set(j, arr.get(j+1));
                    arr.set(j+1, temp);
                }
            }    
        }
        if(pil.equals("2")){
            Collections.reverse(arr);
        }
    }

    public static void bubbleSortMatakuliah(ArrayList<Matakuliah> arr, String pil){
        int size = arr.size();

        for (int i = 0; i<size-1; i++) {
            for (int j = 0; j<size-i-1; j++){
                String data1 = arr.get(j).getKodeMataKuliah();
                String kode1[] = data1.split("_");
                String data2 = arr.get(j+1).getKodeMataKuliah();
                String kode2[] = data2.split("_");
                int id1 = Integer.parseInt(kode1[0]);
                int id2 = Integer.parseInt(kode2[0]);
                if(id1>id2){
                    Matakuliah temp = arr.get(j);
                    arr.set(j, arr.get(j+1));
                    arr.set(j+1, temp);
                }
            }    
        }
        if(pil.equals("2")){
            Collections.reverse(arr);
        }
    }

    public static void bubbleSortTerm(ArrayList<Term> arr, String pil){
        int size = arr.size();

        for (int i = 0; i<size-1; i++) {
            for (int j = 0; j<size-i-1; j++){
                String data1 = arr.get(j).getKodeTerm();
                String kode1[] = data1.split("_");
                String data2 = arr.get(j+1).getKodeTerm();
                String kode2[] = data2.split("_");
                int id1 = Integer.parseInt(kode1[0]);
                int id2 = Integer.parseInt(kode2[0]);
                if(id1>id2){
                    Term temp = arr.get(j);
                    arr.set(j, arr.get(j+1));
                    arr.set(j+1, temp);
                }
            }    
        }
        if(pil.equals("2")){
            Collections.reverse(arr);
        }
    }
}
