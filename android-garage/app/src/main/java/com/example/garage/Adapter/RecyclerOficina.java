package com.example.garage.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.garage.ClasseTela.InfoOficina;
import com.example.garage.Service.ItemClickListener;
import com.example.garage.Models.Oficina;
import com.example.garage.R;
import com.bumptech.glide.Glide;

import java.util.List;

class RecyclerViewHolderOficina extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
{


    public TextView txtNome;
    public TextView txtTelefone;
    public TextView txtEndereco;
    public TextView txtAbertoFechado;
    public ImageView imagem;
    public LinearLayout linearLayout;
    private ItemClickListener itemClickListener;


    public RecyclerViewHolderOficina(@NonNull View itemView) {
        super(itemView);
        linearLayout = (LinearLayout) itemView.findViewById(R.id.espacoOficina);
        txtNome = (TextView) itemView.findViewById(R.id.txtNome);
        txtTelefone = (TextView) itemView.findViewById(R.id.txtTelefone);
        txtEndereco = (TextView) itemView.findViewById(R.id.txtEndereco);
        txtAbertoFechado = (TextView) itemView.findViewById(R.id.txtSituacao);
        imagem = (ImageView) itemView.findViewById(R.id.imgOficina);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener (ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}

public class RecyclerOficina extends RecyclerView.Adapter<RecyclerViewHolderOficina> {

    private List<Oficina> listData;
    private Context context;
//    private List<Oficina> oficinas;

    public RecyclerOficina(Context context, List<Oficina> listData)
    {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public RecyclerViewHolderOficina onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.layout_item_recycler_view,viewGroup,false);

        return new RecyclerViewHolderOficina(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolderOficina recyclerViewHolderOficina, int i) {


        final Oficina unicaOficina = listData.get(i);
        recyclerViewHolderOficina.txtNome.setText(unicaOficina.nome);
        recyclerViewHolderOficina.txtEndereco.setText(unicaOficina.endereco);
        recyclerViewHolderOficina.txtTelefone.setText(unicaOficina.telefone);
        recyclerViewHolderOficina.txtAbertoFechado.setText(unicaOficina.horarioAbertura);

        Glide.with(context)
                .load("http://garageapp.com.br/webservice/imagens/oficinas/"+unicaOficina.image)
                .into(recyclerViewHolderOficina.imagem);

        recyclerViewHolderOficina.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //Bundle args = new Bundle("nome","Teste");
                if(isLongClick)
                {
                    Toast.makeText(context,"Clique Segurado: ", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(context,"Clique Rapido: ", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(context.getApplicationContext(), InfoOficina.class);

                    it.putExtra("nome", listData.get(position).nome);
                    it.putExtra("telefone", listData.get(position).telefone);
                    it.putExtra("endereco", listData.get(position).endereco);
                    it.putExtra("situacao", listData.get(position).horarioAbertura);
                    //it.putExtra("Imagem",listData.get(position).image);
                    context.startActivity(it);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
