package Controller;

import Model.BookListDao;

public abstract class ServiceCommandHandlerBase extends CommandHandlerBase {

    protected BookListDao bookListDao;

    protected ServiceCommandHandlerBase(BookListDao bookListDao) {
        this.bookListDao = bookListDao;
        }

}

