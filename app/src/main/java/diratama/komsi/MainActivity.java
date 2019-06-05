package diratama.komsi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth fAuth;
    private RecyclerView mListCatatan;
    private GridLayoutManager gridLayoutManager;

    private DatabaseReference fCatatanDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListCatatan = findViewById(R.id.list_catatan);

        gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);

        mListCatatan.setHasFixedSize(true);
        mListCatatan.setLayoutManager(gridLayoutManager);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddJadwalActivity.class));
            }
        });

        fAuth = FirebaseAuth.getInstance();
        fCatatanDatabase = FirebaseDatabase.getInstance().getReference().child("Notes");


        //updateUI();

        loadData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void loadData(){
        Query query = fCatatanDatabase.orderByChild("jamKuliah");
        FirebaseRecyclerAdapter<NoteModel, NoteViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<NoteModel, NoteViewHolder>(
                        NoteModel.class,
                        R.layout.single_note_layout,
                        NoteViewHolder.class,
                        query
                ) {
                    @Override
                    protected void populateViewHolder(final NoteViewHolder viewHolder, NoteModel model, int position) {
                        final String noteId = getRef(position).getKey();

                        fCatatanDatabase.child(noteId).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("namaMatkul") && dataSnapshot.hasChild("timestamp")) {


                                    String namaMatkul = dataSnapshot.child("namaMatkul").getValue().toString();
                                    String jamKuliah = dataSnapshot.child("jamKuliah").getValue().toString();
                                    String ruang = dataSnapshot.child("ruang").getValue().toString();

                                    viewHolder.setCatatanNamaMatkul(namaMatkul);
                                    viewHolder.setCatatanJamKuliah(jamKuliah);
                                    viewHolder.setCatatanRuang(ruang);

                                    viewHolder.cardCatatan.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent i = new Intent(MainActivity.this, AddJadwalActivity.class);
                                            i.putExtra("noteId", noteId);
                                            startActivity(i);
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                };
        mListCatatan.setAdapter(firebaseRecyclerAdapter);
    }

    /*private void updateUI(){
        if (fAuth.getCurrentUser() != null){
            Log.i("MainActivity","fAuth !=null");
        }else {
            Intent i = new Intent(MainActivity.this,StartActivity.class);
            startActivity(i);
            finish();
            Log.i("MainActivity","fAuth=null");
        }
    }*/
}
