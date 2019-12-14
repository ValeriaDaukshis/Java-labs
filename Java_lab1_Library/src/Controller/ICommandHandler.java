package Controller;

public interface ICommandHandler {
    void SetNext(ICommandHandler commandHandler);
    void Handle(AppCommandRequest commandRequest);
}
