import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Zad1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Map<String, List<Double>>> ocenyUczniow = new HashMap<>();

        System.out.println("Ile przedmiotów?");
        int liczbaPrzedmiotow = scanner.nextInt();
        scanner.nextLine();

        List<String> przedmioty = new ArrayList<>();
        for (int i = 0; i < liczbaPrzedmiotow; i++) {
            System.out.println("Nazwa przedmiotu " + (i + 1) + ":");
            przedmioty.add(scanner.nextLine());
        }

        System.out.println("Ile uczniów?");
        int liczbaUczniow = scanner.nextInt();
        scanner.nextLine();

        for (int j = 0; j < liczbaUczniow; j++) {
            System.out.println("Podaj imię ucznia " + (j + 1) + ":");
            String imieUcznia = scanner.nextLine();
            Map<String, List<Double>> oceny = new HashMap<>();

            for (String przedmiot : przedmioty) {
                System.out.println("Podawaj oceny z " + przedmiot + " dla " + imieUcznia + " (wpisz -1, aby zakończyć):");
                List<Double> ocenyPrzedmiotu = new ArrayList<>();
                double ocena;
                while (scanner.hasNextDouble() && (ocena = scanner.nextDouble()) != -1) {               
                    ocenyPrzedmiotu.add(ocena);     
                }
                scanner.nextLine();
                oceny.put(przedmiot, ocenyPrzedmiotu);
            }

            ocenyUczniow.put(imieUcznia, oceny);
        }

        for (Map.Entry<String, Map<String, List<Double>>> uczen : ocenyUczniow.entrySet()) {
            System.out.println("Uczeń: " + uczen.getKey());
            for (Map.Entry<String, List<Double>> przedmiot : uczen.getValue().entrySet()) {
                double suma = przedmiot.getValue().stream().mapToDouble(Double::doubleValue).sum();
                double srednia = suma / przedmiot.getValue().size();
                System.out.println("Średnia z " + przedmiot.getKey() + ": " + srednia);
            }
        }
    }
}
