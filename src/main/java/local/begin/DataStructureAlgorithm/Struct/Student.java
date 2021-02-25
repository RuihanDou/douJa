package local.begin.DataStructureAlgorithm.Struct;

public class Student implements Comparable<Student> {

    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // 参数声明使用 Object 与 Object父类保持一致
    @Override
    public boolean equals(Object student) {

        if(this == student) {
            return true;
        }

        if(null == student) {
            return false;
        }

        if(this.getClass() != student.getClass()){
            return false;
        }

        Student another = (Student) student;
        return this.name.toLowerCase().equals(another.name.toLowerCase());
    }


    @Override
    public int compareTo(Student another) {
        return another.score - this.score;
    }

    @Override
    public String toString(){
        return String.format("Student(name : %s , score : %d)", name, score);
    }
}
