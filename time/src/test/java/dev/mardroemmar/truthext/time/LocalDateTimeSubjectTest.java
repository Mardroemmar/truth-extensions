package dev.mardroemmar.truthext.time;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.chrono.ThaiBuddhistDate;
import org.junit.jupiter.api.Test;

class LocalDateTimeSubjectTest {
  @Test
  void isSameLocalTimeAs() {
    final LocalDateTime actual = LocalDateTime.ofEpochSecond(5, 0, ZoneOffset.UTC);
    LocalDateTimeSubject.assertThat(actual).isSameLocalTimeAs(actual);
    LocalDateTimeSubject.assertThat(actual)
        // The Thai Buddhist calendar starts at around 543 BC. Therefore, Gregorian 1970 (the Epoch year), should be Thai year 2484.
        .isSameLocalTimeAs(ThaiBuddhistDate.of(1970 + 543, 1, 1).atTime(LocalTime.ofSecondOfDay(5)));
    assertThrows(AssertionError.class, () -> LocalDateTimeSubject.assertThat(actual).isSameLocalTimeAs(actual.plusNanos(1L)));
    assertThrows(AssertionError.class, () -> LocalDateTimeSubject.assertThat(actual).isSameLocalTimeAs(actual.minusNanos(1L)));
  }

  @Test
  void isNotSameLocalTimeAs() {
    final LocalDateTime actual = LocalDateTime.ofEpochSecond(5, 0, ZoneOffset.UTC);
    LocalDateTimeSubject.assertThat(actual).isNotSameLocalTimeAs(actual.plusNanos(1L));
    LocalDateTimeSubject.assertThat(actual).isNotSameLocalTimeAs(actual.minusNanos(1L));
    LocalDateTimeSubject.assertThat(actual)
        // The Thai Buddhist calendar starts at around 543 BC. Therefore, Gregorian 1970 (the Epoch year), should be Thai year 2484.
        .isNotSameLocalTimeAs(ThaiBuddhistDate.of(1970 + 543, 1, 1).atTime(LocalTime.ofSecondOfDay(6)));
    assertThrows(AssertionError.class, () -> LocalDateTimeSubject.assertThat(actual).isNotSameLocalTimeAs(actual));
  }

  @Test
  void isComparativelyEqualTo() {
    final LocalDateTime actual = LocalDateTime.ofEpochSecond(5, 0, ZoneOffset.UTC);
    LocalDateTimeSubject.assertThat(actual).isComparativelyEqualTo(actual);
    assertThrows(AssertionError.class, () -> LocalDateTimeSubject.assertThat(actual)
        // The Thai Buddhist calendar starts at around 543 BC. Therefore, Gregorian 1970 (the Epoch year), should be Thai year 2484.
        .isComparativelyEqualTo(ThaiBuddhistDate.of(1970 + 543, 1, 1).atTime(LocalTime.ofSecondOfDay(5))));
    assertThrows(AssertionError.class, () -> LocalDateTimeSubject.assertThat(actual).isComparativelyEqualTo(actual.plusNanos(1L)));
    assertThrows(AssertionError.class, () -> LocalDateTimeSubject.assertThat(actual).isComparativelyEqualTo(actual.minusNanos(1L)));
  }

  @Test
  void isComparativelyNotEqualTo() {
    final LocalDateTime actual = LocalDateTime.ofEpochSecond(5, 0, ZoneOffset.UTC);
    LocalDateTimeSubject.assertThat(actual)
        // The Thai Buddhist calendar starts at around 543 BC. Therefore, Gregorian 1970 (the Epoch year), should be Thai year 2484.
        .isComparativelyNotEqualTo(ThaiBuddhistDate.of(1970 + 543, 1, 1).atTime(LocalTime.ofSecondOfDay(5)));
    LocalDateTimeSubject.assertThat(actual).isComparativelyNotEqualTo(actual.plusNanos(1L));
    LocalDateTimeSubject.assertThat(actual).isComparativelyNotEqualTo(actual.minusNanos(1L));
    assertThrows(AssertionError.class, () -> LocalDateTimeSubject.assertThat(actual).isComparativelyNotEqualTo(actual));
  }

  @Test
  void instant() {
    final LocalDateTime actual = LocalDateTime.ofEpochSecond(5, 0, ZoneOffset.UTC);
    LocalDateTimeSubject.assertThat(actual).instant(ZoneOffset.UTC)
        .isEqualTo(Instant.ofEpochSecond(5));
  }

  @Test
  void zoned() {
    final LocalDateTime actual = LocalDateTime.ofEpochSecond(5, 0, ZoneOffset.UTC);
    LocalDateTimeSubject.assertThat(actual).zoned(ZoneOffset.UTC)
        .isSameInstantAs(Instant.ofEpochSecond(5));
  }

  @Test
  void dayOfYear() {
    final LocalDateTime actual = LocalDateTime.ofEpochSecond(5, 0, ZoneOffset.UTC);
    LocalDateTimeSubject.assertThat(actual).dayOfYear().isEqualTo(1);
    LocalDateTimeSubject.assertThat(actual.plusDays(30L)).dayOfYear().isEqualTo(31L);
  }

  @Test
  void dayOfMonth() {
    final LocalDateTime actual = LocalDateTime.ofEpochSecond(5, 0, ZoneOffset.UTC);
    LocalDateTimeSubject.assertThat(actual).dayOfMonth().isEqualTo(1);
    LocalDateTimeSubject.assertThat(actual.plusDays(30L)).dayOfMonth().isEqualTo(31L);
    LocalDateTimeSubject.assertThat(actual.plusDays(31L)).dayOfMonth().isEqualTo(1L);
  }

  @Test
  void hour() {
    final LocalDateTime actual = LocalDateTime.ofEpochSecond(5, 0, ZoneOffset.UTC);
    LocalDateTimeSubject.assertThat(actual).hour().isEqualTo(0);
    LocalDateTimeSubject.assertThat(actual.plusHours(1L)).hour().isEqualTo(1);
    LocalDateTimeSubject.assertThat(actual.plusDays(1L)).hour().isEqualTo(0);
  }

  @Test
  void minute() {
    final LocalDateTime actual = LocalDateTime.ofEpochSecond(5, 0, ZoneOffset.UTC);
    LocalDateTimeSubject.assertThat(actual).minute().isEqualTo(0);
    LocalDateTimeSubject.assertThat(actual.plusSeconds(54L)).minute().isEqualTo(0);
    LocalDateTimeSubject.assertThat(actual.plusSeconds(55L)).minute().isEqualTo(1);
    LocalDateTimeSubject.assertThat(actual.plusMinutes(1L)).minute().isEqualTo(1);
    LocalDateTimeSubject.assertThat(actual.plusHours(1L)).minute().isEqualTo(0);
    LocalDateTimeSubject.assertThat(actual.plusDays(1L)).minute().isEqualTo(0);
  }

  @Test
  void second() {
    final LocalDateTime actual = LocalDateTime.ofEpochSecond(5, 0, ZoneOffset.UTC);
    LocalDateTimeSubject.assertThat(actual).second().isEqualTo(5);
    LocalDateTimeSubject.assertThat(actual.plusSeconds(54L)).second().isEqualTo(59);
    LocalDateTimeSubject.assertThat(actual.plusSeconds(55L)).second().isEqualTo(0);
    LocalDateTimeSubject.assertThat(actual.plusMinutes(1L)).second().isEqualTo(5);
    LocalDateTimeSubject.assertThat(actual.plusHours(1L)).second().isEqualTo(5);
    LocalDateTimeSubject.assertThat(actual.plusDays(1L)).second().isEqualTo(5);
  }
}
