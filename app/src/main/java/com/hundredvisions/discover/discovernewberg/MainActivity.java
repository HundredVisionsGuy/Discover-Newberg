package com.hundredvisions.discover.discovernewberg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Model> list;
    private RecyclerViewAdapter adapter;
    private String baseURL = "https://discover.hundredvisions.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        list = new ArrayList<Model>();
        /// call retrofit
        getRetrofit();

        adapter = new RecyclerViewAdapter(list, MainActivity.this);
        recyclerView.setAdapter(adapter);
    }
    public void getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitArrayApi service = retrofit.create(RetrofitArrayApi.class);
        Call<List<WPPost>> call = service.getPostInfo();

        call.enqueue(new Callback<List<WPPost>>() {
            @Override
            public void onResponse(Call<List<WPPost>> call, Response<List<WPPost>> response) {
                Log.e("mainactivity [abc]", " response " + response.body());

                progressBar.setVisibility(View.GONE);
                for (int i=0; i<response.body().size(); i++) {
                    String tempdetails = response.body().get(i).getExcerpt().getRendered().toString();
                    tempdetails = tempdetails.replace("<p>", "");
                    tempdetails = tempdetails.replace("</p>","");
                    tempdetails = tempdetails.replace("[&hellip;]","");
                    tempdetails = tempdetails.replace("&#8217;", "'");
                    tempdetails = tempdetails.replace("&amp;", "&");
                    tempdetails = tempdetails.replace("&#038;", "&");

                    String title_text = response.body().get(i).getTitle().getRendered().toString();
                    title_text = title_text.replace("&#8217;", "'");
                    title_text = title_text.replace("&amp;", "&");
                    title_text = title_text.replace("&#038;", "&");

                    list.add(new Model(response.body().get(i).getId(), Model.IMAGE_TYPE, title_text,
                            tempdetails,
                            response.body().get(i).getLinks().getWpFeaturedmedia().get(0).getHref()));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<WPPost>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "oops!" + t.toString(), Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
