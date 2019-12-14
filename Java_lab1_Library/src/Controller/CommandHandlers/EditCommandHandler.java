package Controller.CommandHandlers;

import Controller.AppCommandRequest;
import Controller.ServiceCommandHandlerBase;
import Model.Book;
import Model.BookListDao;
import java.io.IOException;

public class EditCommandHandler extends ServiceCommandHandlerBase{
    public EditCommandHandler(BookListDao bookListDao)
    {
        super(bookListDao);
    }

    public void Handle(AppCommandRequest commandRequest) {
        if (commandRequest == null) {
            throw new IllegalArgumentException("commandRequest is null");
        }

        if (commandRequest.getCommand().equals("edit")) {
            try{
                this.Edit(commandRequest.getParameters());
            }catch (IOException ex){
                System.out.println(ex.getMessage());
            }

        } else {
            this.NextHandler.Handle(commandRequest);
        }
    }

    private void Edit(String parameters) throws IOException {
        int Id = 0;
        Book book = null;
        if (parameters.contains(" ")) {
            try {
                Id = Integer.parseInt(parameters.split(" ", 2)[1]);
                book = bookListDao.getByIndex(Id - 1);
            } catch (NumberFormatException e) {
                return;
            }

            if (book == null) {
                System.out.println("No book with Id " + Id + " found");
                return;
            }
        } else {
            System.out.println("No book index found");
            return;
        }

        System.out.print("Pages: ");
        int pages = CheckIntInput();
        System.out.print("Price: ");
        int price = CheckIntInput();
        book.setPages(pages);
        book.setPrice(price);

        System.out.println("Book " + Id + " is successfully updated");
    }
}
