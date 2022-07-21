package khs;

public class KhsDetail {
    public String kodeKHSDetail, kodeKHS;
    private String stringRetake = "";
    private Matakuliah mataKuliah;
    private int nilai = 0;

    public KhsDetail(String kodeKHSDetail, String kodeKHS, Matakuliah mataKuliah, int nilai) {
        this.kodeKHSDetail = kodeKHSDetail;
        this.kodeKHS = kodeKHS;
        this.mataKuliah = mataKuliah;
        this.nilai = nilai;
    }

    public String getStringRetake() {
        return this.stringRetake;
    }

    public void setStringRetake(String retake) {
        this.stringRetake = retake;
    }

    public String getKodeKHSDetail() {
        return this.kodeKHSDetail;
    }

    public void setKodeKHSDetail(String kodeKHSDetail) {
        this.kodeKHSDetail = kodeKHSDetail;
    }

    public String getKodeKHS() {
        return this.kodeKHS;
    }

    public void setKodeKHS(String kodeKHS) {
        this.kodeKHS = kodeKHS;
    }

    public void setMataKuliah(Matakuliah mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public int getNilai() {
        return this.nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    public float konversiNilai(){
        if(nilai>=90 && nilai<=100){
            return 4.00f;
        }
		else if(nilai>=85 && nilai<=89.99){
            return 3.70f;
        }
        else if(nilai>=80 && nilai<=84.99){
            return 3.30f;
        } 
        else if (nilai >= 75 && nilai <= 79.99){
            return 3.00f;
        }
        else if(nilai>=70 && nilai<=74.99){
            return 2.70f;
        } 
        else if(nilai>=65 && nilai<=69.99){
            return 2.30f;
        } 
        else if (nilai >= 60 && nilai <= 64.99){
            return 2.00f;
        }
        else{
            return 0.0f;
        }
    }

    public String konversiNilaiHuruf(){
        if(nilai>=90 && nilai<=100){
            return "A";
        }
		else if(nilai>=85 && nilai<=89.99){
            return "A-";
        }
        else if(nilai>=80 && nilai<=84.99){
            return "B+";
        } 
        else if(nilai>=75 && nilai<=79.99){
            return "B";
        }
        else if(nilai>=70 && nilai<=74.99){
            return "B-";
        } 
        else if(nilai>=65 && nilai<=69.99){
            return "C+";
        } 
        else if(nilai>=60 && nilai<=64.99){
            return "C";
        }
        else{
            return "F";
        }
    } 

    public Matakuliah getDetailMatakuliah(){
        return mataKuliah;
    }
    
    //Nama: Derick Chainatra
    //NIM: 03081210031
    //Keterangan: Menyatukan fungsi tampilkanDetailKHSRetake ke fungsi di bawah ini dan menambahkan parameter retakeString
    //untuk mengetahui apakah matakuliah tersebut hasil retake atau bukan
    public void tampilkanDetailKHS(String retakeString) {
        if (retakeString.equals("retake")) {
            System.out.printf("%-20s %-30s %-5d\n", kodeKHSDetail, mataKuliah.getNamaMataKuliah() + " (retake)", nilai);
        }
        else {
            System.out.printf("%-20s %-30s %-5d\n", kodeKHSDetail, mataKuliah.getNamaMataKuliah(), nilai);
        }
    } 
    
}
