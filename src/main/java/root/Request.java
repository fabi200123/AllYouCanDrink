package root;

import org.dizitart.no2.objects.Id;

public class Request {
    @Id
    private String name;

    public Request(String name) {
        this.name = name;
    }

    public Request() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
