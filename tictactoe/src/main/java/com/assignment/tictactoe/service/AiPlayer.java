package com.assignment.tictactoe.service;

import javafx.application.Platform;
import javafx.scene.control.Button;

import java.util.Random;

public class
AiPlayer extends Player {
    private final BoardController boardController;

    private Random random;

    AiPlayer(Board board, BoardController boardController) {
        super(board);
        this.boardController = boardController;
        random = new Random();
    }

    @Override
    public Piece getPiece() {
        return Piece.O;
    }

    @Override
    public void move(int row, int col) {
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        }while (!board.isLegalMove(row, col));
        board.updateMove(row,col,Piece.O);

        int finalRow = row;
        int finalCol = col;

        Button button = boardController.getButtonAt(finalRow, finalCol);
        button.setText("O");
        button.setStyle("-fx-text-fill: #6e91ea; -fx-border-color: yellow; -fx-border-width: 2px; -fx-background-color: transparent;");


    }

}



//private int minimax(int depth, boolean isMaximizing) {
//    Winner winner = board.checkWinner();
//    if (winner != null) {
//        return winner.getWinningPiece() == Piece.O ? 1 : -1;
//    }
//    if (board.isFull()) {
//        return 0;
//    }
//
//    if (isMaximizing) {
//        int bestScore = Integer.MIN_VALUE;
//        for (int row = 0; row < 3; row++) {
//            for (int col = 0; col < 3; col++) {
//                if (board.isLegalMove(row, col)) {
//                    board.updateMove(row, col, Piece.O);
//                    int score = minimax(depth + 1, false);
//                    board.updateMove(row, col, Piece.EMPTY); // Undo move
//                    bestScore = Math.max(score, bestScore);
//                }
//            }
//        }
//        return bestScore;
//    } else {
//        int bestScore = Integer.MAX_VALUE;
//        for (int row = 0; row < 3; row++) {
//            for (int col = 0; col < 3; col++) {
//                if (board.isLegalMove(row, col)) {
//                    board.updateMove(row, col, Piece.X);
//                    int score = minimax(depth + 1, true);
//                    board.updateMove(row, col, Piece.EMPTY); // Undo move
//                    bestScore = Math.min(score, bestScore);
//                }
//            }
//        }
//        return bestScore;
//    }
//}


