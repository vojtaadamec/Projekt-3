package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.Teacher;
import pro1.apiDataModel.TeachersList;

import java.util.Comparator;
import java.util.List;

public class Main4 {

    public static void main(String[] args) {
         printShortestEmails("KIKM",5);
    }

    public static void printShortestEmails(String department, int count)
    {
        String json2 = Api.getTeachersByDepartment(department);
        TeachersList teachers = new Gson().fromJson(json2, TeachersList.class);
        List<Teacher> result = teachers.items.stream()
                .filter(t -> t.email != null)
                .sorted(Comparator.comparing(t -> t.email.length()))
                .limit(count).toList();
        for(Teacher t : result)
            System.out.println(t.email);
        // TODO 4.1: Vypiš do konzole "count" nejkratších učitelských emailových adres
    }
}