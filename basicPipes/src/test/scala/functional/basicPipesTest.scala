package functional

import com.twitter.scalding.{Tsv, JobTest, FieldConversions}
import freedompipes.basicPipesJob
import org.scalatest.{Matchers, FlatSpec}

import scala.collection.mutable

/**
 * Created by klyk on 10/26/14.
 */
class basicPipesTest extends FlatSpec with Matchers with FieldConversions{

  "Functional-Test: A basicPipesJob" should "do the union of DUNS Ref and CH on hashkey and also update Signals" +
    " for RM data" in {

    val dunsRef = List(
      ("01/07/2014 10:22:11", 1000002L, "http://youtube.com"),
      ("01/07/2014 10:22:11", 1000003L, "http://twitter.com"),
      ("01/07/2014 10:22:11", 1000002L, "http://google.com"),
      ("01/07/2014 10:22:11", 1000002L, "http://facebook.com")
    )
    val ch = List(
      (1000002L, "stefano@email.com", "10 Downing St. London"),
      (1000003L, "antonios@email.com", "1 Kingdom St. London")
    )


    val expectedOutput = List(
      ("2014/07/01", 1000002L, 3L, "stefano@email.com","10 Downing St. London"),
      ("2014/07/01", 1000003L, 1L, "antonios@email.com", "1 Kingdom St. London")
    )

//    JobTest(classOf[basicPipesJob].getName)
//      .arg("input", "input-logs")
//      .arg("users", "users-logs")
//      .arg("output", "output-data")
//      .source(Tsv("input-logs", SCHEMA_D), logs)
//      .source(Tsv("users-logs", USER_SCHEMA), users)
//      .sink(Tsv("output-data", LOG_DAILY_WITH_ADDRESS)) {
//      buffer: mutable.Buffer[(String, Long, Long, String, String)] =>
//        buffer should equal(expectedOutput)
//    }
//      .run // or .runHadoop
  }
}
