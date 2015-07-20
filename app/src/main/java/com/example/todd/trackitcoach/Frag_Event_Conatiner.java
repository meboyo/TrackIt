package com.example.todd.trackitcoach;

import android.app.ActionBar;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Frag_Event_Conatiner.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Frag_Event_Conatiner#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_Event_Conatiner extends Fragment {
    ListView EList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_Event_Conatiner.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_Event_Conatiner newInstance(String param1, String param2) {
        Frag_Event_Conatiner fragment = new Frag_Event_Conatiner();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Frag_Event_Conatiner() {
        // Required empty public constructor
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

       SetUpList("EvesNames");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__event__conatiner, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
        public void SendAMessage(String Message);

    }
public void SetUpList(String namefromBundle )
{
    EList = new ListView(getActivity());
    // ListView EList = ;
    ViewGroup.LayoutParams vg = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
    EList.setLayoutParams(vg);
    //    EList.getLayoutParams().height = 50;
    ArrayAdapter<String> adapt = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,getArguments().getStringArray(namefromBundle));
    EList.setAdapter(adapt);

    getActivity().addContentView(EList, vg);
    //  getActivity().setContentView(EList);
    if(!EList.hasOnClickListeners()) {
        EList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.SendAMessage(String.valueOf(parent.getItemAtPosition(position)));
            }
        });
    }

}


}
