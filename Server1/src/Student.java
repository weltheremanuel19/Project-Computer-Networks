public class Student implements java.io.Serializable {
    String name;
    int age;
    int mark;
    public Student(String name, int age, int mark){
        this.name = name;
        this.age =age;
        this.mark = mark;
    }
    public int getAge(){
        return this.age;
    }
    public String getName(){
        return this.name;
    }
}
