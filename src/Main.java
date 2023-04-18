import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long minors = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();

        List<String> conscripts = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> (x.getAge() >= 18 && x.getAge() <= 36))
                .map(Person::getFamily)
                .collect(Collectors.toList());

        List<Person> worker = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getSex() == Sex.WOMAN ? x.getAge() >= 18 && x.getAge() <= 60 : x.getAge() >= 18 && x.getAge() <= 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

//        System.out.println("Несовершеннолетних: " + minors + "\n");
//        System.out.println("Призывники: " + conscripts);
//        System.out.println("\n");
//        System.out.println("Работоспособные: " + worker);
    }
}