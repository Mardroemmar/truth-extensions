package dev.mardroemmar.truthext.currency;

import java.util.Currency;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A {@link com.google.common.truth.Truth Truth}-like class for {@link java.util.Currency currencies}.
 *
 * @since 0.3.0
 */
@API(status = Status.MAINTAINED)
public final class TruthCurrency {
  private TruthCurrency() {
  }

  /**
   * Assert upon a {@link Currency}. Alias for {@link CurrencySubject#assertThat(Currency)}.
   *
   * @param actual the actual to assert upon
   * @return a new subject for asserting
   * @see CurrencySubject#assertThat(Currency)
   */
  public static CurrencySubject assertThat(final @Nullable Currency actual) {
    return CurrencySubject.assertThat(actual);
  }
}
