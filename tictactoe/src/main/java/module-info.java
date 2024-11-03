module com.assignment.tictactoe.service.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;



    opens com.assignment.tictactoe.service to javafx.fxml;
    exports com.assignment.tictactoe.service;
}