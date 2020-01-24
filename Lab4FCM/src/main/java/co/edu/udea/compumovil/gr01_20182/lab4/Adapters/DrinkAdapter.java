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

import co.edu.udea.compumovil.gr01_20182.lab4.Entities.Drink;
import co.edu.udea.compumovil.gr01_20182.lab4.R;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinksViewHolder> implements  View.OnClickListener {

    ArrayList<Drink> listDrinks;
    private View.OnClickListener listener;
    private Context mContext;

    public DrinkAdapter(ArrayList<Drink> listDrinks) {
        this.listDrinks = listDrinks;
    }

    @Override
    public DrinksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext()).inflate(R.layout.item_list_drinks, null, false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layParams);

        mContext= parent.getContext();//Traer el contexto del adapter para trabajar con Glide

        view.setOnClickListener(this);
        return new DrinksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DrinksViewHolder holder, int position) {

        holder.txtNombre.setText(listDrinks.get(position).getName());
        //holder.txtDescripcion.setText(listDrinks.get(position).getDescription());
        holder.txtPrecio.setText("Precio: $ "+listDrinks.get(position).getPrice());
        String url = listDrinks.get(position).getImage();
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.sin_imagen)
                .into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return listDrinks.size();
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

    public class DrinksViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre, txtDescripcion, txtPrecio;
        ImageView foto;

        public DrinksViewHolder(View itemView) {
            super(itemView);
            txtNombre=itemView.findViewById(R.id.id_name_listDrink);
            //txtDescripcion=itemView.findViewById(R.id.id_descrip_listDrink);
            txtPrecio=itemView.findViewById(R.id.id_price_listDrink);
            foto=itemView.findViewById(R.id.id_photo_listDrink);
        }
    }
}
