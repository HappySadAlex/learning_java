package parsingJsonCsv;

import java.util.Locale;

public class Root {


    private String name;
    private String data; // может быть как глубина, так и дата открытия

    public Root() {

    }

    public Root(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", data= " + data;
    }
}
