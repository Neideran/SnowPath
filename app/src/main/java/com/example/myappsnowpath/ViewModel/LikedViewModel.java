package com.example.myappsnowpath.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myappsnowpath.Model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LikedViewModel extends ViewModel {

    private MutableLiveData<List<Item>> likedData;
    private String toastMessage;

    public void addItemToLiked(Item item){
        toastMessage = "Добавлено в избранное";
        if (likedData == null) {
            likedData = new MutableLiveData<>();
            ArrayList<Item> list = new ArrayList<>();
            list.add(item);
            likedData.setValue(list);
        }
        else{
            ArrayList<Integer> listID = new ArrayList<>();
            for (Item i : Objects.requireNonNull(likedData.getValue())){
                listID.add(i.getId());
            }
            if(listID.contains(item.getId())){
                toastMessage = "Уже в избранном";
            }
            else {
                ArrayList<Item> list = new ArrayList<>(likedData.getValue());
                list.add(item);
                likedData.setValue(list);
            }
        }
    }

    public String getToastMessage(){
        if(toastMessage != null) {
            return toastMessage;
        }
        return "Liked error";
    }

    public LiveData<List<Item>> getLikedData() {
        return likedData;
    }

    public void deleteFromLiked(Item item){
        ArrayList<Item> list = new ArrayList<>(Objects.requireNonNull(likedData.getValue()));
        list.remove(item);
        likedData.setValue(list);
    }
}
