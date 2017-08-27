//     Project: ScalaCocoaExample
//      Module:
// Description:
package app

import cocoa.appkit.AppKit

object Main {
  def main(args: Array[String]): Unit = {
    AppDelegate.__cls  // register AppDelegate ObjC class
    AppKit.NSApplicationMain(0,null)
  }
}
