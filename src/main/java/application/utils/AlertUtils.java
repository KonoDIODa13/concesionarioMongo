package application.utils;

import javafx.scene.control.Alert;

public class AlertUtils {
    // metodo estatico para mostrar un alert de error.
    public static void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setContentText(mensaje);
        alerta.show();
    }

    // metodo estatico para mostrar un alert de confirmacion.
    public static void mostrarConfirmacion(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
