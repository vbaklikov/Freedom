
package freedompipes

import com.twitter.scalding.{Args, Csv, Job}

/**
 * Created by klyk on 10/24/14.
 */
class basicPipesJob(args: Args) extends Job(args){
  import freedompipes.basicPipesWrapper._
  import freedompipes.basicPipesSchema._


//    val source118DR = Csv(args("input1"),",",SCHEMA_118DR, skipHeader = true)
//      .read
//      .map(('name, 'postalcode) -> 'hashkey){
//        pair:(String, String) => pair._2.concat(":").concat(pair._1).replaceAll("""\s+""","").toUpperCase
//      }
//  //    .write(Csv(args("output"),separator=",",fields=SCHEMA_118DR_WITHKEY))

    val sourceDUNSREF = Csv(args("input2"),",",fields = SCHEMA_DUNSREF, skipHeader = true)
      .read
      .project('Duns,'Company,'Postcode)
      .sourceDunsRefCreateHashKey
      .addTrap(Csv("data/error/errorDUNSRef.txt"))

    val sourceCH = Csv(args("input3"),",",fields = SCHEMA_CH, skipHeader = true)
      .read
      .sourceCHCreateHashKey
      .addTrap(Csv("data/error/errorCH.txt"))

    val combinedRosetta = sourceDUNSREF
      .dunsRefJoinWithCH(sourceCH)
      .write(Csv("data/rosetta.txt"," | ",writeHeader=true))

    val sourceRM = Csv(args("input4"),",",fields = SCHEMA_RM, skipHeader = true)
      .read
      .sourceRMCreateHashKey
      .royalMailCountLetters
      .addTrap(Csv("data/error/errorRM"))
      .write(Csv("data/signals_db.txt"," | ",writeHeader=true))


}
