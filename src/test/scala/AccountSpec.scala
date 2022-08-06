import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory

import java.time.{Clock, Instant, ZoneId}

class AccountSpec extends AnyWordSpec with Matchers {

  "An account" should {
    "return the correct balance for a single deposit" in {
      val account = new Account()
      account.deposit(10)
      account.balance shouldEqual 10
    }
  }

}
