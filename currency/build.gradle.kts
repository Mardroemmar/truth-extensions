plugins {
  id("te.java-conventions")
}

dependencies {
  api(libs.checker.qual)
  api(libs.apiguardian)
  api(libs.truth)

  testImplementation(libs.junit.api)
  testImplementation(libs.junit.params)
  testRuntimeOnly(libs.junit.engine)
}
