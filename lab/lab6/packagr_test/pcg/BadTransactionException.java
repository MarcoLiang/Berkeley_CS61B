/* BadTransactionException.java */

/**
 *  Implements an exception that should be thrown for invalid withdraw.
 **/
public class BadTransactionException extends Exception {

  public int withdraw;  // The invalid withdraw

  /**
   *  Creates an exception object for invalid withdraw.
   **/
  public BadTransactionException(int badWithdraw) {
    super("Invalid withdraw: " + badWithdraw);

    withdraw = badWithdraw;
  }
}
