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

    public boolean insertarCoche(List<String> campos) {
        if (comprobaciones(campos)) return false;

        Coche coche = new Coche(campos.get(0), campos.get(1), campos.get(2), campos.get(3));
        if (coches.contains(coche)) {
            AlertUtils.mostrarError("el coche ya esta en la bd.");
            return false;
        }
        if (coches.stream().anyMatch(car -> car.getMatricula().equalsIgnoreCase(coche.getMatricula()))) {
            AlertUtils.mostrarError("El coche con la matricula: " + coche.getMatricula() + " ya existe en la bd.");
            return false;
        }
        dao.insertarCoche(coche);
        return true;
    }

    public boolean modificarCoche(List<String> campos, Coche antiguoCoche) {
        if (comprobaciones(campos)) return false;

        Coche nuevoCoche = new Coche(campos.get(0), campos.get(1), campos.get(2), campos.get(3));
        if (coches.contains(nuevoCoche)) {
            AlertUtils.mostrarError("No se puede realizar la modificación porque ya existe un coche con esos datos en la bd.");
            return false;
        }
        if (coches.stream().anyMatch(car -> car.getMatricula().equalsIgnoreCase(nuevoCoche.getMatricula()))) {
            AlertUtils.mostrarError("No se puede realizar la modificación porque la matrícula " + nuevoCoche.getMatricula() + " ya existe en bd.");
            return false;
        }
        dao.modificarCoche(nuevoCoche, antiguoCoche);
        return true;
    }

    public void eliminarCoche(Coche coche) {
        dao.eliminarCoche(coche);
        AlertUtils.mostrarConfirmacion("Coche eliminado correctamente.");
    }

    public boolean comprobaciones(List<String> campos) {
        return compruebaCampo(campos.get(0), "matricula") ||
                compruebaCampo(campos.get(1), "marca") ||
                compruebaCampo(campos.get(2), "modelo") ||
                compruebaCampo(campos.get(3), "tipo");
    }

    public boolean compruebaCampo(String contenido, String campo) {
        boolean bool = false;
        if (contenido.isEmpty()) {
            AlertUtils.mostrarError("El campo " + campo + " está vacio");
            bool = true;
        }
        return bool;
    }
}
