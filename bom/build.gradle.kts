plugins {
  `java-library`
  `maven-publish`
  id("te.publishing")
}

dependencies {
  constraints {
    api(projects.truthExtensionsCurrency)
  }
}
