package com.iky.myapplicationapidb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PegawaiAdapter extends RecyclerView.Adapter<PegawaiAdapter.ViewHolder> {
    private Context mcontext;
    private ArrayList<Pegawai>pegawais;

    public PegawaiAdapter(Context mcontext){
        this.mcontext = mcontext;
        pegawais = new ArrayList<>();
    }
    public void setPegawais(ArrayList<Pegawai> pegawais ){
        this.pegawais = pegawais ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.list_pegawai_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pegawai currentPegawai =pegawais.get(position);
        holder.tv_nama.setText(currentPegawai.getName());
    }

    @Override
    public int getItemCount() {
       return pegawais.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama;
        Button hapus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nama = itemView.findViewById(R.id.tvNama);
            hapus = itemView.findViewById(R.id.hapus);
        }
    }
}
