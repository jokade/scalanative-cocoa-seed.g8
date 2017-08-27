//
//  AppDelegate.h
//  $name$
//

#import <Cocoa/Cocoa.h>

@interface AppDelegate : NSObject<NSApplicationDelegate>

@property (weak) IBOutlet NSWindow *window;
@property (weak) IBOutlet NSTextField *clickCountView;

- (IBAction)takeClick:(id)sender;

@end
