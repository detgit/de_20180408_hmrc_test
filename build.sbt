name := "de_20180408_hmrc_test"

version := "0.1"

scalaVersion := "2.12.5"


libraryDependencies ++= {
  // Scalatest 3.0.0 supports 2.12.0 but it drops Scala 2.10 support. So just use 2.2.6 for Scala 2.10.
  val scalatestVersion = if (scalaVersion.value.startsWith("2.10")) "2.2.6" else "3.0.0"
  Seq(
    //    "io.reactivex" % "rxjava" % "1.2.4",
    "io.reactivex" %% "rxscala" % "0.26.5"

    // Do not want for the moment.
    //,
    //    "org.mockito" % "mockito-core" % "1.9.5" % "test",
    //    "junit" % "junit" % "4.11" % "test",
    //    "org.scalatest" %% "scalatest" % scalatestVersion % "test"
  )
}

