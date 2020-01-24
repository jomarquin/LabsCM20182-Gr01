package co.edu.udea.compumovil.gr01_20182.lab4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import co.edu.udea.compumovil.gr01_20182.lab4.Entities.Food;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailplateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailplateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailplateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView txtName, txtDescrip, txtType, txtPrice, txtTime;
    ImageView imgDetail;

    public DetailplateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailplateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailplateFragment newInstance(String param1, String param2) {
        DetailplateFragment fragment = new DetailplateFragment();
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
        View view =  inflater.inflate(R.layout.fragment_detailplate, container, false);

        txtName=view.findViewById(R.id.id_detail_name);
        txtDescrip=view.findViewById(R.id.id_detail_descrip);
        txtType=view.findViewById(R.id.id_detail_type);
        txtPrice=view.findViewById(R.id.id_detail_price);
        txtTime=view.findViewById(R.id.id_detail_time);
        imgDetail=view.findViewById(R.id.id_image_detail_plate);

        Bundle objectPlate=getArguments();
        Food food;
        if (objectPlate != null){
            food = (Food) objectPlate.getSerializable("objeto");

            txtName.setText(food.getName());
            txtDescrip.setText("Descripción: "+food.getDescription());
            txtType.setText("Donde se estravió: "+food.getType());
            txtPrice.setText("Sector: "+food.getPrice());
            txtTime.setText("Hace: "+food.getTime()+" días");
            Glide.with(getContext())
                    .load(food.getImage())
                    .placeholder(R.drawable.sin_imagen)
                    .into(imgDetail);

        }
        return view;
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
}
