package impl.builder;

/**
 * Builder interface for factory configuration.
 */
public interface Builder {
    void createFactory();

    void createLines();

    void createLineItems();

    void setRepairmen();

    void startTicking();
}

