package com.example.zahid.mzahidsyafnel_1202154196_studycase4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListNamaFragment extends Fragment {
    public ListNamaFragment(){

    }
    //Item List View dari List Nama Mahasiswa
    public ListNamaMahasiswa.ItemListView itemListView;
    private Activity activity;

    //method untuk activity item list
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        if (itemListView != null){
            itemListView.onAttach(activity);
        }
    }

    //activity untuk melepaskan item list view
    @Override
    public void onDetach() {
        super.onDetach();
        if (itemListView != null){ //kondisi jika dia null
            itemListView.onDetach();
        }
    }

    //method untuk membuat fungsi oncreate
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //method untuk parameter,field, return value dapat null
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    //method interface untuk kelas yang akan dipanggil saat aktivitas kontainer menerima hasil aktivitas.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState); //membuat activity dari list item mahasiswa
        setRetainInstance(true);
    }

    //method untuk memulai AsycnTask
    @Override
    public void onStart() {
        super.onStart();
    }

    //Method untuk melanjutkan AsycnTask
    @Override
    public void onResume() {
        super.onResume();
    }

    //Method untuk menghancurkan AsycnTask
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //Method untuk Menghentikan AsyncTask
    @Override
    public void onStop() {
        super.onStop();
    }
}
