package freedompipes

import cascading.pipe.Pipe
import com.twitter.scalding.RichPipe

/**
 * Created by klyk on 10/26/14.
 */
object basicPipesWrapper {
  implicit class basicPipesOperationsWrapper(val pipe:Pipe) extends basicPipesOperations with Serializable
  implicit def wrapPipe(rp: RichPipe) : basicPipesOperationsWrapper = new basicPipesOperationsWrapper(rp.pipe)
}
