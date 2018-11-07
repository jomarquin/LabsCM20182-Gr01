package co.edu.udea.compumovil.gr01_20182.lab3.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20182.lab3.Entities.Food;
import co.edu.udea.compumovil.gr01_20182.lab3.R;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodsViewHolder> implements  View.OnClickListener{

    ArrayList<Food> listFoods;
    private View.OnClickListener listener;

    public FoodAdapter(ArrayList<Food> listFoods) {
        this.listFoods = listFoods;
    }

    @Override
    public FoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext()).inflate(R.layout.item_list_plates, null, false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layParams);

        view.setOnClickListener(this);
        return new FoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodsViewHolder holder, int position) {
        holder.txtNombre.setText(listFoods.get(position).getfName()); //para traer un entero colocar .toString
        //holder.txtDescrip.setText(listFoods.get(position).getfDescription());
        holder.txtTipo.setText(listFoods.get(position).getfType());
        holder.txtTiempo.setText(listFoods.get(position).getfTime());
        holder.txtPrecio.setText(listFoods.get(position).getfPrice());

        //holder.foto.setImageResource(listFoods.get(position).getfImage());

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

        TextView txtNombre, txtDescrip, txtTipo, txtPrecio, txtTiempo;
        ImageView foto;

        public FoodsViewHolder(View itemView) {
            super(itemView);
            txtNombre=itemView.findViewById(R.id.id_name_listPlate);
            //txtDescrip=itemView.findViewById(R.id.id_nam)
            txtTipo=itemView.findViewById(R.id.id_type_listPlate);
            txtPrecio=itemView.findViewById(R.id.id_price_listPlate);
            txtTiempo=itemView.findViewById(R.id.id_time_listPlate);
            //foto=itemView.findViewById(R.id.id_photo_listPlate);
        }
    }
}
