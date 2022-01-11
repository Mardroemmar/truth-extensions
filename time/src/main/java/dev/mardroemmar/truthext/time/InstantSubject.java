package dev.mardroemmar.truthext.time;

import static com.google.common.truth.Fact.fact;
import static com.google.common.truth.Fact.simpleFact;
import static com.google.common.truth.Truth.assertAbout;
import static dev.mardroemmar.truthext.time.ZonedDateTimeSubject.zonedDateTimes;

import com.google.common.truth.ComparableSubject;
import com.google.common.truth.FailureMetadata;
import com.google.common.truth.IntegerSubject;
import com.google.common.truth.LongSubject;
import com.google.common.truth.Subject;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A {@link com.google.common.truth.Truth Truth} subject for {@link Instant}.
 *
 * @see com.google.common.truth.Subject
 * @see ComparableSubject
 * @since 0.3.0
 */
public class InstantSubject extends ComparableSubject<Instant> {
  /**
   * Assert upon an instant in time.
   *
   * @param actual the actual instant
   * @return a new assertion subject
   */
  public static InstantSubject assertThat(final @Nullable Instant actual) {
    return assertAbout(instants()).that(actual);
  }

  /**
   * @return a subject factory for asserting about {@link Instant instants in time}.
   */
  public static Subject.Factory<InstantSubject, Instant> instants() {
    return InstantSubject::new;
  }

  private final @Nullable Instant actual;

  private InstantSubject(final FailureMetadata metadata, final @Nullable Instant actual) {
    super(metadata, actual);
    this.actual = actual;
  }

  /**
   * Asserts that the {@code actual} is {@link Instant#MAX}.
   *
   * @throws AssertionError if the actual is {@code null}
   * @throws AssertionError if the actual is not {@link Instant#MAX}
   */
  public void isMax() {
    if (!this.nonNull().equals(Instant.MAX)) {
      this.failWithActual(simpleFact("expected actual to be max"));
    }
  }

  /**
   * Asserts that the {@code actual} is not {@link Instant#MAX}.
   *
   * @throws AssertionError if the actual is {@code null}
   * @throws AssertionError if the actual is {@link Instant#MAX}
   */
  public void isNotMax() {
    if (this.nonNull().equals(Instant.MAX)) {
      this.failWithActual(simpleFact("expected actual to not be max"));
    }
  }

  /**
   * Asserts that the {@code actual} is {@link Instant#MIN}.
   *
   * @throws AssertionError if the actual is {@code null}
   * @throws AssertionError if the actual is not {@link Instant#MIN}
   */
  public void isMin() {
    if (!this.nonNull().equals(Instant.MIN)) {
      this.failWithActual(simpleFact("expected actual to be min"));
    }
  }

  /**
   * Asserts that the {@code actual} is not {@link Instant#MIN}.
   *
   * @throws AssertionError if the actual is {@code null}
   * @throws AssertionError if the actual is {@link Instant#MIN}
   */
  public void isNotMin() {
    if (this.nonNull().equals(Instant.MIN)) {
      this.failWithActual(simpleFact("expected actual to not be min"));
    }
  }

  /**
   * Asserts that the {@code actual} is before the {@code otherInstant}.
   *
   * @param otherInstant the other instant to compare to
   * @throws NullPointerException if the {@code otherInstant} is {@code null}
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the actual is not before the {@code otherInstant}
   */
  public void isBefore(final Instant otherInstant) {
    if (!this.nonNull().isBefore(otherInstant)) {
      this.failWithActual(simpleFact("expected actual to be before otherInstant"), fact("otherInstant", otherInstant));
    }
  }

  /**
   * Asserts that the {@code actual} is before or equal to the {@code otherInstant}.
   *
   * @param otherInstant the other instant to compare to
   * @throws NullPointerException if the {@code otherInstant} is {@code null}
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the actual is not before or equal to the {@code otherInstant}
   */
  public void isBeforeOrEqualTo(final Instant otherInstant) {
    if (!this.nonNull().isBefore(otherInstant) && !this.nonNull().equals(otherInstant)) {
      this.failWithActual(simpleFact("expected actual to be before or equal to otherInstant"), fact("otherInstant", otherInstant));
    }
  }

  /**
   * Asserts that the {@code actual} is after the {@code otherInstant}.
   *
   * @param otherInstant the other instant to compare to
   * @throws NullPointerException if the {@code otherInstant} is {@code null}
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the actual is not after the {@code otherInstant}
   */
  public void isAfter(final Instant otherInstant) {
    if (!this.nonNull().isAfter(otherInstant)) {
      this.failWithActual(simpleFact("expected actual to be after otherInstant"), fact("otherInstant", otherInstant));
    }
  }

  /**
   * Asserts that the {@code actual} is after or equal to the {@code otherInstant}.
   *
   * @param otherInstant the other instant to compare to
   * @throws NullPointerException if the {@code otherInstant} is {@code null}
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the actual is not after or equal to the {@code otherInstant}
   */
  public void isAfterOrEqualTo(final Instant otherInstant) {
    if (!this.nonNull().isAfter(otherInstant) && !this.nonNull().equals(otherInstant)) {
      this.failWithActual(simpleFact("expected actual to be after or equal to otherInstant"), fact("otherInstant", otherInstant));
    }
  }

