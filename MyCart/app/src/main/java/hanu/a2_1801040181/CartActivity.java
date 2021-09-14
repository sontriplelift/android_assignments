package hanu.a2_1801040181;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

import hanu.a2_1801040181.adapters.CartAdapter;
import hanu.a2_1801040181.adapters.ProductAdapter;
import hanu.a2_1801040181.db.ProductManager;
import hanu.a2_1801040181.models.Product;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rcvProductInCart;
    private CartAdapter cartAdapter;
    private List<Product> productsInCart;
    private ImageView imgProduct;
    public TextView tvTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        tvTotalPrice = findViewById(R.id.txtTotalPrice);
        rcvProductInCart = findViewById(R.id.recyclerViewCart);

        ProductManager productManager = ProductManager.getInstance(this);
        productsInCart = productManager.allInCart();



        cartAdapter = new CartAdapter(productsInCart, this);
        rcvProductInCart.setAdapter(cartAdapter);
        rcvProductInCart.setLayoutManager(new LinearLayoutManager(this));

        long totalPrice = 0;
        for(Product p : productsInCart){
            totalPrice += Long.parseLong(p.getUnitPrice().substring(1)) * p.getQuantity();
        }
        tvTotalPrice.setText("đ"+totalPrice);
    }



}