package impl.events;

import impl.lineItems.Machine;

import javax.xml.stream.events.StartDocument;
import java.util.Date;

public class Event {

    private Date datetime;
    private String type;
    private Machine machine;

    public Event(String type, Date datetime, Machine machine){
        this.type = type;
        this.datetime = datetime;
        this.machine = machine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDatetime() {
        return datetime;
    }

    public String toString() {
        return "Event type: " + type + ". Time created: " + datetime;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