  /**
   * Asserts that the {@code actual} {@link Instant#isSupported(TemporalUnit) supports} the {@code temporalUnit}.
   *
   * @param temporalUnit the unit to check support for
   * @throws NullPointerException if the {@code temporalUnit} is {@code null}
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the actual does not support the {@code temporalUnit}
   */
  public void isSupported(final TemporalUnit temporalUnit) {
    Objects.requireNonNull(temporalUnit, "temporalUnit must not be null");
    if (!this.nonNull().isSupported(temporalUnit)) {
      this.failWithActual(simpleFact("expected actual to support temporalUnit"), fact("temporalUnit", temporalUnit));
    }
  }

  /**
   * Asserts that the {@code actual} {@link Instant#isSupported(TemporalField) supports} the {@code temporalField}.
   *
   * @param temporalField the field to check support for
   * @throws NullPointerException if the {@code temporalField} is {@code null}
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the actual does not support the {@code temporalField}
   */
  public void isSupported(final TemporalField temporalField) {
    Objects.requireNonNull(temporalField, "temporalField must not be null");
    if (!this.nonNull().isSupported(temporalField)) {
      this.failWithActual(simpleFact("expected actual to support temporalField"), fact("temporalField", temporalField));
    }
  }

  /**
   * Asserts that the {@code actual} {@link Instant#isSupported(TemporalUnit) does not support} the {@code temporalUnit}.
   *
   * @param temporalUnit the unit to check support for
   * @throws NullPointerException if the {@code temporalUnit} is {@code null}
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the actual supports the {@code temporalUnit}
   */
  public void isNotSupported(final TemporalUnit temporalUnit) {
    Objects.requireNonNull(temporalUnit, "temporalUnit must not be null");
    if (this.nonNull().isSupported(temporalUnit)) {
      this.failWithActual(simpleFact("expected actual to not support temporalUnit"), fact("temporalUnit", temporalUnit));
    }
  }

  /**
   * Asserts that the {@code actual} {@link Instant#isSupported(TemporalField) does not support} the {@code temporalField}.
   *
   * @param temporalField the field to check support for
   * @throws NullPointerException if the {@code temporalField} is {@code null}
   * @throws AssertionError       if the actual is {@code null}
   * @throws AssertionError       if the actual supports the {@code temporalField}
   */
  public void isNotSupported(final TemporalField temporalField) {
    Objects.requireNonNull(temporalField, "temporalField must not be null");
    if (this.nonNull().isSupported(temporalField)) {
      this.failWithActual(simpleFact("expected actual to not support temporalField"), fact("temporalField", temporalField));
    }
  }

  /**
   * Assert further on the milliseconds from Epoch.
   *
   * @return a subject about the milliseconds from Epoch
   * @throws AssertionError      if the actual is {@code null}
   * @throws ArithmeticException if the current instant in time is too large to fit in a range from {@code 0} to {@link Long#MAX_VALUE}
   * @see Instant#toEpochMilli()
   */
  public @NonNegative LongSubject epochMilli() {
    return this.check("toEpochMillis()").that(this.nonNull().toEpochMilli());
  }

  /**
   * Assert further on the seconds from Epoch.
   *
   * @return a subject about the seconds from Epoch
   * @throws AssertionError if the actual is {@code null}
   * @see Instant#getEpochSecond()
   */
  public @NonNegative LongSubject epochSecond() {
    return this.check("getEpochSecond()").that(this.nonNull().getEpochSecond());
  }

  /**
   * Assert further on the days from Epoch.
   *
   * @return a subject about the days from Epoch
   * @throws AssertionError if the actual is {@code null}
   */
  public LongSubject epochDay() {
    return this.check("TimeUnit.SECONDS.toDays(getEpochSecond())").that(TimeUnit.SECONDS.toDays(this.nonNull().getEpochSecond()));
  }

  /**
   * Assert further on the nano of the second.
   *
   * @return a subject about the nano of the second
   * @throws AssertionError if the actual is {@code null}
   * @see Instant#getNano()
   */
  public IntegerSubject nano() {
    return this.check("getNano()").that(this.nonNull().getNano());
  }

  /**
   * Assert further on the {@code actual} instant in time at a specific time-zone {@code zoneId}.
   *
   * @return a subject about the {@code actual} instant in time in a time-zone
   * @throws AssertionError if the actual is {@code null}
   * @see Instant#atZone(ZoneId)
   */
  public ZonedDateTimeSubject atZone(final ZoneId zoneId) {
    return this.check("atZone(%s)", zoneId).about(zonedDateTimes()).that(this.nonNull().atZone(zoneId));
  }

  /**
   * Assert further on the {@code actual} instant in time in {@link ZoneOffset#UTC UTC}.
   *
   * @return a subject about the {@code actual} instant in time in {@link ZoneOffset#UTC UTC}
   * @throws AssertionError if the actual is {@code null}
   * @see Instant#atZone(ZoneId)
   * @see #atZone(ZoneId)
   */
  public ZonedDateTimeSubject atUtc() {
    return this.atZone(ZoneOffset.UTC);
  }

  private Instant nonNull() {
    if (this.actual != null) {
      return this.actual;
    }

    failWithActual(simpleFact("expected instant to be non-null"));
    throw new AssertionError("unreachable");
  }
}
