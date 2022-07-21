package khs;

public class Retake extends KhsDetail {
    private Term retakeTerm;
    //Nama: Derick Chainatra
    //NIM: 03081210031
    //Keterangan: Menghapus stringRetake pada konstraktor
    public Retake(String kodeKHSDetail, String kodeKHS, Matakuliah mataKuliah, int nilai, Term retakeTerm) {
        super(kodeKHSDetail, kodeKHS, mataKuliah, nilai);
        this.retakeTerm = retakeTerm;
    }

    public Term getTerm() {
        return this.retakeTerm;
    }

    public void tampilkanMataKuliahRetake() {
        System.out.printf("%-20s %-30s %-5d\n", getDetailMatakuliah().getKodeMataKuliah(), getDetailMatakuliah().getNamaMataKuliah() + " (Retake)", getDetailMatakuliah().getSks());
    }   
}
