package Controller;

public class AppCommandRequest {

    public AppCommandRequest(String Command, String Parameters){
        this.Command = Command;
        this.Parameters = Parameters;
    }
    private String Command;

    private String Parameters;

    public String getCommand() {
        return Command;
    }

    public void setCommand(String command) {
        Command = command;
    }

    public String getParameters() {
        return Parameters;
    }

    public void setParameters(String parameters) {
        Parameters = parameters;
    }
}
