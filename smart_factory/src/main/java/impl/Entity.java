package impl;

import impl.visitor.Visitor;

public interface Entity {
    void accept(Visitor visitor);
}
