package Unit9;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Stream;

public class StudentTest{
    public static List<Student> filter(List<Student> students, Filter<Student> pred){
        List list = new ArrayList<>();
        for (Student student:students){
            if (pred.valid(student)) list.add(student);
        }
        return list;
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(23, "Tran Van A"));
        students.add(new Student(34, "Tran Van B"));
        students.add(new Student(15, "Tran Van C"));
        students.add(new Student(46, "Tran Van AD"));

//        Filter<Student> older = student -> student.getAge()>=30;
//        List<Student> firered = filter(students, older);
//
//        for (Student student :
//                firered) {
//            System.out.println(student);
//        }

//        firered.forEach(student-> System.out.println(student));
//        Stream<Student>stream = students.stream().filter(student->student.getAge()>=30);
//        strean.forEach(student->println(student));

        Collections.sort(students, (Student student1, Student student2)->student1.getAge()-student2.getAge());
        students.forEach(student -> System.out.println(student));


        Comparator<Student>comparator=(student1,student2)->student1.getAge()-student2.getAge();
        Stream<Student>stream=students.stream().sorted(comparator);
        stream.forEach(student -> System.out.println(student));
        System.out.println("\nMax is"+students.stream().max(comparator));

        int sum = students.stream().mapToInt(Student::getAge).sum();
//        int sum = students.stream().mapToInt(s->{
//            return
//        }).sum();
        System.out.println("Average of age is " + sum/students.size());

        students.parallelStream().forEach((it)-> System.out.println(Thread.currentThread().getName()));

    }
}
