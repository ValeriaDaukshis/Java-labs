package Controller.CommandHandlers;

import Controller.AppCommandRequest;
import Controller.ServiceCommandHandlerBase;
import Model.Book;
import Model.BookListDao;

import java.io.IOException;

public class CreateCommandHandler extends ServiceCommandHandlerBase {
    public CreateCommandHandler(BookListDao bookListDao) {
        super(bookListDao);
    }

    public void Handle(AppCommandRequest commandRequest) {
        if (commandRequest == null) {
            throw new IllegalArgumentException("commandRequest is null");
        }

        if (commandRequest.getCommand().equals("create")) {
            this.Create(commandRequest.getParameters());
        } else {
            this.NextHandler.Handle(commandRequest);
        }
    }

    private void Create(String parameters) {
        try{
            String name = PrintNameField();
            String author = PrintAuthorField();
            int pages = PrintPagesField();
            int price = PrintPriceField();
            int count = bookListDao.getStat();

            bookListDao.add(new Book(count + 1, name, author, pages, price));
            System.out.println("New book is added");
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
