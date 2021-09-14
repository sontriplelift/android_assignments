package hanu.a2_1801040181.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.ArrayList;
import java.util.List;

import hanu.a2_1801040181.models.Product;

class ProductCursorWrapper extends CursorWrapper {

    public ProductCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Product getProduct() {
        long id = getLong(getColumnIndex("id"));
        String thumbnail = getString(getColumnIndex(DbSchema.ProductsTable.Cols.THUMBNAIL));
        String name = getString(getColumnIndex(DbSchema.ProductsTable.Cols.NAME));
        String unitPrice = getString(getColumnIndex(DbSchema.ProductsTable.Cols.UNIT_PRICE));
        long quantity = getLong(getColumnIndex(DbSchema.ProductsTable.Cols.QUANTITY));

        Product product = new Product(id, thumbnail, name, unitPrice, quantity);
        return product;
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        moveToFirst();
        while (!isAfterLast()) {
            Product product = getProduct();
            products.add(product);
            moveToNext();
        }
        return products;
    }

    public Product getProductById(){
        Product product = null;

        moveToFirst();
        if (!isAfterLast()){
            product = getProduct();
        }
        return product;
    }

    public Product getProductByName(){
        Product product = null;

        moveToFirst();
        if (!isAfterLast()){
            product = getProduct();
        }
        return product;
    }

}