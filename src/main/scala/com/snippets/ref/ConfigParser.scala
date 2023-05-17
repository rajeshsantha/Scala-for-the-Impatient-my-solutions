package com.snippets.ref

import java.io.File

import com.typesafe.config.{Config, ConfigFactory}

////For parsing application.conf
//libraryDependencies += "com.typesafe" % "config" % "1.2.1"
object ConfigParser {

  val appConfPath = "application.conf"

  def main(args: Array[String]): Unit = {

    val parsedConfig = ConfigFactory.parseFile(new File("src/main/resources/application2.conf"))
      .getConfig("com.project.credentials")
    val snowflakeConf = parsedConfig.getConfig("snowflake")

    val teamXCredentials = returnTeamSpecificConfigMap(snowflakeConf, "ForTeam_X")
    val teamYCredentials = returnTeamSpecificConfigMap(snowflakeConf, "ForTeam_Y")

    println("*********teamXCredentials***********\n")
    teamXCredentials.foreach { case (key, value) => println(s"$key = $value") }
    println("\n\n*********teamYCredentials***********\n")
    teamYCredentials.foreach { case (key, value) => println(s"$key = $value") }

  }

  def returnTeamSpecificConfigMap(configRoot: Config, teamName: String): Map[String, String] = {
    val snowFlakeURL = configRoot.getString("sfUrl")

    val teamRoot = configRoot.getConfig(teamName)

    val sfDbName = teamRoot.getString("databaseName")
    val sfSchemaName = teamRoot.getString("schemaName")
    val sfUser = teamRoot.getString("user")
    val sfPassword = teamRoot.getString("password")
    val sfWarehouseName = teamRoot.getString("warehouseName")

    val sfOptions: Map[String, String] = Map(
      "sfURL" -> snowFlakeURL,
      "sfUser" -> sfUser,
      "sfPassword" -> sfPassword,
      "sfDatabase" -> sfDbName,
      "sfSchema" -> sfSchemaName,
      "sfWarehouse" -> sfWarehouseName
    )
    sfOptions
  }
}
