package khs;

//Nama: Derick Chainatra
//NIM: 03081210031

//Soal No. 1
//Keterangan: Menambahkan class dosen sebagai subclass dari class Member
public class Dosen extends Member{
    private String pendidikan;

    //Keterangan: Membuat konstraktor class dosen
    public Dosen(String id, String nama, int tahunMasuk, String pendidikan){
        this.id = id;
        this.nama = nama;
        this.tahunMasuk = tahunMasuk;
        this.pendidikan = pendidikan;
    }

    //Keterangan: Membuat setter dan getter untuk class dosen
    public String getDosenId(){
        return this.id;
    }

    public void setDosenID(String id){
        this.id = id;
    }

    public String getNama(){
        return this.nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public int getTahunMasuk(){
        return this.tahunMasuk;
    }

    public void setTahunMasuk(int tahunMasuk){
        this.tahunMasuk = tahunMasuk;
    }

    public String getPendidikan() {
        return this.pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    //Soal No. 2
    //Keterangan: Method tampilkanDosenBerdasarkan adalah method overloading
    //Method ini akan menampilkan data dosen dari dosen yang masuk pada tahun tertentu sampai tahun tertentu
    public void tampilkanDosenBerdasarkan(int dari){
        if(tahunMasuk>=dari){
            tampilkanDataMember();
        }
    }

    //Method ini akan menampilkan data dosen dengan pendidikan tertentu
    public void tampilkanDosenBerdasarkan(String gelar){
        if(pendidikan.equalsIgnoreCase(gelar)){
            tampilkanDataMember();
        }
    }

    //Keterangan: Override method yang ada pada abstract class member
    @Override
    public void tampilkanDataMember() {
        System.out.printf("%-10s %-25s %-11s %4d\n", id, nama, pendidikan, tahunMasuk);
    }
    
}
