package dev.mardroemmar.truthext.time;

import static com.google.common.truth.Fact.fact;
import static com.google.common.truth.Fact.simpleFact;
import static com.google.common.truth.Truth.assertAbout;

import com.google.common.truth.FailureMetadata;
import com.google.common.truth.IntegerSubject;
import com.google.common.truth.LongSubject;
import com.google.common.truth.Subject;
import java.time.Month;
import java.time.temporal.TemporalField;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.common.value.qual.IntRange;

/**
 * A {@link com.google.common.truth.Truth Truth} subject for {@link Month}.
 *
 * @see Subject
 * @since 0.3.0
 */
public class MonthSubject extends Subject {
  /**
   * Assert upon a month.
   *
   * @param actual the actual month
   * @return a new assertion subject
   */
  public static MonthSubject assertThat(final @Nullable Month actual) {
    return assertAbout(months()).that(actual);
  }

  /**
   * @return a subject factory for asserting about {@link Month months of the ISO-8601 year}.
   */
  public static Subject.Factory<MonthSubject, Month> months() {
    return MonthSubject::new;
  }

  private final @Nullable Month actual;

  private MonthSubject(final FailureMetadata metadata, final @Nullable Month actual) {
    super(metadata, actual);
    this.actual = actual;
  }

  /**
   * Asserts that the {@code actual} is before the {@code otherMonth}.
   *
   * @param otherMonth the other month to compare against
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the other month is before or the same month as the {@code actual}
   * @throws NullPointerException if {@code otherMonth} is {@code null}
   */
  public void isBefore(final Month otherMonth) {
    if (this.nonNull().getValue() >= otherMonth.getValue()) {
      this.failWithActual(simpleFact("expected actual to be before otherMonth"), fact("otherMonth", otherMonth));
    }
  }

  /**
   * Asserts that the {@code actual} is before or equal to the {@code otherMonth}.
   *
   * @param otherMonth the other month to compare against
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the other month is before the {@code actual}
   * @throws NullPointerException if {@code otherMonth} is {@code null}
   */
  public void isBeforeOrEqualTo(final Month otherMonth) {
    if (this.nonNull().getValue() > otherMonth.getValue()) {
      this.failWithActual(simpleFact("expected actual to be before or equal to otherMonth"), fact("otherMonth", otherMonth));
    }
  }

  /**
   * Asserts that the {@code actual} is after the {@code otherMonth}.
   *
   * @param otherMonth the other month to compare against
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the other month is after or the same month as the {@code actual}
   * @throws NullPointerException if {@code otherMonth} is {@code null}
   */
  public void isAfter(final Month otherMonth) {
    if (this.nonNull().getValue() <= otherMonth.getValue()) {
      this.failWithActual(simpleFact("expected actual to be after otherMonth"), fact("otherMonth", otherMonth));
    }
  }

  /**
   * Asserts that the {@code actual} is after or equal to the {@code otherMonth}.
   *
   * @param otherMonth the other month to compare against
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the other month is after the {@code actual}
   * @throws NullPointerException if {@code otherMonth} is {@code null}
   */
  public void isAfterOrEqualTo(final Month otherMonth) {
    if (this.nonNull().getValue() < otherMonth.getValue()) {
      this.failWithActual(simpleFact("expected actual to be after or equal to otherMonth"), fact("otherMonth", otherMonth));
    }
  }

  /**
   * Asserts that the {@code actual} {@link Month#isSupported(TemporalField) supports} the given {@code field}.
   *
   * @param field the field to check support for
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the {@code field} is not supported
   * @throws NullPointerException if {@code field} is {@code null}
   * @see Month#isSupported(TemporalField)
   */
  public void isSupported(final TemporalField field) {
    if (!this.nonNull().isSupported(field)) {
      this.failWithActual(simpleFact("expected actual to be supported by field"), fact("field", field));
    }
  }

  /**
   * Asserts that the {@code actual} {@link Month#isSupported(TemporalField) does not support} the given {@code field}.
   *
   * @param field the field to check support for
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the {@code field} is supported
   * @throws NullPointerException if {@code field} is {@code null}
   * @see Month#isSupported(TemporalField)
   */
  public void isNotSupported(final TemporalField field) {
    if (this.nonNull().isSupported(field)) {
      this.failWithActual(simpleFact("expected actual to not be supported by field"), fact("field", field));
    }
  }

