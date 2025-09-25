public class Kuis extends Konten {
    private String soal;

    public Kuis(int id, String judul, String soal) {
        super(id, judul);
        this.soal = soal;
    }

    public String getSoal(){
        return soal;
    }
    public void setSoal(String soal){
        this.soal = soal;
    }

    public void tampilkanKonten() {
        System.out.println("Mengakses kuis: " + getJudul());
        System.out.println("Soal: " + soal);
    }
}

