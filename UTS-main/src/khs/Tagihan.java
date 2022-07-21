package khs;

public class Tagihan implements Pembayaran{
    private float tagihan;
    
    public Tagihan (float tagihan) {
        this.tagihan = tagihan;
    }

    public float jumlahTagihan(){
        return this.tagihan;
    }

    @Override
    public void tampilkanPembayaran() {
        System.out.println("Jumlah Tagihan \t   : Rp " + String.format("%,.2f",tagihan));
    }
}
