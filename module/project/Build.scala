import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "mfz-play-module-solrsearch"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "org.apache.solr" % "solr-solrj" % "4.3.0",
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    organization := "com.mfizz",
    organizationName := "Mfizz Inc",
    organizationHomepage := Some(new URL("http://mfizz.com")),
    
    // required for publishing artifact to maven central via sonatype
    publishMavenStyle := true,
    publishTo <<= version { v: String =>
	  val nexus = "https://oss.sonatype.org/"
	  if (v.trim.endsWith("SNAPSHOT"))
	    Some("snapshots" at nexus + "content/repositories/snapshots")
	  else
	    Some("releases" at nexus + "service/local/staging/deploy/maven2")
	},
	
	// in order to pass sonatype's requirements the following properties are required as well
	startYear := Some(2013),
	description := "Play framework 2.x module to simplify indexing and searching documents with Apache Solr (Lucene)",
    licenses := Seq("Apache 2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    homepage := Some(url("http://mfizz.com/oss/play-module-solrsearch")),
    scmInfo := Some(ScmInfo(url("https://github.com/mfizz-inc/play-module-solrsearch"), "https://github.com/mfizz-inc/play-module-solrsearch.git")),
    pomExtra := (
      <developers>
        <developer>
    	  <name>Mfizz Inc (twitter: @mfizz_inc)</name>
          <email>oss@mfizz.com</email>
        </developer>
        <developer>
    	  <name>Joe Lauer (twitter: @jjlauer)</name>
        </developer>
      </developers>
    )    
  )

}
