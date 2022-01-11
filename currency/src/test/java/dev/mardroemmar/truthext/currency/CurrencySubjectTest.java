package dev.mardroemmar.truthext.currency;

import static dev.mardroemmar.truthext.currency.CurrencySubject.assertThat;

import java.util.Currency;
import java.util.Locale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class CurrencySubjectTest {
  @Test
  void correctlyForwarding() {
    final Currency currency = Currency.getInstance("EUR");
    assertThat(currency).currencyCode().isEqualTo(currency.getCurrencyCode());
    assertThat(currency).displayName().isEqualTo(currency.getDisplayName());
    assertThat(currency).displayName(Locale.ENGLISH).isEqualTo(currency.getDisplayName(Locale.ENGLISH));
    assertThat(currency).symbol().isEqualTo(currency.getSymbol());
    assertThat(currency).symbol(Locale.ENGLISH).isEqualTo(currency.getSymbol(Locale.ENGLISH));
    assertThat(currency).defaultFractionDigits().isEqualTo(currency.getDefaultFractionDigits());
    assertThat(currency).numericCode().isEqualTo(currency.getNumericCode());
  }

  @Test
  void correctErrorMessages() {
    final Currency currency = Currency.getInstance("EUR");
    expectAssertionError(() -> assertThat(currency).currencyCode().isEqualTo(123));
    expectAssertionError(() -> assertThat(currency).displayName().isEqualTo(123));
    expectAssertionError(() -> assertThat(currency).displayName(Locale.ENGLISH).isEqualTo(123));
    expectAssertionError(() -> assertThat(currency).symbol().isEqualTo(123));
    expectAssertionError(() -> assertThat(currency).symbol(Locale.ENGLISH).isEqualTo(123));
    expectAssertionError(() -> assertThat(currency).defaultFractionDigits().isEqualTo("not this"));
    expectAssertionError(() -> assertThat(currency).numericCode().isEqualTo("not this"));
  }

  private void expectAssertionError(final Executable executable) {
    Assertions.assertThrows(AssertionError.class, executable).printStackTrace();
  }
}
