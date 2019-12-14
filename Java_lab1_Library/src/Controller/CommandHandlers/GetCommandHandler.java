package Controller.CommandHandlers;

import Controller.AppCommandRequest;
import Controller.ServiceCommandHandlerBase;
import Model.Book;
import Model.BookListDao;
import Model.Printer.IRecordPrinter;

import java.util.ArrayList;

public class GetCommandHandler extends ServiceCommandHandlerBase {
    private IRecordPrinter printer;
    public GetCommandHandler(BookListDao bookListDao, IRecordPrinter printer)
    {
        super(bookListDao);
        this.printer = printer;
    }

    public void Handle(AppCommandRequest commandRequest) {
        if (commandRequest == null) {
            throw new IllegalArgumentException("commandRequest is null");
        }

        if (commandRequest.getCommand().equals("get")) {
            this.get(commandRequest.getParameters());
        } else {
            this.NextHandler.Handle(commandRequest);
        }
    }

    private void get(String parameters){
        ArrayList<Book> b = bookListDao.getBooks();
        printer.print(b);
    }
}
