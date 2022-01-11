package dev.mardroemmar.truthext.time;

import static com.google.common.truth.Fact.fact;
import static com.google.common.truth.Fact.simpleFact;
import static com.google.common.truth.Truth.assertAbout;

import com.google.common.truth.FailureMetadata;
import com.google.common.truth.IntegerSubject;
import com.google.common.truth.LongSubject;
import com.google.common.truth.Subject;
import java.time.DayOfWeek;
import java.time.temporal.TemporalField;
import java.util.Objects;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A {@link com.google.common.truth.Truth Truth} subject for {@link DayOfWeek}.
 *
 * @see Subject
 * @since 0.3.0
 */
@API(status = Status.MAINTAINED)
public class DayOfWeekSubject extends Subject {
  /**
   * Assert upon a day of the week.
   *
   * @param actual the actual day of the week
   * @return a new assertion subject
   */
  public static DayOfWeekSubject assertThat(final @Nullable DayOfWeek actual) {
    return assertAbout(daysOfWeek()).that(actual);
  }

  /**
   * @return a subject factory for asserting about {@link DayOfWeek days of the week}.
   */
  public static Factory<DayOfWeekSubject, DayOfWeek> daysOfWeek() {
    return DayOfWeekSubject::new;
  }

  private final @Nullable DayOfWeek actual;

  private DayOfWeekSubject(final FailureMetadata metadata, final @Nullable DayOfWeek actual) {
    super(metadata, actual);
    this.actual = actual;
  }

  /**
   * Asserts that the {@code actual} is before the {@code otherDayOfWeek}.
   *
   * @param otherDayOfWeek the other day of the week to compare against
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the other day of the week is before or the same day as the {@code actual}
   * @throws NullPointerException if {@code otherDayOfWeek} is {@code null}
   */
  public void isBefore(final DayOfWeek otherDayOfWeek) {
    if (this.nonNull().getValue() >= otherDayOfWeek.getValue()) {
      this.failWithActual(simpleFact("expected actual to be before otherDayOfWeek"), fact("otherDayOfWeek", otherDayOfWeek));
    }
  }

  /**
   * Asserts that the {@code actual} is before or on the same day as the {@code otherDayOfWeek}.
   *
   * @param otherDayOfWeek the other day of the week to compare against
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the other day of the week is before the {@code actual}
   * @throws NullPointerException if {@code otherDayOfWeek} is {@code null}
   */
  public void isBeforeOrEqualTo(final DayOfWeek otherDayOfWeek) {
    if (this.nonNull().getValue() > otherDayOfWeek.getValue()) {
      this.failWithActual(simpleFact("expected actual to be before or equal to otherDayOfWeek"), fact("otherDayOfWeek", otherDayOfWeek));
    }
  }

  /**
   * Asserts that the {@code actual} is after the {@code otherDayOfWeek}.
   *
   * @param otherDayOfWeek the other day of the week to compare against
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the other day of the week is after or on the same day as the {@code actual}
   * @throws NullPointerException if {@code otherDayOfWeek} is {@code null}
   */
  public void isAfter(final DayOfWeek otherDayOfWeek) {
    if (this.nonNull().getValue() <= otherDayOfWeek.getValue()) {
      this.failWithActual(simpleFact("expected actual to be after otherDayOfWeek"), fact("otherDayOfWeek", otherDayOfWeek));
    }
  }

  /**
   * Asserts that the {@code actual} is after or on the same day as the {@code otherDayOfWeek}.
   *
   * @param otherDayOfWeek the other day of the week to compare against
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the other day of the week is after the {@code actual}
   * @throws NullPointerException if {@code otherDayOfWeek} is {@code null}
   */
  public void isAfterOrEqualTo(final DayOfWeek otherDayOfWeek) {
    if (this.nonNull().getValue() < otherDayOfWeek.getValue()) {
      this.failWithActual(simpleFact("expected actual to be after or equal to otherDayOfWeek"), fact("otherDayOfWeek", otherDayOfWeek));
    }
  }

  /**
   * Asserts that the {@code actual} {@link DayOfWeek#isSupported(TemporalField) supports} the given {@code field}.
   *
   * @param field the field to check support for
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the {@code field} is not supported
   * @throws NullPointerException if {@code field} is {@code null}
   * @see DayOfWeek#isSupported(TemporalField)
   */
  public void isSupported(final TemporalField field) {
    Objects.requireNonNull(field, "field must not be null");
    if (!this.nonNull().isSupported(field)) {
      this.failWithActual(simpleFact("expected actual to be supported by field"), fact("field", field));
    }
  }

  /**
   * Asserts that the {@code actual} {@link DayOfWeek#isSupported(TemporalField) does not support} the given {@code field}.
   *
   * @param field the field to check support for
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the {@code field} is supported
   * @throws NullPointerException if {@code field} is {@code null}
   * @see DayOfWeek#isSupported(TemporalField)
   */
  public void isNotSupported(final TemporalField field) {
    Objects.requireNonNull(field, "field must not be null");
    if (this.nonNull().isSupported(field)) {
      this.failWithActual(simpleFact("expected actual to not be supported by field"), fact("field", field));
    }
  }

  /**
   * Assert further on the {@link DayOfWeek#ordinal() ordinal}.
   *
   * @return a subject about the ordinal
   * @throws AssertionError if the actual is {@code null}
   * @see DayOfWeek#ordinal()
   */
  public IntegerSubject ordinal() {
    return this.check("ordinal()").that(this.nonNull().ordinal());
  }

  /**
   * Assert further on the {@link DayOfWeek#getValue() value}.
   *
   * @return a subject about the value
   * @throws AssertionError if the actual is {@code null}
   * @see DayOfWeek#getValue()
   */
  public IntegerSubject value() {
    return this.check("getValue()").that(this.nonNull().getValue());
  }

  /**
   * Assert further on the gotten value of the {@code field}.
   *
   * @return a subject about the value
   * @throws AssertionError                                      if the actual is {@code null}
   * @throws AssertionError                                      if the {@code field} is {@code null}
   * @throws java.time.DateTimeException                         if a value for the field cannot be obtained or the value is outside the
   *                                                             range of valid values for the field
   * @throws java.time.temporal.UnsupportedTemporalTypeException if the field is not supported or the range of values exceeds an {@code
   *                                                             int}
   * @throws ArithmeticException                                 if a numeric overflow occurs
   * @see DayOfWeek#get(TemporalField)
   */
  public IntegerSubject get(final TemporalField field) {
    Objects.requireNonNull(field, "field must not be null");
    return this.check("get(%s)", field).that(this.nonNull().get(field));
  }

  /**
   * Assert further on the gotten value of the {@code field}.
   *
   * @return a subject about the value
   * @throws AssertionError                                      if the actual is {@code null}
   * @throws AssertionError                                      if the {@code field} is {@code null}
   * @throws java.time.DateTimeException                         if a value for the field cannot be obtained
   * @throws java.time.temporal.UnsupportedTemporalTypeException if the field is not supported
   * @throws ArithmeticException                                 if a numeric overflow occurs
   * @see DayOfWeek#getLong(TemporalField)
   */
  public LongSubject getLong(final TemporalField field) {
    Objects.requireNonNull(field, "field must not be null");
    return this.check("getLong(%s)", field).that(this.nonNull().getLong(field));
  }

  private DayOfWeek nonNull() {
    if (this.actual != null) {
      return this.actual;
    }

    failWithActual(simpleFact("expected day of week to be non-null"));
    throw new AssertionError("unreachable");
  }
}
