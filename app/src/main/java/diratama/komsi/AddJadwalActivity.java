package diratama.komsi;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AddJadwalActivity extends AppCompatActivity {

    private Button simpan;
    private EditText eNamaMatkul,eJamMatkul,eRuang,eDosen,eCatatan;

    private DatabaseReference fCatatanDatabase;

    private Menu mainMenu;
    private String noteID;

    private boolean isExist;

    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jadwal);

        try {
            noteID = getIntent().getStringExtra("noteId");

            if (!noteID.trim().equals("")){
                isExist = true;
            }else {
                isExist = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        simpan = (Button)findViewById(R.id.btnSimpan);
        eNamaMatkul = (EditText)findViewById(R.id.namaMatkul);
        eJamMatkul = (EditText)findViewById(R.id.jamKuliah);
        eRuang = (EditText)findViewById(R.id.ruang);
        eDosen= (EditText)findViewById(R.id.dosen);
        eCatatan = (EditText)findViewById(R.id.catatanField);

        fCatatanDatabase = FirebaseDatabase.getInstance().getReference().child("Notes");

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaMatkul = eNamaMatkul.getText().toString().trim();
                String jamKuliah = eJamMatkul.getText().toString().trim();
                String ruang = eRuang.getText().toString().trim();
                String dosen = eDosen.getText().toString().trim();
                String catatan = eCatatan.getText().toString().trim();

                if (!TextUtils.isEmpty(namaMatkul) && !TextUtils.isEmpty(jamKuliah)
                    && !TextUtils.isEmpty(ruang) && !TextUtils.isEmpty(dosen)){
                    buatCatatan(namaMatkul,jamKuliah,ruang,dosen,catatan);

                }else {
                    Snackbar.make(v,"Isi tidak boleh kosong",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        putData();
    }

    private void putData(){
      if (isExist) {
          fCatatanDatabase.child(noteID).addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(DataSnapshot dataSnapshot) {
                  if (dataSnapshot.hasChild("namaMatkul")&& dataSnapshot.hasChild("jamKuliah")&&
                                  dataSnapshot.hasChild("ruang")&& dataSnapshot.hasChild("dosen")&&
                                                  dataSnapshot.hasChild("catatan")) {
                      String namaMatkul = dataSnapshot.child("namaMatkul").getValue().toString();
                      String jamKuliah = dataSnapshot.child("jamKuliah").getValue().toString();
                      String ruang = dataSnapshot.child("ruang").getValue().toString();
                      String dosen = dataSnapshot.child("dosen").getValue().toString();
                      String catatan = dataSnapshot.child("catatan").getValue().toString();

                      eNamaMatkul.setText(namaMatkul);
                      eJamMatkul.setText(jamKuliah);
                      eRuang.setText(ruang);
                      eDosen.setText(dosen);
                      eCatatan.setText(catatan);

                  }
              }

              @Override
              public void onCancelled(DatabaseError databaseError) {

              }
          });
      }
    }

    private void buatCatatan(String namaMatkul, String jamKuliah, String ruang, String dosen, String catatan) {

        if (isExist) {
        //UPDATE CATATAN
            Map updateMap = new HashMap();
            updateMap.put("namaMatkul",eNamaMatkul.getText().toString().trim());
            updateMap.put("jamKuliah",eJamMatkul.getText().toString().trim());
            updateMap.put("ruang",eRuang.getText().toString().trim());
            updateMap.put("dosen",eDosen.getText().toString().trim());
            updateMap.put("catatan",eCatatan.getText().toString().trim());
            updateMap.put("timestamp",ServerValue.TIMESTAMP);

            fCatatanDatabase.child(noteID).updateChildren(updateMap);

            Toast.makeText(this,"Jadwal diperbarui",Toast.LENGTH_SHORT).show();
        } else {
        //BUAT CATTATAN BARU
            final DatabaseReference catatanBaru = fCatatanDatabase.push();

            final Map catatanMap = new HashMap();
            catatanMap.put("namaMatkul", namaMatkul);
            catatanMap.put("jamKuliah", jamKuliah);
            catatanMap.put("ruang", ruang);
            catatanMap.put("dosen", dosen);
            catatanMap.put("catatan", catatan);
            catatanMap.put("timestamp", ServerValue.TIMESTAMP);

            Thread mainThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    catatanBaru.setValue(catatanMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(AddJadwalActivity.this)
                                        .setSmallIcon(R.mipmap.ic_launcher) //ikon notification
                                        .setContentTitle("iKNOW") //judul konten
                                        .setAutoCancel(true)//untuk menswipe atau menghapus notification
                                        .setContentText("Kamu baru saja menambahkan jadwal");

                                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                                notificationManager.notify(NOTIFICATION_ID, builder.build());

                                Toast.makeText(AddJadwalActivity.this, "Jadwal ditambahkan", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AddJadwalActivity.this, "ERROR : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
            mainThread.start();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.add_jadwal_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.add_jadwal_delete_btn:
                if (isExist){
                    deleteNote();
                }else {
                    Toast.makeText(this,"Nothing to delete",Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return true;
    }

    private void deleteNote(){
        fCatatanDatabase.child(noteID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddJadwalActivity.this, "Jadwal dihapus", Toast.LENGTH_SHORT).show();
                    noteID = "no";
                    finish();
                } else {
                    Log.e("AddJadwalActivity", task.getException().toString());
                    Toast.makeText(AddJadwalActivity.this, "ERROR: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
