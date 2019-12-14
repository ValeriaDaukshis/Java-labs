package Model.Printer;

import Model.Book;

import java.util.ArrayList;

public interface IRecordPrinter {
    void print(Book[] book);
    void print(ArrayList<Book> book);
}
