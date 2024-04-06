import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Zad5 {
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
                    if (ocena >= 0 && ocena <= 6) {
                        ocenyPrzedmiotu.add(ocena);
                    } else {
                        System.out.println("Ocena poza zakresem! Wprowadź ocenę od 0 do 6.");
                    }
                }
                scanner.nextLine();
                oceny.put(przedmiot, ocenyPrzedmiotu);
            }

            ocenyUczniow.put(imieUcznia, oceny);
        }

        for (Map.Entry<String, Map<String, List<Double>>> uczen : ocenyUczniow.entrySet()) {
            System.out.println("Uczeń: " + uczen.getKey());

            double sumaWszystkichOcen = 0;
            int liczbaWszystkichOcen = 0;

            for (Map.Entry<String, List<Double>> przedmiot : uczen.getValue().entrySet()) {
                System.out.print("Oceny z " + przedmiot.getKey() + ": ");
                double suma = 0;

                for (double ocena : przedmiot.getValue()) {
                    suma += ocena;
                    System.out.print(ocena + " ");
                }

                double srednia = suma / przedmiot.getValue().size();
                double mediana = obliczMediane(przedmiot.getValue());
                
                System.out.println("\nSuma ocen z " + przedmiot.getKey() + ": " + suma);
                System.out.println("Średnia ocen z " + przedmiot.getKey() + ": " + srednia);
                System.out.println("Mediana ocen z " + przedmiot.getKey() + ": " + mediana);

                sumaWszystkichOcen += suma;
                liczbaWszystkichOcen += przedmiot.getValue().size();
            }

            double sredniaWszystkichOcen = sumaWszystkichOcen / liczbaWszystkichOcen;
            System.out.println("Średnia wszystkich ocen ucznia " + uczen.getKey() + ": " + sredniaWszystkichOcen);
        }
    }

    private static double obliczMediane(List<Double> oceny) {
        if (oceny.isEmpty()) {
            return 0;
        }
        List<Double> posortowaneOceny = new ArrayList<>(oceny);
        Collections.sort(posortowaneOceny);
        int srodek = posortowaneOceny.size() / 2;
        if (posortowaneOceny.size() % 2 == 0) {
            return (posortowaneOceny.get(srodek - 1) + posortowaneOceny.get(srodek)) / 2.0;
        } else {
            return posortowaneOceny.get(srodek);
        }
    }
}
