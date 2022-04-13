package dev.mardroemmar.truthext.time;

import static com.google.common.truth.Fact.fact;
import static com.google.common.truth.Fact.simpleFact;
import static com.google.common.truth.Truth.assertAbout;
import static dev.mardroemmar.truthext.time.DayOfWeekSubject.daysOfWeek;
import static dev.mardroemmar.truthext.time.InstantSubject.instants;
import static dev.mardroemmar.truthext.time.MonthSubject.months;
import static dev.mardroemmar.truthext.time.ZonedDateTimeSubject.zonedDateTimes;

import com.google.common.truth.ComparableSubject;
import com.google.common.truth.FailureMetadata;
import com.google.common.truth.IntegerSubject;
import com.google.common.truth.LongSubject;
import com.google.common.truth.Subject;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Objects;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.common.value.qual.IntRange;

/**
 * A {@link com.google.common.truth.Truth Truth} subject for {@link LocalDateTime}.
 *
 * @see Subject
 * @see ComparableSubject
 * @since 0.4.0
 */
public class LocalDateTimeSubject extends ComparableSubject<LocalDateTime> {
  /**
   * Assert upon a local date-time.
   *
   * @param actual the actual local date-time
   * @return a new assertion subject
   */
  public static LocalDateTimeSubject assertThat(final @Nullable LocalDateTime actual) {
    return assertAbout(localDateTimes()).that(actual);
  }

  /**
   * @return a subject factory for asserting about {@link LocalDateTime local date-times}.
   */
  public static Factory<LocalDateTimeSubject, LocalDateTime> localDateTimes() {
    return LocalDateTimeSubject::new;
  }

  private final @Nullable LocalDateTime actual;

  private LocalDateTimeSubject(final FailureMetadata metadata, final @Nullable LocalDateTime actual) {
    super(metadata, actual);
    this.actual = actual;
  }

  /**
   * Asserts that the {@code actual} is at the same local time as the {@code other}. Note that even if they are different instants in time,
   * this is not checked, as it is not properly conveyed; this uses {@link LocalDateTime#isEqual(ChronoLocalDateTime)}, as opposed to {@link
   * LocalDateTime#isEqual(ChronoLocalDateTime)} which would also take into account chronology.
   *
   * @param other the other time to compare to
   * @throws AssertionError       if the {@code actual} is null
   * @throws AssertionError       if the {@code actual} is not at the local time as the {@code other}
   * @throws NullPointerException if the {@code other} is null
   */
  public void isSameLocalTimeAs(final ChronoLocalDateTime<?> other) {
    Objects.requireNonNull(other, "other must not be null");
    if (!this.nonNull().isEqual(other)) {
      this.failWithActual(simpleFact("expected actual to have same local time as other"), fact("other", other));
    }
  }

  /**
   * Asserts that the {@code actual} is not at the same local time as the {@code other}. Note that even if they are different instants in
   * time, this is not checked, as it is not properly conveyed; this uses {@link LocalDateTime#isEqual(ChronoLocalDateTime)}, as opposed to
   * {@link LocalDateTime#isEqual(ChronoLocalDateTime)} which would also take into account chronology.
   *
   * @param other the other time to compare to
   * @throws AssertionError       if the {@code actual} is null
   * @throws AssertionError       if the {@code actual} is at the local time as the {@code other}
   * @throws NullPointerException if the {@code other} is null
   */
  public void isNotSameLocalTimeAs(final ChronoLocalDateTime<?> other) {
    Objects.requireNonNull(other, "other must not be null");
    if (this.nonNull().isEqual(other)) {
      this.failWithActual(simpleFact("expected actual to not have same local time as other"), fact("other", other));
    }
  }

  /**
   * Asserts that the {@code actual} is at the same local time as the {@code other}, including chronology. Note that even if they are
   * different instants in time, this is not checked, as it is not properly conveyed. This uses the {@link
   * LocalDateTime#compareTo(ChronoLocalDateTime)} method, which should be consistent to the {@link LocalDateTime#equals(Object)} method,
   * but supports more types than just {@link LocalDateTime}. This means that if the {@link LocalDateTime#getChronology()} is different,
   * it will not be considered equal, and will fail this test.
   *
   * @param other the other time to compare to
   * @throws AssertionError       if the {@code actual} is null
   * @throws AssertionError       if the {@code actual} is not at the local time as the {@code other}
   * @throws NullPointerException if the {@code other} is null
   */
  public void isComparativelyEqualTo(final ChronoLocalDateTime<?> other) {
    Objects.requireNonNull(other, "other must not be null");
    if (this.nonNull().compareTo(other) != 0) {
      this.failWithActual(simpleFact("expected actual to be comparatively equal local time as other"), fact("other", other));
    }
  }

  /**
   * Asserts that the {@code actual} is at not the same local time as the {@code other}, including chronology. Note that even if they are
   * different instants in time, this is not checked, as it is not properly conveyed. This uses the {@link
   * LocalDateTime#compareTo(ChronoLocalDateTime)} method, which should be consistent to the {@link LocalDateTime#equals(Object)} method,
   * but supports more types than just {@link LocalDateTime}.
   *
   * @param other the other time to compare to
   * @throws AssertionError       if the {@code actual} is null
   * @throws AssertionError       if the {@code actual} is at the local time as the {@code other}
   * @throws NullPointerException if the {@code other} is null
   */
  public void isComparativelyNotEqualTo(final ChronoLocalDateTime<?> other) {
    Objects.requireNonNull(other, "other must not be null");
    if (this.nonNull().compareTo(other) == 0) {
      this.failWithActual(simpleFact("expected actual to not be comparatively equal local time as other"), fact("other", other));
    }
  }

