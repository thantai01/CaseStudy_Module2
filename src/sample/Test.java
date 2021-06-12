package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import sample.Model.Expense;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
//        String s1 = "01/01/1993 19:39";
//        String[] split1 = s1.split("/");
//        System.out.println(Arrays.toString(split1));
        tes1("01/03/2021","02/04/2021");
        tes1("01/06/2021","05/01/2021");
    }

    static void tes1(String local1, String local2) {
        LocalDate localDate1 = LocalDate.parse(local1,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate localDate2 = LocalDate.parse(local2,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (localDate1.compareTo(localDate2) >= 0) {
            System.out.println("true");
        } else
            System.out.println("false");
    }
}
