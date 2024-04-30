package datos;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import interfaces.IAerolineasDAO;
import java.util.ArrayList;


import objetos.Aerolinea;
import org.bson.types.ObjectId;

/**
 *
 * @author pedro
 */
public class DAOAerolineas implements IAerolineasDAO{
    
//    MongoClient mongoClient;
//    MongoDatabase database;
//    MongoCollection<Document> collection;
//
//    public DAOAerolineas(){ 
//        mongoClient = new MongoClient();
//        database = mongoClient.getDatabase("airport");
//        collection = database.getCollection("airlines");
//    }
    
    private MongoCollection getCollection(){
        ConexionBD conexion = new ConexionBD();
        MongoDatabase database = conexion.crearConexion();
        MongoCollection collection = database.getCollection("airlines", Aerolinea.class);
        return collection;
    }

    @Override
    public ArrayList<Aerolinea> consultarTodos() {
        ArrayList<Aerolinea> aerolineas = new ArrayList();
        try (MongoCursor<Document> cursor = getCollection().find().iterator()) {
            while (cursor.hasNext()) {
                Document d = cursor.next();
                Aerolinea a = new Aerolinea(d.getObjectId("_id"), d.getString("name"), d.getString("country"),
                d.getString("currency"), d.getBoolean("lowcost") == null ? false:true);
                aerolineas.add(a);
            }
        }
        return aerolineas;
    }
    
    @Override
    public boolean agregar(Aerolinea aerolinea){
        
//        Document doc = new Document("name", aerolinea.getNombre())
//                .append("country", aerolinea.getPais())
//                .append("currency", aerolinea.getMoneda())
//                .append("lowcost", aerolinea.isEconomica()
//                );
//        System.out.println(doc.toJson());
        try {
            this.getCollection().insertOne(aerolinea);
            return true;
        } catch (Exception e) {
            return false;
        }      
    }        

    @Override
    public boolean actualizar(Aerolinea a) {
        try {
            UpdateResult result = this.getCollection().updateOne(eq("_id", a.getId()), combine(set("name",a.getNombre()), set("country", a.getPais()), set("currency", a.getMoneda()), set("lowcost", a.isEconomica())));
            
            return result.getModifiedCount()==1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(Aerolinea a) {
        try {
            DeleteResult result = this.getCollection().deleteOne(eq("_id",a.getId()));
            return result.getDeletedCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Aerolinea consultar(Aerolinea a) {
         try {
            Aerolinea result = (Aerolinea) this.getCollection().find(eq("_id",a.getId())).first();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
