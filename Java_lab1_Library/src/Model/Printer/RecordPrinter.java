package Model.Printer;

import Model.Book;

import java.util.ArrayList;

public class RecordPrinter implements IRecordPrinter {
    @Override
    public void print(Book[] book) {
        if (book == null) {
            return;
        }
        for (Book a : book) {
            System.out.println("Id: " + a.getId());
            System.out.println("\tName: " + a.getName());
            System.out.println("\tAuthor: " + a.getAuthor());
            System.out.println("\tPages: " + a.getPages());
            System.out.println("\tPrice: " + a.getPrice());
        }
    }

    public void print(ArrayList<Book> book) {
        if (book == null) {
            return;
        }
        for (Book a : book) {
            System.out.println("Id: " + a.getId());
            System.out.println("\tName: " + a.getName());
            System.out.println("\tAuthor: " + a.getAuthor());
            System.out.println("\tPages: " + a.getPages());
            System.out.println("\tPrice: " + a.getPrice());
        }
    }
}
