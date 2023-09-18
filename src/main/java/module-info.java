module com.chatapp.gigachat {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                            
    opens com.chatapp.gigachat to javafx.fxml;
    exports com.chatapp.gigachat;
}