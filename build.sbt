import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

name := "courio-core"
organization in ThisBuild := "io.cour"
version in ThisBuild := "1.0.3-SNAPSHOT"
scalaVersion in ThisBuild := "2.13.1"

resolvers in ThisBuild ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)
scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation", "-feature")

publishTo in ThisBuild := sonatypePublishToBundle.value
sonatypeProfileName in ThisBuild := "io.cour"
publishMavenStyle in ThisBuild := true
licenses in ThisBuild := Seq("MIT" -> url("https://github.com/courio/core/blob/master/LICENSE"))
sonatypeProjectHosting in ThisBuild := Some(xerial.sbt.Sonatype.GitHubHosting("courio", "core", "contact@courio.com"))
homepage in ThisBuild := Some(url("https://courio.com"))
scmInfo in ThisBuild := Some(
  ScmInfo(
    url("https://github.com/courio/core"),
    "scm:git@github.com:courio/core.git"
  )
)
developers in ThisBuild := List(
  Developer(id="darkfrog", name="Matt Hicks", email="matt@matthicks.com", url=url("http://matthicks.com"))
)

val youiVersion = "0.12.0"
val scarangoVersion = "2.1.5"

lazy val root = project.in(file("."))
  .aggregate(coreJS, coreJVM)
  .settings(
    publish := {},
    publishLocal := {}
  )

lazy val core = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("core"))
  .settings(
    name := "courio-core",
    libraryDependencies ++= Seq(
      "io.youi" %%% "youi-core" % youiVersion,
      "com.outr" %%% "scarango-core" % scarangoVersion
    )
  )

lazy val coreJS = core.js
lazy val coreJVM = core.jvm