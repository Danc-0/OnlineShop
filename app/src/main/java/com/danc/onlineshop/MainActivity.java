package com.danc.onlineshop;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danc.onlineshop.adapter.CategoryItemAdapter;
import com.danc.onlineshop.adapter.ProductAdapter;
import com.danc.onlineshop.adapter.ProductCategoryAdapter;
import com.danc.onlineshop.adapter.SingleProductAdapter;
import com.danc.onlineshop.adapter.listItemsAdapters.BeerAdapter;
import com.danc.onlineshop.adapter.listItemsAdapters.VodkaAdapter;
import com.danc.onlineshop.adapter.listItemsAdapters.WhiskeyAdapter;
import com.danc.onlineshop.adapter.listItemsAdapters.WineAdapter;
import com.danc.onlineshop.model.AlcoholItemModel;
import com.danc.onlineshop.model.ProductCategory;
import com.danc.onlineshop.model.Products;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ProductCategoryAdapter productCategoryAdapter;
    RecyclerView productCatRecycler, prodItemRecycler;
    ProductAdapter productAdapter;
    private ListView mListView;
    List<AlcoholItemModel> alcoholItemModels = new ArrayList<>();
    List<String> itemCategory = new ArrayList<>();
    AlcoholItemModel alcoholItemModel;
    CategoryItemAdapter categoryItemAdapter;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;

    List<ProductCategory> productCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        prodItemRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false);
        prodItemRecycler.setLayoutManager(layoutManager);

        sampleData();
        listAlcoholItems();


    }

    private void sampleData() {
        //        List<String> productCategoryList = new ArrayList<>();
        productCategoryList = new ArrayList<>();
        productCategoryList.add(new ProductCategory(1, "Whisky"));
        productCategoryList.add(new ProductCategory(2, "Wine"));
        productCategoryList.add(new ProductCategory(3, "Beer"));
        productCategoryList.add(new ProductCategory(4, "Brandy"));
        productCategoryList.add(new ProductCategory(5, "Vodka"));
        productCategoryList.add(new ProductCategory(6, "Soft Drinks"));
        productCategoryList.add(new ProductCategory(7, "Fragrance"));
//
        setProductRecycler(productCategoryList);
        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products(1, "Japanese Cherry Blossom", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "African Mango Shower Gel", "350 ml", "$ 25.00", R.drawable.prod1));
        productsList.add(new Products(1, "Japanese Cherry Blossom", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "African Mango Shower Gel", "350 ml", "$ 25.00", R.drawable.prod1));
        productsList.add(new Products(1, "Japanese Cherry Blossom", "250 ml", "$ 17.00", R.drawable.prod2));
        productsList.add(new Products(2, "African Mango Shower Gel", "350 ml", "$ 25.00", R.drawable.prod1));

        setProdItemRecycler(productsList);
    }

    private void setProductRecycler(List<ProductCategory> productCategoryList) {

        productCatRecycler = findViewById(R.id.cat_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        productCatRecycler.setLayoutManager(layoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(this, productCategoryList);
        CategoryItemAdapter adapter = new CategoryItemAdapter(this, itemCategory);
        productCatRecycler.setAdapter(adapter);
//        productCatRecycler.setAdapter(productCategoryAdapter);


    }

    private void setProdItemRecycler(List<Products> productsList) {

        prodItemRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        prodItemRecycler.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this, productsList);
//        prodItemRecycler.setAdapter(productAdapter);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                SingleProductAdapter adapter = new SingleProductAdapter(MainActivity.this, alcoholItemModels);
                prodItemRecycler.setAdapter(adapter);
            }
        });
    }

    private void listAlcoholItems() {
        mListView = findViewById(R.id.alcohol_items);
        String[] mobileArray = {"Wine", "Whiskey", "Beer", "Vodka"};

        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_layout, R.id.textView3, mobileArray);
        productCatRecycler.setAdapter(productCategoryAdapter);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                WineAdapter adapter = new WineAdapter(MainActivity.this, alcoholItemModels);
                                prodItemRecycler.setAdapter(adapter);
                            }
                        });
                        Toast.makeText(MainActivity.this, "Hello World 0 " + adapter.getPosition(position), Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                WhiskeyAdapter adapter = new WhiskeyAdapter(MainActivity.this, alcoholItemModels);
                                prodItemRecycler.setAdapter(adapter);
                            }
                        });
                        Toast.makeText(MainActivity.this, "Hello World 1 " + adapter.getPosition(position), Toast.LENGTH_SHORT).show();
                        break;

                    case 2:
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                BeerAdapter adapter = new BeerAdapter(MainActivity.this, alcoholItemModels);
                                prodItemRecycler.setAdapter(adapter);
                            }
                        });
                        Toast.makeText(MainActivity.this, "Hello World 2" + adapter.getPosition(position), Toast.LENGTH_SHORT).show();
                        break;

                    case 3:
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                VodkaAdapter adapter = new VodkaAdapter(MainActivity.this, alcoholItemModels);
                                prodItemRecycler.setAdapter(adapter);
                            }
                        });
                        Toast.makeText(MainActivity.this, "Hello World 3" + adapter.getPosition(position), Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        return;
                }
            }
        });
    }


    // Hi all today we are going to make a online product selling app.
    // basically its a cosmatic selling app.
    // The design is very coll and something new from traditional design.
    // But before getting started lets make sure to subscribe and hit
    // the bell icon to get latest update and notified enerytime i post a new video.
    // so lets get started
    // lets app some font and import some image assets
    // Now we will setup a recyclerview for product category list.
    // now we will ad a model class for our category
    // lets create a adapter class for data model
    // Our adapter class is ready Now we will setup recyclerview
    //now we will add some data to our model class
    // Lets run and see
    // so we have setup category recycler.
    // Now in a same way we create a another recycycler view for products
    // first we make model class then adapter
    // product recycler also setup.
    // now we will setup on clicklistener on products and navigate to details activity.
    // lets do this.
    // before going to details page we need cart button in home page and some layout setup.
    // Now its perfect
    // lets move to the details activity.
    // so lets add some animation in this.
    // So this tutorial has been completed if you love my work plz like share and subscribe
    // and dont forget to comments
    // see you in the next video


}
