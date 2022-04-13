package dev.mardroemmar.truthext.time;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A {@link com.google.common.truth.Truth Truth}-like class for {@code java.time} values.
 *
 * @since 0.3.0
 */
@API(status = Status.MAINTAINED)
public final class TruthTime {
  private TruthTime() {
  }

  /**
   * Assert upon a {@link DayOfWeek}. Alias for {@link DayOfWeekSubject#assertThat(DayOfWeek)}.
   *
   * @param actual the actual to assert upon
   * @return a new subject for asserting
   * @see DayOfWeekSubject#assertThat(DayOfWeek)
   */
  public static DayOfWeekSubject assertThat(final @Nullable DayOfWeek actual) {
    return DayOfWeekSubject.assertThat(actual);
  }

  /**
   * Assert upon a {@link Instant}. Alias for {@link InstantSubject#assertThat(Instant)}.
   *
   * @param actual the actual to assert upon
   * @return a new subject for asserting
   * @see InstantSubject#assertThat(Instant)
   */
  public static InstantSubject assertThat(final @Nullable Instant actual) {
    return InstantSubject.assertThat(actual);
  }

  /**
   * Assert upon a {@link Month}. Alias for {@link MonthSubject#assertThat(Month)}.
   *
   * @param actual the actual to assert upon
   * @return a new subject for asserting
   * @see MonthSubject#assertThat(Month)
   */
  public static MonthSubject assertThat(final @Nullable Month actual) {
    return MonthSubject.assertThat(actual);
  }

  /**
   * Assert upon a {@link ZonedDateTime}. Alias for {@link ZonedDateTimeSubject#assertThat(ZonedDateTime)}.
   *
   * @param actual the actual to assert upon
   * @return a new subject for asserting
   * @see ZonedDateTimeSubject#assertThat(ZonedDateTime)
   */
  public static ZonedDateTimeSubject assertThat(final @Nullable ZonedDateTime actual) {
    return ZonedDateTimeSubject.assertThat(actual);
  }

  /**
   * Assert upon a {@link LocalDateTime}. Alias for {@link LocalDateTimeSubject#assertThat(LocalDateTime)}.
   *
   * @param actual the actual to assert upon
   * @return a new subject for asserting
   * @see LocalDateTimeSubject#assertThat(LocalDateTime)
   */
  public static LocalDateTimeSubject assertThat(final @Nullable LocalDateTime actual) {
    return LocalDateTimeSubject.assertThat(actual);
  }
}
