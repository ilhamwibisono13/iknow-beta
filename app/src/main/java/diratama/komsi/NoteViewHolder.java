package diratama.komsi;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    View mView;

    TextView textNamaMatkul, textJamKuliah, textRuang;
    CardView cardCatatan;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
        textNamaMatkul = mView.findViewById(R.id.cardViewNamaMatkul);
        textJamKuliah = mView.findViewById(R.id.cardViewJamKuliah);
        textRuang = mView.findViewById(R.id.cardViewRuang);

        cardCatatan = mView.findViewById(R.id.cardCatatan);
    }

    public void setCatatanNamaMatkul(String namaMatkul){
        textNamaMatkul.setText(namaMatkul);
    }
    public void setCatatanJamKuliah(String jamMatkul){
        textJamKuliah.setText(jamMatkul);
    }
    public void setCatatanRuang(String ruang){
        textRuang.setText(ruang);
    }
    public void setDosen(String dosen){

    }
    public void setCatatan(String catatan){

    }
}
