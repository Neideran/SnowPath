package com.example.myappsnowpath.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myappsnowpath.Data.Repository;
import com.example.myappsnowpath.Model.Catalog;
import com.example.myappsnowpath.Model.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class CatalogViewModel  extends ViewModel {

    private MutableLiveData<ArrayList<Catalog>> catalogData;
    private MutableLiveData<ArrayList<Item>> itemData;
    private MutableLiveData<Item> curItem;
    private final Repository repository = new Repository();

    public void setItemData(ArrayList<Item> itemList) {
        if (itemData == null) {
            itemData = new MutableLiveData<>();
        }
        itemData.postValue(itemList);
    }

    public void setDefaultItemData() {
        if (itemData == null) {
            itemData = new MutableLiveData<>();
        }
        itemData.setValue(repository.getItemListDB());
    }

    public void setDefaultCatalogData() {
        if (catalogData == null) {
            catalogData = new MutableLiveData<>();
        }
        catalogData.setValue(repository.getCatalogListDB());
    }

    public LiveData<Item> getCurrItem(int id) {
        if (curItem == null) {
            curItem = new MutableLiveData<>();
        }
        findItem(id);
        return curItem;
    }

    public LiveData<ArrayList<Item>> getItemData(String category) {
        if (itemData == null) {
            itemData = new MutableLiveData<>();
        }
        loadItemData(category);
        return itemData;
    }

    public LiveData<ArrayList<Item>> getEditedItemData() {
        return itemData;
    }

    public LiveData<ArrayList<Catalog>> getCatalogData(String parent) {
        if (catalogData == null) {
            catalogData = new MutableLiveData<>();
        }
        loadCatalogData(parent);
        return catalogData;
    }

    public void loadItemData(String category) {
        setDefaultItemData();
        ArrayList<Item> selectedItems = new ArrayList<>();
        for (Item c : itemData.getValue()){
            if(category.equals(c.getCategory())){
                selectedItems.add(c);
            }
        }
        itemData.setValue(selectedItems);
    }

    private void loadCatalogData(String parent) {
        setDefaultCatalogData();
        ArrayList<Catalog> selectedCatalog = new ArrayList<>();
        for (Catalog c : catalogData.getValue()){ //DB
            if(parent.equals(c.getParent())){
                selectedCatalog.add(c);
            }
        }
        catalogData.setValue(selectedCatalog);
    }

    private void findItem(int id) {
        for (Item c : repository.getItemListDB()){
            if(id == c.getId()){
                curItem.setValue(c);
            }
        }
    }

    public List<Item> getSearch(String query, String category) {
        List<Item> filteredList = new ArrayList<>();
        for (Item item : Objects.requireNonNull(getItemData(category).getValue())) {
            if(item.getName().toLowerCase().contains(query.toLowerCase())){
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    public Comparator<Item> PriceComparatorExp = (p1, p2) -> Integer.compare(p2.getPrice(),p1.getPrice());

    public Comparator<Item> PriceComparatorCheap = Comparator.comparingInt(Item::getPrice);

    public Comparator<Item> NameComparator = (s1, s2) -> {
        String name1 = s1.getName().toUpperCase();
        String name2 = s2.getName().toUpperCase();
        return name1.compareTo(name2);
    };

    public Comparator<Item> SeasonComparator = (s1, s2) -> {
        String str_season1 = s1.getSeason().substring(3);
        int season1 = Integer.parseInt(str_season1);
        String str_season2 = s2.getSeason().substring(3);
        int season2 = Integer.parseInt(str_season2);
        return Integer.compare(season2,season1);
    };
}