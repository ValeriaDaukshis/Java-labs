package Model;

import Model.SearchClasses.IFindCriterion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BookListDao extends Book {
    private ArrayList<Book> books;

    public BookListDao() {
        books = new ArrayList<Book>();
    }

    public void ReadBooksInfoFromFile(String path) {
        books = new FileExtensions(path).ReadXmlFile();
    }

    public int getStat() {
        return books.size();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void add(Book b) {
        try {
            for (Book book : books) {
                if (book.equals(b))
                    throw new Exception("Book is already in library");
            }
            books.add(b);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeBook(Book b) {
        try {
            for (Book book : books)
                if (book.equals(b)) {
                    RewritingBooksId(b);
                    books.remove(b);
                    return;
                }
            throw new Exception("No such book in library");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Book getByIndex(int index) {
        if (index < 0 || index > books.size())
            return null;
        return books.get(index);
    }

    public ArrayList<Book> findByTag(Object value, IFindCriterion c) {
        ArrayList<Book> list = new ArrayList<>();
        try {
            for (Book b : books) {
                Object o = (Object) c.getTag(value, b).toString().toLowerCase();
                if (value.equals(o)) {
                    list.add(b);
                }
            }

            if (list.size() == 0) {
                throw new Exception("No such books");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void sortByTag(Book[] obj, Comparator c) {
        int i = 0;
        for (Book b : books) {
            obj[i] = b;
            ++i;
        }
        Arrays.sort(obj, c);
    }

    private void RewritingBooksId(Book b) {
        int removedBookId = b.getId();
        for (Book book : books) {
            if (book.getId() > removedBookId) {
                book.setId(book.getId() - 1);
            }
        }
    }
}
