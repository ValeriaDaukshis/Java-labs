package Controller.CommandHandlers;

import Controller.AppCommandRequest;
import Controller.CommandHandlerBase;

public class ExitCommandHandler extends CommandHandlerBase {

    public Boolean getRunning() {
        return isRunning;
    }

    private Boolean isRunning = true;
    public void Handle(AppCommandRequest commandRequest) {
        if (commandRequest == null) {
            throw new IllegalArgumentException("commandRequest is null");
        }

        if (commandRequest.getCommand().equals("exit")) {
            this.Exit(commandRequest.getParameters());
        } else {
            this.NextHandler.Handle(commandRequest);
        }
    }

    private void Exit(String parameters){
        System.out.println("Exiting an application...");
        isRunning = false;
    }
}
