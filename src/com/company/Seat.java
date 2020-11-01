package com.company;

public class Seat {
    /**
     * Номер ряда
     */
    private final int row;
    /**
     * Номер места
     */
    private final int seat;
    /**
     * Стоимость места
     */
    private final int summ;


    public Seat(int row, int seat) {
        this.row = row;
        this.seat = seat;
        this.summ = row*seat;
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public int getSumm() {
        return summ;
    }

    @Override
    public String toString() {
        return "Seat (" +
                row +
                ' ' + seat +
                ')';
    }
}
