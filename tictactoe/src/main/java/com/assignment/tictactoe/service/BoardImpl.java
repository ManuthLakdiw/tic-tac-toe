package com.assignment.tictactoe.service;

import javafx.application.Platform;
import javafx.scene.control.Button;


import java.util.ArrayList;
import java.util.Arrays;


public class BoardImpl implements Board,BoardUI {
    private final BoardController boardController;
    public static Piece[][] pieces;
    BoardUI boardUI;


    public Winner checkColWin() {
        for (int i = 0; i < 3; i++) {
            if (pieces[0][i] != Piece.EMPTY && pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i]) {
                return new Winner(pieces[0][i], 0, i, 1, i, 2, i);
            }
        }
        return null;
    }

    public Winner checkRowWin() {
        for (int i = 0; i < 3; i++) {
            if (pieces[i][0] != Piece.EMPTY && pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2]) {
                return new Winner(pieces[i][0], i, 0, i, 1, i, 2);  // Row win
            }
        }
        return null;
    }

    public Winner checkDiagonalWin() {
        if (pieces[0][0] != Piece.EMPTY && pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2]) {
            return new Winner(pieces[0][0], 0, 0, 1, 1, 2, 2);
        }
        if (pieces[0][2] != Piece.EMPTY && pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0]) {
            return new Winner(pieces[0][2], 0, 2, 1, 1, 2, 0);
        }
        return null;
    }

    public BoardImpl(BoardController boardController) {
        this.boardController = boardController;
        initializeBoard();
    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public void initializeBoard() {
        pieces = new Piece[3][3];
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
        printBoard();

    }

    @Override
    public boolean isLegalMove(int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            return pieces[row][col] == Piece.EMPTY;
        }
        return false;
    }

    @Override
    public void updateMove(int row, int col, Piece piece) {
        if (isLegalMove(row, col)) {
            pieces[row][col] = piece;
        } else {
            System.out.println("Illegal move attempted.");
        }
    }

    @Override
    public Winner checkWinner() {
      Winner winner;

      winner = checkColWin();
      if (winner != null) {
          return winner;
      }

      winner = checkRowWin();
      if (winner != null) {
          return winner;
      }

      winner = checkDiagonalWin();
      if (winner != null) {
          return winner;
      }
      return null;
    }

    @Override
    public void printBoard() {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                System.out.print("|      ");
                System.out.print(pieces[i][j] + "     ");
            }
            System.out.println();

        }
        System.out.println("\n");
    }

    @Override
    public void update(int col, int row, boolean isHuman) {

    }

    @Override
    public  void notifyWinner() {
        Winner winner = checkWinner();
        if (winner != null) {
            if (winner.winningPiece == Piece.X) {
                Platform.runLater(() -> {
                    boardController.setLabelWinner("You win!");
                    System.out.println("You win!");
                    printBoard();

                });
                buttonSetDisable();



            }else if (winner.winningPiece == Piece.O) {
                Platform.runLater(() -> {
                    boardController.setLabelWinner("Ai win!");
                    System.out.println("Ai win!");
                    printBoard();
                });
                buttonSetDisable();
            }

        }else {
            if (isBoardFull()) {
                Platform.runLater(() -> {
                    boardController.setLabelWinner("Draw!!!");
                    System.out.println("Draw!!!");
                    printBoard();
                });
                buttonSetDisable();
            }
        }
    }

    public boolean hasWinner() {
        Winner winner = checkWinner();
        if (winner != null) {
            return true;
        }
        return false;
    }

    public boolean isBoardFull() {
            for (int i = 0; i < pieces.length; i++) {
                for (int j = 0; j < pieces[i].length; j++) {
                    if (pieces[i][j] == Piece.EMPTY) {
                        return false;
                    }
                }
            }
            return true;
    }

    public  void buttonSetDisable(){
        ArrayList<Button>  buttons = new ArrayList<>(Arrays.asList(boardController.getBtn1(),boardController.getBtn2(),
                boardController.getBtn3(),boardController.getBtn4(),boardController.getBtn5(),boardController.getBtn6(),
                boardController.getBtn7(),boardController.getBtn8(),boardController.getBtn9()));
        for (Button button : buttons) {
            button.setDisable(true);
        }
    }
}
