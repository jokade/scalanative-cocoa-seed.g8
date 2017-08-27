//     Project: ScalaCocoaExample
//      Module:
// Description:
package app

import cocoa.appkit.{NSApplicationClass, NSApplicationDelegate, NSTextField}
import cocoa.foundation._
import objc.ScalaObjC

@ScalaObjC
class AppDelegate(self: AppDelegate.InstanceType) extends NSApplicationDelegate {
  private var _clickCount = 0

  /* Outlets */
  var window: NSObject = _
  var clickCountView: NSTextField = _

  /* Actions */
  def takeClick(id: NSObject): Unit = {
    _clickCount += 1
    updateView()
  }

  override def applicationDidFinishLaunching(notification: NSNotification): Unit = {
    updateView()
  }

  private def updateView(): Unit = {
    clickCountView.setIntegerValue(_clickCount)
  }
}

object AppDelegate extends NSApplicationClass {
  type InstanceType = NSObject //NSApplicationDelegate
}
