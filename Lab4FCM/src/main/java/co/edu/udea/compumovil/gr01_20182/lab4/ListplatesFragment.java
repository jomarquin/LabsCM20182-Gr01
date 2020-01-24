package co.edu.udea.compumovil.gr01_20182.lab4;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20182.lab4.Adapters.FoodAdapter;
import co.edu.udea.compumovil.gr01_20182.lab4.Entities.Food;
import co.edu.udea.compumovil.gr01_20182.lab4.Utilities.Utilities;


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

    ImageView imageView;
    RecyclerView recyclerListPlates;
    ArrayList<Food> ListFood;
    Button btnFltFound;
    //Comunicación entre fragments
    Activity activity;
    IComunicaFragments interfaceComunicaFragments;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference databaseRef = ref.child("foods");



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

        ListFood=new ArrayList<>();
        recyclerListPlates=view.findViewById(R.id.id_recycler_plates);
        recyclerListPlates.setLayoutManager(new LinearLayoutManager(getContext()));


        Button repFound = view.findViewById(R.id.id_btn_reportarEncontrado);
        repFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment miFrag = new ComidasFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main, miFrag).addToBackStack(null).commit();
            }
        });

        consultListFoods();

        return view;
    }



    private void consultListFoods() {


        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Food food;
                //clearing the previous artist list
                ListFood.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    food=new Food();

                    food.setName(postSnapshot.child("name").getValue().toString());
                    Utilities.WG_NAME=postSnapshot.child("name").getValue().toString();
                    food.setDescription(postSnapshot.child("description").getValue().toString());
                    food.setImage(postSnapshot.child("image").getValue().toString());
                    food.setType(postSnapshot.child("type").getValue().toString());
                    Utilities.WG_TYPE=postSnapshot.child("type").getValue().toString();
                    food.setTime(postSnapshot.child("time").getValue().toString());
                    food.setPrice(postSnapshot.child("price").getValue().toString());
                    Utilities.WG_PRICE=postSnapshot.child("price").getValue().toString();

                    SharedPreferences preferences = getActivity().getSharedPreferences("credentials", getContext().MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("wg_name", Utilities.WG_NAME);
                    editor.putString("wg_type", Utilities.WG_TYPE);
                    editor.putString("wg_price", Utilities.WG_PRICE);
                    editor.commit();

                    ListFood.add(food);
                }

                FoodAdapter adapter =  new FoodAdapter(ListFood);
                recyclerListPlates.setAdapter(adapter);
                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //evento para llamar DetailplateFragment
                        interfaceComunicaFragments.sendFood
                                (ListFood.get(recyclerListPlates.getChildAdapterPosition(view)));
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "No se pudo obtener información ", Toast.LENGTH_SHORT).show();
            }
        });
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
