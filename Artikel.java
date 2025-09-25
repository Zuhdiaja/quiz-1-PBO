public class Artikel extends Konten {
    private String teks;

    public Artikel(int id, String judul, String teks) {
        super(id, judul);
        this.teks = teks;
    }

    public String getTeks(){
        return teks;
    }
    public void setTeks(String teks){
        this.teks = teks;
    }

    public void tampilkanKonten() {
        System.out.println("Membaca artikel: " + getJudul());
        System.out.println("Isi: " + teks);
    }
}
