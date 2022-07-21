package khs;

public class Matakuliah {
    private String kodeMataKuliah;
    private String namaMataKuliah;
    private int sks;


    public Matakuliah(String kodeMataKuliah, String namaMataKuliah, int sks) {
        this.kodeMataKuliah = kodeMataKuliah;
        this.namaMataKuliah = namaMataKuliah;
        this.sks = sks;
    }

    public String getKodeMataKuliah() {
        return this.kodeMataKuliah;
    }

    public void setKodeMataKuliah(String kodeMataKuliah) {
        this.kodeMataKuliah = kodeMataKuliah;
    }

    public String getNamaMataKuliah() {
        return this.namaMataKuliah;
    }

    public void setMataKuliah(String mataKuliah) {
        this.namaMataKuliah = mataKuliah;
    }

    public int getSks() {
        return this.sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public void tampilkanDataMataKuliah() {
        System.out.printf("%-20s %-25s %-4d\n", kodeMataKuliah, namaMataKuliah, sks);
    }
}

