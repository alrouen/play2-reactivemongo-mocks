package org.freesp.reactivemongo.test

import org.freesp.reactivemongo.mocks.MongoMocks
import org.freesp.reactivemongo.mocks.facets.Logging

import scala.concurrent.duration.Duration
import scala.concurrent.{ Future, Await }
import scala.concurrent.ExecutionContext.Implicits.global
import org.specs2.matcher._
import org.specs2.specification.Scope
import org.specs2.mutable.Specification
import reactivemongo.api.commands.WriteResult
import play.modules.reactivemongo.json.collection.JSONCollection
import play.modules.reactivemongo.json._
import play.api.libs.json.JsObject
import play.api.libs.json.JsNumber

trait CommonMongoTests extends Logging
    with MustThrownMatchers
    with MongoTestFixtures {
  val shortWait = Duration(100L, "millis")

  protected class MockedCollectionScope extends Scope {
    val testSpec = new Specification with MongoMocks {
      val coll = mockedCollection("foo")
    }

    val c = testSpec.coll
  }

  def haveThrownTheTestThrowable = {
    throwAn[UnsupportedOperationException].like {
      case e: UnsupportedOperationException => e must beEqualTo(testThrowable)
    }
  }

  def findOne(c: JSONCollection, thingToFind: JsObject): Option[JsObject] = {
    val qb = c.find(thingToFind)
    Await.result(qb.one[JsObject], shortWait)
  }

  def findCursorHeadOption(c: JSONCollection, thingToFind: JsObject): Option[JsObject] = {
    val qb = c.find(thingToFind)
    Await.result(qb.cursor[JsObject].headOption, shortWait)
  }

  def findCursorCollect(c: JSONCollection, thingToFind: JsObject): List[JsObject] = {
    findCursorCollect(c, thingToFind, Int.MaxValue, true)
  }

  def findCursorCollect(c: JSONCollection, thingToFind: JsObject, upTo: Int, stopOnErr: Boolean): List[JsObject] = {
    val qb = c.find(thingToFind)
    Await.result(qb.cursor[JsObject].collect[List](upTo, stopOnErr), shortWait)
  }

  def resultOf(op: => Future[WriteResult]): Boolean = {
    Await.result(op, shortWait).ok
  }
}
