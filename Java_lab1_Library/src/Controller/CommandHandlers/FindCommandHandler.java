package Controller.CommandHandlers;

import Controller.AppCommandRequest;
import Controller.ServiceCommandHandlerBase;
import Model.Book;
import Model.BookListDao;
import Model.Printer.IRecordPrinter;
import Model.SearchClasses.FindByAuthor;
import Model.SearchClasses.FindByName;
import Model.SearchClasses.FindByPages;
import Model.SearchClasses.FindByPrice;

import java.util.ArrayList;

public class FindCommandHandler extends ServiceCommandHandlerBase {

    private IRecordPrinter printer;
    public FindCommandHandler(BookListDao bookListDao, IRecordPrinter printer)
    {
        super(bookListDao);
        this.printer = printer;
    }

    public void Handle(AppCommandRequest commandRequest) {
        if (commandRequest == null) {
            throw new IllegalArgumentException("commandRequest is null");
        }

        if (commandRequest.getCommand().equals("find")) {
            this.Find(commandRequest.getParameters());
        } else {
            this.NextHandler.Handle(commandRequest);
        }
    }

    private void Find(String parameters){
        String[] params = parameters.toLowerCase().split(" ", 3);
        boolean isBookFound = false;
        if (params[1].equals("name")) {
            ArrayList<Book> b = bookListDao.findByTag(params[2], new FindByName());
            printer.print(b);
            isBookFound = true;
        } else if (params[1].equals("author")) {
            ArrayList<Book> b = bookListDao.findByTag(params[2], new FindByAuthor());
            printer.print(b);
            isBookFound = true;
        } else if (params[1].equals("pages")) {
            ArrayList<Book> b = bookListDao.findByTag(params[2], new FindByPages());
            printer.print(b);
            isBookFound = true;
        } else if (params[1].equals("price")) {
            ArrayList<Book> b = bookListDao.findByTag(params[2], new FindByPrice());
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
