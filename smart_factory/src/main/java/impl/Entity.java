package impl;

public interface Entity {
    void accept(Visitor visitor);
}
