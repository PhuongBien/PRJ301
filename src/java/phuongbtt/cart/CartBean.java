
package phuongbtt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CartBean implements Serializable{
    private String customerId;
    private Map<String , Integer > items;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    //ko ton tai pp Set
    public Map<String, Integer> getItems() {
        return items;
    }
    public void addItemToCart(String product){
        if(product == null){
            return;
        }
        if(product.trim().isEmpty()){
            return;
        }
        //1.Ktra cai hop co ton tai hay khong
        if(this.items == null){
            this.items = new HashMap<>();
        }
        //2.Ktra mon hang co ton tai hay k
        int quantity = 1;
        if(this.items.containsKey(product)){
            quantity = this.items.get(product) + 1;
        }
        //3.Bo mon do vao ngan chua do
        this.items.put(product, quantity);
        
    }
    public void removeItemFromCart(String product){
        if(product == null){
            return;
        }
        if(product.trim().isEmpty()){
            return;
        }
        //1. Check exist items
        if ( this.items == null){
            return;
        }
        //2. Check item
        if ( this.items.containsKey(product)){
            //3. remove item form items
            this.items.remove(product);
            if(this.items.isEmpty()){ // ktra kich thuoc phan tu neu = 0 thi tra han ve null ( terminal )
                this.items = null;
            }
        }
    }
}
