package app

import scalanative.native._
import objc._
import cocoa.foundation._
import cocoa.appkit._

@ScalaObjC
class AppDelegate(self: id) extends NSObject with NSApplicationDelegate {
  private var _clickCount = 0

  /* Outlets */
  var window: NSWindow = _
  var clickCountView: NSTextField = _

  /* Actions */
  def takeClick_(id: NSObject): Unit = {
    _clickCount += 1
    updateView()
  }

  override def applicationDidFinishLaunching_(notification: NSNotification): Unit = {
    updateView()
  }

  private def updateView(): Unit = {
    clickCountView.setIntegerValue_(_clickCount)
  }
}

object AppDelegate extends NSApplicationClass {
  type InstanceType = AppDelegate
}
