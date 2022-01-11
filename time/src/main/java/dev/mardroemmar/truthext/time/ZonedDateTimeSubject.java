package dev.mardroemmar.truthext.time;

import static com.google.common.truth.Fact.fact;
import static com.google.common.truth.Fact.simpleFact;
import static com.google.common.truth.Truth.assertAbout;
import static dev.mardroemmar.truthext.time.DayOfWeekSubject.daysOfWeek;
import static dev.mardroemmar.truthext.time.InstantSubject.instants;
import static dev.mardroemmar.truthext.time.MonthSubject.months;

import com.google.common.truth.ComparableSubject;
import com.google.common.truth.FailureMetadata;
import com.google.common.truth.IntegerSubject;
import com.google.common.truth.LongSubject;
import com.google.common.truth.Subject;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Objects;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.common.value.qual.IntRange;

/**
 * A {@link com.google.common.truth.Truth Truth} subject for {@link ZonedDateTime}.
 *
 * @see Subject
 * @see ComparableSubject
 * @since 0.3.0
 */
public class ZonedDateTimeSubject extends ComparableSubject<ZonedDateTime> {
  /**
   * Assert upon a zoned date-time.
   *
   * @param actual the actual zoned date-time
   * @return a new assertion subject
   */
  public static ZonedDateTimeSubject assertThat(final @Nullable ZonedDateTime actual) {
    return assertAbout(zonedDateTimes()).that(actual);
  }

  /**
   * @return a subject factory for asserting about {@link ZonedDateTime zoned date-times}.
   */
  public static Factory<ZonedDateTimeSubject, ZonedDateTime> zonedDateTimes() {
    return ZonedDateTimeSubject::new;
  }

  private final @Nullable ZonedDateTime actual;

  private ZonedDateTimeSubject(final FailureMetadata metadata, final @Nullable ZonedDateTime actual) {
    super(metadata, actual);
    this.actual = actual;
  }

  /**
   * Asserts that the {@code actual} is at the same instant in time as the {@code other}.
   *
   * @param other the other time to compare to
   * @throws AssertionError       if the {@code actual} is null
   * @throws AssertionError       if the {@code actual} is not at the same instant in time as the {@code other}
   * @throws NullPointerException if the {@code other} is null
   */
  public void isSameInstantAs(final ChronoZonedDateTime<?> other) {
    Objects.requireNonNull(other, "other must not be null");
    if (!this.nonNull().isEqual(other)) {
      this.failWithActual(simpleFact("expected actual to have same instant in time as other"), fact("other", other));
    }
  }

  /**
   * Asserts that the {@code actual} is at the same instant in time as the {@code other}.
   *
   * @param other the other time to compare to
   * @throws AssertionError       if the {@code actual} is null
   * @throws AssertionError       if the {@code actual} is not at the same instant in time as the {@code other}
   * @throws NullPointerException if the {@code other} is null
   */
  public void isSameInstantAs(final Instant other) {
    Objects.requireNonNull(other, "other must not be null");
    if (!this.nonNull().toInstant().equals(other)) {
      this.failWithActual(simpleFact("expected actual to have same instant in time as other"), fact("other", other));
    }
  }

  /**
   * Asserts that the {@code actual} is not at the same instant in time as the {@code other}.
   *
   * @param other the other time to compare to
   * @throws AssertionError       if the {@code actual} is null
   * @throws AssertionError       if the {@code actual} is at the same instant in time as the {@code other}
   * @throws NullPointerException if the {@code other} is null
   */
  public void isNotSameInstantAs(final ChronoZonedDateTime<?> other) {
    Objects.requireNonNull(other, "other must not be null");
    if (this.nonNull().isEqual(other)) {
      this.failWithActual(simpleFact("expected actual to have different instant in time as other"), fact("other", other));
    }
  }

