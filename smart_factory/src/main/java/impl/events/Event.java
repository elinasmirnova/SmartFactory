package impl.events;

import javax.xml.stream.events.StartDocument;
import java.util.Date;

public class Event {

    private Date datetime;
    private String type;


    public Event(String type, Date datetime){
        this.type = type;
        this.datetime = datetime;
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

}
