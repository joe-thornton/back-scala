import java.time.LocalDate
import scala.collection.mutable

class Account() {
  private[this] val ledger: scala.collection.mutable.Set[Transaction] = scala.collection.mutable.Set.empty[Transaction]

  def deposit(amount: Double): Unit = {
    ledger += new Transaction(amount)
  }

  def withdraw(amount: Double): Unit = {
    ledger += new Transaction(-amount)
  }

  def balance(): Double = {
    ledger.foldLeft(0.0)((balance, transaction) => balance + transaction.amount)
  }
}