package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        // файл кинозала с доступными местами (0), забронированными (1) и недоступными (2)
        File f = new File("seats.txt");
        int rows, seats;
        try (Scanner in = new Scanner(f)) {
            // кол-во рядов задается в файле первым числом
            rows = in.nextInt();
            // кол-во мест в ряду задается в файле вторым числом
            seats = in.nextInt();
            // весь кинозал
            int[][] hall = new int[rows][seats];
            // свободные места в зале в виде (ряд, место)
            Set<Seat> freeSeats = new HashSet<>();
            // недоступные или забронированные места в зале в виде (ряд, место)
            Set<Seat> bookedSeats = new HashSet<>();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < seats; j++) {
                    hall[i][j] = in.nextInt();
                    if (hall[i][j] == 0) {
                        freeSeats.add(new Seat(i, j));
                    } else {
                        bookedSeats.add(new Seat(i, j));
                    }
                }
            }

            Thread t1 = new Thread(new ShowTime(freeSeats, rows, seats, bookedSeats));
            Thread t2 = new Thread(new ShowTime(freeSeats, rows, seats, bookedSeats));

            t1.start();
            t2.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

