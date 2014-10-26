package unit

import cascading.pipe.Pipe
import com.twitter.scalding.bdd.BddDsl
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, FlatSpec}

import scala.collection.mutable

import freedompipes.basicPipesSchema.SCHEMA_DUNSREF
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
      List(List("1111","11032","905760265","A A Components","Unit 3","10 Munro Road","Springkerse Industrial Estate",
        "STIRLING","Stirlingshire","FK7 7UU","1786834040","","Engineers Merchants","634","51870","Wholesale",
        "trade and navigation","46180","Agents","5084","INDUSTRIAL","1964","WA","S","","5","www.aa-components.com",
        "customerservices@aacomponents.co.uk","Mr","Martin","Ainslie","Manager","10000000000","Mr","Norman",
        "Ainsley","Managing Director","0","Mr","Norman","Ainslie","Managing Director","0","Mr","","Ainsliey",
        "Partner","0","","","","","","","","","","","","","","","","25-Sep-14","C","NULL")) withSchema SCHEMA_DUNSREF
    } When {
      pipe:Pipe => pipe.sourceDunsRefCreateHashKey
    } Then {
      buffer: mutable.Buffer[SCHEMA_DUNSREF.type] =>
        buffer.print()
        buffer should contain (("FK77UU:AACOMPONENTS"))
    }
  }

}
