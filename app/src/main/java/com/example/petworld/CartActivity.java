package com.example.petworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {

    ListView listView;
    TextView teks;

    Integer[] itemImages = new Integer[]{
            R.drawable.food1,R.drawable.food2,
            R.drawable.mainan,R.drawable.kandang2};
    String[][] itemsDetail = new String[][]{
            {"Heart Kitten Food","Rp 72.000"},
            {"Vitakraft Bird Food","Rp 28.000"},
            {"Chew Ball","Rp 80.000"},
            {"Kandang kecil","Rp 185.000"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        teks=(TextView)findViewById(R.id.Judul);
        Typeface customfont = getResources().getFont(R.font.alba);
        teks.setTypeface(customfont);

        listView = (ListView) findViewById(R.id.listItem);

        ArrayList<HashMap<String, String>> arrayList= new ArrayList<>();
        for (int i=0;i<itemImages.length;i++)
        {
            HashMap<String,String> hashMap=new HashMap<>();//create a hashmap to store the data in key value pair
            hashMap.put("image",itemImages[i]+"");
            hashMap.put("name",itemsDetail[i][0]);
            hashMap.put("price",itemsDetail[i][1]);
            arrayList.add(hashMap);//add the hashmap into arrayList
        }
        String[] from={"image","name","price"};
        int[] to={R.id.itemImg,R.id.barangText,R.id.hargaText};
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,arrayList,R.layout.list_cart,from,to);//Create object and set the parameters for simpleAdapter
        listView.setAdapter(simpleAdapter);
    }
}
