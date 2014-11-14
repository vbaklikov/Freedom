package unit

import cascading.pipe.Pipe
import com.twitter.scalding.bdd.BddDsl
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, FlatSpec}

import scala.collection.mutable

import freedompipes.basicPipesSchema._
import freedompipes.basicPipesWrapper
/**
 * Created by klyk on 10/26/14.
 */
//@RunWith(classOf[JUnitRunner])
class basicPipesOperationsUnitTests extends FlatSpec with Matchers with BddDsl{

  import basicPipesWrapper._

  "Unit-Test: A pipe transformation for DUNS Ref data source" should "add column with hashkey POST:NAME " +
    "with uppercase and trimmed empty spaces" in {
    Given {
      List(("4278","Molestie Sed LLP","Ap #417-366 Donec Rd.","XK1H 2BG","(01678) 342187",
        "Lorem,mattis.")) withSchema SCHEMA_118DR_ORIG
    } When {
      pipe:Pipe => pipe.sourceDunsRefCreateHashKey
    } Then {
      buffer: mutable.Buffer[SCHEMA_118DR_ORIG.type] =>
        buffer.toList.print()
        buffer should contain (("FK77UU:AACOMPONENTS"))
    }
  }

}
