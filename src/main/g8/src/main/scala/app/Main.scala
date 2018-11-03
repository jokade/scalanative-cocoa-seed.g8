package app

import cocoa.foundation._
import cocoa.appkit._

object Main {
  def main(args: Array[String]): Unit = {
    registerClass(AppDelegate) 
    NSApplicationMain()
  }
}
