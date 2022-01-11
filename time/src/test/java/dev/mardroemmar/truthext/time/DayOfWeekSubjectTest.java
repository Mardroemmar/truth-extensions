package dev.mardroemmar.truthext.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DayOfWeekSubjectTest {
  @Test
  void isBefore() {
    DayOfWeekSubject.assertThat(DayOfWeek.MONDAY).isBefore(DayOfWeek.TUESDAY);
    DayOfWeekSubject.assertThat(DayOfWeek.MONDAY).isBefore(DayOfWeek.SUNDAY);
    Assertions.assertThrows(AssertionError.class,
        () -> DayOfWeekSubject.assertThat(DayOfWeek.MONDAY).isBefore(DayOfWeek.MONDAY));
  }

  @Test
  void isBeforeOrEqualTo() {
    DayOfWeekSubject.assertThat(DayOfWeek.MONDAY).isBeforeOrEqualTo(DayOfWeek.WEDNESDAY);
    DayOfWeekSubject.assertThat(DayOfWeek.MONDAY).isBeforeOrEqualTo(DayOfWeek.MONDAY);
    Assertions.assertThrows(AssertionError.class,
        () -> DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).isBeforeOrEqualTo(DayOfWeek.MONDAY));
  }

  @Test
  void isEqualTo() {
    DayOfWeekSubject.assertThat(DayOfWeek.MONDAY).isEqualTo(DayOfWeek.MONDAY);
  }

  @Test
  void isAfterOrEqualTo() {
    DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).isAfterOrEqualTo(DayOfWeek.TUESDAY);
    DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).isAfterOrEqualTo(DayOfWeek.MONDAY);
    Assertions.assertThrows(AssertionError.class,
        () -> DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).isAfterOrEqualTo(DayOfWeek.WEDNESDAY));
  }

  @Test
  void isAfter() {
    DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).isAfter(DayOfWeek.MONDAY);
    Assertions.assertThrows(AssertionError.class,
        () -> DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).isAfter(DayOfWeek.TUESDAY));
    Assertions.assertThrows(AssertionError.class,
        () -> DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).isAfter(DayOfWeek.THURSDAY));
  }

  @Test
  void isSupported() {
    DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).isSupported(ChronoField.DAY_OF_WEEK);
    Assertions.assertThrows(AssertionError.class,
        () -> DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).isSupported(ChronoField.DAY_OF_MONTH));
  }

  @Test
  void isNotSupported() {
    DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).isNotSupported(ChronoField.DAY_OF_MONTH);
    Assertions.assertThrows(AssertionError.class,
        () -> DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).isNotSupported(ChronoField.DAY_OF_WEEK));
  }

  @Test
  void ordinal() {
    DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).ordinal().isEqualTo(DayOfWeek.TUESDAY.ordinal());
    Assertions.assertThrows(AssertionError.class,
        () -> DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).ordinal().isEqualTo(DayOfWeek.MONDAY.ordinal()));
  }

  @Test
  void value() {
    DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).value().isEqualTo(DayOfWeek.TUESDAY.getValue());
    Assertions.assertThrows(AssertionError.class,
        () -> DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).value().isEqualTo(DayOfWeek.MONDAY.getValue()));
  }

  @Test
  void get() {
    DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).get(ChronoField.DAY_OF_WEEK).isEqualTo(DayOfWeek.TUESDAY.getValue());
    Assertions.assertThrows(AssertionError.class,
        () -> DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).get(ChronoField.DAY_OF_WEEK).isEqualTo(DayOfWeek.MONDAY.getValue()));
  }

  @Test
  void getLong() {
    DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).getLong(ChronoField.DAY_OF_WEEK).isEqualTo(DayOfWeek.TUESDAY.getValue());
    Assertions.assertThrows(AssertionError.class,
        () -> DayOfWeekSubject.assertThat(DayOfWeek.TUESDAY).getLong(ChronoField.DAY_OF_WEEK).isEqualTo(DayOfWeek.MONDAY.getValue()));
  }
}
