package dev.mardroemmar.truthext.time;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ZonedDateTimeSubjectTest {
  @Test
  void isSameInstantAs() {
    final Instant now = Instant.now();
    ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC)).isSameInstantAs(now); // Instant overload
    // ChronoZonedDateTime overload:
    ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC)).isSameInstantAs(now.atZone(ZoneOffset.UTC));
    ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC)).isSameInstantAs(now.atZone(ZoneOffset.MAX));
    ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC)).isSameInstantAs(now.atZone(ZoneOffset.MIN));
    Assertions.assertThrows(AssertionError.class,
        () -> ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC)).isSameInstantAs(Instant.now()));
    Assertions.assertThrows(AssertionError.class,
        () -> ZonedDateTimeSubject.assertThat(ZonedDateTime.now()).isSameInstantAs(now.atZone(ZoneOffset.UTC)));
  }

  @Test
  void isNotSameInstantAs() {
    final Instant now = Instant.now();
    ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC)).isNotSameInstantAs(now.plusSeconds(1L)); // Instant overload
    // ChronoZonedDateTime overload:
    ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC)).isNotSameInstantAs(now.plusSeconds(1L).atZone(ZoneOffset.UTC));
    ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC))
        .isNotSameInstantAs(now.plusSeconds(1L).atZone(ZoneOffset.ofTotalSeconds(-1)));
    Assertions.assertThrows(AssertionError.class,
        () -> ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC)).isNotSameInstantAs(now.atZone(ZoneOffset.UTC)));
    Assertions.assertThrows(AssertionError.class,
        () -> ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC)).isNotSameInstantAs(now.atZone(ZoneOffset.MIN)));
    Assertions.assertThrows(AssertionError.class,
        () -> ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC)).isNotSameInstantAs(now.atZone(ZoneOffset.MAX)));
  }

  @Test
  void isSameLocalAs() {
    final Instant now = Instant.now();
    ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC)).isSameLocalAs(now.plus(1, ChronoUnit.HOURS).atZone(ZoneOffset.ofHours(-1)));
    Assertions.assertThrows(AssertionError.class,
        () -> ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC))
            .isSameLocalAs(now.plus(1, ChronoUnit.HOURS).atZone(ZoneOffset.UTC)));
    Assertions.assertThrows(AssertionError.class,
        () -> ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC)).isSameLocalAs(ZonedDateTime.now()));
  }

  @Test
  void isNotSameLocalAs() {
    final Instant now = Instant.now();
    ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC)).isNotSameLocalAs(now.atZone(ZoneOffset.ofHours(1)));
    ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC)).isNotSameLocalAs(now.atZone(ZoneOffset.ofTotalSeconds(-1)));
    Assertions.assertThrows(AssertionError.class,
        () -> ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC))
            .isNotSameLocalAs(now.atZone(ZoneOffset.UTC)));
  }

  @Test
  void instant() {
    final Instant now = Instant.now();
    ZonedDateTimeSubject.assertThat(now.atZone(ZoneOffset.UTC)).instant().isEqualTo(now);
    Assertions.assertThrows(AssertionError.class,
        () -> ZonedDateTimeSubject.assertThat(ZonedDateTime.now()).instant().isEqualTo(now));
  }

  @Test
  void withZoneSameInstant() {
    final ZonedDateTime now = ZonedDateTime.now();
    ZonedDateTimeSubject.assertThat(now).withZoneSameInstant(ZoneOffset.MIN).isSameInstantAs(now);
    ZonedDateTimeSubject.assertThat(now).withZoneSameInstant(ZoneOffset.MAX).isSameInstantAs(now);
    ZonedDateTimeSubject.assertThat(now).withZoneSameInstant(ZoneOffset.UTC).isSameInstantAs(now);
    Assertions.assertThrows(AssertionError.class,
        () -> ZonedDateTimeSubject.assertThat(now).withZoneSameInstant(ZoneOffset.MIN).isSameInstantAs(ZonedDateTime.now()));
  }

  @Test
  void withZoneSameLocal() {
    final ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
    ZonedDateTimeSubject.assertThat(now).withZoneSameLocal(ZoneOffset.MIN)
        .isSameInstantAs(now.minusSeconds(ZoneOffset.MIN.getTotalSeconds()));
    ZonedDateTimeSubject.assertThat(now).withZoneSameLocal(ZoneOffset.MIN).isSameLocalAs(now);
    ZonedDateTimeSubject.assertThat(now).withZoneSameLocal(ZoneOffset.MAX)
        .isSameInstantAs(now.minusSeconds(ZoneOffset.MAX.getTotalSeconds()));
    ZonedDateTimeSubject.assertThat(now).withZoneSameLocal(ZoneOffset.MAX).isSameLocalAs(now);
    ZonedDateTimeSubject.assertThat(now).withZoneSameLocal(ZoneOffset.UTC).isSameInstantAs(now);
    ZonedDateTimeSubject.assertThat(now).withZoneSameLocal(ZoneOffset.UTC).isSameLocalAs(now);
    Assertions.assertThrows(AssertionError.class,
        () -> ZonedDateTimeSubject.assertThat(now).withZoneSameLocal(ZoneOffset.MIN).isSameInstantAs(now));
    Assertions.assertThrows(AssertionError.class,
        () -> ZonedDateTimeSubject.assertThat(now).withZoneSameLocal(ZoneOffset.MAX).isSameInstantAs(now));
  }

  @Test
  void dayOfYear() {
    final ZonedDateTime momentsBeforeDisaster = ZonedDateTime.of(
        LocalDate.of(1986, Month.APRIL, 26),
        LocalTime.of(1, 23, 45, 678910),
        ZoneOffset.ofHours(4)
    );
    ZonedDateTimeSubject.assertThat(momentsBeforeDisaster).dayOfYear().isEqualTo(31 + 28 + 31 + 26);
  }

  @Test
  void dayOfMonth() {
    final ZonedDateTime disastrousDay = ZonedDateTime.of(
        LocalDate.of(1986, Month.FEBRUARY, 28),
        LocalTime.of(23, 50, 0),
        ZoneOffset.ofHours(1)
    );
    ZonedDateTimeSubject.assertThat(disastrousDay).dayOfMonth().isEqualTo(28);
  }

  @Test
  void dayOfWeek() {
    final ZonedDateTime allMondaysAreDisasters = ZonedDateTime.of(
        LocalDate.of(1945, Month.AUGUST, 6),
        LocalTime.of(8, 15, 45),
        ZoneOffset.ofHours(9)
    );
    ZonedDateTimeSubject.assertThat(allMondaysAreDisasters).dayOfWeek().isEqualTo(DayOfWeek.MONDAY);
  }

  @Test
  void hour() {
    final ZonedDateTime allMondaysAreDisasters = ZonedDateTime.of(
        LocalDate.of(1945, Month.AUGUST, 6),
        LocalTime.of(8, 15, 45),
        ZoneOffset.ofHours(9)
    );
    ZonedDateTimeSubject.assertThat(allMondaysAreDisasters).hour().isEqualTo(8);
  }

  @Test
  void minute() {
    final ZonedDateTime allMondaysAreDisasters = ZonedDateTime.of(
        LocalDate.of(1945, Month.AUGUST, 6),
        LocalTime.of(8, 15, 45),
        ZoneOffset.ofHours(9)
    );
    ZonedDateTimeSubject.assertThat(allMondaysAreDisasters).minute().isEqualTo(15);
  }

  @Test
  void second() {
    final ZonedDateTime allMondaysAreDisasters = ZonedDateTime.of(
        LocalDate.of(1945, Month.AUGUST, 6),
        LocalTime.of(8, 15, 45),
        ZoneOffset.ofHours(9)
    );
    ZonedDateTimeSubject.assertThat(allMondaysAreDisasters).second().isEqualTo(45);
  }

  @Test
  void nano() {
    final ZonedDateTime allMondaysAreDisasters = ZonedDateTime.of(
        LocalDate.of(1945, Month.AUGUST, 6),
        LocalTime.of(8, 15, 45, 123),
        ZoneOffset.ofHours(9)
    );
    ZonedDateTimeSubject.assertThat(allMondaysAreDisasters).nano().isEqualTo(123);
  }

  @Test
  void epochMilli() {
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
        .epochMilli().isEqualTo(0);
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 999_999, ZoneOffset.UTC))
        .epochMilli().isEqualTo(0);
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 1_000_000, ZoneOffset.UTC))
        .epochMilli().isEqualTo(1);
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 1, 1, 0, 0, 1, 0, ZoneOffset.UTC))
        .epochMilli().isEqualTo(1_000);
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1971, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
        .epochMilli().isEqualTo(TimeUnit.DAYS.toMillis(365));
  }

  @Test
  void epochSecond() {
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
        .epochSecond().isEqualTo(0);
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 999_999_999, ZoneOffset.UTC))
        .epochSecond().isEqualTo(0);
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 1, 1, 0, 0, 1, 0, ZoneOffset.UTC))
        .epochSecond().isEqualTo(1);
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 1, 1, 0, 1, 0, 0, ZoneOffset.UTC))
        .epochSecond().isEqualTo(60);
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1971, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
        .epochSecond().isEqualTo(TimeUnit.DAYS.toSeconds(365));
  }

  @Test
  void epochDay() {
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
        .epochDay().isEqualTo(0);
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 1, 1, 23, 59, 59, 999_999_999, ZoneOffset.UTC))
        .epochDay().isEqualTo(0);
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 1, 2, 0, 0, 0, 0, ZoneOffset.UTC))
        .epochDay().isEqualTo(1);
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1971, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
        .epochDay().isEqualTo(365);
  }

  @Test
  void monthValue() {
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
        .monthValue().isEqualTo(1);
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 2, 1, 0, 0, 0, 0, ZoneOffset.UTC))
        .monthValue().isEqualTo(2);
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 12, 1, 0, 0, 0, 0, ZoneOffset.UTC))
        .monthValue().isEqualTo(12);
  }

  @Test
  void month() {
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
        .month().isEqualTo(Month.JANUARY);
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 2, 1, 0, 0, 0, 0, ZoneOffset.UTC))
        .month().isEqualTo(Month.FEBRUARY);
    ZonedDateTimeSubject.assertThat(ZonedDateTime.of(1970, 12, 1, 0, 0, 0, 0, ZoneOffset.UTC))
        .month().isEqualTo(Month.DECEMBER);
  }
}
