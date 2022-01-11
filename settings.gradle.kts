enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
  includeBuild("build-logic")
}

rootProject.name = "truth-extensions"
proj("bom")
proj("currency")
proj("time")

fun proj(path: String, name: String = "${rootProject.name}-${path.replace('/', '-')}") {
  include(path)
  val proj = project(":${path.replace('/', ':')}")
  proj.name = name
}
