package impl;

import java.util.List;

public class Factory implements Entity{
    private String name;
    private List<Line> lines;

    public Factory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Line line : lines) {
            visitor.visit(line);
        }
    }
}
