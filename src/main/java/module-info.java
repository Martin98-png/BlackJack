module com.example.ud {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ud to javafx.fxml;
    exports com.example.ud;
}