package diratama.komsi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class NavDrawer extends AppCompatActivity {

    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_nav_drawer);

        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        CardView senin = findViewById(R.id.cardViewSenin);
        senin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavDrawer.this,MainActivity.class));
            }
        });
        CardView selasa = findViewById(R.id.cardViewSelasa);
        selasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavDrawer.this,MainActivity.class));
            }
        });
        CardView rabu = findViewById(R.id.cardViewRabu);
        rabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavDrawer.this,MainActivity.class));
            }
        });
        CardView kamis = findViewById(R.id.cardViewKamis);
        kamis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavDrawer.this,MainActivity.class));
            }
        });
        CardView jumat = findViewById(R.id.cardViewJumat);
        jumat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavDrawer.this,MainActivity.class));
            }
        });
        CardView sabtu = findViewById(R.id.cardViewSabtu);
        sabtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavDrawer.this,MainActivity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.atur:
                startActivity(new Intent(this, PengaturanActivity.class));
                break;

            case R.id.notif_off:
                return true;

            default:
                return true;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_nav_drawer, menu);
        return true;
    }
}
