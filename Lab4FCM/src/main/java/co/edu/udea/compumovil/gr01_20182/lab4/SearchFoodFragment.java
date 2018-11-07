package co.edu.udea.compumovil.gr01_20182.lab4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import co.edu.udea.compumovil.gr01_20182.lab4.Utilities.Utilities;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFoodFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFoodFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;
    ImageView photo;
    EditText fieldName, fieldPrice, fieldDescrip;
    Button btnSearchFood;
    ConexionSQLiteHelper conn;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference databaseRef = ref.child("foods");
    Context context;

    private OnFragmentInteractionListener mListener;

    public SearchFoodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFoodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFoodFragment newInstance(String param1, String param2) {
        SearchFoodFragment fragment = new SearchFoodFragment();
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
        view = inflater.inflate(R.layout.fragment_search_food, container, false);

        conn = new ConexionSQLiteHelper(getContext(), "bd_maxipollo", null, 1);

        fieldName=view.findViewById(R.id.txtNameFood);
        fieldPrice=view.findViewById(R.id.txtPriceFood);
        fieldDescrip=view.findViewById(R.id.txtDescripFood);

        photo = view.findViewById(R.id.search_image_id);

        btnSearchFood = view.findViewById(R.id.idSearchFood);
        btnSearchFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFood();
            }
        });

        return view;
    }


    private void searchFood() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] param={fieldName.getText().toString()};

        String[] campos={Utilities.FIELD_F_NAME,Utilities.FIELD_F_PRICE, Utilities.FIELD_F_DESCRIPTION};

        try {
            Cursor cursor =db.query(Utilities.TABLE_FOODS,campos,Utilities.FIELD_F_NAME+"=?",param,null,null,null);
            cursor.moveToFirst();
            fieldName.setText(cursor.getString(0));
            fieldPrice.setText(cursor.getString(1));
            fieldDescrip.setText(cursor.getString(2));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getContext(),"La comida no existe...",Toast.LENGTH_LONG).show();
            clearFields();
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

        private void clearFields(){
            fieldName.setText("");
            fieldPrice.setText("");
            fieldDescrip.setText("");
        }

}