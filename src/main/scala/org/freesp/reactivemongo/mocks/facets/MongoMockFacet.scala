package org.freesp.reactivemongo.mocks.facets

import org.specs2.mock.Mockito
import reactivemongo.core.commands.{ GetLastError, LastError }
import reactivemongo.api.commands.{ UpdateWriteResult, WriteResult, WriteConcern }
import org.mockito.Matchers
import play.api.libs.json.{ Reads, JsObject, OWrites }
import scala.concurrent.ExecutionContext
import reactivemongo.api.{ CursorProducer, ReadPreference }

trait MongoMockFacet extends Mockito with Logging {

  protected def mockResult(ok: Boolean): WriteResult = {
    val mockResult = mock[WriteResult]
    mockResult.ok returns ok
    mockResult
  }

  protected def mockUpdateResult(ok: Boolean): UpdateWriteResult = {
    val mockResult = mock[UpdateWriteResult]
    mockResult.ok returns ok
    mockResult
  }

  protected def bool2Success(ok: Boolean) = if (ok) "success" else "failure"

  // Useful additional matchers:

  def anyBoolean = Matchers.anyBoolean

  def anyReadPreference = Matchers.any[ReadPreference]

  def anyWriteConcern = Matchers.any[WriteConcern] //[GetLastError]

  def anyWriteResult = Matchers.any[WriteResult]

  def anyJsReads = Matchers.any[Reads[JsObject]]

  //def anyJsWrites = Matchers.any[Writes[JsObject]]
  def anyJsWrites = Matchers.any[OWrites[JsObject]]

  def anyJs = Matchers.any[JsObject]

  def anyEC = Matchers.any[ExecutionContext]

  def anyCP = Matchers.any[CursorProducer[JsObject]]
}
