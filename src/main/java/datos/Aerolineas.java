/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import objetos.Aerolinea;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Pedro
 */
public class Aerolineas {
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;

    public Aerolineas() {
        this.mongoClient = MongoClients.create();
        this.database = mongoClient.getDatabase("airport");
        this.collection = database.getCollection("airlines");
    }
    
    private Document objectToDocument(Aerolinea a){
        Document aerolinea = new Document()
                .append("name", a.getNombre())
                .append("country", a.getPais())
                .append("currency", a.getMoneda())
                .append("lowcost", a.isEconomica());
        return aerolinea;
    }
    
    public ObjectId insertAirline(Aerolinea a){
        ObjectId id = new ObjectId();
        Document aerolinea = objectToDocument(a).append("_id", id);
        collection.insertOne(aerolinea);
        return id;
    }
    
    public boolean updateAirline(Aerolinea a){
        try {
            Bson idQuery = Filters.eq("_id", a.getId());
            Bson updates = Updates.combine(Updates.set("name", a.getNombre()), Updates.set("country", a.getPais()),Updates.set("currency", a.getMoneda()),Updates.set("lowcost", a.isEconomica()));
            UpdateResult result = collection.updateMany(idQuery, updates);
            System.out.println(result.getModifiedCount());
            return result.getModifiedCount() == 1;
        } catch (Exception e) {
            return false;
        }         
    }
    
    public boolean deleteAirlineById(ObjectId id){
        try {
            Bson idQuery = Filters.eq("_id",id);
            DeleteResult result = collection.deleteOne(idQuery);
                System.out.println(result.getDeletedCount());
            return result.getDeletedCount() ==  1;
        } catch (Exception e) {
            return false;
        }
    }
    
    public ArrayList<Aerolinea> getAllAirlines(){
        ArrayList<Aerolinea> aerolineas = new ArrayList();
        MongoCursor<Document> cursor = collection.find().iterator();
        try  {
            while (cursor.hasNext()) {
                Document d = cursor.next();
                Aerolinea a = new Aerolinea(d.getObjectId("_id"), d.getString("name"), d.getString("country"),
                d.getString("currency"), d.getBoolean("lowcost") == null ? false:true);
                aerolineas.add(a);
            }
            return aerolineas;
        }finally{
            cursor.close();
        }        
    }
    
}
