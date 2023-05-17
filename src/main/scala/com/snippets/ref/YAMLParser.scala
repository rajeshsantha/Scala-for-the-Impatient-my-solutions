package com.snippets.ref


object YAMLParser {

  val jsonString: String =
    """
      {
        "languages": [{
            "name": "English",
            "is_active": true,
            "completeness": 2.5
        }, {
            "name": "Latin",
            "is_active": false,
            "completeness": 0.9
        }]
      }
    """.stripMargin

  def main(args: Array[String]): Unit = {
/*
    val result = JSON.parseFull(jsonString)
    result match {
      // Matches if jsonStr is valid JSON and represents a Map of Strings to Any
      case Some(map: Map[String, Any]) => println(map)
      case None => println("Parsing failed")
      case other => println("Unknown data structure: " + other)
    }
*/

  }
}
