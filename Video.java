public class Video extends Konten {
    private int durasi;

    public Video(int id, String judul, int durasi) {
        super(id, judul);
        this.durasi = durasi;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public void tampilkan() {
        System.out.println("Video: " + getJudul() + " (durasi " + durasi + " menit)");
    }
}

