package com.example.myappsnowpath.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.myappsnowpath.Data.Repository;
import com.example.myappsnowpath.Model.Item;
import com.example.myappsnowpath.Model.Order;

import java.security.SecureRandom;
import java.util.HashMap;

public class RegistrationViewModel extends ViewModel {

    private String toastMessage;
    final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    SecureRandom rnd = new SecureRandom();
    private final Repository repository = new Repository();

    public Integer countDelPrice(int price){
        int price_del;

        if(price < 10000) {
            price_del = 500;
        } else if(price > 100000){
            price_del = 2000;
        }
        else{
            price_del = price / 50;
        }

        return price_del;
    }

    public Integer countTotPrice(int price){
        int price_tot;

        if(price < 10000) {
            price_tot = price + 500;
        } else if(price > 100000){
            price_tot = price + 2000;
        }
        else{
            price_tot = price + price / 50;
        }
        return price_tot;
    }

    public boolean checkIfTextFilled(String name, String phone, String email, String address){
        if(name.isEmpty()){
            toastMessage = "Введиете ФИО";
            return false;
        }else if(phone.isEmpty()){
            toastMessage = "Введиете номер телефона";
            return false;
        } else if(email.isEmpty()){
            toastMessage = "Введиете email";
            return false;
        }else if(address.isEmpty()){
            toastMessage = "Введиете адрес доставки";
            return false;
        }
        else {
            toastMessage = "Заказ успешно оформлен";
            return true;
        }
    }

    public String getToastMessage(){
        return toastMessage;
    }

    public String randomString(){
        StringBuilder sb = new StringBuilder(24);
        for(int i = 0; i < 24; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public HashMap<String,String> createOrderMap(HashMap<Item,Integer> hashMap){
        HashMap<String,String> map = new HashMap<>();
        for (Item i : hashMap.keySet()){
            map.put(i.getId() + "  name", i.getName());
            map.put(i.getId() + "  value",String.valueOf(hashMap.get(i)));
        }
        return map;
    }
    public void saveOrder(Order order) {
        repository.writeToDB(order);
    }
}
