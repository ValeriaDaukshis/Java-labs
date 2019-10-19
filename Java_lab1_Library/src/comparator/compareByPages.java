package comparator;

import util.Book;

import java.util.Comparator;

public class compareByPages implements Comparator<Book> {
    public int compare(Book b1, Book b2){
        Integer pages1 = b1.getPages();
        Integer pages2 = b2.getPages();
        return pages1.compareTo(pages2);
    }
}
