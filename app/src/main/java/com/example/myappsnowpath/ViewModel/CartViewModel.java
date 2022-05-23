package com.example.myappsnowpath.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myappsnowpath.Model.Item;

import java.util.HashMap;
import java.util.Objects;


public class CartViewModel extends ViewModel {

    private MutableLiveData<HashMap<Item,Integer>> cartData;
    private String toastMessage;

    public LiveData<HashMap<Item,Integer>> getCartData() {
        return cartData;
    }

    public void addItemToCart(Item item){
        toastMessage = "Добавлено в корзину";
        if (cartData == null) {
            cartData = new MutableLiveData<>();
            HashMap<Item, Integer> map = new HashMap<>();
            map.put(item, 1);
            cartData.setValue(map);
        }
        else{
            HashMap<Item,Integer> mapItem = getMapItem();
            HashMap<Integer,Integer> mapID = getMapID();

            if(mapID.containsKey(item.getId())){
                int val = mapID.get(item.getId()) + 1;
                mapID.replace(item.getId(),val);
                mapItem.replace(item,val);
                cartData.setValue(mapItem);
                toastMessage = "Увеличено количество: " + val;
            }
            else {
                HashMap <Item,Integer> map = new HashMap<>(cartData.getValue());
                map.put(item, 1);
                cartData.setValue(map);
            }

        }
    }

    public String getToastMessage(){
        if(toastMessage != null) {
            return toastMessage;
        }
        return "Cart error";
    }

    public void deleteFromCart(Item item){
        HashMap<Item,Integer> mapItem = getMapItem();
        HashMap<Integer,Integer> mapID = getMapID();
        int val = mapID.get(item.getId());

        if(val > 1){
            val -= 1;
            mapID.replace(item.getId(),val);
            mapItem.replace(item,val);
            cartData.setValue(mapItem);
            toastMessage = "Уменьшено количество: " + val;
        }
        else {
            HashMap <Item,Integer> map = new HashMap<>(cartData.getValue());
            map.remove(item);
            cartData.setValue(map);
            toastMessage = "Удалено из корзины";
        }
    }

    public HashMap<Integer,Integer> getMapID() {

        HashMap<Integer, Integer> mapID = new HashMap<>();
        for (Item i : Objects.requireNonNull(cartData.getValue()).keySet()) {
            mapID.put(i.getId(), cartData.getValue().get(i));
        }
         return mapID;
    }

    public HashMap<Item,Integer> getMapItem() {
        HashMap<Item, Integer> mapItem = new HashMap<>();
        for (Item i : Objects.requireNonNull(cartData.getValue()).keySet()) {
            mapItem.put(i, cartData.getValue().get(i));
        }
        return mapItem;
    }

    public Integer getFullPrice(){
        int price = 0;
        if (cartData.getValue() != null) {
            HashMap<Item, Integer> map = new HashMap<>(cartData.getValue());
            for (Item i : map.keySet()) {
                price += i.getPrice() * map.get(i);
            }
        }
        return price;
    }

    public void clearCart(){
        if (!Objects.requireNonNull(cartData.getValue()).isEmpty()){
            cartData.setValue(new HashMap<>());
        }
    }

    public String sharePrice(String price){
        if(price.length() == 4){
            return price.charAt(0) + " " + price.substring(1);
        }
        if(price.length() == 5){
            return price.substring(0,2) + " " + price.substring(2);
        }
        if (price.length() == 6){
            return price.substring(0,3) + " " + price.substring(3);
        }
        if (price.length() == 7){
            return price.charAt(0) + " " + price.substring(1,4) + " " + price.substring(4);
        }
        else {
            return price;
        }
    }
}
