import scala.collection.mutable
import com.github.nscala_time.time.Imports._

class Account(val statement: StatementBase = StatementGenerator) {
  private[this] val ledger: scala.collection.mutable.Set[Transaction] = scala.collection.mutable.Set.empty[Transaction]

  def deposit(amount: Double, dateAndTime: LocalDateTime = LocalDateTime.now()): Unit = {
    ledger += new Transaction(amount, dateAndTime)
  }

  def withdraw(amount: Double, dateAndTime: LocalDateTime = LocalDateTime.now()): Unit = {
    if (sufficientFunds(amount)) ledger += new Transaction(-amount, dateAndTime)
    else throw new Exception("Insufficient funds")
  }

  def balance(): Double = {
    ledger.foldLeft(0.0)((balance, transaction) => balance + transaction.amount)
  }

  def printStatement(): Unit = {
    statement.print(ledger: scala.collection.mutable.Set[Transaction])
  }

  private def sufficientFunds(amount: Double): Boolean = {
    if (amount <= balance()) true
    else false
  }
}