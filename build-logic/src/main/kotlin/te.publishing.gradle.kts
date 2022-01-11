plugins {
  id("net.kyori.indra.publishing")
}

val signingKey = System.getenv("SIGNING_KEY")
val signingPassword = System.getenv("SIGNING_PASSWORD")
if (signingKey != null && signingPassword != null) {
  signing.useInMemoryPgpKeys(signingKey, signingPassword)
}

indra {
  javaVersions {
    target(8)
  }

  github("Mardroemmar", "truth-extensions") {
    ci(true)
  }

  mitLicense()

  configurePublications {
    pom {
      developers {
        developer {
          id.set("Proximyst")
          name.set("Mariell Hoversholm")
          timezone.set("Europe/Stockholm")
        }
      }
    }
  }
}
