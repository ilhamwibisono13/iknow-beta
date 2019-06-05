package diratama.komsi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Jadwal extends AppCompatActivity {

    private DatabaseReference fCatatanDatabase;

    TextView detailNamaMatkul,detailRuang,detailJamKuliah,detailDosen,detailCatatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        fCatatanDatabase = FirebaseDatabase.getInstance().getReference().child("Notes");

        detailNamaMatkul = findViewById(R.id.detailNamaMatkul);
        detailRuang = findViewById(R.id.detailRuang);
        detailJamKuliah = findViewById(R.id.detailJamKuliah);
        detailDosen = findViewById(R.id.detailDosen);
        detailCatatan = findViewById(R.id.detailCatatan);
    }

    public void setDetailNamaMatkul(TextView detailNamaMatkul) {
        this.detailNamaMatkul = detailNamaMatkul;
    }

    public void setDetailRuang(TextView detailRuang) {
        this.detailRuang = detailRuang;
    }

    public void setDetailJamKuliah(TextView detailJamKuliah) {
        this.detailJamKuliah = detailJamKuliah;
    }

    public void setDetailDosen(TextView detailDosen) {
        this.detailDosen = detailDosen;
    }

    public void setDetailCatatan(TextView detailCatatan) {
        this.detailCatatan = detailCatatan;
    }
}
