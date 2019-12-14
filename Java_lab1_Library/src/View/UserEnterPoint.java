package View;

import Controller.AppCommandRequest;
import Controller.CommandHandlers.*;
import Controller.ICommandHandler;
import Model.*;
import Model.Printer.IRecordPrinter;
import Model.Printer.RecordPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserEnterPoint {

    private String path = "C:\\Users\\dauks\\Java-labs\\Java_lab1_Library\\books.xml";
    private boolean isRunning;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BookListDao list;
    private static ExitCommandHandler exitCommand;

    public UserEnterPoint() {
        list = new BookListDao();
    }

    private static String[] helpMessages = new String[]
            {
                    "The 'help' command prints the help screen.",
                    "The 'exit' command exits the application.",
                    "The 'stat' command prints the record statistics.",
                    "The 'create' command creates a new record.",
                    "The 'get' command get all record.",
                    "The 'edit' command edit record by id.",
                    "The 'find' command find record by name/author/pages/price.",
                    "The 'remove' command remove book by id.",
                    "The 'sort' command sort books by name/author/pages/price.."
            };

    public void UserCommands() throws IOException {
        list.ReadBooksInfoFromFile(path);
        ICommandHandler commandHandler = CreateCommandHandlers();
        this.isRunning = true;
        do {
            System.out.print("> ");
            String params = reader.readLine();
            String inputs = params;
            if (inputs.contains(" ")) {
                inputs = inputs.split(" ")[0];
            }

            String command = inputs;
            String parameters = params;

            commandHandler.Handle(new AppCommandRequest(command, parameters));
            this.isRunning = exitCommand.getRunning();
        }
        while (isRunning);
    }

    private static ICommandHandler CreateCommandHandlers()
    {
        IRecordPrinter printer = new RecordPrinter();

        HelpCommandHandler helpCommand = new HelpCommandHandler(helpMessages);
        CreateCommandHandler createCommand = new CreateCommandHandler(list);
        RemoveCommandHandler removeCommand = new RemoveCommandHandler(list);
        StatCommandHandler statCommand = new StatCommandHandler(list);
        SortCommandHandler sortCommand = new SortCommandHandler(list, printer);
        EditCommandHandler editCommand = new EditCommandHandler(list);
        FindCommandHandler findCommand = new FindCommandHandler(list, printer);
        GetCommandHandler getCommand = new GetCommandHandler(list, printer);
        exitCommand = new ExitCommandHandler();
        MissedCommandHandler missedCommand = new MissedCommandHandler();

        helpCommand.SetNext(createCommand);
        createCommand.SetNext(removeCommand);
        removeCommand.SetNext(statCommand);
        statCommand.SetNext(sortCommand);
        sortCommand.SetNext(editCommand);
        editCommand.SetNext(findCommand);
        findCommand.SetNext(getCommand);
        getCommand.SetNext(exitCommand);
        exitCommand.SetNext(missedCommand);

        return helpCommand;
    }
}
