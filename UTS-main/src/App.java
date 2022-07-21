//Nama: Derick Chainatra
//NIM: 03081210031
//Soal No. 4, 5

import khs.Dosen;
import khs.Khs;
import khs.KhsDetail;
import khs.Krs;
import khs.Mahasiswa;
import khs.Matakuliah;
import khs.Retake;
import khs.Term;
import util.Sorting;
import util.Util;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {}
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //Keterangan: Menambahkan fungsi untuk cek penulisan kode term dan matakuliah ada '_' atau tidak
    public static int cekGarisBawah(String kode){
        for (int i = 0; i<kode.length(); i++) {
            if(kode.charAt(i)=='_'){
                return 1;
            }
        }
        return 2;
    }

    //Keterangan: Menambahkan fungsi untuk mendapatkan index krs dari kode term
    public static int getIndexKrsFromTerm(String kodeTerm, String studentID, ArrayList<Mahasiswa> mahasiswa){
        int idx = getIndexFromMahasiswa(mahasiswa, studentID);
        int index=0;
        for (Krs krs : mahasiswa.get(idx).getKRS()) {
            if (krs.getTerm().getKodeTerm().equals(kodeTerm)){
                return index;
            }
            index++;
        }
        return -1;
    }

    //Keterangan: Menambah fungsi untuk mengecek apakah ada matakuliah yang ingin diambil sudah pernah terdaftar di krs atau belum
    public static int cekMatkulPadaKrs(String studentID, String matkul, ArrayList<Mahasiswa> mahasiswa){
        int index = getIndexFromMahasiswa(mahasiswa, studentID);
        for (Krs krs : mahasiswa.get(index).getKRS()) {
            for(Matakuliah matakuliah : krs.getDaftarMataKuliah()){
                if(matakuliah.getKodeMataKuliah().equals(matkul)) return 1;
            }
        }
        return -1;
    }

    //Keterangan: Fungsi untuk mendapatkan index khs dengan mengecek kesamaan kodeTerm yang dimiliki khs 
    public static int getIndexKhsFromTerm(String studentID, String kodeTerm, ArrayList<Mahasiswa> mahasiswa){
        int idx = getIndexFromMahasiswa(mahasiswa, studentID);
        int index = 0;
        for(Khs khs: mahasiswa.get(idx).getKHS()){
            if(khs.getDetailTerm().getKodeTerm().equals(kodeTerm)){
                return index;
            }
            index++;
        }
        return -1;
    }

    public static int getIndexFromMataKuliah(ArrayList<Matakuliah> array, String kodeMatKul) {
        int index = 0;
        for (Matakuliah matakuliah : array) {
            if (matakuliah.getKodeMataKuliah().equals(kodeMatKul)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public static int getIndexFromMahasiswa(ArrayList<Mahasiswa> array, String studentID) {
        int index = 0;
        for (Mahasiswa mhs : array) {
            if (mhs.getStudentID().equals(studentID)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    //Keterangan: Menambah fungsi utk mendapatkan index dosen
    public static int getIndexFromDosen(ArrayList<Dosen> array, String dosenID) {
        int index = 0;
        for (Dosen dosen : array) {
            if (dosen.getDosenId().equals(dosenID)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public static int getIndexFromTerm(ArrayList<Term> array, String kodeTerm) {
        int index = 0;
        for (Term term : array) {
            if (term.getKodeTerm().equals(kodeTerm)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public static int getIndexFromKhs(String studentID, String kodeKHS, ArrayList<Mahasiswa> mahasiswa) {
        int idx = getIndexFromMahasiswa(mahasiswa, studentID);
        int index = 0;
        for (Khs khs : mahasiswa.get(idx).getKHS()) {
            if (khs.getKodeKHS().equals(kodeKHS)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public static int cekMatkulRetake(String kodeMatKul, String studentID, ArrayList<Mahasiswa> mahasiswa, ArrayList<Matakuliah> matakuliahs) {
        int idx = getIndexFromMahasiswa(mahasiswa, studentID);
        for (Khs khs : mahasiswa.get(idx).getKHS()) {
            for (KhsDetail khsdetail : khs.getKhsDetails()) {
                if (khsdetail.getDetailMatakuliah().getKodeMataKuliah().equals(kodeMatKul)) return 1;
            }
        }
        return 2;
    }

    public static int getKodeKhsRetake(String MatKul, String studentID, ArrayList<Mahasiswa> mahasiswa, ArrayList<Matakuliah> matakuliah) {
        int idx = getIndexFromMahasiswa(mahasiswa, studentID);
        int index = 0;
        for (Khs khs : mahasiswa.get(idx).getKHS()) {
            for (KhsDetail khsdetail : khs.getKhsDetails()) {
                if (khsdetail.getDetailMatakuliah().getKodeMataKuliah().equals(MatKul)) return index;
            }
            index++;
        }
        return -1;
    }

    public static int getKodeKhsDetailretake(String kodeTerm, String matKul, String studentID, ArrayList<Mahasiswa> mahasiswa, ArrayList<Matakuliah> matakuliah) {
        int idx = getIndexFromMahasiswa(mahasiswa, studentID);
        // int Idx = getKodeKhsRetake(matKul, studentID, mahasiswa, matakuliah);
        int Idx = getIndexKhsFromTerm(studentID, kodeTerm, mahasiswa);
        int Index = 0;
        for (KhsDetail khsdetail : mahasiswa.get(idx).getKHS().get(Idx).getKhsDetails()) {
            if (khsdetail.getDetailMatakuliah().getKodeMataKuliah().equals(matKul)) {
                return Index;
            }
            Index++;
        }
        return -1;
    }

    //Keterangan: fungsi untuk validasi id dosen
    public static int validasiDosenID(String dosenID, ArrayList<Dosen> dosen) {
        int idx = getIndexFromDosen(dosen, dosenID);
        if (idx == -1) { 
            if (dosenID.length() == 3) {
                return 1;
            }
            else{
                return 2;
            }
        }
        else {
            return 3;       
        }
    }

    public static int validasiStudentID(String studentID, ArrayList<Mahasiswa> mahasiswa) {
        int idx = getIndexFromMahasiswa(mahasiswa, studentID);
        if (idx == -1) { //21001
            if (studentID.length() == 5) {
                return 1;
            }
            else{
                return 2;
            }
        }
        else {
            return 3;       
        }
    }

    public static int validasiKodeMatKul(String kodeMatkul, ArrayList<Matakuliah> matakuliahs) {
        int idx = getIndexFromMataKuliah(matakuliahs, kodeMatkul);
        if (idx == -1) { //21001
            if (kodeMatkul.length() == 5) {
                return 1;
            }
            else{
                return 2;
            }
        }
        else {
            return 3;       
        }
    }

    public static int validasiSks(int sks) {
        if (sks <= 4 && sks > 1) {
            return 1;
        }
        else return 2;
    }

    public static int validasiTahunMasuk(int tahun) {
        if (tahun >= 1980 && tahun < 2023) {
            return 1;
        }
        else {
            return 2;
        }
    }

    public static int validasiTahunAjaran(int tahun) {
        if (tahun >= 1980 && tahun < 2023) {
            return 1;
        }
        else {
            return 2;
        }
    }

    public static int validasiKodeTerm(String kodeTerm, ArrayList<Term> term) {
        int idx = getIndexFromTerm(term, kodeTerm);
        if (idx == -1) { //01_term
            if (kodeTerm.length() == 7) {
                return 1;
            }
            else{
                return 2;
            }
        }
        else {
            return 3;       
        }
    }

    public static int validasiKodeKrs(String kodeKrs, String studentID, ArrayList<Mahasiswa> mahasiswa) {
        if (kodeKrs.length() == 6) {
            int idx = getIndexFromMahasiswa(mahasiswa, studentID);
            for (Krs krs : mahasiswa.get(idx).getKRS()) {
                if (krs.getKodeKrs().equals(kodeKrs)) return 1;
            }
            return 2;
        }
        else {
            return 3;
        }
    }

    public static int getIndexKrs(String kodeKrs, String studentID, ArrayList<Mahasiswa> mahasiswa) {
        int idx = getIndexFromMahasiswa(mahasiswa, studentID);
        int index = 0;
        for (Krs krs : mahasiswa.get(idx).getKRS()) {
            if (krs.getKodeKrs().equals(kodeKrs)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public static int validasiKodeKhs(String kodeKhs, String studentID, ArrayList<Mahasiswa> mahasiswa) {
        if (kodeKhs.length() == 6) {
            int idx = getIndexFromMahasiswa(mahasiswa, studentID);
            for (Khs khs : mahasiswa.get(idx).getKHS()) {
                if (khs.getKodeKHS().equals(kodeKhs)) return 1;
            }
            return 2;
        }
        else {
            return 3;
        }
    }

    public static int validasiKodeKhsDetail(String kodeKhsDetail, String kodeTerm, String studentID, ArrayList<Mahasiswa> mahasiswa) {
        if (kodeKhsDetail.length() == 7) { //01_khsd
            int idx = getIndexFromMahasiswa(mahasiswa, studentID);
            int index = 0;
            for (Khs khs : mahasiswa.get(idx).getKHS()) {
                if(khs.getDetailTerm().getKodeTerm().equals(kodeTerm)){
                    break;
                }
                index++;
            }
            for (KhsDetail khsd : mahasiswa.get(idx).getKHS().get(index).khsDetails) {
                if (khsd.getKodeKHSDetail().equals(kodeKhsDetail)) return 1;
            }
            return 2;
        }
        else {
            return 3;
        }
    }

    public static int validasiKodeTermKrs(String kodeTerm, String studentID, ArrayList<Mahasiswa> mahasiswa) {
        int idx = getIndexFromMahasiswa(mahasiswa, studentID);
        for (Krs krs : mahasiswa.get(idx).getKRS()) {
            if (krs.getTerm().getKodeTerm().equals(kodeTerm)) {
                return 1;
            }
        }
        return 2;
    }

    public static int validasiKodeTermKhs(String kodeTerm, String studentID, ArrayList<Mahasiswa> mahasiswa) {
        int idx = getIndexFromMahasiswa(mahasiswa, studentID);
        for (Khs khs : mahasiswa.get(idx).getKHS()) {
            if (khs.getTerm().getKodeTerm().equals(kodeTerm)) {
                return 1;
            }
        }
        return 2;
    }

    //Keterangan: Mengganti dari pengecekan dengan kode khs menjadi dengan kode term
    public static int validasiMatKulKrs(String kodeMatkul, String kodeTerm, String studentID, ArrayList<Mahasiswa> mahasiswa) {
        int idx = getIndexFromMahasiswa(mahasiswa, studentID);
        int Index = 0;
        for (Krs krs : mahasiswa.get(idx).getKRS()) {
            if(krs.getTerm().getKodeTerm().equals(kodeTerm)) {
                for (Matakuliah matakuliah : mahasiswa.get(idx).getKRS().get(Index).daftarMataKuliah) {
                    if (kodeMatkul.equals(matakuliah.getKodeMataKuliah())) {
                        return 1;
                    }
                }
            }
            Index++;
        }
        return 2;    
    }

    //Keterangan: Mengganti dari pengecekan dengan kode khs menjadi dengan kode term
    public static int validasiKodeMatKulKhsd(String kodeMatkul, String kodeTerm, String studentID, ArrayList<Mahasiswa> mahasiswa){
        int idx = getIndexFromMahasiswa(mahasiswa, studentID);
        int index = getIndexKhsFromTerm(studentID, kodeTerm, mahasiswa);
        for(KhsDetail khsd : mahasiswa.get(idx).khs.get(index).getKhsDetails()){
            if(khsd.getDetailMatakuliah().getKodeMataKuliah().equals(kodeMatkul)){
                return 1;
            }
        }
        return 2;
    }

    public static int validasiKodeMatKulRetake(String studentID, String kodeKHS, String kodeMatkul, ArrayList<Mahasiswa> mahasiswa) {
        int index = getIndexFromMahasiswa(mahasiswa, studentID);
        int Idx = getIndexFromKhs(studentID, kodeKHS, mahasiswa);
        if (kodeMatkul.length() == 5) {
            for (Retake retake : mahasiswa.get(index).getKHS().get(Idx).retake) {
                if (retake.getDetailMatakuliah().getKodeMataKuliah().equals(kodeMatkul)) {
                    if (retake.getNilai() < 60) {
                        return 1;
                    }
                    else {
                        return 2;
                    }
                }
            }
            return 3;
        }
        else return 4;
    }

    public static int getindexMatKulRetake(String studentID, String kodeKHS, String kodeMatkul, ArrayList<Mahasiswa> mahasiswa) {
        int index = getIndexFromMahasiswa(mahasiswa, studentID);
        int Idx = getIndexFromKhs(studentID, kodeKHS, mahasiswa);
        int idx = 0;
        for (Retake retake : mahasiswa.get(index).getKHS().get(Idx).retake) {
            if (retake.getDetailMatakuliah().getKodeMataKuliah().equals(kodeMatkul)) {
                return idx;
            }
            idx++;
        }
        return -1;
    }

    public static String getKrsDariTerm(String studentID, String kodeTerm, ArrayList<Mahasiswa> mahasiswa) {
        int idx = getIndexFromMahasiswa(mahasiswa, studentID);
        for (Krs krs : mahasiswa.get(idx).getKRS()) {
            if (krs.getTerm().getKodeTerm().equals(kodeTerm)) {
                return krs.getKodeKrs();
            }
        }
        return "";
    }

    public static int validasiTermRetake(String kodeTerm, String kodeTermRetake, ArrayList<Term> term) {
        int Idx = getIndexFromTerm(term, kodeTerm); //term krs
        int Index = getIndexFromTerm(term, kodeTermRetake); //term krs baru
        if (Idx >= Index) {
            return 1;
        }
        else {
            return 2;
        }
    }

    public static int cekKodeDetailKhs(int index, String matkul, String studentID, ArrayList<Mahasiswa> mahasiswa, ArrayList<Matakuliah> matakuliah) {
        int Idx = getIndexFromMahasiswa(mahasiswa, studentID);
        int count = 0;
        for (int i = 0; i <= index; i++) {
            for (KhsDetail khsdetail : mahasiswa.get(Idx).getKHS().get(i).getKhsDetails()) {
                if (matkul.equals(khsdetail.getDetailMatakuliah().getKodeMataKuliah())) count++;
            }
        }
        if (count == 1) {
            return 1;
        }
        else return 2;
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Term> term = new ArrayList<Term>();
        ArrayList<Matakuliah> mataKuliah = new ArrayList<Matakuliah>();
        ArrayList<Mahasiswa> mahasiswa = new ArrayList<Mahasiswa>();
        ArrayList<Dosen> dosen = new ArrayList<Dosen>();
        
        //Keterangan: Melakukan init data menggunakan fungsi dari class util dengan membaca file
        Util.initMahasiswa(mahasiswa);
        Util.initTerm(term);
        Util.initMatkul(mataKuliah);
        Util.initDosen(dosen);

        Scanner keyboard = new Scanner(System.in);
        String yn = "y";
        while(yn.equalsIgnoreCase("y")){
            clearScreen();
            System.out.println(" Selamat datang! Kami akan melayani Anda, Para Dosen");
            System.out.println(" ---------------------------------------------------");
            System.out.println(" 1. Input/Cetak Mata Kuliah");
            System.out.println(" 2. Input/Cetak Data Mahasiswa");
            System.out.println(" 3. Input/Cetak Data Term/Semester");
            System.out.println(" 4. Input/Cetak Data KRS (Overall)");
            System.out.println(" 5. Input/Cetak Data KHS (Overall)");
            System.out.println(" 6. Input/Cetak Data Detail KHS (MatKul)");
            System.out.println(" 7. Cetak Mata Kuliah Retake");
            System.out.println(" 8. Cetak IP Mahasiswa");
            System.out.println(" 9. Cetak Transkrip (Sementara) Mahasiswa");
            System.out.println("10. Cetak Transkrip Pembayaran (Mahasiswa)");
            System.out.println("11. Input/Cetak Data Dosen");
            System.out.println("12. Keluar");
            System.out.print("Pilihan Anda [1/2/3/4/5/6/7/8/9/10/11] ? ");
            String pilihan = keyboard.next();
            
            clearScreen();
            if (pilihan.equals("1")) {
                System.out.println("Data Mata Kuliah");
                System.out.println("----------------");
                System.out.println("1. Input");
                System.out.println("2. Cetak Mata Kuliah (Kode MatKul)");
                System.out.println("3. Cetak Semua Mata Kuliah");
                System.out.print("Pilihan Anda [1/2/3] ? ");
                String opsi = keyboard.next();

                clearScreen();
                if (opsi.equals("1")) {
                    boolean check = false;
                    while (check == false) {
                        clearScreen();
                        System.out.println("Input Mata Kuliah Baru");
                        System.out.println("----------------------");
                        System.out.print("Masukkan kode mata kuliah : ");
                        //Keterangan: Menambah exception handling agar penulisan kode matkul sesuai yang saya mau
                        try{
                            String kodeMatKul = keyboard.next();
                            String tes[] = kodeMatKul.split("_");
                            int kiri = Integer.parseInt(tes[0]);
                            String kanan = tes[1];
                            if(cekGarisBawah(kodeMatKul) == 1){
                                if (validasiKodeMatKul(kodeMatKul, mataKuliah) == 1) {
                                    System.out.print("Masukkan nama mata kuliah : ");
                                    String namaMatKul = keyboard.nextLine();
                                    namaMatKul += keyboard.nextLine();
                                    while (true) {
                                        try {
                                            clearScreen();
                                            System.out.println("Input Mata Kuliah Baru");
                                            System.out.println("----------------------");
                                            System.out.print("Masukkan kode mata kuliah : " + kodeMatKul);
                                            System.out.print("\nMasukkan nama mata kuliah : " + namaMatKul);
                                            System.out.print("\nMasukkan jumlah sks       : ");
                                            int sksMatKul = keyboard.nextInt();
                                            if (validasiSks(sksMatKul) == 1) {
                                                mataKuliah.add(new Matakuliah(kodeMatKul, namaMatKul, sksMatKul));
                                                check = true;
                                                break;
                                            }
                                            else {
                                                System.out.println("\nJumlah Sks harus berupa angka [2-4]");
                                                sleep(2000);
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("\nJumlah sks harus berupa angka [2-4]");
                                            sleep(2000);
                                            keyboard.nextLine();
                                        }
                                    }
                                }
                                else if (validasiKodeMatKul(kodeMatKul, mataKuliah) == 2) {
                                    throw new Exception("\nKode Matakuliah harus berupa 5 digit!");
                                }
                                else {
                                    throw new Exception("\nKode Matakuliah telah terdaftar..");
                                }
                            }
                            else {
                                throw new Exception("\nKode Matakuliah harus berisi '_' " +
                                "\nContoh: 01_SD");
                            }
                        } catch(NumberFormatException e){
                            System.out.println("\nKode Matakuliah harus berisi angka di sebelah kiri '_' " +
                            "\nContoh: 01_SD");
                            sleep(3000);
                            continue;
                        } catch(IndexOutOfBoundsException e){
                            System.out.println("\nKode Matakuliah harus berisi huruf di sebelah kanan '_' " +
                            "\nContoh: 01_SD");
                            sleep(3000);
                            continue;
                        } catch(Exception e){
                            System.out.println(e.getMessage());
                            sleep(3000);
                            continue;
                        }
                    }
                }
                else if (opsi.equals("2")) {
                    System.out.println("Cetak Mata Kuliah");
                    System.out.println("-----------------");
                    System.out.print("Masukkan kode mata kuliah : ");
                    String kode = keyboard.next();
                    if (mataKuliah.size() > 0) {
                        int idx = getIndexFromMataKuliah(mataKuliah, kode);
                        if (idx == -1) {
                            System.out.println("Kode yang Anda masukkan invalid..");
                        }
                        else {
                            clearScreen();
                            System.out.println("Data Mata Kuliah");
                            System.out.println("----------------");
                            System.out.println("Kode  : " + mataKuliah.get(idx).getKodeMataKuliah());
                            System.out.println("Nama  : " + mataKuliah.get(idx).getNamaMataKuliah());
                            System.out.println("Sks   : " + mataKuliah.get(idx).getSks());
                        }
                    }
                    else {
                        System.out.println("Tidak ada data mata kuliah yang ditemukan..");
                    }
                }
                else if (opsi.equals("3")) {
                    //Soal No. 5
                    //Keterangan: Menambahkan opsi untuk menampilkan data secara ascending atau descending
                    System.out.print("Ascending [1] / Descending [2] ? ");
                    String pil = keyboard.next();
                    if(pil.equals("1") || pil.equals("2")){
                        clearScreen();
                        System.out.println("----------------------------------------------------------------");
                        System.out.println("\t\t\tData Mata Kuliah");
                        System.out.println("----------------------------------------------------------------");
                        System.out.printf("%-20s %-25s %-4s\n", "Kode MatKul", "Nama MatKul", "Sks");
                        System.out.println("----------------------------------------------------------------");
                        Sorting.bubbleSortMatakuliah(mataKuliah, pil);
                        for (Matakuliah matKul : mataKuliah) {
                            matKul.tampilkanDataMataKuliah();
                        }
                        System.out.println("----------------------------------------------------------------");
                    }
                    else{
                        System.out.println("Pilihan tidak tersedia..");
                    }
                }
                else {
                    System.out.println("Pilihan tidak tersedia..");
                }
            }

            else if(pilihan.equals("2")) {
                System.out.println("Data Mahasiswa");
                System.out.println("--------------");
                System.out.println("1. Input");
                System.out.println("2. Cetak Mahasiswa (studentID)");
                System.out.println("3. Cetak Semua Data Mahasiswa");
                System.out.print("Pilihan Anda [1/2/3] ? ");
                String opsi = keyboard.next();

                clearScreen();
                if (opsi.equals("1")) {
                    boolean check = false;
                    while (check == false) {
                        clearScreen();
                        System.out.println("Input Mahasiswa Baru");
                        System.out.println("--------------------");
                        System.out.print("Masukkan studentID      : ");
                        // Keterangan: Menambah exception handling agar hanya boleh menginput angka
                        try{
                            String studentID = keyboard.next();
                            int tes = Integer.parseInt(studentID);
                            if (validasiStudentID(studentID, mahasiswa) == 1) {
                                System.out.print("Masukkan nama mahasiswa : ");
                                String namaMhsString = keyboard.nextLine();
                                namaMhsString += keyboard.nextLine();
                                System.out.print("Masukkan jurusan        : ");
                                String jurusanMhs = keyboard.nextLine();
                                while (true) {
                                    clearScreen();
                                    System.out.println("Input Mahasiswa Baru");
                                    System.out.println("--------------------");
                                    System.out.print("Masukkan studentID      : " + studentID);
                                    System.out.print("\nMasukkan nama mahasiswa : " + namaMhsString);
                                    System.out.print("\nMasukkan jurusan        : " + jurusanMhs);
                                    try {
                                        System.out.print("\nMasukkan tahun masuk    : ");
                                        int tahunMasuk = keyboard.nextInt();
                                        if (validasiTahunMasuk(tahunMasuk) == 1) {
                                            mahasiswa.add(new Mahasiswa(studentID, namaMhsString, jurusanMhs, tahunMasuk));
                                            check = true;
                                            break;
                                        }
                                        else {
                                            throw new Exception("\nTahun masuk harus berupa [1980-2022]!");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("\nTahun masuk harus berupa [1980-2022]!");
                                        sleep(2000);
                                        keyboard.nextLine();
                                        continue;
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        sleep(2000);
                                    }
                                    keyboard.nextLine();
                                }
                            }
                            else if (validasiStudentID(studentID, mahasiswa) == 2){
                                throw new Exception("\nStudentID harus berupa 5 digit");
                            }
                            else {
                                throw new Exception("\nStudentID telah terdaftar..");
                            }
                        }catch (NumberFormatException e){
                            System.out.println("\nStudenID harus berupa angka..");
                            sleep(2000);
                            keyboard.nextLine();
                            continue;
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                            sleep(2000);
                            keyboard.nextLine();
                            continue; 
                        }   
                    }
                }
                else if (opsi.equals("2")) {
                    System.out.println("Cetak Data Mahasiswa");
                    System.out.println("--------------------");
                    System.out.print("Masukkan studentID : ");
                    String studentID = keyboard.next();
                    if (mahasiswa.size()>0) {
                        int idx = getIndexFromMahasiswa(mahasiswa, studentID);
                        if (idx == -1) {
                            System.out.println("Kode yang Anda masukkan invalid..");
                        }
                        else {
                            clearScreen();
                            System.out.println("Data Mahasiswa");
                            System.out.println("--------------");
                            System.out.println("Student ID    : " + mahasiswa.get(idx).getStudentID());
                            System.out.println("Nama          : " + mahasiswa.get(idx).getNama());
                            System.out.println("Jurusan       : " + mahasiswa.get(idx).getJurusan());
                            System.out.println("Tahun masuk   : " + mahasiswa.get(idx).getTahunMasuk());
                        }
                    }
                    else {
                        System.out.println("Tidak ada data yang ditemukan..");
                    }
                }
                else if (opsi.equals("3")) {
                    //Soal No. 5
                    //Keterangan: Menambahkan opsi untuk menampilkan data secara ascending atau descending
                    System.out.print("Ascending [1] / Descending [2] ? ");
                    String pil = keyboard.next();
                    if(pil.equals("1") || pil.equals("2")){
                        clearScreen();
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("\t\t\tData Mahasiswa");
                        System.out.println("------------------------------------------------------------------------");
                        System.out.printf("%-10s %-25s %-20s %4s\n", "StudentID", "Nama","Jurusan", "Tahun Masuk");
                        System.out.println("------------------------------------------------------------------------");  
                        Sorting.bubbleSortMahasiswa(mahasiswa,pil);
                        for (Mahasiswa mhs : mahasiswa) {
                            mhs.tampilkanDataMember();
                        } 
                        System.out.println("------------------------------------------------------------------------");
                    }
                    else{
                        System.out.println("Pilihan tidak tersedia..");
                    }
                            
                }
                else {
                    System.out.println("Pilihan tidak tersedia..");
                }
            }

            else if (pilihan.equals("3")) {
                System.out.println("Data Term/Semester");
                System.out.println("------------------");
                System.out.println("1. Input");
                System.out.println("2. Cetak Term (kode term)");
                System.out.println("3. Cetak Semua Term");
                System.out.print("Pilihan Anda [1/2/3] ? ");
                String opsi = keyboard.next();

                clearScreen();
                if (opsi.equals("1")) {
                    boolean check = false;
                    while (check == false) {
                        clearScreen();
                        System.out.println("Input Data Term/Semester Baru");
                        System.out.println("-----------------------------");
                        System.out.print("Masukkan kode term          : ");
                        //Keterangan: Menambah exception handling agar format penulisan kode term sesuai yang saya mau
                        try{
                            String kodeTerm = keyboard.next();
                            String tes[] = kodeTerm.split("_");
                            int kiri = Integer.parseInt(tes[0]);
                            String kanan = tes[1];
                            if(cekGarisBawah(kodeTerm) == 1){
                                if (validasiKodeTerm(kodeTerm, term) == 1) {
                                    boolean check1 = false;
                                    while (check1 == false) {
                                        clearScreen();
                                        System.out.println("Input Data Term/Semester Baru");
                                        System.out.println("-----------------------------");
                                        System.out.print("Masukkan kode term          : " + kodeTerm);
                                        try {
                                            System.out.print("\nMasukkan tahun ajaran       : ");
                                            int tahunAjaran = keyboard.nextInt();
                                            keyboard.nextLine();
                                            if (validasiTahunAjaran(tahunAjaran) == 1) {
                                                while (true) {
                                                    clearScreen();
                                                    System.out.println("Input Data Term/Semester Baru");
                                                    System.out.println("-----------------------------");
                                                    System.out.print("Masukkan kode term          : " + kodeTerm);
                                                    System.out.print("\nMasukkan tahun ajaran       : " + tahunAjaran);
                                                    try {
                                                        System.out.print("\nMasukkan semester           : ");
                                                        String semester = keyboard.nextLine();
                                                        int Semester = Integer.parseInt(semester);
                                                        System.out.print("Masukkan keterangan         : ");
                                                        String keterangan = keyboard.next();
                                                        keterangan += keyboard.nextLine();
                                                        System.out.print("Masukkan keterangan singkat : ");
                                                        String keteranganSingkat = keyboard.nextLine();
                                                        term.add(new Term(kodeTerm, tahunAjaran, semester, keterangan, keteranganSingkat));
                                                        check1 = true;
                                                        check = true;
                                                        break;                                                 
                                                    } catch (Exception e) {
                                                        System.out.println("\nSemester harus diinput dengan angka bulat!");
                                                        sleep(2000);
                                                        continue;
                                                    }                                        
                                                }                                       
                                            }
                                            else {
                                                throw new Exception("\nTahun masuk harus berupa [1980-2022]!");
                                            }                                    
                                        } catch (InputMismatchException e) {
                                            System.out.println("\nTahun masuk harus berupa [1980-2022]!");
                                            sleep(2000);
                                            keyboard.nextLine();
                                            continue;
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                            sleep(2000);
                                        }                                                              
                                    }                            
                                }
                                else if (validasiKodeTerm(kodeTerm, term) == 2) {
                                    System.out.println("\nKode Term harus berupa 7 digit");
                                    sleep(2000);
                                    keyboard.nextLine();
                                    continue;
                                }
                                else {
                                    System.out.println("\nKode term telah terdaftar..");
                                    sleep(2000);
                                    keyboard.nextLine();
                                    continue;
                                }
                            }
                            else {
                                throw new Exception("\nKode term harus berisi '_' " +
                                "\nContoh: 01_term");
                            }
                        } catch(NumberFormatException e){
                            System.out.println("\nKode term harus berisi angka di sebelah kiri '_' " +
                            "\nContoh: 01_term");
                            sleep(3000);
                            continue;
                        } catch(IndexOutOfBoundsException e){
                            System.out.println("\nKode term harus berisi huruf di sebelah kanan '_' " +
                            "\nContoh: 01_term");
                            sleep(3000);
                            continue;
                        } catch(Exception e){
                            System.out.println(e.getMessage());
                            sleep(3000);
                            continue;
                        }
                    }
                }
                else if (opsi.equals("2")) {
                    System.out.println("Cetak Data Term/Semester");
                    System.out.println("--------------------");
                    System.out.print("Masukkan kode term : ");
                    String kodeTerm = keyboard.nextLine();
                    kodeTerm += keyboard.nextLine();
                    if (mahasiswa.size()>0) {
                        int idx = getIndexFromTerm(term, kodeTerm);
                        if (idx == -1) {
                            System.out.println("Kode yang Anda masukkan invalid..");
                        }
                        else {
                            term.get(idx).tampilTerm();
                        }
                    }
                    else {
                        System.out.println("Tidak ada data yang ditemukan..");
                    }
                }
                else if (opsi.equals("3")) {
                    //Soal No. 5
                    //Keterangan: Menambahkan opsi untuk menampilkan data secara ascending atau descending
                    System.out.print("Ascending [1] / Descending [2] ? ");
                    String pil = keyboard.next();
                    if(pil.equals("1") || pil.equals("2")){
                        clearScreen();
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("\t\t\t\tData Term");
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.printf("%-10s %-15s %-10s %-20s %-10s\n", "Kode", "Tahun Ajaran","Semester", "Keterangan", "Keterangan Singkat");
                        System.out.println("-------------------------------------------------------------------------------");                     
                        Sorting.bubbleSortTerm(term, pil);
                        for (Term t : term) {
                            t.tampilTerm();
                        }
                        System.out.println("-------------------------------------------------------------------------------");
                    }
                    else{
                        System.out.println("Pilihan tidak tersedia..");
                    }
                }
                else {
                    System.out.println("Pilihan tidak tersedia..");
                }
            }

            else if (pilihan.equals("4")) {
                System.out.println("Data Kartu Rencana studi (KRS)");
                System.out.println("------------------------------");
                System.out.println("1. Input");
                System.out.println("2. Tambah Matkul pada KRS");
                System.out.println("3. Cetak");
                System.out.print("Pilihan Anda [1/2/3] ? ");
                String opsi = keyboard.next();

                clearScreen();
                if (opsi.equals("1")) {
                    System.out.println("Input Data KRS Baru");
                    System.out.println("-------------------");
                    System.out.print("Masukkan studentID           : ");
                    String studentID = keyboard.next();
                    int index = getIndexFromMahasiswa(mahasiswa, studentID);
                    if (index == -1) {
                        System.out.println("\nstudentID yang Anda masukkan invalid..");
                    }
                    else {
                        boolean check = false;
                        while (check == false) {
                            clearScreen();
                            System.out.println("Input Data KRS Baru");
                            System.out.println("-------------------");
                            System.out.println("Masukkan studentID           : " + studentID);  
                            //Keterangan: Menghapus penginputan kode krs                          
                            System.out.print("Masukkan kode term           : ");
                            String kodeTerm = keyboard.next();
                            int idx = getIndexFromTerm(term, kodeTerm);
                            if (idx == -1) {
                                System.out.println("\nKode term yang Anda masukkan invalid..");
                            }
                            else {
                                try {
                                    if (validasiKodeTermKrs(kodeTerm, studentID, mahasiswa) == 1) {
                                        throw new Exception("\nAnda telah menginput KRS untuk term/semester tersebut..");
                                    }
                                    else if (validasiKodeTermKrs(kodeTerm, studentID, mahasiswa) == 2) {
                                        String yesNo = "y";
                                        int first = 0;
                                        while (yesNo.equalsIgnoreCase("y")) {
                                            clearScreen();
                                            System.out.println("Mata kuliah yang ingin diambil");
                                            System.out.println("------------------------------");
                                            System.out.print("Kode mata kuliah : ");
                                            String kodeMatKul = keyboard.next();
                                            int Index = getIndexFromMataKuliah(mataKuliah, kodeMatKul);
                                            if (Index == -1) {
                                                System.out.println("\nKode mata kuliah yang Anda masukkan invalid..");
                                            }
                                            //Keterangan: Menambah validasi agar tidak dapat mengambil matakuliah sudah pernah diambil
                                            else if(cekMatkulPadaKrs(studentID, kodeMatKul, mahasiswa)==1){
                                                System.out.println("\nMatakuliah telah pernah didaftarkan..");
                                            }
                                            else {
                                                //Keterangan: Membuat auto-generate kode krs
                                                if(first == 0){
                                                    mahasiswa.get(index).setCountKrs();
                                                    first++;
                                                }
                                                String kodeKRS = String.format("%02d", mahasiswa.get(index).getCountKrs())+"_krs";
                                                mahasiswa.get(index).krs.add(new Krs(kodeKRS, term.get(idx)));
                                                int idxKrs = getIndexKrsFromTerm(kodeTerm, studentID, mahasiswa);
                                                mahasiswa.get(index).getKRS().get(idxKrs).getDaftarMataKuliah().add(new Matakuliah(mataKuliah.get(Index).getKodeMataKuliah(), mataKuliah.get(Index).getNamaMataKuliah(), mataKuliah.get(Index).getSks()));
                                            }
                                            System.out.print("\nApakah Anda ingin memasukkan data lainnya [y/n] ? ");
                                            yesNo = keyboard.next();
                                        }                                            
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    sleep(2000);
                                    continue;
                                }
                            }
                            check = true;
                        }
                    }
                }
                else if (opsi.equals("2")) {
                    System.out.println("Tambah Matkul ke KRS");
                    System.out.println("--------------------");
                    System.out.print("Masukkan studentID : ");
                    String studentID = keyboard.nextLine();
                    studentID += keyboard.nextLine();
                    int index = getIndexFromMahasiswa(mahasiswa, studentID);
                    if (index == -1) {
                        System.out.println("\nStudentID yang Anda masukkan invalid..");
                    }
                    else {
                        //Keterangan: Mengganti penginputan kode krs menjadi kode term
                        System.out.print("Masukkan kode term  : ");
                        try{
                            String kodeTerm = keyboard.next();
                            int idx = getIndexFromTerm(term, kodeTerm);
                            //Keterangan: Menambahkan Exception Handling
                            if (idx == -1) {
                                throw new Exception("\nKode term yang Anda masukkan invalid..");
                            }
                            else if(mahasiswa.get(index).krs.get(idx) == null){
                                throw new IndexOutOfBoundsException();
                            }
                            else{
                                clearScreen();
                                String yesNo = "y";
                                while (yesNo.equalsIgnoreCase("y")) {
                                    clearScreen();
                                    System.out.println("Mata kuliah yang ingin diambil");
                                    System.out.println("------------------------------");
                                    System.out.print("Kode mata kuliah : ");
                                    String kodeMatKul = keyboard.next();
                                    int Index = getIndexFromMataKuliah(mataKuliah, kodeMatKul);
                                    if (Index == -1) {
                                        System.out.println("\nKode mata kuliah yang Anda masukkan invalid..");
                                    }
                                    //Keterangan: Menambah validasi agar tidak dapat mengambil matakuliah sudah pernah diambil
                                    else if(cekMatkulPadaKrs(studentID, kodeMatKul, mahasiswa)==1){
                                        System.out.println("\nMatakuliah telah pernah didaftarkan..");
                                    }
                                    else {
                                        int Idx = getIndexKrsFromTerm(kodeTerm, studentID, mahasiswa);
                                        mahasiswa.get(index).getKRS().get(Idx).getDaftarMataKuliah().add(new Matakuliah(mataKuliah.get(Index).getKodeMataKuliah(), mataKuliah.get(Index).getNamaMataKuliah(), mataKuliah.get(Index).getSks()));
                                    }
                                    System.out.print("\nApakah Anda ingin memasukkan data lainnya [y/n] ? ");
                                    yesNo = keyboard.next();
                                }                                                                
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("\nTidak ada krs yang terdaftar pada term ini..");
                            sleep(3000);
                            continue;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            sleep(2000);
                            continue;
                        }
                    }
                }
                else if (opsi.equals("3")) {
                    System.out.println("Cetak Data KRS");
                    System.out.println("--------------");
                    System.out.print("Masukkan studentID : ");
                    String studentID = keyboard.next();
                    int index = getIndexFromMahasiswa(mahasiswa, studentID);
                    if (index == -1) {
                        System.out.println("\nStudentID yang Anda masukkan invalid..");
                    }
                    else {
                        //Keterangan: Mengganti penginputan kode krs menjadi kode term
                        System.out.print("Masukkan kode term  : ");
                        try{
                            String kodeTerm = keyboard.next();
                            int cek = getIndexFromTerm(term, kodeTerm);
                            int idx = getIndexKrsFromTerm(kodeTerm, studentID, mahasiswa);
                            //Keterangan: Menambahkan Exception Handling
                            if(cek == -1){
                                throw new Exception("\nKode term yang Anda masukkan invalid..");
                            }
                            else if(mahasiswa.get(index).krs.get(idx) == null){
                                throw new IndexOutOfBoundsException();
                            }
                            else {
                                clearScreen();  
                                System.out.println("Data Kartu Rencana Studi (KRS)");
                                System.out.println("------------------------------");
                                System.out.println("Daftar Mata Kuliah yang diambil  : ");
                                System.out.println("---------------------------------------------------------------------------------------");
                                System.out.printf("%-10s %-20s %-13s %-30s %-10s\n", "Semester", "Keterangan", "Kode Matkul", "Nama Matkul", "Jumlah SKS");
                                System.out.println("---------------------------------------------------------------------------------------");
                                mahasiswa.get(index).krs.get(idx).tampilkanKrs();
                                System.out.println("---------------------------------------------------------------------------------------");
                            }
                        }catch(IndexOutOfBoundsException e){
                            System.out.println("\nBelum ada Krs pada term yang Anda inputkan..");
                            sleep(3000);
                            continue;
                        }catch(Exception e){
                            System.out.println(e.getMessage());
                            sleep(2000);
                            continue;
                        }
                    }
                }
                else {
                    System.out.println("Pilihan tidak tersedia..");
                }
            }

            else if (pilihan.equals("5")) {
                System.out.println("Data Kartu hasil studi (KHS)");
                System.out.println("---------------------------");
                System.out.println("1. Input");
                System.out.println("2. Cetak");
                System.out.print("Pilihan Anda [1/2] ? ");
                String opsi = keyboard.next();

                clearScreen();
                if (opsi.equals("1")) {
                    System.out.println("Input Data KHS Baru");
                    System.out.println("-------------------");
                    System.out.print("Masukkan studentID           : ");
                    String studentID = keyboard.next();
                    int index = getIndexFromMahasiswa(mahasiswa, studentID);
                    if (index == -1) {
                        System.out.println("studentID yang Anda masukkan invalid..");
                    }
                    else {
                        boolean check = false;
                        int first = 0;
                        while (check == false) {
                            clearScreen();
                            System.out.println("Input Data KHS Baru");
                            System.out.println("-------------------");
                            System.out.print("Masukkan studentID           : " + studentID);
                            //Keterangan: Menghapus penginputan kode khs
                            System.out.print("\nMasukkan kode term           : ");
                            String kodeTerm = keyboard.next();
                            int idx = getIndexFromTerm(term, kodeTerm);
                            if (idx == -1) {
                                System.out.println("\nKode term yang Anda masukkan invalid..");
                                sleep(2000);
                            }
                            else {
                                try{
                                    if (validasiKodeTermKhs(kodeTerm, studentID, mahasiswa) == 1) {
                                    throw new Exception("\nAnda telah menginput KHS untuk term/semester tersebut..");
                                    }
                                    else {
                                        System.out.print("Masukkan keterangan          : ");
                                        String keterangan = keyboard.next();
                                        System.out.print("Masukkan keterangan singkat  : ");
                                        String keteranganSingkat = keyboard.next();
                                        //Keterangan: Membuat auto-generate code khs
                                        if(first == 0){
                                            mahasiswa.get(index).setCountKhs();
                                            first++;
                                        }
                                        String kodeKHS = String.format("%02d", mahasiswa.get(index).getCountKhs()) + "_khs";
                                        mahasiswa.get(index).khs.add(new Khs(kodeKHS, keterangan, keteranganSingkat, mahasiswa.get(index), term.get(idx)));
                                        check = true;
                                    }
                                }
                                catch (Exception e){
                                    System.out.println(e.getMessage());
                                    sleep(2000);
                                    continue;
                                }
                            }                                
                        }
                    }
                }
                else if (opsi.equals("2")) {
                    System.out.println("Cetak Data KHS");
                    System.out.println("--------------");
                    System.out.print("Masukkan studentID : ");
                    String studentID = keyboard.nextLine();
                    studentID += keyboard.nextLine();
                    int index = getIndexFromMahasiswa(mahasiswa, studentID);
                    if (index == -1) {
                        System.out.println("\nStudentID yang Anda masukkan invalid..");
                    }
                    else {
                        //Keterangan: Mengganti penginputan kode khs dengan kode term
                        System.out.print("Masukkan kode term  : ");
                        String kodeTerm = keyboard.next();
                        boolean temu = false;
                        int idx = 0;    
                        for (Khs khs1 : mahasiswa.get(index).getKHS()) {
                            //Keterangan: Mengganti pencarian dengan kode khs menjadi dengan kode term
                            if (khs1.getDetailTerm().getKodeTerm().equalsIgnoreCase(kodeTerm)) {
                                temu = true;
                                if (mahasiswa.get(index).khs.get(idx).getKhsDetails().size() == 0) {
                                    System.out.println("\nTidak ada Detail KHS yang ditemukan..");
                                }
                                else {
                                    clearScreen();
                                    System.out.println("---------------------------------------------------------------------------");
                                    System.out.println("\t\t\tKartu Hasil Studi");
                                    System.out.println("---------------------------------------------------------------------------");
                                    System.out.println("Nama        : " +  mahasiswa.get(index).getNama());
                                    System.out.println("Student ID  : " +  mahasiswa.get(index).getStudentID());
                                    System.out.println("Jurusan     : " +  mahasiswa.get(index).getJurusan());
                                    System.out.println("Term        : " +  term.get(idx).getSemester());
                                    System.out.println("---------------------------------------------------------------------------");
                                    System.out.printf("%-20s %-30s %-5s\n", "Kode Detail Khs", "Nama MatKul","Nilai");
                                    System.out.println("---------------------------------------------------------------------------");
                                    for (KhsDetail khsdetail : mahasiswa.get(index).getKHS().get(idx).getKhsDetails()) {
                                        if (cekKodeDetailKhs(idx, khsdetail.getDetailMatakuliah().getKodeMataKuliah(), studentID, mahasiswa, mataKuliah) == 1) {
                                            khsdetail.tampilkanDetailKHS("");
                                        }
                                        else {
                                            khsdetail.tampilkanDetailKHS("retake");
                                        }
                                    }
                                    System.out.println("---------------------------------------------------------------------------");
                                }
                                break;
                            }
                            idx++;
                        }
                        if(temu == false){
                            System.out.println("\nKode term yang Anda masukkan invalid..");
                        }
                    }
                }
                else {
                    System.out.println("\nPilihan tidak tersedia..");
                }
            }

            else if (pilihan.equals("6")) {
                System.out.println("Data Detail KHS (MatKul)");
                System.out.println("------------------------");
                System.out.println("1. Input");
                System.out.println("2. Cetak");
                System.out.print("Pilihan Anda [1/2] ? ");
                String opsi = keyboard.next();

                clearScreen();
                if (opsi.equals("1")) {
                    System.out.println("Input Data Detail KHS Baru");
                    System.out.println("-----------------------------");
                    System.out.print("Masukkan studentID        : ");
                    String studentID = keyboard.nextLine();
                    studentID += keyboard.nextLine();
                    int index = getIndexFromMahasiswa(mahasiswa, studentID);
                    if (index == -1) {
                        System.out.println("\nStudentID yang Anda masukkan invalid..");
                    }
                    else {
                        boolean check = false;
                        int first = 0;
                        while (check == false) {
                            clearScreen();
                            System.out.println("Input Data Detail KHS Baru");
                            System.out.println("-----------------------------");
                            System.out.println("Masukkan studentID        : " + studentID);
                            //Keterangan: Mengganti penginputan kode khs menjadi kode term
                            System.out.print("Masukkan kode term        : ");
                            String kodeTerm = "";
                            try {
                                kodeTerm = keyboard.next();
                                int idx = getIndexFromTerm(term, kodeTerm);
                                if (idx == -1){
                                    throw new Exception("\nKode term yang Anda masukkan invalid..");
                                }
                                else{
                                    int idxKhs = getIndexKhsFromTerm(studentID, kodeTerm, mahasiswa);
                                    if(first == 0){
                                        mahasiswa.get(index).getKHS().get(idxKhs).setCountKhsd();
                                        first++;
                                    }
                                    String kodeDetailKHS = String.format("%02d", mahasiswa.get(index).getKHS().get(idxKhs).getCountKhsd()) + "_khsd";                                
                                    for (Khs khs : mahasiswa.get(index).getKHS()) {
                                        //Keterangan: Mengganti pengecekan dengan kode khs menjadi kode term
                                        if (khs.getDetailTerm().getKodeTerm().equals(kodeTerm)) {
                                            boolean check2 = false;
                                            while (check2 == false) {
                                                clearScreen();
                                                System.out.println("Input Data Detail KHS Baru");
                                                System.out.println("-----------------------------");
                                                System.out.println("Masukkan studentID        : " + studentID);
                                                System.out.println("Masukkan kode term        : " + kodeTerm);
                                                System.out.print("Masukkan kode mata kuliah : ");
                                                String matKul = keyboard.next();
                                                int IDX = getIndexFromMataKuliah(mataKuliah, matKul);
                                                try {
                                                    if (IDX != -1) {
                                                        if (validasiMatKulKrs(matKul, kodeTerm, studentID, mahasiswa) == 1 && 
                                                            validasiKodeMatKulKhsd(matKul, kodeTerm, studentID, mahasiswa) == 2) {
                                                            int first1 = 0;
                                                            while (true) {
                                                                clearScreen();
                                                                System.out.println("Input Data Detail KHS Baru");
                                                                System.out.println("-----------------------------");
                                                                System.out.println("Masukkan studentID        : " + studentID);
                                                                System.out.println("Masukkan kode term        : " + kodeTerm);
                                                                System.out.println("Masukkan kode mata kuliah : " + matKul);
                                                                System.out.print("Masukkan nilai            : ");
                                                                try {                                                
                                                                    int nilai = keyboard.nextInt();
                                                                    Mahasiswa mhs = mahasiswa.get(index);
                                                                    if(first1 == 0){
                                                                        mhs.setCountKhs();
                                                                        first1++;
                                                                    }
                                                                    String kodeKHS = String.format("%02d", mhs.getCountKhs()) + "_khs"; 
                                                                    if (nilai >= 0 && nilai <= 100) {
                                                                        if (nilai < 60 && nilai >= 0) {
                                                                            System.out.print("\nTekan y untuk retake matakuliah ini: ");
                                                                            String jwb = keyboard.next();
                                                                            if(jwb.equalsIgnoreCase("y")) {
                                                                                int first2 = 0;
                                                                                while (true) {
                                                                                    clearScreen();
                                                                                    System.out.println("Retake Mata Kuliah");
                                                                                    System.out.println("------------------");
                                                                                    System.out.print("Masukkan term untuk melakukan retake (Kode Term) : ");
                                                                                    try {
                                                                                        String kodeTerm2 = keyboard.next();
                                                                                        int idx2 = getIndexFromTerm(term, kodeTerm2);
                                                                                        if (idx2 == -1) {
                                                                                            throw new Exception("\nKode term yang Anda masukkan invalid..");
                                                                                        }
                                                                                        else {
                                                                                            int Idx = getIndexKhsFromTerm(studentID, kodeTerm, mahasiswa);
                                                                                            if (validasiTermRetake(mahasiswa.get(index).getKHS().get(Idx).getTerm().getKodeTerm(), kodeTerm2, term) == 1){
                                                                                                throw new Exception("\nRetake tidak dapat dilakukan pada term sebelum dan term saat ini..");
                                                                                            }
                                                                                            else {
                                                                                                clearScreen();
                                                                                                String kodeKrs = getKrsDariTerm(studentID, kodeTerm2, mahasiswa);
                                                                                                if (kodeKrs.equals("")) {
                                                                                                    if(first2 == 0){
                                                                                                        mhs.setCountKrs();
                                                                                                        first2++;
                                                                                                    }
                                                                                                    String kodeKrsBaru = String.format("%02d", mhs.getCountKrs()) + "_krs";
                                                                                                    mhs.khs.get(idxKhs).khsDetails.add(new KhsDetail(kodeDetailKHS, kodeKHS, mataKuliah.get(IDX), nilai));
                                                                                                    mhs.getKRS().add(new Krs(kodeKrsBaru, term.get(idx2)));
                                                                                                    int indexKrs = getIndexKrs(kodeKrsBaru, studentID, mahasiswa);
                                                                                                    mhs.getKRS().get(indexKrs).getDaftarMataKuliah().add(new Matakuliah(mataKuliah.get(IDX).getKodeMataKuliah(), mataKuliah.get(IDX).getNamaMataKuliah() + " (Retake)", mataKuliah.get(IDX).getSks()));
                                                                                                }
                                                                                                else {
                                                                                                    int indexKrs = getIndexKrs(kodeKrs, studentID, mahasiswa);
                                                                                                    mhs.getKRS().get(indexKrs).getDaftarMataKuliah().add(new Matakuliah(mataKuliah.get(IDX).getKodeMataKuliah(), mataKuliah.get(IDX).getNamaMataKuliah() + " (Retake)", mataKuliah.get(IDX).getSks()));
                                                                                                    mhs.khs.get(idxKhs).khsDetails.add(new KhsDetail(kodeDetailKHS, kodeKHS, mataKuliah.get(IDX), nilai));
                                                                                                }
                                                                                                System.out.println("\nMatakuliah akan di-retake pada term yang telah ditentukan");
                                                                                                break;
                                                                                            }
                                                                                        }                                                                                               
                                                                                    } catch (Exception e) {
                                                                                        System.out.println(e.getMessage());
                                                                                        sleep(2000);
                                                                                        continue;
                                                                                    }
                                                                                }
                                                                            }
                                                                            else {
                                                                                mhs.khs.get(idxKhs).khsDetails.add(new KhsDetail(kodeDetailKHS, kodeKHS, mataKuliah.get(IDX), nilai));
                                                                                System.out.println("\nMatakuliah ini tidak akan di-retake");
                                                                            }
                                                                        }
                                                                        else {
                                                                            if (cekMatkulRetake(matKul, studentID, mahasiswa, mataKuliah) == 1) {
                                                                                int idxx = getKodeKhsRetake(matKul, studentID, mahasiswa, mataKuliah);
                                                                                mhs.khs.get(idxx).retake.add(new Retake(kodeDetailKHS, kodeKHS, mataKuliah.get(IDX), nilai, mahasiswa.get(index).getKHS().get(idxx).getTerm()));
                                                                                mhs.khs.get(idxKhs).khsDetails.add(new KhsDetail(kodeDetailKHS, kodeKHS, mataKuliah.get(IDX), nilai));
                                                                                int INDEX = getKodeKhsDetailretake(kodeTerm, matKul, studentID, mahasiswa, mataKuliah);
                                                                                mhs.khs.get(idxKhs).khsDetails.get(INDEX).setStringRetake("retake");
                                                                            }
                                                                            else mhs.khs.get(idxKhs).khsDetails.add(new KhsDetail(kodeDetailKHS, kodeKHS, mataKuliah.get(IDX), nilai));
                                                                        }
                                                                        check2 = true;
                                                                        break;
                                                                    }
                                                                    else {
                                                                        throw new Exception("\nNilai harus berupa angka [1-100]!");
                                                                    }
                                                                } catch (InputMismatchException e) {
                                                                    System.out.println("\nNilai harus berupa angka!");
                                                                    keyboard.nextLine();
                                                                    sleep(2000);
                                                                    continue;
                                                                } catch(Exception e){
                                                                    System.out.println(e.getMessage());
                                                                    sleep(2000);
                                                                    continue;
                                                                }
                                                            }
                                                        }
                                                        else if(validasiMatKulKrs(matKul, kodeTerm, studentID, mahasiswa) == 2){
                                                            throw new Exception("\nMatakuliah ini tidak terdaftar pada KRS Anda..");
                                                        }  
                                                        else if(validasiKodeMatKulKhsd(matKul, kodeTerm, studentID, mahasiswa) == 1){
                                                            throw new Exception("\nMatakuliah ini sudah terdaftar pada KHS Anda..");
                                                        }
                                                    }
                                                    else {
                                                        if (matKul.length() == 5) {
                                                            System.out.println("\nKode Mata Kuliah yang Anda masukkan invalid..");
                                                        }
                                                        else System.out.println("\nKode Mata Kuliah harus berupa 5 digit!");
                                                        sleep(2000);
                                                    }    
                                                } catch (Exception e) {
                                                    System.out.println(e.getMessage());
                                                    sleep(2000);
                                                    continue;
                                                }
                                            }
                                            break;
                                        }
                                    }                                
                                }  
                            //Keterangan: tambah exception handling                           
                            }catch (IndexOutOfBoundsException e) {
                                System.out.println("\nTidak ada Khs detail pada term yang Anda masukkan..");
                                sleep(2000);
                                break; 
                            }catch (Exception e) {
                                System.out.println(e.getMessage());
                                sleep(2000);
                                continue;
                            } 
                            check = true;
                        }
                    }
                }
                else if (opsi.equals("2")) {
                    System.out.println("Cetak Data Detail KHS");
                    System.out.println("---------------------");
                    System.out.print("Masukkan studentID        : ");
                    String studentID = keyboard.nextLine();
                    studentID += keyboard.nextLine();
                    int index = getIndexFromMahasiswa(mahasiswa, studentID);
                    if (index == -1) {
                        System.out.println("\nStudentID yang Anda masukkan invalid..");
                    }
                    else {
                        //Keterangan: Mengganti penginputan kode khs menjadi kode term
                        System.out.print("Masukkan kode term        : ");
                        try {
                            String kodeTerm = keyboard.next();
                            int idxTerm = getIndexFromTerm(term, kodeTerm);
                            //Keterangan: Mengganti Exception Handling
                            if (idxTerm != 1) {
                                int idx = 0;
                                boolean temu = false;
                                for (Khs khs : mahasiswa.get(index).getKHS()) {
                                    if (khs.getDetailTerm().getKodeTerm().equals(kodeTerm)) {
                                        temu = true;
                                        System.out.print("Masukkan kode detail KHS  : ");
                                        try {
                                            String kodeDetailKHS = keyboard.next();
                                            if (validasiKodeKhsDetail(kodeDetailKHS, kodeTerm, studentID, mahasiswa) == 1) {
                                                clearScreen();
                                                boolean ketemu = false;
                                                int Index = 0;
                                                System.out.println("-------------------------------------------------------------");
                                                System.out.printf("%-20s %-10s\n", " ","Detail KHS");
                                                System.out.println("-------------------------------------------------------------");
                                                System.out.printf("%-20s %-30s %-5s\n", "Kode Detail Khs", "Nama Matkul", "Nilai");
                                                System.out.println("-------------------------------------------------------------");
                                                for (KhsDetail khsDetail : mahasiswa.get(index).khs.get(idx).khsDetails) {
                                                    if (khsDetail.kodeKHSDetail.equals(kodeDetailKHS)) {
                                                        ketemu = true;
                                                        if (cekKodeDetailKhs(idx, khsDetail.getDetailMatakuliah().getKodeMataKuliah(), studentID, mahasiswa, mataKuliah) == 1) {
                                                            mahasiswa.get(index).khs.get(idx).khsDetails.get(Index).tampilkanDetailKHS("");
                                                        }
                                                        else {
                                                            mahasiswa.get(index).khs.get(idx).khsDetails.get(Index).tampilkanDetailKHS("retake");
                                                        }
                                                    }
                                                    Index++;
                                                }
                                                System.out.println("-------------------------------------------------------------");
                                                if(ketemu == false) {
                                                    System.out.println("\nKode detail KHS yang Anda masukkan invalid..");
                                                }
                                                break;                                            
                                            }
                                            else if (validasiKodeKhsDetail(kodeDetailKHS, kodeTerm, studentID, mahasiswa) == 2){
                                                throw new Exception("\nKode detail KHS tidak terdaftar..");
                                            }else{
                                                throw new Exception("\nKode detail KHS harus berupa 7 digit!");
                                            }
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                            sleep(2000);
                                            continue;
                                        }
                                    }
                                    idx++;
                                }
                                //Keterangan: Tambah exception handling
                                if(temu == false){
                                    throw new Exception("\nTidak ada khs detail pada term yang Anda masukkan..");
                                }
                            }
                            else {
                                throw new Exception ("\nKode term yang Anda masukkan invalid..");
                            }  
                        //Keterangan: Tambah exception handling                         
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("\nTidak ada khs detail pada term yang Anda masukkan..");
                            sleep(3000);
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            sleep(2000);
                            continue;
                        }
                    }
                }
                else {
                    System.out.println("Pilihan tidak tersedia..");
                }
            }

            else if (pilihan.equals("7")) {
                System.out.println("Cetak Mata Kuliah Retake");
                System.out.println("-------------------------");
                System.out.print("Masukkan studentID : ");
                String studentID = keyboard.next();
                int index = getIndexFromMahasiswa(mahasiswa, studentID);
                if (index == -1) {
                    System.out.println("StudentID yang Anda masukkan invalid..");
                }
                else {
                    clearScreen();
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("\t\t     Data Mata Kuliah Retake");
                    System.out.println("----------------------------------------------------------------");
                    System.out.printf("%-20s %-30s %-4s\n", "Kode MatKul", "Nama MatKul", "Sks");
                    System.out.println("----------------------------------------------------------------");
                    int jumlahRetake = 0;
                    for (Khs khs : mahasiswa.get(index).getKHS()) {
                        jumlahRetake+=khs.retake.size();
                        for (Retake retake : khs.retake) {
                            retake.tampilkanMataKuliahRetake();
                        }
                    }
                    if (jumlahRetake == 0) {
                        System.out.println("Tidak ada data yang ditemukan..");
                    }
                    System.out.println("----------------------------------------------------------------");
                }
            }
            
            else if (pilihan.equals("8")) {
                System.out.println("Cetak IP Mahasiswa");
                System.out.println("------------------");
                System.out.println("1. IP Mahasiswa per Semester");
                System.out.println("2. IPK Sementara Mahasiswa");
                System.out.print("Pilihan Anda [1/2] ? ");
                String opsi = keyboard.next();

                clearScreen();
                if(opsi.equals("1")){
                    System.out.println("IP Mahasiswa per Semester");
                    System.out.println("--------------------------");
                    System.out.print("Masukkan studentID : ");
                    String studentID = keyboard.next();
                    int index = getIndexFromMahasiswa(mahasiswa, studentID);
                    if (index == -1) {
                        System.out.print("\nStudentID yang Anda masukkan invalid..");
                    }
                    else {
                        System.out.print("Masukkan kode term : ");
                        String kodeTerm = keyboard.next();
                        int idx = getIndexFromTerm(term, kodeTerm);
                        if (idx == -1) {
                            System.out.println("Kode term yang Anda masukkan invalid..");
                        }
                        else {
                            clearScreen();
                        System.out.println("----------------------------------------------------------------");
                        System.out.println("\t\t  IPK Mahasiswa per Semester");
                        System.out.println("----------------------------------------------------------------");
                        System.out.println("Nama          : " +  mahasiswa.get(index).getNama());
                        System.out.println("Student ID    : " +  mahasiswa.get(index).getStudentID());
                        System.out.println("Jurusan       : " +  mahasiswa.get(index).getJurusan()); 
                        System.out.println("Term          : " +  term.get(idx).getSemester());
                        System.out.println("IP Semester   : " +  String.format("%.2f",mahasiswa.get(index).getIPKTerm(term.get(idx))));
                        System.out.println("----------------------------------------------------------------");
                        }
                    }
                }
                else if(opsi.equals("2")){
                    System.out.println("IPK Sementara Mahasiswa");
                    System.out.println("-----------------------");
                    System.out.print("Masukkan studentID : ");
                    String studentID = keyboard.next();
                    int idx = getIndexFromMahasiswa(mahasiswa, studentID);
                    if (idx == -1) {
                        System.out.println("\nStudentID yang Anda masukkan invalid..");
                    }
                    else {
                        clearScreen();
                        System.out.println("----------------------------------------------------------------");
                        System.out.println("\t\t  IPK Sementara Mahasiswa");
                        System.out.println("----------------------------------------------------------------");
                        System.out.println("Nama          : " +  mahasiswa.get(idx).getNama());
                        System.out.println("Student ID    : " +  mahasiswa.get(idx).getStudentID());
                        System.out.println("Jurusan       : " +  mahasiswa.get(idx).getJurusan()); 
                        System.out.println("IPK Sementara : " +  String.format("%.2f",mahasiswa.get(idx).getIPK()));
                        System.out.println("----------------------------------------------------------------");
                    }
                }
                else{
                    System.out.println("\nPilihan tidak tersedia..");
                }
            }

            else if (pilihan.equals("9")) {
                System.out.println("Transkrip Nilai Mahasiswa");
                System.out.println("-------------------------");
                System.out.print("Masukkan studentID : ");
                String studentID = keyboard.next();
                int idx = getIndexFromMahasiswa(mahasiswa, studentID);
                if (idx == -1) {
                    System.out.println("\nstudentID yang Anda masukkan invalid..");
                }
                else {
                    clearScreen();
                    System.out.println("------------------------------------------------------------------------------------------------");
                    System.out.println("\t\t\t\tTranskrip Nilai Mahasiswa");
                    System.out.println("------------------------------------------------------------------------------------------------");
                    System.out.println("Nama        : " +  mahasiswa.get(idx).getNama());
                    System.out.println("Student ID  : " +  mahasiswa.get(idx).getStudentID());
                    System.out.println("Jurusan     : " +  mahasiswa.get(idx).getJurusan());
                    System.out.println("------------------------------------------------------------------------------------------------");
                    
                    if (mahasiswa.get(idx).khs.size() == 0) {
                        System.out.println("\nData tidak ditemukan..");
                    }
                    else {
                        int index = 0;
                        System.out.printf("%-8s  %-13s %-25s %-6s %-12s %-12s %-7s\n", "Semester", "Kode MatKul", "Nama MatKul","Sks","Nilai Huruf", "Nilai Angka","Angka Kualitas");
                        System.out.println("------------------------------------------------------------------------------------------------");
                        for (Khs khs : mahasiswa.get(idx).khs) {
                            String semester = khs.getDetailTerm().getSemester();
                            for (KhsDetail khsDetail : khs.getKhsDetails()) {
                                String kode = khsDetail.getDetailMatakuliah().getKodeMataKuliah();
                                String nama = khsDetail.getDetailMatakuliah().getNamaMataKuliah();
                                int sks = khsDetail.getDetailMatakuliah().getSks();
                                String huruf = khsDetail.konversiNilaiHuruf();
                                int nilai = khsDetail.getNilai();
                                float angka = khsDetail.getDetailMatakuliah().getSks() *khsDetail.konversiNilai();
                                if (cekKodeDetailKhs(index, khsDetail.getDetailMatakuliah().getKodeMataKuliah(), studentID, mahasiswa, mataKuliah) == 2) {
                                    nama = khsDetail.getDetailMatakuliah().getNamaMataKuliah() + " (Retake)";
                                }
                                System.out.printf("%-8s  %-13s %-25s %-6d %-12s %-12d %-7.2f\n", semester, kode, nama, sks, huruf, nilai, angka);
                            }
                            index++;
                        }
                        System.out.println("------------------------------------------------------------------------------------------------\n");
                    }
                }
            }

            else if (pilihan.equals("10")) {
                System.out.println("Cetak Transkrip Pembayaran");
                System.out.println("--------------------------");
                System.out.println("1. Biaya Tagihan per Semester");
                System.out.println("2. Biaya Tagihan Keseluruhan Semester");
                System.out.print("Pilihan Anda [1/2] ? ");
                String opsi = keyboard.next();

                clearScreen();
                if (opsi.equals("1")) {
                    System.out.println("Biaya Tagihan Semester Mahasiswa");
                    System.out.println("--------------------------------");
                    System.out.print("Masukkan studentID : ");
                    String studentID = keyboard.next();
                    int index = getIndexFromMahasiswa(mahasiswa, studentID);
                    if (index == -1) {
                        System.out.print("StudentID yang Anda masukkan invalid..");
                    }
                    else {
                        System.out.print("Masukkan kode term : ");
                        String kodeTerm = keyboard.next();
                        int idx = getIndexFromTerm(term, kodeTerm);
                        if (idx == -1) {
                            System.out.println("Kode term yang Anda masukkan invalid..");
                        }
                        else {
                            try{
                                int Index = 0;
                                for (Krs krs : mahasiswa.get(index).getKRS()) {
                                    if (krs.getTerm().getKodeTerm().equals(kodeTerm)) {
                                        break;
                                    }
                                    Index++;
                                }
                                mahasiswa.get(index).getKRS().get(Index).getTagihan().tampilkanPembayaran();
                            } catch(IndexOutOfBoundsException e){
                                System.out.println("\nBelum ada matakuliah yang terdaftar pada semester ini..");
                                sleep(2000);
                                continue;
                            }
                        }
                    } 
                } 
                else if(opsi.equals("2")) {
                    System.out.println("Biaya Tagihan Keseluruhan Semester Mahasiswa");
                    System.out.println("--------------------------------------------");
                    System.out.print("Masukkan studentID : ");
                    String studentID = keyboard.next();
                    int index = getIndexFromMahasiswa(mahasiswa, studentID);
                    if (index == -1) {
                        System.out.print("StudentID yang Anda masukkan invalid..");
                    }
                    else {
                        try{
                            mahasiswa.get(index).tampilkanPembayaran();;
                        } catch(IndexOutOfBoundsException e){
                            System.out.println("\nBelum ada matakuliah yang terdaftar pada semester ini..");
                            sleep(2000);
                            continue;
                        }
                    
                    }
                }
                else {
                    System.out.println("\nPilihan tidak tersedia..");
                }  
            }

            //Keterangan: Membuat menu untuk data-data dosen
            else if(pilihan.equals("11")) {
                System.out.println("Data Dosen");
                System.out.println("----------");
                System.out.println("1. Input Data Dosen");
                System.out.println("2. Cetak Data Dosen");
                System.out.println("3. Cetak Semua Data Dosen");
                System.out.print("Pilihan Anda [1/2/3] ? ");
                String opsi = keyboard.next();

                clearScreen();
                if (opsi.equals("1")) {
                    boolean check = false;
                    while (check == false) {
                        clearScreen();
                        System.out.println("Input Dosen Baru");
                        System.out.println("----------------");
                        System.out.print("Masukkan ID Dosen            : ");
                        try{
                            String dosenID = keyboard.next();
                            int num = Integer.parseInt(dosenID);
                            if (validasiDosenID(dosenID, dosen) == 1) {
                                System.out.print("Masukkan nama dosen          : ");
                                String namaDosen = keyboard.nextLine();
                                namaDosen += keyboard.nextLine();
                                while(true){
                                    clearScreen();
                                    System.out.println("Input Dosen Baru");
                                    System.out.println("----------------");
                                    System.out.print("Masukkan ID Dosen            : " + dosenID);
                                    System.out.print("\nMasukkan nama dosen          : " + namaDosen);
                                    System.out.print("\nMasukkan pendidikan terakhir : ");
                                    try{
                                        String pendidikan = keyboard.next();
                                        if (pendidikan.equalsIgnoreCase("S1") || 
                                        pendidikan.equalsIgnoreCase("S2") || 
                                        pendidikan.equalsIgnoreCase("S3")) {
                                            while (true) {
                                                clearScreen();
                                                System.out.println("Input Dosen Baru");
                                                System.out.println("----------------");
                                                System.out.print("Masukkan ID Dosen            : " + dosenID);
                                                System.out.print("\nMasukkan nama dosen          : " + namaDosen);
                                                System.out.print("\nMasukkan pendidikan terakhir : " + pendidikan);
                                                System.out.print("\nMasukkan tahun masuk         : ");
                                                try {
                                                    int tahunMasuk = keyboard.nextInt();
                                                    if (validasiTahunMasuk(tahunMasuk) == 1) {
                                                        dosen.add(new Dosen(dosenID, namaDosen, tahunMasuk, pendidikan));
                                                        check = true;
                                                        break;
                                                    }
                                                    else {
                                                        throw new Exception("\nTahun masuk harus berupa [1980-2022]!");
                                                    }
                                                } catch (InputMismatchException e) {
                                                    System.out.println("\nTahun masuk harus berupa [1980-2022]!");
                                                    sleep(2000);
                                                    keyboard.nextLine();
                                                    continue;
                                                } catch (Exception e) {
                                                    System.out.println(e.getMessage());
                                                    sleep(2000);
                                                }
                                                keyboard.nextLine();
                                            }
                                        }
                                        else{
                                            throw new Exception("\nPendidikan terakhir harus berupa S1, S2, atau S3");
                                        }
                                        break;
                                    } catch(Exception e){
                                        System.out.println(e.getMessage());
                                        sleep(2000);
                                        continue;
                                    }
                                }
                            }
                            else if (validasiDosenID(dosenID, dosen) == 2){
                                throw new Exception("\nID dosen harus berupa 3 digit..");
                            }
                            else if (validasiDosenID(dosenID, dosen) == 3){
                                throw new Exception("\nID dosen telah terdaftar..");
                            }                         
                        }catch (NumberFormatException e){
                            System.out.println("\nID dosen harus berupa angka..");
                            sleep(2000);
                            keyboard.nextLine();
                            continue;
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                            sleep(2000);
                            keyboard.nextLine();
                            continue;
                        }
                    }
                }
                else if (opsi.equals("2")) {
                    System.out.println("Cetak Data Dosen");
                    System.out.println("----------------");
                    System.out.println("1. Berdasarkan ID Dosen");
                    System.out.println("2. Berdasarkan tahun masuk Dosen");
                    System.out.println("3. Berdasarkan Pendidikan terakhir Dosen");
                    System.out.print("Pilihan Anda [1/2/3] ? ");
                    String pil = keyboard.next();

                    clearScreen();
                    if(pil.equals("1")){
                        System.out.println("Cetak Data Dosen Berdasarkan ID Dosen");
                        System.out.println("-------------------------------------");
                        System.out.print("Masukkan ID Dosen : ");
                        String dosenID = keyboard.next();
                        if (dosen.size()>0) {
                            int idx = getIndexFromDosen(dosen, dosenID);
                            if (idx == -1) {
                                System.out.println("Kode yang Anda masukkan invalid..");
                            }
                            else {
                                clearScreen();
                                System.out.println("Data Dosen");
                                System.out.println("--------------");
                                System.out.println("ID Dosen            : " + dosen.get(idx).getDosenId());
                                System.out.println("Nama                : " + dosen.get(idx).getNama());
                                System.out.println("Pendidikan terakhir : " + dosen.get(idx).getPendidikan());
                                System.out.println("Tahun masuk         : " + dosen.get(idx).getTahunMasuk());
                            }
                        }
                        else {
                            System.out.println("Tidak ada data yang ditemukan..");
                        }
                    }
                    else if(pil.equals("2")){
                        System.out.println("Cetak Data Dosen dari tahun");
                        System.out.println("---------------------------");
                        System.out.print("Masukkan tahun : ");
                        try{
                            int tahunMasuk = keyboard.nextInt();
                            if (validasiTahunMasuk(tahunMasuk) == 1) {
                                if (dosen.size()>0) {
                                    clearScreen();
                                    System.out.println("------------------------------------------------------------");
                                    System.out.println("\t\t\tData Dosen");
                                    System.out.println("------------------------------------------------------------");
                                    System.out.printf("%-10s %-25s %-11s %4s\n", "StudentID", "Nama","Pendidikan", "Tahun Masuk");
                                    System.out.println("------------------------------------------------------------");  
                                    for (Dosen d : dosen) {
                                        d.tampilkanDosenBerdasarkan(tahunMasuk);
                                    } 
                                    System.out.println("------------------------------------------------------------");
                                }
                                else {
                                    throw new Exception("\nTidak ada data yang ditemukan..");
                                }
                            }
                            else{
                                throw new Exception("\nTahun masuk harus berupa [1980-2022]!");
                            }
                        }catch (InputMismatchException e){
                            System.out.println("\nTahun masuk harus berupa angka..");
                            sleep(2000);
                            keyboard.nextLine();
                            continue;
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                            sleep(2000);
                            continue;
                        }
                    }
                    else if(pil.equals("3")){
                        System.out.println("Cetak Data Dosen Berdasarkan Pendidikan Terakhir");
                        System.out.println("------------------------------------------------");
                        System.out.print("Masukkan pendidikan terakhir : ");
                        try{
                            String pendidikan = keyboard.next();
                            if (pendidikan.equalsIgnoreCase("S1") || 
                                pendidikan.equalsIgnoreCase("S2") || 
                                pendidikan.equalsIgnoreCase("S3")) {
                                if (dosen.size()>0) {
                                    clearScreen();
                                    System.out.println("------------------------------------------------------------");
                                    System.out.println("\t\t\tData Dosen");
                                    System.out.println("------------------------------------------------------------");
                                    System.out.printf("%-10s %-25s %-11s %4s\n", "StudentID", "Nama","Pendidikan", "Tahun Masuk");
                                    System.out.println("------------------------------------------------------------");  
                                    for (Dosen d : dosen) {
                                        d.tampilkanDosenBerdasarkan(pendidikan);
                                    } 
                                    System.out.println("------------------------------------------------------------");
                                }
                                else {
                                    throw new Exception("\nTidak ada data yang ditemukan..");
                                }
                            }
                            else{
                                throw new Exception("\nPendidikan terakhir harus berupa S1, S2, atau S3");
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                            sleep(2000);
                            continue;
                        }
                    }
                    else{
                        System.out.println("Pilihan tidak tersedia..");
                    }
                    
                }
                else if (opsi.equals("3")) {
                    //Soal No. 5
                    //Keterangan: Menambahkan opsi untuk menampilkan data secara ascending atau descending
                    System.out.print("Ascending [1] / Descending [2] ? ");
                    String pil = keyboard.next();
                    if(pil.equals("1") || pil.equals("2")){
                        clearScreen();
                        System.out.println("------------------------------------------------------------");
                        System.out.println("\t\t\tData Dosen");
                        System.out.println("------------------------------------------------------------");
                        System.out.printf("%-10s %-25s %-11s %4s\n", "StudentID", "Nama","Pendidikan", "Tahun Masuk");
                        System.out.println("------------------------------------------------------------"); 
                        Sorting.bubbleSortDosen(dosen, pil); 
                        for (Dosen d : dosen) {
                            d.tampilkanDataMember();
                        } 
                        System.out.println("------------------------------------------------------------");   
                    }
                    else{
                        System.out.println("Pilihan tidak tersedia..");
                    }     
                }
                else {
                    System.out.println("Pilihan tidak tersedia..");
                }
            }
            
            else if (pilihan.equals("12")) {
                break;
            }

            else {
                System.out.println("Pilihan tidak tersedia..");
            }

            System.out.print("\nKembali ke halaman utama [y/n] ? ");
            yn = keyboard.next();
        }

            clearScreen();
            System.out.println("\nTerima Kasih!\n");
            keyboard.close();
        }
}   
