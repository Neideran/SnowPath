package com.example.myappsnowpath.Data;

import androidx.annotation.NonNull;

import com.example.myappsnowpath.Model.Catalog;
import com.example.myappsnowpath.Model.Item;
import com.example.myappsnowpath.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Repository {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final ArrayList<Catalog> catalogListDB = new ArrayList<>();
    private final ArrayList<Item> itemListDB = new ArrayList<>();

    public void writeToDB(Order order) {
        DatabaseReference orderRef = firebaseDatabase.getReference().child("Snowpath").child("Orders");
        DatabaseReference newOrderRef = orderRef.push();
        newOrderRef.setValue(order);
    }

    private void readCatalogFromDB(){
        DatabaseReference catalogRef = firebaseDatabase.getReference().child("Snowpath").child("Catalogs");

        catalogRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!catalogListDB.isEmpty()){
                    catalogListDB.clear();
                }
                for (DataSnapshot ds : snapshot.getChildren()){
                    Catalog catalog = ds.getValue(Catalog.class);
                    assert catalog != null;
                    catalogListDB.add(catalog);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    private void readItemFromDB(){
        DatabaseReference itemsRef = firebaseDatabase.getReference().child("Snowpath").child("Items");

        itemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!itemListDB.isEmpty()){
                    itemListDB.clear();
                }
                for (DataSnapshot ds : snapshot.getChildren()){
                    Item item = ds.getValue(Item.class);
                    assert item != null;
                    itemListDB.add(item);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public ArrayList<Catalog> getCatalogListDB() {
        readCatalogFromDB();
        return catalogListDB;
    }

    public ArrayList<Item> getItemListDB() {
        readItemFromDB();
        return itemListDB;
    }
}
