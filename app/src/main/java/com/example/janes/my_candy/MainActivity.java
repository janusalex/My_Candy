        package com.example.janes.my_candy;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;
        import com.loopj.android.http.AsyncHttpClient;
        import com.loopj.android.http.TextHttpResponseHandler;

        import java.util.ArrayList;

        import cz.msebera.android.httpclient.Header;

        public class MainActivity extends AppCompatActivity {
        private Candy[] candies;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                TextView textView = (TextView)this.findViewById(R.id.text_view_title);
                textView.setText(R.string.title_text);

                final ArrayList<String> candy_list = new ArrayList<String>();

                candy_list.add("Candy 1");
                /*candy_list.add("Candy 2");
                candy_list.add("Candy 3");
                candy_list.add("Candy 4");
                candy_list.add("Candy 5");
                candy_list.add("Candy 6");
                candy_list.add("Candy 7");
                candy_list.add("Candy 8");
                candy_list.add("Candy 9");
                candy_list.add("Candy 10");
                candy_list.add("Candy 12");
                candy_list.add("Candy 13");
                candy_list.add("Candy 14");
                candy_list.add("Candy 15");*/

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_candy,R.id.text_view_candy,candy_list);

                ListView listView = (ListView)this.findViewById(R.id.list_view_candy);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                        /*Toast toast = Toast.makeText(MainActivity.this,""+i,Toast.LENGTH_SHORT);
                                                        toast.show();*/
                                                        Intent detailIntent = new Intent(MainActivity.this,DetailActivity.class);
                                                        /*detailIntent.putExtra("candy_name",candy_list.get(i));*/
                                                        detailIntent.putExtra("candy_name",candies[i].name);
                                                        detailIntent.putExtra("candy_image",candies[i].image);
                                                        detailIntent.putExtra("candy_price",candies[i].price);
                                                        detailIntent.putExtra("candy_description",candies[i].description);

                                                        startActivity(detailIntent);
                                                    }
                                                }
                );

                AsyncHttpClient client = new AsyncHttpClient();
                client.get("https://s3.amazonaws.com/courseware.codeschool.com/super_sweet_android_time/API/CandyCoded.json",
                        new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.e("AsyncHttpClient","response = "+responseString);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.d("AsyncHttpClient","response = "+responseString);

                        Gson gson = new GsonBuilder().create();
                        Candy[] candies = gson.fromJson(responseString,Candy[].class);

                        adapter.clear();
                        for(Candy candy:candies){
                            adapter.add(candy.name);
                        }
                    }
                });
            }
        }
