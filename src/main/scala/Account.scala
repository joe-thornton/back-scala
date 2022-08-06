import java.time.LocalDate
import scala.collection.mutable

class Account(val statement: StatementBase = StatementGenerator) {
  private[this] val ledger: scala.collection.mutable.Set[Transaction] = scala.collection.mutable.Set.empty[Transaction]

  def deposit(amount: Double): Unit = {
    ledger += new Transaction(amount)
  }

  def withdraw(amount: Double): Unit = {
    if (sufficientFunds(amount)) ledger += new Transaction(-amount)
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