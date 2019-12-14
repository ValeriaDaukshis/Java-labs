package Controller.CommandHandlers;

import Controller.AppCommandRequest;
import Controller.ServiceCommandHandlerBase;
import Model.Printer.IRecordPrinter;
import Model.comparator.CompareByAuther;
import Model.comparator.CompareByName;
import Model.comparator.CompareByPrice;
import Model.comparator.compareByPages;
import Model.Book;
import Model.BookListDao;

public class SortCommandHandler extends ServiceCommandHandlerBase {
    private IRecordPrinter printer;
    public SortCommandHandler(BookListDao bookListDao, IRecordPrinter printer) {
        super(bookListDao);
        this.printer = printer;
    }

    public void Handle(AppCommandRequest commandRequest) {
        if (commandRequest == null) {
            throw new IllegalArgumentException("commandRequest is null");
        }

        if (commandRequest.getCommand().equals("sort")) {
            this.Sort(commandRequest.getParameters());
        } else {
            this.NextHandler.Handle(commandRequest);
        }
    }

    private void Sort(String parameters) {
        String[] params = parameters.toLowerCase().split(" ", 2);
        boolean isBookFound = false;
        Book[] b = new Book[bookListDao.getStat()];
        if (params[1].equals("name")) {
            bookListDao.sortByTag(b, new CompareByName());
            printer.print(b);
            isBookFound = true;
        } else if (params[1].equals("author")) {
            bookListDao.sortByTag(b, new CompareByAuther());
            printer.print(b);
            isBookFound = true;
        } else if (params[1].equals("pages")) {
            bookListDao.sortByTag(b, new compareByPages());
            printer.print(b);
            isBookFound = true;
        } else if (params[1].equals("price")) {
            bookListDao.sortByTag(b, new CompareByPrice());
            printer.print(b);
            isBookFound = true;
        } else {
            System.out.println(parameters + " not fount");
            isBookFound = true;
        }

        if (!isBookFound) {
            System.out.println("No books with parameter " + params[1]);
        }
    }
}