  /**
   * Asserts that the {@code actual} is not at the same instant in time as the {@code other}.
   *
   * @param other the other time to compare to
   * @throws AssertionError       if the {@code actual} is null
   * @throws AssertionError       if the {@code actual} is at the same instant in time as the {@code other}
   * @throws NullPointerException if the {@code other} is null
   */
  public void isNotSameInstantAs(final Instant other) {
    Objects.requireNonNull(other, "other must not be null");
    if (this.nonNull().toInstant().equals(other)) {
      this.failWithActual(simpleFact("expected actual to have different instant in time as other"), fact("other", other));
    }
  }

  /**
   * Asserts that the {@code actual} is at the same local time as the {@code other}.
   *
   * @param other the other time to compare to
   * @throws AssertionError       if the {@code actual} is null
   * @throws AssertionError       if the {@code actual} is not at the same local time as the {@code other}
   * @throws NullPointerException if the {@code other} is null
   */
  public void isSameLocalAs(final ChronoZonedDateTime<?> other) {
    Objects.requireNonNull(other, "other must not be null");
    if (!this.nonNull().toLocalDateTime().isEqual(other.toLocalDateTime())) {
      this.failWithActual(simpleFact("expected actual to have same local as other"), fact("other", other));
    }
  }

  /**
   * Asserts that the {@code actual} is not at the same local time as the {@code other}.
   *
   * @param other the other time to compare to
   * @throws AssertionError       if the {@code actual} is null
   * @throws AssertionError       if the {@code actual} is at the same local time as the {@code other}
   * @throws NullPointerException if the {@code other} is null
   */
  public void isNotSameLocalAs(final ChronoZonedDateTime<?> other) {
    Objects.requireNonNull(other, "other must not be null");
    if (this.nonNull().toLocalDateTime().isEqual(other.toLocalDateTime())) {
      this.failWithActual(simpleFact("expected actual to have different local as other"), fact("other", other));
    }
  }

  /**
   * Assert further on the {@link Instant} of this time-date.
   *
   * @return a subject about the instant in time of this time-date
   * @throws AssertionError if the actual is {@code null}
   * @see ZonedDateTime#toInstant()
   */
  public InstantSubject instant() {
    return this.check("toInstant()").about(instants()).that(this.nonNull().toInstant());
  }

  /**
   * Assert further on the {@link ZonedDateTime} of this time-date at another time-zone (but the same instant in time).
   *
   * @return a subject about the zoned date-time at another time-zone
   * @throws AssertionError if the actual is {@code null}
   * @see ZonedDateTime#withZoneSameInstant(ZoneId)
   */
  public ZonedDateTimeSubject withZoneSameInstant(final ZoneId zoneId) {
    return this.check("withZoneSameInstant(%s)", zoneId)
        .about(zonedDateTimes())
        .that(this.nonNull().withZoneSameInstant(zoneId));
  }

  /**
   * Assert further on the {@link ZonedDateTime} of this time-date at the local time of another time-zone.
   * <p>
   * This means that if a zone 1 hour ahead is given, the instant in time will be moved 1 hour ahead, but reading the clock will yield the
   * same result as if you had moved to that other time-zone when checking the time again.
   *
   * @return a subject about the zoned date-time at another instant in time and time-zone
   * @throws AssertionError if the actual is {@code null}
   * @see ZonedDateTime#withZoneSameLocal(ZoneId)
   */
  public ZonedDateTimeSubject withZoneSameLocal(final ZoneId zoneId) {
    return this.check("withZoneSameLocal(%s)", zoneId)
        .about(zonedDateTimes())
        .that(this.nonNull().withZoneSameLocal(zoneId));
  }

  /**
   * Assert further on the day of the year.
   *
   * @return a subject about the day of the year
   * @throws AssertionError if the actual is {@code null}
   * @see ZonedDateTime#getDayOfYear()
   */
  public @IntRange(from = 1, to = 366) IntegerSubject dayOfYear() {
    return this.check("getDayOfYear()").that(this.nonNull().getDayOfYear());
  }

