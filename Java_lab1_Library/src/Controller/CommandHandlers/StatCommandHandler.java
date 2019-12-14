package Controller.CommandHandlers;

import Controller.AppCommandRequest;
import Controller.ServiceCommandHandlerBase;
import Model.BookListDao;

public class StatCommandHandler extends ServiceCommandHandlerBase {
    public StatCommandHandler(BookListDao bookListDao) {
        super(bookListDao);
    }

    public void Handle(AppCommandRequest commandRequest) {
        if (commandRequest == null) {
            throw new IllegalArgumentException("commandRequest is null");
        }

        if (commandRequest.getCommand().equals("stat")) {
            this.Stat(commandRequest.getParameters());
        } else {
            this.NextHandler.Handle(commandRequest);
        }
    }

    private void Stat(String parameters) {
        int countOfBooks = bookListDao.getStat();
        System.out.println("Count of books: " + countOfBooks);
    }
}
