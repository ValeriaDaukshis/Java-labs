package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class CommandHandlerBase implements ICommandHandler{
    protected ICommandHandler NextHandler;

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public abstract void Handle(AppCommandRequest commandRequest);

    public void SetNext(ICommandHandler commandHandler)
    {
        this.NextHandler = commandHandler;
    }

    public int CheckIntInput() throws IOException {
        boolean success = false;
        int number = 0;
        do {
            String value = reader.readLine();
            if (value.length() == 0 || value == null)
                continue;
            try {
                number = Integer.parseInt(value);
                success = true;
            } catch (NumberFormatException e) {
                continue;
            }

        } while (!success);

        return number;
    }

    protected String PrintNameField() throws IOException {
        System.out.print("Name: ");
        String name = reader.readLine();
        return name;
    }

    protected String PrintAuthorField() throws IOException {
        System.out.print("Author: ");
        String author = reader.readLine();
        return author;
    }

    protected int PrintPriceField() throws IOException {
        System.out.print("Price: ");
        int price = CheckIntInput();
        return price;
    }

    protected int PrintPagesField() throws IOException {
        System.out.print("Pages: ");
        int pages = CheckIntInput();
        return pages;
    }
}
