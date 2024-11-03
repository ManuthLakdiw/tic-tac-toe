package com.assignment.tictactoe.service;

public class HumanPlayer extends Player {
    public HumanPlayer(Board board) {
        super(board);
    }

    @Override
    public Piece getPiece() {
        return Piece.X;
    }

    @Override
    public void move(int row, int col) {
        board.updateMove(row,col,Piece.X);
    }
}