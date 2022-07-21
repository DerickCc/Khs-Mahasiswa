package khs;

public class Term {
    private String kodeTerm;
    private int tahunAjaran;
    private String semester;
    private String keterangan;
    private String keteranganSingkat;

    public Term(String kodeTerm, Integer tahunAjaran, String semester, String keterangan, String keteranganSingkat) {
        this.kodeTerm = kodeTerm;
        this.tahunAjaran = tahunAjaran;
        this.semester = semester;
        this.keterangan = keterangan;
        this.keteranganSingkat = keteranganSingkat;
    }

    public String getKodeTerm() {
        return this.kodeTerm;
    }

    public void setKodeTerm(String kodeTerm) {
        this.kodeTerm = kodeTerm;
    }

    public int getTahunAjaran() {
        return this.tahunAjaran;
    }

    public void setTahunAjaran(int tahunAjaran) {
        this.tahunAjaran = tahunAjaran;
    }

    public String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getKeterangan() {
        return this.keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKeteranganSingkat() {
        return this.keteranganSingkat;
    }

    public void setKeteranganSingkat(String keretanganSingkat) {
        this.keteranganSingkat = keretanganSingkat;
    }

    public void tampilTerm() {
        System.out.printf("%-10s %-15s %-10s %-20s %-10s\n", kodeTerm, tahunAjaran, semester, keterangan, keteranganSingkat);
    }
}

