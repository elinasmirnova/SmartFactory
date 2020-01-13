package impl;

import impl.visitor.Visitor;

/**
 * Interface for the visitor design pattern.
 */
public interface Entity {
    void accept(Visitor visitor);
}
