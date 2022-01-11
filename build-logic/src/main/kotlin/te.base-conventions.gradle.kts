import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
  id("te.publishing")
  id("net.kyori.indra")
  id("com.adarshr.test-logger")
  java
  jacoco
}

repositories {
  mavenCentral()
}

testlogger {
  theme = ThemeType.PLAIN_PARALLEL
}

configurations {
  testImplementation {
    exclude(group = "junit")
  }

  testCompileClasspath {
    exclude(group = "junit")
  }
}

tasks {
  withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
  }

  javadoc {
    val opt = options as StandardJavadocDocletOptions
    opt.addStringOption("Xdoclint:none", "-quiet")

    opt.encoding("UTF-8")
    opt.charSet("UTF-8")
    doFirst {
      opt.links(
        "https://docs.oracle.com/javase/8/docs/api/",
      )
    }
  }
}
