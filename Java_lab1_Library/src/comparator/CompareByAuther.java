package comparator;

import util.Book;

import java.util.Comparator;

public class CompareByAuther implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getAuthor().compareTo(b2.getAuthor());
    }
}
