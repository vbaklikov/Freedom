package freedompipes

import java.text.SimpleDateFormat
import java.util.Calendar

import cascading.pipe.Pipe
import com.twitter.scalding.Dsl
import Dsl._

/**
 * Created by klyk on 10/26/14.
 */
trait basicPipesOperations {
  def pipe : Pipe

  val fmt = org.joda.time.format.DateTimeFormat.forPattern("yyyyMMdd")

  def sourceDunsRefCreateHashKey : Pipe = {
    pipe
      .map(('CompanyName, 'RegAddressPostCode) -> 'hashkey){
      pair:(String, String) => pair._2.concat(":").concat(pair._1).replaceAll("""\s+""","").toUpperCase
    }
  }

  def sourceCHCreateHashKey : Pipe = {
    pipe
      .map(('Company_ch,'Postcode_ch) -> 'hashkey){
      pair:(String, String) => pair._2.concat(":").concat(pair._1).replaceAll("""\s+""","").toUpperCase
    }
  }

  def source118DRCreateHashKeyAndTimestamp : Pipe = {
    pipe
      .map(('name, 'postalcode) -> ('hashkey, 'hashkeyAndDate)){
      pair:(String, String) => (pair._2.concat(":").concat(pair._1).replaceAll("""\s+""","").toUpperCase,
        pair._2.concat(":").concat(pair._1).replaceAll("""\s+""","").toUpperCase.concat(":").concat("20141029"))
    }
  }

  def source118DRCreateHashKey : Pipe = {
    pipe
      .map(('name, 'postalcode) -> 'hashkey){
      pair:(String, String) => pair._2.concat(":").concat(pair._1).replaceAll("""\s+""","").toUpperCase
    }
  }

  def sourceRMCreateHashKey : Pipe = {
    pipe
      .map(('Company,'Postcode ) -> 'hashkey){
      pair:(String, String) => pair._2.concat(":").concat(pair._1).replaceAll("""\s+""","").toUpperCase
    }
  }

  def royalMailCountLetters : Pipe = {
    pipe
      .groupBy('hashkey) { group => group
        .size('numletters)
      }
  }


  def dunsRefJoinWithCH(chData : Pipe) : Pipe = pipe
    .joinWithSmaller('hashkey -> 'hashkey, chData)

}
