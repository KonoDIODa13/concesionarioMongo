package application.CRUD;


import application.DAO.CocheDAO;
import application.DAO.ConnectionDB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class CocheCRUD {

    CocheDAO dao;

    public CocheCRUD() {
        dao = new CocheDAO();
        dao.conectarBD();
    }


}
