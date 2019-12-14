package Controller.CommandHandlers;

import Controller.AppCommandRequest;
import Controller.ServiceCommandHandlerBase;
import Model.Book;
import Model.BookListDao;

public class RemoveCommandHandler extends ServiceCommandHandlerBase {
    public RemoveCommandHandler(BookListDao bookListDao) {
        super(bookListDao);
    }

    public void Handle(AppCommandRequest commandRequest) {
        if (commandRequest == null) {
            throw new IllegalArgumentException("commandRequest is null");
        }

        if (commandRequest.getCommand().equals("remove")) {
            this.Create(commandRequest.getParameters());
        } else {
            this.NextHandler.Handle(commandRequest);
        }
    }

    private void Create(String parameters) {
        int Id = 0;
        Book book = null;
        if (parameters.contains(" ")) {
            try {
                Id = Integer.parseInt(parameters.split(" ", 2)[1]);
                book = bookListDao.getByIndex(Id - 1);
            } catch (NumberFormatException e) {
                return;
            }
        } else {
            System.out.println("No book index found");
            return;
        }
        bookListDao.removeBook(book);
        System.out.println("Book with id" + Id + "was removed");
    }
}
