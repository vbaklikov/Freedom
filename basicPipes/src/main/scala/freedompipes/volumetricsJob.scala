package freedompipes

import cascading.tuple.Fields
import com.twitter.scalding.{Csv, Job, Args}
import parallelai.spyglass.hbase.HBaseConstants.SourceMode
import parallelai.spyglass.hbase.{HBaseSource, HBasePipeConversions}
import freedompipes.basicPipesSchema._

/**
 * Created by klyk on 10/28/14.
 */
class volumetricsJob(args: Args) extends Job(args) with HBasePipeConversions {

  val tableName = args("tableName")
  val hbaseHost = args("hbaseServer")

  val data = new HBaseSource(
    tableName,
    hbaseHost,
    SCHEMA_SIGNALSDB.head,
    SCHEMA_SIGNALSDB.tail.map((x: Symbol) => "signals"),
    SCHEMA_SIGNALSDB.tail.map((x: Symbol) => new Fields(x.name)),
    sourceMode = SourceMode.SCAN_RANGE,
    startKey = "AB238YU:BALMEDIEBODYREPAIRS:20141028",
    stopKey = "S711XH:BRUCEOATES:20141101")
    .read
    .fromBytesWritable(SCHEMA_SIGNALSDB)
    .debug
    .mapTo(('hashkeyAndDate, 'numletters, 'numparcels, 'src118) -> ('hashkey,'total)){
      tuple4:(String, Int, Int, Int) => (tuple4._1.take(tuple4._1.lastIndexOf(":")),tuple4._2 + tuple4._3 + tuple4._4)
    }
    .groupBy('hashkey){ group => group
      .sum[Int]('total -> 'totalSignals)
    }
    .write(Csv("data/signals_db.txt"," | ",writeHeader=true))


}
