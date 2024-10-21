package application.CRUD;


import application.DAO.CocheDAO;
import application.domain.Coche;
import application.utils.AlertUtils;

import java.util.List;

public class CocheCRUD {

    CocheDAO dao;
    List<Coche> coches;

    public CocheCRUD() {
        dao = new CocheDAO();
        dao.conectarBD();
    }

    public void salir() {
        dao.desconectarBD();
    }

    public List<Coche> getCoches() {
        coches = dao.getCoches();
        return coches;
    }

    public void insertarCoche(List<String> campos) {
        if (!comprobaciones(campos)) return;
        Coche coche = new Coche(campos.get(0), campos.get(1), campos.get(2), campos.get(3));
        if (coches.contains(coche)) {
            AlertUtils.mostrarError("el coche ya esta en la bd");
            return;
        }
        if (coches.stream().anyMatch(car -> car.getMatricula().equalsIgnoreCase(campos.get(0)))) {
            AlertUtils.mostrarError("El coche con la matricula: " + campos.get(0) + " ya existe en la bd");
            return;
        }
        dao.insertarCoche(coche);
        AlertUtils.mostrarConfirmacion("Coche creado correctamente");
    }

    public boolean compruebaCampo(String contenido, String campo) {
        boolean bool = true;
        if (contenido.isEmpty()) {
            AlertUtils.mostrarError("El campo " + campo + " está vacio");
            bool = false;
        }
        return bool;
    }

    public void modificarCoche(List<String> campos, Coche antiguoCoche) {
        if (!comprobaciones(campos)) return;
        Coche nuevoCoche = new Coche(campos.get(0), campos.get(1), campos.get(2), campos.get(3));
        dao.modificarCoche(nuevoCoche, antiguoCoche);
        AlertUtils.mostrarConfirmacion("Coche modificado correctamente");
    }

    public void eliminarCoche(Coche coche){
        dao.eliminarCoche(coche);
        AlertUtils.mostrarConfirmacion("Coche eliminado correctamente");
    }

    public boolean comprobaciones(List<String> campos) {
        return compruebaCampo(campos.get(0), "matricula") &&
                compruebaCampo(campos.get(1), "marca") &&
                compruebaCampo(campos.get(2), "modelo") &&
                compruebaCampo(campos.get(3), "tipo");
    }

}
