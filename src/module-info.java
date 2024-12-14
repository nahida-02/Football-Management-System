module myjfx {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires com.jfoenix;
    opens sample to javafx.graphics, javafx.fxml, javafx.controls, javafx.base;
    opens Network to javafx.graphics, javafx.fxml, javafx.controls, javafx.base;
    opens playerinfosearch to javafx.graphics, javafx.fxml, javafx.controls, javafx.base;
    opens dto to javafx.graphics, javafx.fxml, javafx.controls, javafx.base;

}
