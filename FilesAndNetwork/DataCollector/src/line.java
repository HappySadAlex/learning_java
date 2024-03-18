import com.google.gson.annotations.SerializedName;
import java.util.List;


public class line {

    @SerializedName("number")
    public String numbOfLine;
    @SerializedName("name")
    public String nameOfLine;
    public List<String> stationsOnLine;

    public line(String nameOfLine, String numbOfLine) {
        this.numbOfLine = numbOfLine;
        this.nameOfLine = nameOfLine;
    }

    public void setStationsOnLine(List<String> stations){
        this.stationsOnLine = stations;
    }

    public line(String nameOfLine) {
        this.nameOfLine = nameOfLine;
    }

    public String getNumbOfLine() {
        return numbOfLine;
    }

    public void setNumbOfLine(String numbOfLine) {
        this.numbOfLine = numbOfLine;
    }

    public String getNameOfLine() {
        return nameOfLine;
    }

    public void setNameOfLine(String nameOfLine) {
        this.nameOfLine = nameOfLine;
    }
    public String toString(){
        return nameOfLine + " - " + numbOfLine;
    }
}
