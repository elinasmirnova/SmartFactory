package impl.events;

import java.util.Date;

public class BreakdownEvent extends Event {
    public BreakdownEvent(String type, Date datetime) {
        super(type, datetime);
    }
}
