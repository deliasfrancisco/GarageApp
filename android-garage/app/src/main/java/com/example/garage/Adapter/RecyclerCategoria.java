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

import com.example.garage.ClasseTela.OficinaCategoria;
import com.example.garage.Service.ItemClickListener;
import com.example.garage.Models.Categoria;
import com.example.garage.R;
import com.bumptech.glide.Glide;

import java.util.List;


class RecyclerViewHolderCategoria extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
{


    public TextView txtNome;
    public ImageView imagem;
    public LinearLayout linearLayout;
    private ItemClickListener itemClickListener;


    public RecyclerViewHolderCategoria(@NonNull View itemView) {
        super(itemView);
        linearLayout = (LinearLayout) itemView.findViewById(R.id.espacoCategoria);
        txtNome = (TextView) itemView.findViewById(R.id.txtNomeCategoria);
        imagem = (ImageView) itemView.findViewById(R.id.imgCategoria);

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

public class RecyclerCategoria extends RecyclerView.Adapter<RecyclerViewHolderCategoria> {
    private List<Categoria> listData;
    private Context context;

    public RecyclerCategoria(Context context, List<Categoria> listData)
    {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public RecyclerViewHolderCategoria onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.layout_item_recycler_categoria,viewGroup,false);

        return new RecyclerViewHolderCategoria(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolderCategoria recyclerViewHolderCategoria, int i) {


        final Categoria unicaCategoria = listData.get(i);
        recyclerViewHolderCategoria.txtNome.setText(unicaCategoria.nome);

        Glide.with(context)
                .load("http://garageapp.com.br/webservice/imagens/servicos/"+unicaCategoria.image)
                .into(recyclerViewHolderCategoria.imagem);

        recyclerViewHolderCategoria.setItemClickListener(new ItemClickListener() {
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
                    Intent it = new Intent(context.getApplicationContext(), OficinaCategoria.class);

                    it.putExtra("id", listData.get(position).id);
                    it.putExtra("nome",listData.get(position).nome);
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
