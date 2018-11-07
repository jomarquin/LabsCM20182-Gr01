package co.edu.udea.compumovil.gr01_20182.lab4.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20182.lab4.Entities.Food;
import co.edu.udea.compumovil.gr01_20182.lab4.R;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodsViewHolder> implements  View.OnClickListener{

    ArrayList<Food> listFoods;
    private View.OnClickListener listener;
    private Context mContext;


    public FoodAdapter(ArrayList<Food> listFoods) {
        this.listFoods = listFoods;
    }

    @Override
    public FoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext()).inflate(R.layout.item_list_plates, null, false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layParams);

        mContext= parent.getContext();//Traer el contexto del fragment al adapter para trabajar con Glide

        view.setOnClickListener(this);
        return new FoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodsViewHolder holder, int position) {
        holder.txtNombre.setText(listFoods.get(position).getName()); //para traer un entero colocar .toString
        //holder.txtDescrip.setText(listFoods.get(position).getDescription());
        holder.txtTipo.setText("Tipo comida: "+listFoods.get(position).getType());
        holder.txtTiempo.setText("Tiempo preparación: "+listFoods.get(position).getTime()+" min.");
        holder.txtPrecio.setText("Precio: $"+listFoods.get(position).getPrice());
        String url = listFoods.get(position).getImage();
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.no_image)
                .into(holder.foto);

    }

    @Override
    public int getItemCount() {
        return listFoods.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class FoodsViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre, txtTipo, txtPrecio, txtTiempo;
        ImageView foto;

        public FoodsViewHolder(View itemView) {
            super(itemView);
            txtNombre=itemView.findViewById(R.id.id_name_listPlate);
            //txtDescrip=itemView.findViewById(R.id.id_nam)
            txtTipo=itemView.findViewById(R.id.id_type_listPlate);
            txtPrecio=itemView.findViewById(R.id.id_price_listPlate);
            txtTiempo=itemView.findViewById(R.id.id_time_listPlate);
            foto=itemView.findViewById(R.id.id_photo_listPlate);
        }
    }
}
