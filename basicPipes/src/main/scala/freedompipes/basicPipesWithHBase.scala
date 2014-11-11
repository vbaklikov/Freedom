package freedompipes

import cascading.tuple.Fields
import com.twitter.scalding.{Tsv, Job, Args}
import parallelai.spyglass.hbase.HBaseConstants.SourceMode
import parallelai.spyglass.hbase.{HBaseSource, HBasePipeConversions}

/**
 * Created by klyk on 10/25/14.
 */
class basicPipesWithHBase(args: Args) extends Job(args) with HBasePipeConversions{

  val SCHEMA = List('key, 'numletters, 'numparcels, 'src118)
  val tableName = "signals_db"
  val hbaseHost = "localhost:2181"
  //  val hbaseHost = "5.10.85.234:2181"
//  val hbaseHost = "159.253.148.147:2181"

  val data = new HBaseSource(
    tableName,
    hbaseHost,
    SCHEMA.head,
    SCHEMA.tail.map((x: Symbol) => "signals"),
    SCHEMA.tail.map((x: Symbol) => new Fields(x.name)),
    sourceMode = SourceMode.SCAN_ALL)
    .read
    .fromBytesWritable(SCHEMA)
    .debug
    .write(Tsv("data/hbase.tsv", writeHeader=true))

}
