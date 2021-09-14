package hanu.a2_1801040181.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import hanu.a2_1801040181.CartActivity;
import hanu.a2_1801040181.R;
import hanu.a2_1801040181.db.ProductManager;
import hanu.a2_1801040181.models.Product;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {

    private List<Product> products;
    private CartActivity cartActivity;

    public CartAdapter(List<Product> products, CartActivity cartActivity){
        this.products = products;
        this.cartActivity = cartActivity;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // activity to display
        Context context = parent.getContext();

        // layout inflater xml ->> java object refs
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_product_in_cart, parent, false);

        return new CartHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartHolder holder, int position) {
        Product product = products.get(position);

        // bind data with view template
        holder.bind(product);
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        private ImageView imgProduct, btnPlus, btnMinus;
        private TextView tvName, tvUnitPrice, tvSumPrice, tvQuantity;
        private Context context;

        public CartHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;

            imgProduct = itemView.findViewById(R.id.img_product1);
            tvName = itemView.findViewById(R.id.txtName1);
            tvUnitPrice = itemView.findViewById(R.id.txtUnitPrice1);
            tvSumPrice = itemView.findViewById(R.id.txtSumPrice);
            tvQuantity = itemView.findViewById(R.id.txtQuantity);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
        }

        public void bind(Product product) {
            tvName.setText(product.getName());
            tvUnitPrice.setText(product.getUnitPrice());
            tvQuantity.setText(String.valueOf(product.getQuantity()));
            tvSumPrice.setText(String.valueOf(Integer.parseInt(product.getUnitPrice().substring(1))*product.getQuantity()));
            ImageLoader imageLoader = new ImageLoader();
            imageLoader.execute(product.getThumbnail());

            btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductManager productManager = ProductManager.getInstance(context);
                    long quantityAfterUpdate = product.getQuantity() + 1;
                    product.setQuantity(quantityAfterUpdate);
                    productManager.update(product);
//                    Log.i("PRODUCT", product.getQuantity()+"");
                    tvSumPrice.setText(String.valueOf(Integer.parseInt(product.getUnitPrice().substring(1))*product.getQuantity()));

                    long totalPrice = 0;
                    for(Product p : productManager.all()){
                        totalPrice += Long.parseLong(p.getUnitPrice().substring(1)) * p.getQuantity();
                    }
                    cartActivity.tvTotalPrice.setText("đ"+totalPrice);

                    notifyDataSetChanged();
                }
            });

            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductManager productManager = ProductManager.getInstance(context);

                    if(product.getQuantity()==1){
                        new AlertDialog.Builder(context)
                                .setTitle("Remove Product")
                                .setMessage("Are you sure to remove this product from cart?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        products.remove(product);
                                        long quantityAfterUpdate = product.getQuantity() - 1;
                                        product.setQuantity(quantityAfterUpdate);
                                        productManager.update(product);
                                        long totalPrice = 0;
                                        for(Product p : productManager.all()){
                                            totalPrice += Long.parseLong(p.getUnitPrice().substring(1)) * p.getQuantity();
                                        }
                                        cartActivity.tvTotalPrice.setText("đ"+totalPrice);
                                        Toast toast = Toast.makeText(context,"Removed",Toast.LENGTH_SHORT);
                                        toast.show();
                                        notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .show();
                    } else {
                        long quantityAfterUpdate = product.getQuantity() - 1;
                        product.setQuantity(quantityAfterUpdate);
                        productManager.update(product);
                        tvSumPrice.setText(String.valueOf(Integer.parseInt(product.getUnitPrice().substring(1))*product.getQuantity()));
                        long totalPrice = 0;
                        for(Product p : productManager.all()){
                            totalPrice += Long.parseLong(p.getUnitPrice().substring(1)) * p.getQuantity();
                        }
                        cartActivity.tvTotalPrice.setText("đ"+totalPrice);
                        notifyDataSetChanged();
                    }

                }
            });

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

}
