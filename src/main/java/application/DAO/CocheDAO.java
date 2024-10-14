package application.DAO;

import application.domain.Coche;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class CocheDAO {
    MongoClient con;

    public void conectarBD() {
        MongoCollection<Document> collection = null;
        String json;
        Document doc;

        try {
            con = ConnectionDB.conectar();

            //La clase MongoDatabase nos ofrece el método getDatabase() que nos permite seleccionar la base de datos
            //con la que queremos trabajar
            // Me conecto a la BD "taller" si NO existe la crea.

            assert con != null;
            MongoDatabase database = con.getDatabase("concesionario");

            //creando una coleccion
            database.createCollection("coche");


            //Inserto un documento en la coleccion coches
            collection = database.getCollection("coche");

            // Eliminar la colección y empezar de nuevo
            collection.drop();
            System.out.println("La coleccion se ha borrado Correctamente.\n");
            //creo una nueva coleccion
            database.createCollection("coche");
            System.out.println("Coleccion creada Satisfactoriamente.\n");


            //me desconecto de la BD
            //ConnectionDB.desconectar(con);

            //System.out.println("\n Desconectado de la BD.");

        } catch (Exception exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
        }

        String matricula = "6666HHH";
        String marca = "Renault";
        String modelo = "Clio";
        String tipo = "Deportivo";

        Coche coche1 = new Coche(matricula, marca, modelo, tipo); // Creo un objeto

        String matricula2 = "6666HHH";
        String marca2 = "Renault";
        String modelo2 = "Clio";
        String tipo2 = "Deportivo";

        Coche coche2 = new Coche(matricula2, marca2, modelo2, tipo2); // Creo un objeto


        // Deserializar objeto a string json
        Gson gson = new Gson();
        json = gson.toJson(coche1);

        //Ten en cuenta que lo que se inserta en Mongo son documentos por tanto tengo que convertirlo
        // Parsear un documento bson e insertar
        doc = Document.parse(json);

        collection.insertOne(doc);
        //inserto el coche 2
        json = gson.toJson(coche2);
        // Parsear un documento bson e insertar
        doc = Document.parse(json);

        collection.insertOne(doc);

        // Recuperar para asegurarse de que se insertó el objeto
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                //System.out.println(cursor3.next().toJson());
                //También puede usar Gson para convertir el documento bson recuperado de nuevo al objeto Java
                //SERIALIZAR
                Coche coche3 = gson.fromJson(cursor.next().toJson(), Coche.class);
                System.out.print(coche3.toString());
            }

        } finally {
            cursor.close();
        }

    }

    public void desconectarBD() {
        ConnectionDB.desconectar(con);
    }
}