  /**
   * Assert further on the day of the month.
   *
   * @return a subject about the day of the month
   * @throws AssertionError if the actual is {@code null}
   * @see ZonedDateTime#getDayOfMonth()
   */
  public IntegerSubject dayOfMonth() {
    return this.check("getDayOfMonth()").that(this.nonNull().getDayOfMonth());
  }

  /**
   * Assert further on the hour of the day.
   *
   * @return a subject about the hour of the day
   * @throws AssertionError if the actual is {@code null}
   * @see ZonedDateTime#getHour()
   */
  public @IntRange(from = 0, to = 23) IntegerSubject hour() {
    return this.check("getHour()").that(this.nonNull().getHour());
  }

  /**
   * Assert further on the minute of the hour.
   *
   * @return a subject about the minute of the hour
   * @throws AssertionError if the actual is {@code null}
   * @see ZonedDateTime#getMinute()
   */
  public @IntRange(from = 0, to = 59) IntegerSubject minute() {
    return this.check("getMinute()").that(this.nonNull().getMinute());
  }

  /**
   * Assert further on the second of the minute.
   *
   * @return a subject about the second of the minute
   * @throws AssertionError if the actual is {@code null}
   * @see ZonedDateTime#getSecond()
   */
  public @IntRange(from = 0, to = 59) IntegerSubject second() {
    return this.check("getSecond()").that(this.nonNull().getSecond());
  }

  /**
   * Assert further on the nano of the second.
   *
   * @return a subject about the nano of the second
   * @throws AssertionError if the actual is {@code null}
   * @see ZonedDateTime#getNano()
   */
  public @IntRange(from = 0, to = 999_999_999) IntegerSubject nano() {
    return this.check("getNano()").that(this.nonNull().getNano());
  }

  /**
   * Assert further on the milliseconds from Epoch.
   *
   * @return a subject about the milliseconds from Epoch
   * @throws AssertionError      if the actual is {@code null}
   * @throws ArithmeticException if the current instant in time is too large to fit in a range from {@code 0} to {@link Long#MAX_VALUE}
   * @see Instant#toEpochMilli()
   * @see InstantSubject#epochMilli()
   */
  public @NonNegative LongSubject epochMilli() {
    return this.instant().epochMilli();
  }

  /**
   * Assert further on the seconds from Epoch.
   *
   * @return a subject about the seconds from Epoch
   * @throws AssertionError if the actual is {@code null}
   * @see Instant#getEpochSecond()
   * @see InstantSubject#epochSecond()
   */
  public @NonNegative LongSubject epochSecond() {
    return this.instant().epochSecond();
  }

  /**
   * Assert further on the days from Epoch.
   *
   * @return a subject about the days from Epoch
   * @throws AssertionError if the actual is {@code null}
   * @see InstantSubject#epochDay()
   */
  public @NonNegative LongSubject epochDay() {
    return this.instant().epochDay();
  }

  /**
   * Assert further on the value of the month.
   *
   * @return a subject about the month value
   * @throws AssertionError if the actual is {@code null}
   * @see ZonedDateTime#getMonthValue()
   */
  public @IntRange(from = 1, to = 12) IntegerSubject monthValue() {
    return this.check("getMonthValue()").that(this.nonNull().getMonthValue());
  }

  /**
   * Assert further on the month.
   *
   * @return a subject about the month
   * @throws AssertionError if the actual is {@code null}
   * @see ZonedDateTime#getMonth()
   */
  public MonthSubject month() {
    return this.check("getMonth()").about(months()).that(this.nonNull().getMonth());
  }

  /**
   * Assert further on the day.
   *
   * @return a subject about the day
   * @throws AssertionError if the actual is {@code null}
   * @see ZonedDateTime#getDayOfWeek()
   */
  public DayOfWeekSubject dayOfWeek() {
    return this.check("getDayOfWeek()").about(daysOfWeek()).that(this.nonNull().getDayOfWeek());
  }

  private ZonedDateTime nonNull() {
    if (this.actual != null) {
      return this.actual;
    }

    failWithActual(simpleFact("expected zoned date time to be non-null"));
    throw new AssertionError("unreachable");
  }
}
