package es.upm.miw.iwvg_devops.code;

import org.apache.logging.log4j.LogManager;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

public class Searches {
    //Esto te devuelve los apellidos, y en el caso de que haya alguna persona repetida solo te devuelve uno
    public Stream<String> findUserFamilyNameByUserNameDistinct(String userName) {
        return new UsersDatabase().findAll()
                .filter(user -> userName.equals(user.getName()))
                .map(User::getFamilyName)
                .distinct();
    }

    public Stream<Integer> findFractionNumeratorByUserFamilyName(String userFamilyName) {
        return new UsersDatabase().findAll()  //cojo todos los datos del dataset
                .filter(user -> userFamilyName.equals(user.getFamilyName())) //filtro por nombre
                .flatMap(user -> user.getFractions().stream())   //Me hace un stram de las fracciones
                .map(Fraction::getNumerator); //Funcion de transformacion, paso una clase Fraction y me devuelve un getNumertar
    }

    public Stream<String> findUserFamilyNameByFractionDenominator(int fractionDenominator) {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream().anyMatch(fraction -> fractionDenominator == fraction.getDenominator()))
                .map(User::getFamilyName);
    }

    public Stream<String> findUserNameInitialByAnyProperFraction(int fractionDenominator) {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream().anyMatch(fraction -> fraction.getNumerator()<fraction.getDenominator()))
                .map(User::getName);

    }
    public Stream<String> findUserIdByAnyProperFraction(int fractionDenominator) {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream().anyMatch(fraction -> fraction.getNumerator()<fraction.getDenominator()))
                .map(User::getId);
    }


    public Stream<Double> findFirstDecimalFractionByUserName(String name) {
        return new UsersDatabase().findAll()
                .filter(user->name.equals(user.getName()))
                .flatMap(user -> user.getFractions().stream())
                .map(Fraction::decimal)
                ;
    }


    public Stream<Double> findDecimalImproperFractionByUserName(String name) {
        return Stream.empty();
    }


    public Stream<String> findUserFamilyNameByImproperFraction(int fractionDenominator) {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream().anyMatch(fraction -> fraction.getNumerator()>fraction.getDenominator()))
                .map(User::getFamilyName)
                .distinct();
    }


    public Stream<String> findUserFamilyNameInitialByAnyProperFraction(int fractionDenominator) {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream().anyMatch(fraction -> fraction.getNumerator()<fraction.getDenominator()))
                .map(User::getFamilyName)
                .distinct();
    }

    //Encuentra la primera division por user id, osea devuelves el valor de la primera division dependiendo del Id
    //.reduce((fraction1, fraction2) -> fraction1.divide(fraction2))
    //.reduce((fraction1, fraction2) -> fraction1.revalue((fraction1.getNumerator() * fraction2.getDenominator()), (fraction1.getDenominator() * fraction2.getNumerator())))
    public Fraction findFirstFractionDivisionByUserId(String id) {
        return new UsersDatabase().findAll()
                .filter(user -> id.equals(user.getId()))
                .map(User::getFractions)
                .flatMap(Collection::stream)
                .reduce(Fraction::divide)
                .orElse(null);

    }


    public Stream<String> findUserFamilyNameByAllNegativeSignFractionDistinct() {
        return Stream.empty();

    }

    public Stream<String> findUserNameByAnyImproperFraction(int denominator) {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream().anyMatch(fraction -> fraction.getNumerator()>fraction.getDenominator()))
                .map(User::getFamilyName)
                .distinct();
    }


    public Stream<Double> findDecimalFractionByNegativeSignFraction(int fractionDenominator) {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream()
                        .anyMatch(fraction -> fraction.decimal() < 0))
                .flatMap(user -> user.getFractions().stream())
                .filter(fraction->fraction.decimal()<0)
                .map(Fraction::decimal);


    }

    public Fraction findHighestFraction() {
        return new UsersDatabase().findAll()
                .flatMap(user -> user.getFractions().stream())
                .max(Comparator.comparingDouble(Fraction::decimal)).get();
    }

}
