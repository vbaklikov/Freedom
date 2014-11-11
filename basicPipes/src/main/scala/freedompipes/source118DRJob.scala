package freedompipes

import cascading.tuple.Fields
import com.twitter.scalding.{Csv, Tsv, Job, Args}
import parallelai.spyglass.hbase.HBaseConstants.SourceMode
import parallelai.spyglass.hbase.{HBaseSource, HBasePipeConversions}

/**
 * Created by klyk on 10/27/14.
 */
class source118DRJob(args: Args) extends Job(args) with HBasePipeConversions{

  import freedompipes.basicPipesSchema._
  import freedompipes.basicPipesWrapper._

  val tableName = args("tableName")
  val hbaseHost = args("hbaseServer")


  val source118DR = Csv(args("inputFile"),",",SCHEMA_118DR, skipHeader = true)
    .read
    .source118DRCreateHashKeyAndTimestamp

  val rosettaInsert =  source118DR
    .toBytesWritable('hashkey :: SCHEMA_118DR)
    .debug
    .write(new HBaseSource(
    tableName,
    hbaseHost,
    'hashkey,
    SCHEMA_118DR.map((x: Symbol) => "dr"),
    SCHEMA_118DR.map((x: Symbol) => new Fields(x.name)),
    useSalt = false))

  val source118DRforSignals = source118DR
    .project('hashkeyAndDate)
    .groupBy('hashkeyAndDate){ group => group.size('src118)}
    .toBytesWritable(List('hashkeyAndDate, 'src118))
    .debug
    .write(new HBaseSource(
    "signals_db",
    hbaseHost,
    'hashkeyAndDate,
    List("signals"),
    List(new Fields("src118")),
    useSalt = false))




}
