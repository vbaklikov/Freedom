package freedompipes

import cascading.tuple.Fields
import com.twitter.scalding.{Tsv, Args, Csv, Job}
import parallelai.spyglass.hbase.{HBasePipeConversions, HBaseSource}

/**
 * Created by klyk on 10/27/14.
 */
class sourceTradeBGJob(args: Args) extends Job(args) with HBasePipeConversions{

  import freedompipes.basicPipesSchema._
  import freedompipes.basicPipesWrapper._

  val tableName = args("tableName")
  val hbaseHost = args("hbaseServer")

  val sourceTradeBG = Tsv(args("inputFile"),fields = SCHEMA_TRADE, skipHeader = true)
    .read
    .sourceTradeBGCreateHashKeyAndTimestamp
    .addTrap(Csv("data/error/Trade/TradeBGBadRecords"))

//  val rosettaInsert =  sourceTradeBG
//    .toBytesWritable('hashkey :: SCHEMA_TRADE)
//    .debug
//    .write(new HBaseSource(
//    tableName,
//    hbaseHost,
//    'hashkey,
//    SCHEMA_TRADE.map((x: Symbol) => "trade"),
//    SCHEMA_TRADE.map((x: Symbol) => new Fields(x.name)),
//    useSalt = false))

  val signalsInsert = sourceTradeBG
    .project('hashkeyAndDate)
    .groupBy('hashkeyAndDate){ group => group.size('trade)}
    .toBytesWritable(List('hashkeyAndDate, 'trade))
    .debug
    .write(new HBaseSource(
    "signals_db",
    hbaseHost,
    'hashkeyAndDate,
    List("data"),
    List(new Fields("trade")),
    useSalt = false))

}
