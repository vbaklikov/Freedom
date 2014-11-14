package freedompipes

import cascading.tuple.Fields
import com.twitter.scalding.{Csv, Job, Args}
import org.joda.time.{Interval, DateTime}
import parallelai.spyglass.hbase.HBaseConstants.SourceMode
import parallelai.spyglass.hbase.{HBaseSource, HBasePipeConversions}
import freedompipes.basicPipesSchema._

/**
 * Created by klyk on 10/28/14.
 */
class volumetricsJob(args: Args) extends Job(args) with HBasePipeConversions {

  val tableName = args("tableName")
  val hbaseHost = args("hbaseServer")

  val fmt = org.joda.time.format.DateTimeFormat.forPattern("yyyyMMdd")

  val week = new Interval(DateTime.now(),DateTime.now().minusDays(7))


  val data = new HBaseSource(
    tableName,
    hbaseHost,
    SCHEMA_SIGNALSDB.head,
    SCHEMA_SIGNALSDB.tail.map((x: Symbol) => "data"),
    SCHEMA_SIGNALSDB.tail.map((x: Symbol) => new Fields(x.name)),
    sourceMode = SourceMode.SCAN_RANGE,
    startKey = "0",
    stopKey = "z")
    .read
    .fromBytesWritable(SCHEMA_SIGNALSDB)
    .debug
    .mapTo(('hashkeyAndDate, 'numletters, 'numparcels, 'src118, 'trade) -> ('hashkey,'date, 'total)){
      tuple5:(String, Int, Int, Int,Int) =>
        (tuple5._1.take(tuple5._1.lastIndexOf(":")),tuple5._1.takeRight(8),tuple5._2 + tuple5._3 + tuple5._4 + tuple5._5)
    }
    .groupBy('hashkey){ group => group
      .takeWhile('date){x:String => week.contains(fmt.parseDateTime(x))}
//      .sum[Int]('total -> 'totalSignals)
    }
    .write(Csv("data/signals_db.txt"," | ",writeHeader=true))


}
