package freedompipes

import cascading.tuple.Fields
import com.twitter.scalding.{Csv, Job, Args}
import freedompipes.basicPipesSchema._
import freedompipes.basicPipesWrapper._
import parallelai.spyglass.hbase.{HBaseSource, HBasePipeConversions}

/**
 * Created by klyk on 10/27/14.
 */
class sourceRMJob(args: Args) extends Job(args) with HBasePipeConversions {

  val tableName = args("tableName")
  val hbaseHost = args("hbaseServer")

  val partialSignalsSchema = List('hashkey,'numletters)

  val sourceRM = Csv(args("inputFile"),",",fields = SCHEMA_RM, skipHeader = true)
    .read
    .sourceRMCreateHashKey
    .royalMailCountLetters
    .addTrap(Csv("data/error/errorRM"))
    .toBytesWritable(partialSignalsSchema)
    .debug
    .write(new HBaseSource(
      tableName,
      hbaseHost,
      'hashkey,
      partialSignalsSchema.tail.map((x: Symbol) => "signals"),
      partialSignalsSchema.tail.map((x: Symbol) => new Fields(x.name)),
      useSalt = false))

}
