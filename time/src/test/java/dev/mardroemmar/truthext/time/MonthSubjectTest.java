package dev.mardroemmar.truthext.time;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class MonthSubjectTest {
  @Test
  void isBefore() {
    MonthSubject.assertThat(Month.MAY).isBefore(Month.OCTOBER);
    MonthSubject.assertThat(Month.JANUARY).isBefore(Month.FEBRUARY);
    MonthSubject.assertThat(Month.JANUARY).isBefore(Month.DECEMBER);
    Assertions.assertThrows(AssertionError.class,
        () -> MonthSubject.assertThat(Month.DECEMBER).isBefore(Month.JANUARY));
    Assertions.assertThrows(AssertionError.class,
        () -> MonthSubject.assertThat(Month.MAY).isBefore(Month.MAY));
  }

  @Test
  void isBeforeOrEqualTo() {
    MonthSubject.assertThat(Month.MAY).isBeforeOrEqualTo(Month.MAY);
    MonthSubject.assertThat(Month.MAY).isBeforeOrEqualTo(Month.OCTOBER);
    Assertions.assertThrows(AssertionError.class,
        () -> MonthSubject.assertThat(Month.OCTOBER).isBeforeOrEqualTo(Month.SEPTEMBER));
  }

  @Test
  void isEqualTo() {
    MonthSubject.assertThat(Month.JANUARY).isEqualTo(Month.JANUARY);
  }

  @Test
  void isAfterOrEqualTo() {
    MonthSubject.assertThat(Month.MARCH).isAfterOrEqualTo(Month.FEBRUARY);
    MonthSubject.assertThat(Month.MARCH).isAfterOrEqualTo(Month.MARCH);
    Assertions.assertThrows(AssertionError.class,
        () -> MonthSubject.assertThat(Month.MARCH).isAfterOrEqualTo(Month.APRIL));
  }

  @Test
  void isAfter() {
    MonthSubject.assertThat(Month.MARCH).isAfter(Month.FEBRUARY);
    MonthSubject.assertThat(Month.DECEMBER).isAfter(Month.JANUARY);
    Assertions.assertThrows(AssertionError.class,
        () -> MonthSubject.assertThat(Month.MARCH).isAfter(Month.APRIL));
    Assertions.assertThrows(AssertionError.class,
        () -> MonthSubject.assertThat(Month.MARCH).isAfter(Month.MARCH));
  }

  @Test
  void isSupported() {
    MonthSubject.assertThat(Month.JANUARY).isSupported(ChronoField.MONTH_OF_YEAR);
    Assertions.assertThrows(AssertionError.class,
        () -> MonthSubject.assertThat(Month.JANUARY).isSupported(ChronoField.DAY_OF_MONTH));
  }

  @Test
  void isNotSupported() {
    MonthSubject.assertThat(Month.JUNE).isNotSupported(ChronoField.DAY_OF_MONTH);
    Assertions.assertThrows(AssertionError.class,
        () -> MonthSubject.assertThat(Month.JULY).isNotSupported(ChronoField.MONTH_OF_YEAR));
  }

  @Test
  void ordinal() {
    MonthSubject.assertThat(Month.AUGUST).ordinal().isEqualTo(Month.AUGUST.ordinal());
    Assertions.assertThrows(AssertionError.class,
        () -> MonthSubject.assertThat(Month.AUGUST).ordinal().isEqualTo(Month.DECEMBER.ordinal()));
  }

  @Test
  void value() {
    MonthSubject.assertThat(Month.MARCH).value().isEqualTo(Month.MARCH.getValue());
    Assertions.assertThrows(AssertionError.class,
        () -> MonthSubject.assertThat(Month.MARCH).value().isEqualTo(Month.MAY.getValue()));
  }

  @ParameterizedTest
  @CsvSource({
      "JANUARY, 31",
      "FEBRUARY, 28",
      "MARCH, 31",
      "APRIL, 30",
      "MAY, 31",
      "JUNE, 30",
      "JULY, 31",
      "AUGUST, 31",
      "SEPTEMBER, 30",
      "OCTOBER, 31",
      "NOVEMBER, 30",
      "DECEMBER, 31",
  })
  void minLength(final Month month, final int length) {
    MonthSubject.assertThat(month).minLength().isEqualTo(length);
  }

  @ParameterizedTest
  @CsvSource({
      "JANUARY, 31",
      "FEBRUARY, 29",
      "MARCH, 31",
      "APRIL, 30",
      "MAY, 31",
      "JUNE, 30",
      "JULY, 31",
      "AUGUST, 31",
      "SEPTEMBER, 30",
      "OCTOBER, 31",
      "NOVEMBER, 30",
      "DECEMBER, 31",
  })
  void maxLength(final Month month, final int length) {
    MonthSubject.assertThat(month).maxLength().isEqualTo(length);
  }

  @ParameterizedTest
  @MethodSource("lengthSource")
  void length(final Month month, final int length, final boolean leapYear) {
    MonthSubject.assertThat(month).length(leapYear).isEqualTo(length);
  }

  static Stream<Arguments> lengthSource() {
    return Stream.concat(
        Stream
            .of(
                Stream.of(Month.JANUARY, 31),
                Stream.of(Month.MARCH, 31),
                Stream.of(Month.APRIL, 30),
                Stream.of(Month.MAY, 31),
                Stream.of(Month.JUNE, 30),
                Stream.of(Month.JULY, 31),
                Stream.of(Month.AUGUST, 31),
                Stream.of(Month.SEPTEMBER, 30),
                Stream.of(Month.OCTOBER, 31),
                Stream.of(Month.NOVEMBER, 30),
                Stream.of(Month.DECEMBER, 31)
            )
            .map(it -> it.collect(Collectors.toList()))
            .flatMap(it -> Stream.of(Stream.concat(it.stream(), Stream.of(true)), Stream.concat(it.stream(), Stream.of(false))))
            .map(it -> it.collect(Collectors.toList()))
            .map(it -> arguments(it.get(0), it.get(1), it.get(2))),
        Stream.of(
            arguments(Month.FEBRUARY, 28, false),
            arguments(Month.FEBRUARY, 29, true)
        )
    );
  }

  @Test
  void get() {
    MonthSubject.assertThat(Month.NOVEMBER).get(ChronoField.MONTH_OF_YEAR).isEqualTo(Month.NOVEMBER.getValue());
    Assertions.assertThrows(UnsupportedTemporalTypeException.class,
        () -> MonthSubject.assertThat(Month.NOVEMBER).get(ChronoField.DAY_OF_MONTH));
  }

  @Test
  void getLong() {
    MonthSubject.assertThat(Month.NOVEMBER).getLong(ChronoField.MONTH_OF_YEAR).isEqualTo(Month.NOVEMBER.getValue());
    Assertions.assertThrows(UnsupportedTemporalTypeException.class,
        () -> MonthSubject.assertThat(Month.NOVEMBER).getLong(ChronoField.DAY_OF_MONTH));
  }

  @ParameterizedTest
  @MethodSource("firstDayOfYearSource")
  void firstDayOfYear(final Month month, final int day, final boolean leapYear) {
    MonthSubject.assertThat(month).firstDayOfYear(leapYear).isEqualTo(day);
  }

  static Stream<Arguments> firstDayOfYearSource() {
    return Stream.of(
        arguments(Month.JANUARY, 1, false),
        arguments(Month.JANUARY, 1, true),
        arguments(Month.FEBRUARY, 31 + 1, false),
        arguments(Month.FEBRUARY, 31 + 1, true),
        arguments(Month.MARCH, 31 + 28 + 1, false),
        arguments(Month.MARCH, 31 + 29 + 1, true)
    );
  }

  @ParameterizedTest
  @CsvSource({
      "JANUARY, JANUARY",
      "FEBRUARY, JANUARY",
      "MARCH, JANUARY",
      "APRIL, APRIL",
      "MAY, APRIL",
      "JUNE, APRIL",
      "JULY, JULY",
      "AUGUST, JULY",
      "SEPTEMBER, JULY",
      "OCTOBER, OCTOBER",
      "NOVEMBER, OCTOBER",
      "DECEMBER, OCTOBER",
  })
  void firstMonthOfQuarter(final Month month, final Month quarterMonth) {
    MonthSubject.assertThat(month).firstMonthOfQuarter().isEqualTo(quarterMonth);
    // Shouldn't matter how many times we call it, it shouldn't return something different.
    MonthSubject.assertThat(month).firstMonthOfQuarter().firstMonthOfQuarter().isEqualTo(quarterMonth);
  }
}
