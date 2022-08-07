import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory
import com.github.nscala_time.time.Imports._

class AccountSpec extends AnyWordSpec with Matchers with MockFactory {

  "An account" should {
    "return the correct balance for a single deposit" in {
      val account = new Account()
      account.deposit(10)
      account.balance shouldEqual 10
    }
  }

  "An account" should {
    "return the correct balance for 2 deposits" in {
      val account = new Account()
      account.deposit(10)
      account.deposit(20)
      account.balance shouldEqual 30
    }
  }

  "An account" should {
    "be able to withdraw money" in {
      val account = new Account()
      account.deposit(20)
      account.withdraw(10)
      account.balance shouldEqual 10
    }
  }

  "An account" should {
    "not be able to withdraw more than its balance" in {
      val account = new Account()
      val error = intercept[Exception] {
        account.withdraw(30)
      }
      assert(error.getMessage === "Insufficient funds")
    }
  }

  "An account" should {
    "call print statement with the ledger" in {
      val mockStatement = mock[StatementBase]
      val account = new Account(mockStatement)
      val transactionDate = new LocalDateTime("2022-07-27T11:39:45.618")
      val mockLedger = scala.collection.mutable.ArrayBuffer(Transaction(10, transactionDate))
      account.deposit(10, transactionDate)
      (mockStatement.print _).expects(mockLedger)
      account.printStatement()
    }
  }

}
