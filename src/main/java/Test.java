import org.apache.commons.collections4.CollectionUtils;

import java.nio.channels.Pipe;
import java.util.*;

public class Test {

    private static int method(){
        int i = 1;

        try {
            throw new RuntimeException();
        }
        catch (RuntimeException e) {
            return 5;
        }
        finally {
             return i+1;
        }
    }
    public static void main(String[] args) {
        System.out.println(method());

        String a1 = "Hello";
        String a2 = "Hel";
        String s3 = a2 + "lo";
        final String a3 = "Hello";
        System.out.println(a1 ==  s3);
        System.out.println(a1 == a3);

        a2 = "fe";
        int size = Optional.ofNullable(a2.length()).orElse(0);

        final Street street = getStreet(getHouse(HouseType.PRIVATE, 1));
        final Address address = getAddress(street);
        Student student = new Student();

        List<Student> students = new ArrayList<>();


        Address length = CollectionUtils.emptyIfNull(students).stream()
                .map(Student::getAddress)
                .findFirst().orElse(new Address());

        System.out.println(length);

        Optional<Address> length2 = Optional.ofNullable(students)
                .map(s1 -> s1.stream()
                        .map(Student::getAddress)
                            .findFirst()
                ).orElse(Optional.of(new Address()));

        System.out.println(length2);

        Address addressa = Optional.ofNullable(student)
                .map(Student::getAddress)
                .orElse(new Address(getStreet(getHouse(HouseType.PRIVATE, 12))));
        System.out.println(addressa);



    }

    private static Street getStreet(House house) {
        return new Street(house);
    }

    private static House getHouse(HouseType type, int number) {
        return new House(type, number);
    }
    private static Address getAddress(Street street) {
        return new Address(street);
    }


}
