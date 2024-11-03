    package com.assignment.tictactoe.service;

    import javafx.application.Platform;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Button;
    import javafx.scene.control.ButtonType;
    import javafx.scene.control.Label;
    import javafx.scene.input.MouseEvent;
    import javafx.scene.layout.AnchorPane;
    import lombok.Getter;

    import java.net.URL;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.ResourceBundle;

    public class BoardController implements Initializable {

        private final BoardImpl boardImpl =  new BoardImpl(this);;
        private final Player humanPlayer = new HumanPlayer(boardImpl);;
        private final Player aiPlayer = new AiPlayer(boardImpl,this);
        private boolean isHumanTurn = true;
        private int round = 1;

        @FXML
        private AnchorPane backGroundPane;

        @Getter
        @FXML
        private Button btn1;

        @Getter
        @FXML
        private Button btn2;

        @Getter
        @FXML
        private Button btn3;

        @Getter
        @FXML
        private Button btn4;

        @Getter
        @FXML
        private Button btn5;

        @Getter
        @FXML
        private Button btn6;

        @Getter
        @FXML
        private Button btn7;

        @Getter
        @FXML
        private Button btn8;

        @Getter
        @FXML
        private Button btn9;

        @FXML
        private Button btnRestart;

        @FXML
        private Label lblTitle;


        @FXML
        private Label lblWin;

        @FXML
        private Label lblRound;


        private void currentButtonClick(Button button , int row , int col){
            if (isHumanTurn && boardImpl.isLegalMove(row, col)) {
                humanPlayer.move(row, col);
                button.setText("X");
                button.setStyle("-fx-text-fill: #90EE90; -fx-border-color: yellow; -fx-border-width: 2px; -fx-background-color: transparent;");
                isHumanTurn = false;

                boardImpl.notifyWinner();

                if (boardImpl.hasWinner()){
                    return;
                }
                if (!boardImpl.isBoardFull()) {
                    aiMove();
                }
            }else {
                showInvalidMoveAlert();
            }
        }

        private void showInvalidMoveAlert() {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Already filled! ");
            alert.showAndWait();
        }

        public void btn1OnClicked(MouseEvent mouseEvent) {
           currentButtonClick(btn1 , 0  , 0);
        }

        public void btn2OnClicked(MouseEvent mouseEvent) {
            currentButtonClick(btn2 , 0  , 1);
        }

        public void btn3OnClicked(MouseEvent mouseEvent) {
            currentButtonClick(btn3 , 0  , 2);
        }

        public void btn4OnClicked(MouseEvent mouseEvent) {
            currentButtonClick(btn4 , 1  , 0);
        }

        public void btn5OnClicked(MouseEvent mouseEvent) {
            currentButtonClick(btn5 , 1  , 1);
        }

        public void btn6OnClicked(MouseEvent mouseEvent) {
            currentButtonClick(btn6 , 1  , 2);
        }

        public void btn7OnClicked(MouseEvent mouseEvent) {
            currentButtonClick(btn7 , 2  , 0);
        }

        public void btn8OnClicked(MouseEvent mouseEvent) {
            currentButtonClick(btn8 , 2  , 1);
        }

        public void btn9OnClicked(MouseEvent mouseEvent) {
            currentButtonClick(btn9 , 2  , 2);
        }

        public void setBtnRestart(ActionEvent actionEvent) {
            round++;

               lblRound.setText("Round : " + round);

            isHumanTurn = true;
            lblWin.setText(" ");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you Sure \n Restart this game?",ButtonType.YES,ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES){
                ArrayList<Button> buttons = new ArrayList<>(Arrays.asList(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9));

                for (Button button : buttons){
                    button.setText(" ");
                    button.setDisable(false);
                }
                boardImpl.initializeBoard();
            }

        }


        private void aiMove(){
                aiPlayer.move(-1, -1);
                boardImpl.notifyWinner();
                if (!boardImpl.isBoardFull()) {
                    isHumanTurn = true;
                }
        }

        public void setLabelWinner(String winner){
            switch (winner) {
                case "Draw!!!" -> lblWin.setStyle("-fx-text-fill: #ff0000;");
                case "You win!" -> lblWin.setStyle("-fx-text-fill: #90EE90;");
                case "Ai win!" -> lblWin.setStyle("-fx-text-fill: #6e91ea;");

            }



            lblWin.setText(winner);
        }

        public Button getButtonAt(int row, int col) {
            if (row == 0 && col == 0) return btn1;
            if (row == 0 && col == 1) return btn2;
            if (row == 0 && col == 2) return btn3;
            if (row == 1 && col == 0) return btn4;
            if (row == 1 && col == 1) return btn5;
            if (row == 1 && col == 2) return btn6;
            if (row == 2 && col == 0) return btn7;
            if (row == 2 && col == 1) return btn8;
            if (row == 2 && col == 2) return btn9;
            return null;
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            lblRound.setText("Round : "+round);
            boardImpl.initializeBoard();

        }

    }
