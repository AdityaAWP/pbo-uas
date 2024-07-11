module adit.uas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens adit.uas.controller to javafx.fxml;
    opens adit.uas.model to javafx.base;

    exports adit.uas;
    exports adit.uas.controller;
}
