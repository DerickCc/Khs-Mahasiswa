package khs;

import java.util.ArrayList;

public class Krs {
    private String kodeKrs;
    public ArrayList<Matakuliah> daftarMataKuliah = new ArrayList<Matakuliah>();
    private Tagihan tagihan;
    private Term term;

    public Krs(String kodeKrs, Term term) {
        this.kodeKrs = kodeKrs;
        this.term = term;
    }

    public String getKodeKrs() {
        return this.kodeKrs;
    }

    public void setKodeKrs(String kodeKrs) {
        this.kodeKrs = kodeKrs;
    }

    public ArrayList<Matakuliah> getDaftarMataKuliah() {
        return this.daftarMataKuliah;
    }

    public void setDaftarMataKuliah(ArrayList<Matakuliah> daftarMataKuliah) {
        this.daftarMataKuliah = daftarMataKuliah;
    }
    
    public Term getTerm() {
        return this.term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public void tampilkanKrs() {
        for (Matakuliah matakuliah : daftarMataKuliah) {
            System.out.printf("%-10s %-20s %-13s %-30s %-10d\n", getTerm().getSemester(), getTerm().getKeteranganSingkat(), matakuliah.getKodeMataKuliah(), matakuliah.getNamaMataKuliah(), matakuliah.getSks());                                                  
        }
    }

    public Tagihan getTagihan() {
        setTagihan();
        return this.tagihan;
    }

    public void setTagihan() {
        int jumlahSKS = 0;
        for (Matakuliah matakuliah : daftarMataKuliah) {
            jumlahSKS+=matakuliah.getSks();
        }
        this.tagihan = new Tagihan(jumlahSKS * 500000.0f);
    }
}
