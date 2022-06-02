public class Line implements java.io.Serializable{
    String lineNumber;
    public Line(String lineNumber){
        this.lineNumber = lineNumber;
    }
    public String getLineNumber()
    {
        return this.lineNumber;
    }
}
