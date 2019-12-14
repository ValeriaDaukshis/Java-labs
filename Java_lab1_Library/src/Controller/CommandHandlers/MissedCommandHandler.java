package Controller.CommandHandlers;

import Controller.AppCommandRequest;
import Controller.CommandHandlerBase;

public class MissedCommandHandler extends CommandHandlerBase {
    public void Handle(AppCommandRequest commandRequest) {
        if (commandRequest == null) {
            throw new IllegalArgumentException("commandRequest is null");
        }

        this.missedCommand(commandRequest.getParameters());
    }

    private void missedCommand(String parameters){
        System.out.println("No command with name "+ parameters + ".Use help.");
    }
}
