package co.edu.udea.compumovil.gr01_20182.lab2;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20182.lab2.Adapters.PlateAdapter;
import co.edu.udea.compumovil.gr01_20182.lab2.Entities.Plate;
import co.edu.udea.compumovil.gr01_20182.lab2.Utilities.Utilities;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListplatesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListplatesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListplatesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerListPlates;
    ArrayList<Plate> listPlate;
    ConexionSQLiteHelper conn;
    Activity activity;
    IComunicaFragments interfaceComunicaFragments;

    public ListplatesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListplatesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListplatesFragment newInstance(String param1, String param2) {
        ListplatesFragment fragment = new ListplatesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_listplates, container, false);

        conn = new ConexionSQLiteHelper(getContext(), "bd_maxipollo", null, 1);
        listPlate=new ArrayList<>();
        recyclerListPlates=view.findViewById(R.id.id_recycler_plates);
        recyclerListPlates.setLayoutManager(new LinearLayoutManager(getContext()));

        //fillPlatesList();
        consultListPlates();

        PlateAdapter adapter =  new PlateAdapter(listPlate);
        recyclerListPlates.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //evento para llamar DetailplateFragment
                interfaceComunicaFragments.sendPlate
                        (listPlate.get(recyclerListPlates.getChildAdapterPosition(view)));
            }
        });

        return view;
    }

    private void consultListPlates() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Plate plate;

        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilities.TABLE_PLATES, null);

        String name;
        while (cursor.moveToNext()){
            plate=new Plate();
            plate.setpName(cursor.getString(0));
            plate.setpType("Tipo: "+cursor.getString(1));
            plate.setpPrice("Precio: $"+cursor.getString(2));
            plate.setpTime("Tiempo Prep: "+cursor.getString(3)+" min.");

            name=cursor.getString(0);
            if (name.equals("Pollo Asado")){
                plate.setpImage(R.drawable.pollo_asado);
                plate.setpImageDetail(R.drawable.pollo_asado);
            }else if(name.equals("Pollo Broaster")){
                plate.setpImage(R.drawable.pollo_broaster);
                plate.setpImageDetail(R.drawable.pollo_broaster);
            }else if(name.equals("Nuggets De Pollo")){
                plate.setpImage(R.drawable.nuggets_de_pollo);
                plate.setpImageDetail(R.drawable.nuggets_de_pollo);
            }else if(name.equals("Pollo Apanado")){
                plate.setpImage(R.drawable.pollo_apanado);
                plate.setpImageDetail(R.drawable.pollo_apanado);
            }else if(name.equals("Pollo a la Plancha")){
                plate.setpImage(R.drawable.pollo_a_la_plancha);
                plate.setpImageDetail(R.drawable.pollo_a_la_plancha);
            }else if(name.equals("Pinchos de Pollo")){
                plate.setpImage(R.drawable.pinchos_pollo);
                plate.setpImageDetail(R.drawable.pinchos_pollo);
            }else if(name.equals("Bandeja Paisa")){
                plate.setpImage(R.drawable.bandeja_paisa);
                plate.setpImageDetail(R.drawable.bandeja_paisa);
            }else if(name.equals("Consome")){
                plate.setpImage(R.drawable.consome);
                plate.setpImageDetail(R.drawable.consome);
            }else {
                plate.setpImage(R.drawable.pollo_broaster);
                plate.setpImageDetail(R.drawable.pollo_asado);
            }
            listPlate.add(plate);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){ //Establece comunicacion entre la lista y el detalle
            this.activity = (Activity) context;
            interfaceComunicaFragments = (IComunicaFragments) this.activity;
        }

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
