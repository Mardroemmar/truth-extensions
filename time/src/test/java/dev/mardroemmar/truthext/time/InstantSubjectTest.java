package dev.mardroemmar.truthext.time;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InstantSubjectTest {
  @Test
  void isMax() {
    InstantSubject.assertThat(Instant.MAX).isMax();
    Assertions.assertThrows(AssertionError.class, () -> InstantSubject.assertThat(Instant.MIN).isMax());
    Assertions.assertThrows(AssertionError.class, () -> InstantSubject.assertThat(Instant.now()).isMax());
  }

  @Test
  void isNotMax() {
    InstantSubject.assertThat(Instant.MIN).isNotMax();
    InstantSubject.assertThat(Instant.now()).isNotMax();
    Assertions.assertThrows(AssertionError.class, () -> InstantSubject.assertThat(Instant.MAX).isNotMax());
  }

  @Test
  void isMin() {
    InstantSubject.assertThat(Instant.MIN).isMin();
    Assertions.assertThrows(AssertionError.class, () -> InstantSubject.assertThat(Instant.MAX).isMin());
    Assertions.assertThrows(AssertionError.class, () -> InstantSubject.assertThat(Instant.now()).isMin());
  }

  @Test
  void isNotMin() {
    InstantSubject.assertThat(Instant.MAX).isNotMin();
    InstantSubject.assertThat(Instant.now()).isNotMin();
    Assertions.assertThrows(AssertionError.class, () -> InstantSubject.assertThat(Instant.MIN).isNotMin());
  }

  @Test
  void isBefore() {
    InstantSubject.assertThat(Instant.MIN).isBefore(Instant.MAX);
    InstantSubject.assertThat(Instant.MIN).isBefore(Instant.now());
    InstantSubject.assertThat(Instant.now()).isBefore(Instant.now().plus(1, ChronoUnit.DAYS));
    Assertions.assertThrows(AssertionError.class, () -> InstantSubject.assertThat(Instant.MAX).isBefore(Instant.MIN));
    Assertions.assertThrows(AssertionError.class, () -> InstantSubject.assertThat(Instant.MAX).isBefore(Instant.now()));
    Assertions.assertThrows(AssertionError.class, () -> {
      final Instant now = Instant.now();
      InstantSubject.assertThat(now).isBefore(now);
    });
    Assertions.assertThrows(AssertionError.class,
        () -> InstantSubject.assertThat(Instant.now()).isBefore(Instant.now().minus(1, ChronoUnit.DAYS)));
  }

  @Test
  void isBeforeOrEqualTo() {
    InstantSubject.assertThat(Instant.MIN).isBeforeOrEqualTo(Instant.MAX);
    InstantSubject.assertThat(Instant.MIN).isBeforeOrEqualTo(Instant.now());
    InstantSubject.assertThat(Instant.now()).isBeforeOrEqualTo(Instant.now().plus(1, ChronoUnit.DAYS));
    InstantSubject.assertThat(Instant.now()).isBeforeOrEqualTo(Instant.now());
    Assertions.assertThrows(AssertionError.class, () -> InstantSubject.assertThat(Instant.MAX).isBeforeOrEqualTo(Instant.MIN));
    Assertions.assertThrows(AssertionError.class, () -> InstantSubject.assertThat(Instant.MAX).isBeforeOrEqualTo(Instant.now()));
    Assertions.assertThrows(AssertionError.class,
        () -> InstantSubject.assertThat(Instant.now()).isBeforeOrEqualTo(Instant.now().minus(1, ChronoUnit.DAYS)));
  }

  @Test
  void isAfter() {
    InstantSubject.assertThat(Instant.MAX).isAfter(Instant.MIN);
    InstantSubject.assertThat(Instant.MAX).isAfter(Instant.now());
    InstantSubject.assertThat(Instant.now()).isAfter(Instant.now().minus(1, ChronoUnit.DAYS));
    Assertions.assertThrows(AssertionError.class, () -> InstantSubject.assertThat(Instant.MIN).isAfter(Instant.MAX));
    Assertions.assertThrows(AssertionError.class, () -> InstantSubject.assertThat(Instant.MIN).isAfter(Instant.now()));
    Assertions.assertThrows(AssertionError.class, () -> {
      final Instant now = Instant.now();
      InstantSubject.assertThat(now).isAfter(now);
    });
    Assertions.assertThrows(AssertionError.class,
        () -> InstantSubject.assertThat(Instant.now()).isAfter(Instant.now().plus(1, ChronoUnit.DAYS)));
  }
}
