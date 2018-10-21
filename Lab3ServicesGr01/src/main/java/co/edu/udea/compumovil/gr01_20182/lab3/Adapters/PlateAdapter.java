package co.edu.udea.compumovil.gr01_20182.lab3.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20182.lab3.Entities.Plate;
import co.edu.udea.compumovil.gr01_20182.lab3.R;

public class PlateAdapter extends RecyclerView.Adapter<PlateAdapter.PlatesViewHolder> implements  View.OnClickListener{

    ArrayList<Plate> listPlates;
    private View.OnClickListener listener;

    public PlateAdapter(ArrayList<Plate> listPlates) {
        this.listPlates = listPlates;
    }

    @Override
    public PlatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext()).inflate(R.layout.item_list_plates, null, false);
        RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layParams);

        view.setOnClickListener(this);
        return new PlatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlatesViewHolder holder, int position) {
        holder.txtNombre.setText(listPlates.get(position).getpName()); //para traer un entero colocar .toString
        holder.txtTipo.setText(listPlates.get(position).getpType());
        holder.txtPrecio.setText(listPlates.get(position).getpPrice());//aca tenia el error del recyclerview
        holder.txtTiempo.setText(listPlates.get(position).getpTime()); // por las variables tipo int que habian
        holder.foto.setImageResource(listPlates.get(position).getpImage());

    }

    @Override
    public int getItemCount() {
        return listPlates.size();
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

    public class PlatesViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre, txtTipo, txtPrecio, txtTiempo;
        ImageView foto;

        public PlatesViewHolder(View itemView) {
            super(itemView);
            txtNombre=itemView.findViewById(R.id.id_name_listPlate);
            txtTipo=itemView.findViewById(R.id.id_type_listPlate);
            txtPrecio=itemView.findViewById(R.id.id_price_listPlate);
            txtTiempo=itemView.findViewById(R.id.id_time_listPlate);
            foto=itemView.findViewById(R.id.id_photo_listPlate);
        }
    }
}
