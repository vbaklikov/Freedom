package freedompipes

import cascading.tuple.Fields
import com.twitter.scalding.{Csv, Job, Args}
import parallelai.spyglass.hbase.{HBaseSource, HBasePipeConversions}
import freedompipes.basicPipesSchema._
import freedompipes.basicPipesWrapper._

/**
 * Created by klyk on 10/27/14.
 */
class sourceCHJob(args: Args) extends Job(args) with HBasePipeConversions {

  val tableName = args("tableName")
  val hbaseHost = args("hbaseServer")

  val sourceCH = Csv(args("inputFile"), ",", SCHEMA_CH_ORIG, skipHeader = true)
    .read
    .sourceDunsRefCreateHashKey
    .toBytesWritable('hashkey :: SCHEMA_CH_ORIG)
    .debug
    .write(new HBaseSource(
    tableName,
    hbaseHost,
    'hashkey,
    SCHEMA_CH_ORIG.map((x: Symbol) => "ch"),
    SCHEMA_CH_ORIG.map((x: Symbol) => new Fields(x.name)),
    useSalt = false))

}