  /**
   * Assert further on the {@link Instant} of this time-date, assuming it is in the given time-zone.
   *
   * @param timeZone the time-zone to use
   * @return a subject about the instant in time of this time-date
   * @throws AssertionError if the actual is {@code null}
   * @see LocalDateTime#toInstant(ZoneOffset)
   */
  public InstantSubject instant(final ZoneOffset timeZone) {
    return this.check("toInstant(%s)", timeZone).about(instants()).that(this.nonNull().toInstant(timeZone));
  }

  /**
   * Assert further on the {@link ZonedDateTime} of this time-date, assuming it is in the given time-zone.
   *
   * @param timeZone the time-zone to use
   * @return a subject about the instant in time of this time-date
   * @throws AssertionError if the actual is {@code null}
   * @see LocalDateTime#atZone(ZoneId)
   */
  public ZonedDateTimeSubject zoned(final ZoneId timeZone) {
    return this.check("atZone(%s)", timeZone).about(zonedDateTimes()).that(this.nonNull().atZone(timeZone));
  }

  /**
   * Assert further on the day of the year.
   *
   * @return a subject about the day of the year
   * @throws AssertionError if the actual is {@code null}
   * @see LocalDateTime#getDayOfYear()
   */
  public @IntRange(from = 1, to = 366) IntegerSubject dayOfYear() {
    return this.check("getDayOfYear()").that(this.nonNull().getDayOfYear());
  }

  /**
   * Assert further on the day of the month.
   *
   * @return a subject about the day of the month
   * @throws AssertionError if the actual is {@code null}
   * @see LocalDateTime#getDayOfMonth()
   */
  public IntegerSubject dayOfMonth() {
    return this.check("getDayOfMonth()").that(this.nonNull().getDayOfMonth());
  }

  /**
   * Assert further on the hour of the day.
   *
   * @return a subject about the hour of the day
   * @throws AssertionError if the actual is {@code null}
   * @see LocalDateTime#getHour()
   */
  public @IntRange(from = 0, to = 23) IntegerSubject hour() {
    return this.check("getHour()").that(this.nonNull().getHour());
  }

  /**
   * Assert further on the minute of the hour.
   *
   * @return a subject about the minute of the hour
   * @throws AssertionError if the actual is {@code null}
   * @see LocalDateTime#getMinute()
   */
  public @IntRange(from = 0, to = 59) IntegerSubject minute() {
    return this.check("getMinute()").that(this.nonNull().getMinute());
  }

  /**
   * Assert further on the second of the minute.
   *
   * @return a subject about the second of the minute
   * @throws AssertionError if the actual is {@code null}
   * @see LocalDateTime#getSecond()
   */
  public @IntRange(from = 0, to = 59) IntegerSubject second() {
    return this.check("getSecond()").that(this.nonNull().getSecond());
  }

  /**
   * Assert further on the nano of the second.
   *
   * @return a subject about the nano of the second
   * @throws AssertionError if the actual is {@code null}
   * @see LocalDateTime#getNano()
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
    // If this is the UTC timestamp, no modifications of the time will have to be made. I.e. it should represent this exact local time,
    // but with the correct time from Epoch.
    return this.instant(ZoneOffset.UTC).epochMilli();
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
    // If this is the UTC timestamp, no modifications of the time will have to be made. I.e. it should represent this exact local time,
    // but with the correct time from Epoch.
    return this.instant(ZoneOffset.UTC).epochSecond();
  }

  /**
   * Assert further on the days from Epoch.
   *
   * @return a subject about the days from Epoch
   * @throws AssertionError if the actual is {@code null}
   * @see InstantSubject#epochDay()
   */
  public @NonNegative LongSubject epochDay() {
    // If this is the UTC timestamp, no modifications of the time will have to be made. I.e. it should represent this exact local time,
    // but with the correct time from Epoch.
    return this.instant(ZoneOffset.UTC).epochDay();
  }

  /**
   * Assert further on the value of the month.
   *
   * @return a subject about the month value
   * @throws AssertionError if the actual is {@code null}
   * @see LocalDateTime#getMonthValue()
   */
  public @IntRange(from = 1, to = 12) IntegerSubject monthValue() {
    return this.check("getMonthValue()").that(this.nonNull().getMonthValue());
  }

  /**
   * Assert further on the month.
   *
   * @return a subject about the month
   * @throws AssertionError if the actual is {@code null}
   * @see LocalDateTime#getMonth()
   */
  public MonthSubject month() {
    return this.check("getMonth()").about(months()).that(this.nonNull().getMonth());
  }

  /**
   * Assert further on the day.
   *
   * @return a subject about the day
   * @throws AssertionError if the actual is {@code null}
   * @see LocalDateTime#getDayOfWeek()
   */
  public DayOfWeekSubject dayOfWeek() {
    return this.check("getDayOfWeek()").about(daysOfWeek()).that(this.nonNull().getDayOfWeek());
  }

  private LocalDateTime nonNull() {
    if (this.actual != null) {
      return this.actual;
    }

    failWithActual(simpleFact("expected local date time to be non-null"));
    throw new AssertionError("unreachable");
  }
}
