package khs;

import java.util.ArrayList;

public class Mahasiswa extends Member implements Pembayaran{
    //Keterangan: Menghapus studentid, nama, dan tahun masuk karena sudah diwariskan dari class member
    private String jurusan;
    public ArrayList<Khs> khs = new ArrayList<Khs>();
    public ArrayList<Krs> krs = new ArrayList<Krs>();
    //Nama: Derick Chainatra
    //NIM: 03081210031
    //Keterangan: Menghapus arraylist retake pada mahasiswa
    //Keterangan: Membuat countkhs untuk digunakan pada auto-generate kode khs dan kode krs
    private int countKhs = 0;
    private int countKrs = 0;

    public Mahasiswa(String id, String nama, String jurusan, int tahunMasuk) {
        this.id = id;
        this.nama = nama;
        this.jurusan = jurusan;
        this.tahunMasuk = tahunMasuk;
    }

    //Nama: Derick Chainatra
    //NIM: 03081210031
    //Keterangan: Menambahkan getter dan setter untuk countKrs dan countKhs dan juga
    public int getCountKrs(){
        return this.countKrs;
    }

    public void setCountKrs(){
        this.countKrs+=1;
    }

    public int getCountKhs(){
        return this.countKhs;
    }

    public void setCountKhs(){
        this.countKhs+=1;
    }

    public String getStudentID() {
        return this.id;
    }

    public void setStudentID(String id) {
        this.id = id;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJurusan() {
        return this.jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public int getTahunMasuk() {
        return this.tahunMasuk;
    }

    public void setTahunMasuk(int tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
    }

    public ArrayList<Khs> getKHS() {
        return this.khs;
    }

    public void setKHS(ArrayList<Khs> khs) {
        this.khs = khs;
    }

    public ArrayList<Krs> getKRS() {
        return this.krs;
    }

    public void setKRS(ArrayList<Krs> krs) {
        this.krs = krs;
    }

    public Khs getKhs(Term term){
        int idx=0;
        for (Khs i : khs) {
            if(i.getDetailTerm().getKodeTerm().equalsIgnoreCase(term.getKodeTerm())){
                return khs.get(idx);
            }
            idx += 1;
        }
        return null;
    }
    
    public double getIPKTerm(Term term) {
        for (Khs i : khs) {
            if(i.getDetailTerm().getKodeTerm().equalsIgnoreCase(term.getKodeTerm())){
                return getKhs(term).getIPSemester();
            }
        }
        return 0.0;
    }

    public double getIPK(){
        double total = 0;
        double jumlahSemester = 0;
        for (Khs khs2 : khs) {
            total+=khs2.getIPSemester();
            if (khs2.getIPSemester() != 0) jumlahSemester++;
        }
        return total/jumlahSemester;
    }

    //Keterangan: Override method yang ada pada abstract class member
    @Override
    public void tampilkanDataMember() {
        System.out.printf("%-10s %-25s %-20s %4d\n", id, nama, jurusan, tahunMasuk);
    }

    public float jumlahTagihanSeluruhSemester(){
        float jumlah = 0.0f;
        for (Krs r : krs) {
            jumlah += r.getTagihan().jumlahTagihan();
        }
        return jumlah;
    }

    @Override
    public void tampilkanPembayaran() {
        System.out.println("Jumlah Tagihan Seluruh Semester : Rp " + String.format("%,.2f",jumlahTagihanSeluruhSemester()));
        
    }
}