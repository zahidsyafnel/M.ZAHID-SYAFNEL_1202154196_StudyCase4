package com.example.zahid.mzahidsyafnel_1202154196_studycase4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ListNamaMahasiswa extends AppCompatActivity {


    private ListView mListView; //List View
    private ProgressBar mProgressBar; //Progress Bar
    private String [] mUsers= { //User pengguna yang ditampilkan
            "Zahid",
            "Awal",
            "Pagas",
            "Abay",
            "Ariffin",
            "Angga",
            "Parjo",
    };

    //
    private ItemListView itemListView; //Item List View
    private Button z; //Button Click List View

    // Nama dan Activity List View
    ListNamaFragment fragment;
    Activity activity;

    //Method untuk membuat activity list
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama_mahasiswa);

        //variabel untuk membuat progress loading
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mListView = (ListView) findViewById(R.id.listView);
        z = (Button) findViewById(R.id.carilist);

        //Memproses dari visibilitas list view
        mListView.setVisibility(View.GONE);

        //setting toolbar yang akan muncul di atas dengan nama list nama mahasiswa
        Toolbar mToolbar = (Toolbar) findViewById(R.id.listnama);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("List Mahasiswa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //List View Adapter
        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new ArrayList<String>()));

        //Setting untuk saat diclick langsung keluar list view activity
        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListView = new ItemListView(activity);
                itemListView.execute();
            }
        });

        //Kodisi if dari ListNamaFragment
        if (savedInstanceState == null){
            fragment = new ListNamaFragment();
            getSupportFragmentManager().beginTransaction().add(fragment,"task").commit();
        }else{ //activity created for subscquent time
            fragment = (ListNamaFragment) getSupportFragmentManager().findFragmentByTag("task");
        }
        //Status dari AsyncTask Berjalan
        if (fragment != null){
            if (fragment.itemListView != null && fragment.itemListView.getStatus() == AsyncTask.Status.RUNNING){
            }
        }
    }

    // class untuk itemlistview
    class ItemListView  extends AsyncTask<Void, String, Void> {
        private Activity activity;
        public ItemListView(Activity activity){
            this.activity = activity;
        }
        private ArrayAdapter<String> mAdapter;
        private int counter=1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListNamaMahasiswa.this);

        //Method untuk memanggil view yang terlampir
        public void onAttach(Activity activity){
            this.activity = activity;
        }

        //Method untuk melepaskan view yang terlampir
        public void onDetach(){
            activity = null;
        }

        // Method untuk array dari list view
        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter(); //casting suggestion
            //for progress dialog
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setMessage("Please wait....");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);

            //this will handle cacle asynctack when click cancle button
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    itemListView.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            mProgressDialog.show();
        }

        // Method background dari progress list item
        @Override
        protected Void doInBackground(Void... params) {
            for (String item : mUsers){
                publishProgress(item);
                try { //Method try
                    Thread.sleep(100);
                }catch (Exception e){ //Method catch
                    e.printStackTrace();
                } //Kondisi jika di cancel
                if(isCancelled()){
                    itemListView.cancel(true);
                }
            }
            return null;
        }

        // Method untuk progress update dari suatu nilai
        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);

            Integer current_status = (int) ((counter/(float)mUsers.length)*100);
            mProgressBar.setProgress(current_status);

            //set progress only working for horizontal loading
            mProgressDialog.setProgress(current_status);

            //set message will not working when using horizontal loading
            mProgressDialog.setMessage(String.valueOf(current_status+"%"));
            counter++;
        }

        // Method Post dan execute dari progress bar dan dialog
        @Override
        protected void onPostExecute(Void aVoid) {
            //hide progreebar
            mProgressBar.setVisibility(View.GONE);

            //remove progress dialog
            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);
        }
    }
}
