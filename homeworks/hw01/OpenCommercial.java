/* OpenCommercial.java */

import java.net.*;
import java.io.*;

/**  A class that provides a main function to read five lines of a commercial
 *   Web page and print them in reverse order, given the name of a company.
 */

class OpenCommercial {

  /** Prompts the user for the name X of a company (a single string), opens
   *  the Web site corresponding to www.X.com, and prints the first five lines
   *  of the Web page in reverse order.
   *  @param arg is not used.
   *  @exception Exception thrown if there are any problems parsing the 
   *             user's input or opening the connection.
   */
  private String address;
  private String[] Text;

  public static void main(String[] arg) throws Exception {

    BufferedReader keyboard;
    String inputLine;

    keyboard = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("Please enter the name of a company (without spaces): ");
    System.out.flush();        /* Make sure the line is printed immediately. */
    inputLine = keyboard.readLine();

    OpenCommercial start = new OpenCommercial(inputLine);
    start.get_part_of_web(5);
    start.reverse_print();

  }
  public OpenCommercial(String company) {
    address = "http://www.".concat(company).concat(".com");

  }

  public void get_part_of_web(int num_of_line) throws IOException{
    URL url = new URL(address);
    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
    for(int index = 0; index < num_of_line; index++)
      Text[index] = br.readLine();
  }

  public void reverse_print(){
    for(int index = Text.length - 1; index >= 0; index--)
      System.out.println(Text[index]);
  }




}
