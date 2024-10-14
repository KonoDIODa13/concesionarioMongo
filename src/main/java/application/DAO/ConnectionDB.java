/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.DAO;

import application.utils.R;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.util.Properties;

public class ConnectionDB {

    public static MongoClient conectar() {
        try {
            Properties configuration = new Properties();
            configuration.load(R.getProperties("database.properties"));
            String username = configuration.getProperty("username");
            String password = configuration.getProperty("password");
            String port = configuration.getProperty("port");
            String authSource = configuration.getProperty("authSource");

            // final MongoClient conexion = new MongoClient(new MongoClientURI("mongodb://admin:1234@localhost:27017/?authSource=admin"));
            final MongoClient conexion = new MongoClient(new MongoClientURI("mongodb://" + username + ":" + password + "@localhost:" + port + "/?authSource=" + authSource));

            System.out.println("Conectada correctamente a la BD");
            return conexion;
        } catch (Exception e) {
            System.out.println("Conexion Fallida");
            System.out.println(e);
            return null;
        }
    }

    public static void desconectar(MongoClient con) {
        con.close();
    }

}
