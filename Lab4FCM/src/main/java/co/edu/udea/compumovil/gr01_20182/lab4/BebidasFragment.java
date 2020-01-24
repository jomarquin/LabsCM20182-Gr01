package co.edu.udea.compumovil.gr01_20182.lab4;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import co.edu.udea.compumovil.gr01_20182.lab4.Entities.Drink;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BebidasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BebidasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BebidasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View view;
    EditText fieldName, fieldDescrip, fieldPrice;
    ImageView imgDrink;
    Button btnAdd, btnLoadImgDrink;
    String path_uri;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference databaseRef = ref.child("bebidas");
    StorageReference storageRef;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BebidasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BebidasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BebidasFragment newInstance(String param1, String param2) {
        BebidasFragment fragment = new BebidasFragment();
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
        view=inflater.inflate(R.layout.fragment_bebidas, container, false);

        fieldName = view.findViewById(R.id.nomBebida_id);
        fieldDescrip = view.findViewById(R.id.descripBebidas_id);
        fieldPrice = view.findViewById(R.id.precioBebida_id);
        imgDrink = view.findViewById(R.id.imagenBebida_id);

        storageRef = FirebaseStorage.getInstance().getReference();

        btnAdd=view.findViewById(R.id.id_btnAddDrink);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerDrinks();
            }
        });

        btnLoadImgDrink= view.findViewById(R.id.btnImgDrink);
        btnLoadImgDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });


        return view;
    }

    private void loadImage() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");//si hay problemas para cargar algunas imagenes colocar "image/*"
        startActivityForResult(intent.createChooser(intent, "Seleccione una aplicación"), 10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== RESULT_OK){
            Uri uri=data.getData();
            imgDrink.setImageURI(uri);
            StorageReference filePath = storageRef.child("drinks")
                    .child(uri.getLastPathSegment()); //Referenciamos donde quedará guardada nuestra imagen
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(),"Imagen Cargada", Toast.LENGTH_SHORT).show();
                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful());
                    path_uri = urlTask.getResult().toString();
                }
            });

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

    /**
    private void registerDrinks() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getContext(), "bd_maxipollo", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilities.FIELD_D_NAME, fieldName.getText().toString());
        values.put(Utilities.FIELD_D_DESCRIPTION, fieldDescrip.getText().toString());
        values.put(Utilities.FIELD_D_PRICE, fieldPrice.getText().toString());

        long idResult = db.insert(Utilities.TABLE_DRINKS, Utilities.FIELD_D_NAME, values);
        Toast.makeText(getContext().getApplicationContext(), "Bebida Registrado: "+idResult, Toast.LENGTH_SHORT).show();
        db.close();
        fieldName.setText("");
        fieldPrice.setText("");
        fieldDescrip.setText("");
    }**/

    private void registerDrinks() {
        //getting the values to save
        String name = fieldName.getText().toString().trim();
        String descrip = fieldDescrip.getText().toString().trim();
        String price = fieldPrice.getText().toString().trim();
        String image = path_uri;


        //checking if the value is provided
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(descrip) &&
                !TextUtils.isEmpty(price)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our user
            String id = databaseRef.push().getKey();

            //creating an Artist Object
            Drink drink = new Drink(name, descrip, price, image);

            //Saving the Food
            databaseRef.child(id).setValue(drink);

            //setting edittext to blank again
            fieldName.setText("");
            fieldDescrip.setText("");
            fieldPrice.setText("");
            imgDrink.setImageResource(R.drawable.sin_imagen);

            //displaying a success toast
            Toast.makeText(getContext(), "Bebida Registrada Correctamente", Toast.LENGTH_SHORT).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(getContext(), "Por favor complete toda la información", Toast.LENGTH_LONG).show();
        }
    }
}
