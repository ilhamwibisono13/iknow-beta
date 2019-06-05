package diratama.komsi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {

    private FirebaseAuth fAuth;
    private Button btnLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        fAuth = FirebaseAuth.getInstance();
        btnLog = (Button)findViewById(R.id.signInBtn);

        updateUI();

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void updateUI() {
        if (fAuth.getCurrentUser() != null){
            Log.i("StartActivity","fAuth !=null");
        }else {
            Intent i = new Intent(StartActivity.this,MainActivity.class);
            startActivity(i);
            finish();
            Log.i("StartActivity","fAuth=null");
        }
    }

    private void login() {

    }


}
