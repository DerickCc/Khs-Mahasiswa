package khs;

//Keterangan: Menambah class Member untuk mewariskan ke class dosen dan mahasiswa
public abstract class Member {
    public String id;
    public String nama;
    public int tahunMasuk;

    public abstract void tampilkanDataMember();
}
