plugins {
  id("te.publishing")
  alias(libs.plugins.indra.publishing.sonatype)
}

group = "dev.mardroemmar"
version = "0.1.0"
description = "Commonly wanted extensions for Truth."

indraSonatype {
  this.useAlternateSonatypeOSSHost("s01")
}
