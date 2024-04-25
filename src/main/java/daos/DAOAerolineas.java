package daos;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;


import modelos.Aerolinea;
import org.bson.types.ObjectId;

/**
 *
 * @author pedro
 */
public class DAOAerolineas {
    
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;

    public DAOAerolineas(){ 
        mongoClient = new MongoClient();
        database = mongoClient.getDatabase("airport");
        collection = database.getCollection("airlines");
    }

    public ArrayList<Aerolinea> obtenerAerolineas() {
        ArrayList<Aerolinea> aerolineas = new ArrayList();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document d = cursor.next();
                Aerolinea a = new Aerolinea(d.getObjectId("_id"), d.getString("name"), d.getString("country"),
                d.getString("currency"), d.getBoolean("lowcost") == null ? false:true);
                aerolineas.add(a);
            }
        }
        return aerolineas;
    }
    
    public void agregarAerolinea(Aerolinea aerolinea){
        
        Document doc = new Document("name", aerolinea.getNombre())
                .append("country", aerolinea.getPais())
                .append("currency", aerolinea.getMoneda())
                .append("lowcost", aerolinea.isEconomica()
                );
        collection.insertOne(doc);
        System.out.println(doc.toJson());
    }
    
    public void eliminarAerolinea(String id){
        collection.deleteOne(new Document("_id", new ObjectId(id)));
    }

}
