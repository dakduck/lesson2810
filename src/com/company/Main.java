package com.company;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Seat seat11 = new Seat(1,1);
        Seat seat12 = new Seat(1,2);
        Seat seat13 = new Seat(1,3);
        Seat seat21 = new Seat(2,1);
        Seat seat22 = new Seat(2,2);

        Set<Seat> seats1 = new HashSet<>();
        seats1.add(seat11);
        seats1.add(seat12);
        seats1.add(seat13);
        seats1.add(seat21);
        seats1.add(seat22);

        Thread t1 = new Thread(new ShowTime(seats1));
        Thread t2 = new Thread(new ShowTime(seats1));

        t1.start();
        t2.start();
    }
}
