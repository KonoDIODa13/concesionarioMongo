package application.DAO;

import application.connection.ConnectionDB;
import application.domain.Coche;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class CocheDAO {
    MongoClient con;
    Gson gson = new Gson();

    public void conectarBD() {
        MongoCollection<Document> collection = null;
        try {
            con = ConnectionDB.conectar();

            //La clase MongoDatabase nos ofrece el método getDatabase() que nos permite seleccionar la base de datos
            //con la que queremos trabajar
            // Me conecto a la BD "taller" si NO existe la crea.

            MongoDatabase database = con.getDatabase("concesionario");

            //creando una coleccion
            database.createCollection("coche");
            System.out.println("Coleccion creada Satisfactoriamente.\n");
            // instancio la coleccion a la tabla que me interesa
            collection = database.getCollection("coche");

        } catch (Exception exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
        }
    }

    public void desconectarBD() {
        ConnectionDB.desconectar(con);
    }

    public List<Coche> getCoches() {
        List<Coche> coches = new ArrayList<>();
        MongoCollection<Document> collection = con.getDatabase("concesionario").getCollection("coche");
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Coche coche = gson.fromJson(cursor.next().toJson(), Coche.class);
                coches.add(coche);
            }
            return coches;
        } finally {
            cursor.close();
        }
    }

    public void insertarCoche(Coche coche) {
        MongoCollection<Document> collection = con.getDatabase("concesionario").getCollection("coche");
        String json = gson.toJson(coche);
        Document doc = Document.parse(json);
        collection.insertOne(doc);
    }

    public void modificarCoche(Coche nuevoCoche, Coche antiguoCoche) {
        MongoCollection<Document> collection = con.getDatabase("concesionario").getCollection("coche");
        collection.updateOne(new Document("matricula", antiguoCoche.getMatricula()),
                new Document("$set", new Document("matricula", nuevoCoche.getMatricula()).append("marca", nuevoCoche.getMarca()).append("modelo", nuevoCoche.getModelo()).append("tipo", nuevoCoche.getTipo())
                )
        );
    }

    public void eliminarCoche(Coche coche) {
        MongoCollection<Document> collection = con.getDatabase("concesionario").getCollection("coche");
        collection.deleteOne(new Document("matricula", coche.getMatricula()));
    }
}
