/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Point_Of_Sale;

import static com.sun.javafx.fxml.expression.Expression.add;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Arrays; 
/**
 *
 * @author rahulkartick
 */
public class Item {
    private int uid;
    private String name;
    private String category;
    private String shelf_location [];
    private double weight;
    private double price;
    private String allergens[];
    private String diet_restrictions[];
    private String img_path;
    private int barcode_number;
   
    public Item(String initial_json) throws ParseException{
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(initial_json);
        uid = (int) json.get("uid");
        name = (String) json.get("name");
        category = (String) json.get("category");
        
        JSONArray shelf_location_jsonArray = (JSONArray)json.get("shelf_location"); 
        int length = shelf_location_jsonArray.size();
        for (int i=0;i<length;i++){ 
            add(shelf_location,shelf_location_jsonArray.get(i).toString()); 
        }
        
        weight = (double) json.get("weight");
        price = (double) json.get("price");
        
        JSONArray allergens_jsonArray = (JSONArray)json.get("allergens"); 
        int length2 = allergens_jsonArray.size();
        for (int i=0;i<length2;i++){ 
            add(allergens,allergens_jsonArray.get(i).toString()); 
        }
        
        JSONArray diet_restrictions_jsonArray = (JSONArray)json.get("diet_restrictions"); 
        int length3 = diet_restrictions_jsonArray.size();
        for (int i=0;i<length3;i++){ 
            add(diet_restrictions,diet_restrictions_jsonArray.get(i).toString()); 
        }
        
        img_path = (String) json.get("img_path");
        barcode_number = (int) json.get("barcode_number");
    }
    
    public int get_uid(){
        return uid;
    }
    
    public String get_name(){
        return name;
    }
    
    public String get_category(){
        return category;
    }
    
    public String [] get_shelf_location(){
        return shelf_location;
    }
    
    public double get_weight(){
        return weight;
    }
    
    public double get_price(){
        return price;
    }

    
    public String get_img_path(){
        return img_path;
    }
    
    public int get_barcode_number(){
        return barcode_number;
    }
    
    //cannot get allergens or dietary_restrictions
    
    //checks single allergen 
    public boolean contains_allergen(String allergen){
        return Arrays.asList(allergens).contains(allergen); 
    }
    
    //checks allergens 
    public boolean contains_allergen(String [] allergen){
        for (String allergen1 : allergen) {
            if (Arrays.asList(allergens).contains(allergen1)){
                return true;
            }
        }
        return false;
    }
    
    
    public boolean contains_diet_restrictions(String diet_restriction){
        return Arrays.asList(diet_restrictions).contains(diet_restriction); 
    }
    
    public boolean contains_diet_restrictions(String [] diet_restriction){
        for (String diet_restriction1 : diet_restriction) {
            if (Arrays.asList(diet_restrictions).contains(diet_restriction1)){
                return true;
            }
        }
        return false;
    }
    
}
