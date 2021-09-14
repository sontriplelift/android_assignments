package hanu.a2_1801040181;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hanu.a2_1801040181.adapters.ProductAdapter;
import hanu.a2_1801040181.db.ProductManager;
import hanu.a2_1801040181.models.Product;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvProduct;
    private ProductAdapter productAdapter;
    private List<Product> list;

    private ImageView imgProduct, btnViewCart;
    private TextView tvName, tvUnitPrice;
    private EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.edtSearch);
        // recycle view
        rcvProduct = findViewById(R.id.recyclerView);

        list = new ArrayList<>();
        String url =
                "https://mpr-cart-api.herokuapp.com/products";
        RestLoader restLoader = new RestLoader();
        restLoader.execute(url);

        btnViewCart = findViewById(R.id.btnViewCart);
        btnViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void filter(String text) {
        ArrayList<Product> fileredList = new ArrayList<>();
        for (Product p : list) {
            if(p.getName().toLowerCase().contains(text.toLowerCase())){
                fileredList.add(p);
            }
        }
        productAdapter.filterList(fileredList);
    }

    public void setAdapter(List<Product> listProduct) {
        ProductManager productManager = ProductManager.getInstance(this);
        for (Product p : listProduct){
            if(productManager.findByName(p.getName()) == null){
                productManager.add(p);
            }
        }

//        for(Product p : productManager.all()){
//            Log.i("PRODUCT", p.getId()+"");
//            Log.i("PRODUCT",p.getThumbnail());
//            Log.i("PRODUCT",p.getName());
//            Log.i("PRODUCT",p.getUnitPrice());
//            Log.i("PRODUCT",p.getQuantity()+"");
//        }

        productAdapter = new ProductAdapter(listProduct);
        rcvProduct.setAdapter(productAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcvProduct.setLayoutManager(gridLayoutManager);

    }

    public class RestLoader extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream is = urlConnection.getInputStream();
                Scanner sc = new Scanner(is);
                StringBuilder result = new StringBuilder();
                String line;
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    result.append(line);
                }
                return result.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result == null) {
                return;
            }
            try {
                JSONArray root = new JSONArray(result);
//                Log.i("product_info",String.valueOf(root.length()));
                for (int i = 0; i < root.length(); i++) {
                    JSONObject product = root.getJSONObject(i);
                    int id = product.getInt("id");
                    String thumbnail = product.getString("thumbnail");
                    String name = product.getString("name");
                    int unitPrice = product.getInt("unitPrice");
//                    Log.i("PRODUCT",name);
//                    Log.i("PRODUCT",id+"");
//                    Log.i("PRODUCT",thumbnail);
//                    Log.i("PRODUCT",unitPrice+"");
                    Product p = new Product(thumbnail, name, "đ"+unitPrice, 0);
//                    Log.i("PRODUCT", p.getName());

                    list.add(p);
                }
                setAdapter(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}


