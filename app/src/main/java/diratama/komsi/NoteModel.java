package diratama.komsi;

public class NoteModel {

    public String catatanNamaMatkul;
    public String catatanJamKuliah;
    public String catatanRuang;
    public String catatanDosen;
    public String catatanCatatan;

    public NoteModel(){

    }

    public NoteModel(String catatanNamaMatkul, String catatanJamKuliah, String catatanRuang, String catatanDosen, String catatanCatatan) {
        this.catatanNamaMatkul = catatanNamaMatkul;
        this.catatanJamKuliah = catatanJamKuliah;
        this.catatanRuang = catatanRuang;
        this.catatanDosen = catatanDosen;
        this.catatanCatatan = catatanCatatan;
    }

    public String getCatatanNamaMatkul() {
        return catatanNamaMatkul;
    }

    public void setCatatanNamaMatkul(String catatanNamaMatkul) {
        this.catatanNamaMatkul = catatanNamaMatkul;
    }

    public String getCatatanJamKuliah() {
        return catatanJamKuliah;
    }

    public void setCatatanJamKuliah(String catatanJamKuliah) {
        this.catatanJamKuliah = catatanJamKuliah;
    }

    public String getCatatanRuang() {
        return catatanRuang;
    }

    public void setCatatanRuang(String catatanRuang) {
        this.catatanRuang = catatanRuang;
    }

    public String getCatatanDosen() {
        return catatanDosen;
    }

    public void setCatatanDosen(String catatanDosen) {
        this.catatanDosen = catatanDosen;
    }

    public String getCatatanCatatan() {
        return catatanCatatan;
    }

    public void setCatatanCatatan(String catatanCatatan) {
        this.catatanCatatan = catatanCatatan;
    }
}
