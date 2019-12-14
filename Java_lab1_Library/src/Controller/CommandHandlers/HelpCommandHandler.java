package Controller.CommandHandlers;

import Controller.AppCommandRequest;
import Controller.CommandHandlerBase;

public class HelpCommandHandler extends CommandHandlerBase {
    private String[] helpMessages;
    public HelpCommandHandler(String[] help)
    {
        this.helpMessages = help;
    }

    public void Handle(AppCommandRequest commandRequest) {
        if (commandRequest == null) {
            throw new IllegalArgumentException("commandRequest is null");
        }

        if (commandRequest.getCommand().equals("help")) {
            this.PrintHelp(commandRequest.getParameters());
        } else {
            this.NextHandler.Handle(commandRequest);
        }
    }

    private void PrintHelp(String parameters){
        for (int i = 0; i < helpMessages.length; i++)
            System.out.println(helpMessages[i]);
    }
}
