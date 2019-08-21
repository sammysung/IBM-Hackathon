public class Student{
    private String id = "";
    private String first = "";
    private String last= "";

    public void Student(){

    }
    public void setID(String id){
        this.id = id;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last){
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast(){
        return last;
    }

    public String getId(){
        return id;
    }

}