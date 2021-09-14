package hanu.a2_1801040181.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import hanu.a2_1801040181.R;
import hanu.a2_1801040181.db.ProductManager;
import hanu.a2_1801040181.models.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    //dataset
    private List<Product> products;
    List<Product> filterProducts;

    public ProductAdapter(List<Product> products) {
        this.products = products;
        this.filterProducts = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // activity to display
        Context context = parent.getContext();

        // layout inflater xml ->> java object refs
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_product, parent, false);

        return new ProductHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductHolder holder, int position) {
        Product product = products.get(position);
        // bind data with view template
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        private ImageView imgProduct;
        private TextView tvName;
        private TextView tvUnitPrice;
        private ImageView btnAddToCart;
        private Context context;

        public ProductHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;

            imgProduct = itemView.findViewById(R.id.img_product);
            tvName = itemView.findViewById(R.id.txtName);
            tvUnitPrice = itemView.findViewById(R.id.txtUnitPrice);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }

        public void bind(Product product) {
            tvName.setText(product.getName());
            tvUnitPrice.setText(String.valueOf(product.getUnitPrice()));
            ImageLoader imageLoader = new ImageLoader();
            imageLoader.execute(product.getThumbnail());

            btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductManager productManager = ProductManager.getInstance(context);
                    Product p = productManager.findByName(product.getName());

                    if (p.getQuantity()==0){
                        p.setQuantity(1);
                        productManager.update(p);
//                        Log.i("PRODUCT", String.valueOf(productManager.update(product)));
                        Toast toast = Toast.makeText(context,"Add product to cart successfully!",Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(context,"Product is already in your cart!",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
//--
        }


        public class ImageLoader extends AsyncTask<String, Void, Bitmap> {

            @Override
            protected Bitmap doInBackground(String... strings) {
                URL url;
                HttpURLConnection urlConnection;
                try {
                    url = new URL(strings[0]);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.connect();

                    InputStream is = urlConnection.getInputStream();
                    Bitmap image = BitmapFactory.decodeStream(is);
                    return image;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap b) {
                super.onPostExecute(b);
                imgProduct.setImageBitmap(b);
            }
        }
    }

    public void filterList(ArrayList<Product> filteredList) {
        products = filteredList;
        notifyDataSetChanged();
    }

}
