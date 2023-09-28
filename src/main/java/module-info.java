module com.chatapp.gigachat {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    opens Client to javafx.fxml, javafx.graphics; // Åbn for begge moduler med et komma

    exports Client;
    exports Controller;
    opens Controller to javafx.fxml, javafx.graphics;
    exports Server;
    opens Server to javafx.fxml, javafx.graphics;
}
