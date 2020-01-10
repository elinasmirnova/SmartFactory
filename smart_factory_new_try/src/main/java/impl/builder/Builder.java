package impl.builder;

public interface Builder {
    void createFactory();

    void createLines();

    void createLineItems();

    void setRepairmen();

    void setDirector();

    void setInspection();

    void startTicking();
}