  /**
   * Assert further on the {@link Month#ordinal() ordinal}.
   *
   * @return a subject about the ordinal
   * @throws AssertionError if the actual is {@code null}
   * @see Month#ordinal()
   */
  public IntegerSubject ordinal() {
    return this.check("ordinal()").that(this.nonNull().ordinal());
  }

  /**
   * Assert further on the {@link Month#getValue() value}.
   *
   * @return a subject about the value
   * @throws AssertionError if the actual is {@code null}
   * @see Month#getValue()
   */
  public @IntRange(from = 1, to = 12) IntegerSubject value() {
    return this.check("getValue()").that(this.nonNull().getValue());
  }

  /**
   * Assert further on the minimum number of days in a month.
   *
   * @return a subject about the minimum number of days in a month
   * @throws AssertionError if the actual is {@code null}
   * @see Month#minLength()
   */
  public @IntRange(from = 28, to = 31) IntegerSubject minLength() {
    return this.check("minLength()").that(this.nonNull().minLength());
  }

  /**
   * Assert further on the maximum number of days in a month. Note that this assumes a leap year.
   *
   * @return a subject about the maximum number of days in a month
   * @throws AssertionError if the actual is {@code null}
   * @see Month#maxLength()
   */
  public @IntRange(from = 29, to = 31) IntegerSubject maxLength() {
    return this.check("maxLength()").that(this.nonNull().maxLength());
  }

  /**
   * Assert further on the length of the {@code actual} month.
   *
   * @param leapYear is the year a leap year?
   * @return a subject about the number of days in a month
   * @throws AssertionError if the actual is {@code null}
   * @see Month#length(boolean)
   */
  public IntegerSubject length(final boolean leapYear) {
    return this.check("length(%s)", leapYear).that(this.nonNull().length(leapYear));
  }

  /**
   * Assert further on the value of the {@code field} on the {@code actual}.
   *
   * @param field the field to get the value of
   * @return a subject about the value of the {@code field}
   * @throws AssertionError                                      if the actual is {@code null}
   * @throws java.time.DateTimeException                         if the field's value cannot be obtained or is outside the valid ranges for
   *                                                             the field
   * @throws java.time.temporal.UnsupportedTemporalTypeException if the field is unsupported or its value exceeds what an int can store
   * @see Month#get(TemporalField)
   */
  public IntegerSubject get(final TemporalField field) {
    return this.check("get(%s)", field).that(this.nonNull().get(field));
  }

  /**
   * Assert further on the value of the {@code field} on the {@code actual}.
   *
   * @param field the field to get the value of
   * @return a subject about the value of the {@code field}
   * @throws AssertionError                                      if the actual is {@code null}
   * @throws java.time.DateTimeException                         if the field's value cannot be obtained
   * @throws java.time.temporal.UnsupportedTemporalTypeException if the field is unsupported
   * @see Month#getLong(TemporalField)
   */
  public LongSubject getLong(final TemporalField field) {
    return this.check("getLong(%s)", field).that(this.nonNull().getLong(field));
  }

  /**
   * Assert further on the value of the first day of the year during this month.
   * <p>
   * This returns the day of the year that the given month starts on. As an example, January 1st is day 1 of the year, March 1st is either
   * day 60 or day 61 of the year, depending on whether the year is a {@code leapYear}.
   *
   * @param leapYear is the year a leap year?
   * @return a subject about day of the year
   * @throws AssertionError if the actual is {@code null}
   * @see Month#firstDayOfYear(boolean)
   */
  public @IntRange(from = 1, to = 336) IntegerSubject firstDayOfYear(final boolean leapYear) {
    return this.check("firstDayOfYear(%s)", leapYear).that(this.nonNull().firstDayOfYear(leapYear));
  }

  /**
   * Assert further on the first month of the {@code actual}'s quarter.
   * <p>
   * For January, February and March, the first month of the quarter is January. For April, May and June, the first month of the quarter is
   * April. For July, August and September, the first month of the quarter is July. For October, November and December, the first month of
   * the quarter is October.
   *
   * @return a subject about the first month of the quarter
   * @throws AssertionError if the actual is {@code null}
   * @see Month#firstMonthOfQuarter()
   */
  public MonthSubject firstMonthOfQuarter() {
    return this.check("firstMonthOfQuarter()").about(months()).that(this.nonNull().firstMonthOfQuarter());
  }

  private Month nonNull() {
    if (this.actual != null) {
      return this.actual;
    }

    failWithActual(simpleFact("expected month to be non-null"));
    throw new AssertionError("unreachable");
  }
}
