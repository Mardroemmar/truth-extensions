package dev.mardroemmar.truthext.currency;

import static com.google.common.truth.Fact.simpleFact;
import static com.google.common.truth.Truth.assertAbout;

import com.google.common.truth.FailureMetadata;
import com.google.common.truth.IntegerSubject;
import com.google.common.truth.StringSubject;
import com.google.common.truth.Subject;
import java.util.Currency;
import java.util.Locale;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A {@link com.google.common.truth.Truth Truth} subject for {@link Currency}.
 *
 * @see Subject
 * @since 0.1.0
 */
@API(status = Status.MAINTAINED)
public class CurrencySubject extends Subject {
  /**
   * Assert upon a currency.
   *
   * @param actual the actual currency
   * @return a new assertion subject
   */
  public static CurrencySubject assertThat(final @Nullable Currency actual) {
    return assertAbout(currencies()).that(actual);
  }

  /**
   * @return a subject factory for asserting about {@link Currency currencies}.
   */
  public static Subject.Factory<CurrencySubject, Currency> currencies() {
    return CurrencySubject::new;
  }

  private final @Nullable Currency actual;

  private CurrencySubject(final FailureMetadata metadata, final @Nullable Currency actual) {
    super(metadata, actual);
    this.actual = actual;
  }

  /**
   * Assert further upon the currency code.
   *
   * @return a subject about the currency code of the currency
   * @throws AssertionError if the actual is {@code null}
   * @see Currency#getCurrencyCode()
   */
  public StringSubject currencyCode() {
    return this.check("getCurrencyCode()").that(this.nonNull().getCurrencyCode());
  }

  /**
   * Assert further upon the display name.
   *
   * @return a subject about the display name of the currency
   * @throws AssertionError if the actual is {@code null}
   * @see Currency#getDisplayName()
   */
  public StringSubject displayName() {
    return this.check("getDisplayName()").that(this.nonNull().getDisplayName());
  }

  /**
   * Assert further upon the display name.
   *
   * @param locale the locale to fetch the display name for
   * @return a subject about the display name of the currency
   * @throws AssertionError if the actual is {@code null}
   * @see Currency#getDisplayName(Locale)
   */
  public StringSubject displayName(final Locale locale) {
    return this.check("getDisplayName(%s)", locale).that(this.nonNull().getDisplayName(locale));
  }

  /**
   * Assert further upon the numeric currency code.
   *
   * @return a subject about the numeric code of the currency
   * @throws AssertionError if the actual is {@code null}
   * @see Currency#getNumericCode()
   */
  public IntegerSubject numericCode() {
    return this.check("getNumericCode()").that(this.nonNull().getNumericCode());
  }

  /**
   * Assert further upon the symbol of the currency.
   *
   * @return a subject about the symbol of the currency
   * @throws AssertionError if the actual is {@code null}
   * @see Currency#getSymbol()
   */
  public StringSubject symbol() {
    return this.check("getSymbol()").that(this.nonNull().getSymbol());
  }

  /**
   * Assert further upon the symbol of the currency.
   *
   * @param locale the locale to fetch the symbol for
   * @return a subject about the symbol of the currency
   * @throws AssertionError if the actual is {@code null}
   * @see Currency#getSymbol(Locale)
   */
  public StringSubject symbol(final Locale locale) {
    return this.check("getSymbol(%s)", locale).that(this.nonNull().getSymbol(locale));
  }

  /**
   * Assert further upon the default fraction digits.
   *
   * @return a subject about the default fraction digits of the currency
   * @throws AssertionError if the actual is {@code null}
   * @see Currency#getDefaultFractionDigits()
   */
  public IntegerSubject defaultFractionDigits() {
    return this.check("getDefaultFractionDigits()").that(this.nonNull().getDefaultFractionDigits());
  }

  private Currency nonNull() {
    if (this.actual != null) {
      return this.actual;
    }

    failWithActual(simpleFact("expected currency to be non-null"));
    throw new AssertionError("unreachable");
  }
}
