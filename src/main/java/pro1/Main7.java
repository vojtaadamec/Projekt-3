package pro1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import pro1.apiDataModel.Specialization;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main7 {
    public static void main(String[] args) {
        System.out.println(specializationDeadlines(2025));
    }
    public static String specializationDeadlines(int year) {
        String json = Api.getSpecializations(year);
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray dataArray = jsonObject.getAsJsonArray("prijimaciObor");
        List<Specialization> list = gson.fromJson(dataArray, new TypeToken<List<Specialization>>(){}.getType());


        return list.stream()
                .map(s -> s.eprDeadlinePrihlaska)
                .filter(d -> d != null && d.value != null && !d.value.isEmpty())
                .map(d -> d.value)
                .sorted(Comparator.comparing(d ->
                        LocalDate.parse(d, DateTimeFormatter.ofPattern("d.M.yyyy"))
                ))
                .distinct()
                .collect(Collectors.joining(","));
    }
}
