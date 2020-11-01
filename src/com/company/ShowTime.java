package com.company;


import java.util.Scanner;
import java.util.Set;

public class ShowTime implements Runnable {
    private static final Object lock = new Object();
    private Set<Seat> freeSeats;

    /**
     * Конструктор сеанса кино
     *
     * @param s Все доступные места для покупателей
     */
    public ShowTime(Set<Seat> s) {
        freeSeats = s;
    }

    /**
     * Функция получения всех свободных мест
     *
     * @return Набор мест, доступных для бронирования
     */
    public Set<Seat> getFreeSeats() {
        return freeSeats;
    }

    /**
     * Вывод на экран свободных для брони мест
     *
     * @param fs Множество свободных мест
     */
    public void printFreeSeats(Set<Seat> fs) {
        System.out.println("Available seats:");
        for (Seat s :
                fs) {
            System.out.println("* " + s.toString());
        }
    }

    /**
     * Функция бронирования места на сеанс
     *
     * @param seat Желаемое место для бронирования
     * @return true, если место успешно забронировано; false, если бронь не удалась
     */
    public boolean bookSeat(Seat seat) {
        for (Seat freeSeat :
                freeSeats) {
            if (seat.getRow() == freeSeat.getRow() && seat.getSeat() == freeSeat.getSeat()) {
                freeSeats.remove(freeSeat);
                return true;
            }
        }
        return false;
    }

    /**
     * Функция получения от пользователя места для брони
     *
     * @return Место, желаемое пользователем
     */
    private Seat whatToBook() {
        System.out.print("What seat? ");
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int seat = in.nextInt();
        return new Seat(row, seat);
    }

    @Override
    public void run() {
        while (!freeSeats.isEmpty()) {
            synchronized (lock) {
                printFreeSeats(getFreeSeats());
                Seat seatToBook = whatToBook();
                if (bookSeat(seatToBook)) {
                    System.out.println(seatToBook.toString() + " is BOOKED for YOU. Pay " + seatToBook.getSumm() + "$");
                    break;
                } else {
                    System.out.println("Sorry, " + seatToBook.toString() + " has ALREADY been booked.");
                }
            }
        }
    }
}
