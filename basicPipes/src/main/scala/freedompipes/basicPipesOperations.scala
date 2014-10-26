package freedompipes

import cascading.pipe.Pipe
import com.twitter.scalding.Dsl
import Dsl._

/**
 * Created by klyk on 10/26/14.
 */
trait basicPipesOperations {
  def pipe : Pipe

  def sourceDunsRefCreateHashKey : Pipe = {
    pipe
      .map(('Company, 'Postcode) -> 'hashkey){
      pair:(String, String) => pair._2.concat(":").concat(pair._1).replaceAll("""\s+""","").toUpperCase
    }
  }

  def sourceCHCreateHashKey : Pipe = {
    pipe
      .map(('Company_ch,'Postcode_ch) -> 'hashkey){
      pair:(String, String) => pair._2.concat(":").concat(pair._1).replaceAll("""\s+""","").toUpperCase
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

  def royalMailCountLetters : Pipe = pipe
    .groupBy('hashkey){ group => group.size('numLetters)}


  def dunsRefJoinWithCH(chData : Pipe) : Pipe = pipe
    .joinWithSmaller('hashkey -> 'hashkey, chData)
}
