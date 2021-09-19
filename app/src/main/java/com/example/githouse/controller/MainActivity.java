package com.example.githouse.controller;

import static com.example.githouse.api.Client.retrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.githouse.ItemAdapter;
import com.example.githouse.R;
import com.example.githouse.api.Client;
import com.example.githouse.api.Service;
import com.example.githouse.model.Item;
import com.example.githouse.model.ItemResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    TextView Disconnected;
    private Item item;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON();
                Toast.makeText(MainActivity.this,"github users refreshed ",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initViews(){
        pd=new ProgressDialog(this);
        pd.setMessage("Fetching Github Users.. ");
        pd.show();
        recyclerView=(RecyclerView) findViewById(R.id.rvList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();
    }
    private void loadJSON(){
        Disconnected = (TextView) findViewById(R.id.disconnected);
        try {
            Client Client=new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<ItemResponse>call=apiService.getItems();
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    List<Item>items = response.body().getItems();
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(),items));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    pd.hide();
                }
                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.d("ERROR",t.getMessage());
                    Toast.makeText(MainActivity.this,"Error Fetching Data!", Toast.LENGTH_LONG).show();
                    Disconnected.setVisibility(View.VISIBLE);
                    pd.hide();
                }
            });
        } catch (Exception e) {
            Log.d("ERROR",e.getMessage());
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();

        }
    }
}