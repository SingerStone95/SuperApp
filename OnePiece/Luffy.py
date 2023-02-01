import frida, sys

def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)

jscode = """
Java.perform(() => {
  // Function to hook is defined here
  const MainActivity = Java.use('singerstone.com.superapp.MainActivity');

  // Whenever button is clicked
  const onFridaHookMe = MainActivity.onFridaHookMe;
  onFridaHookMe.implementation = function (v) {
    // Show a message to know that the function got called
    send('onFridaHookMe');

    // Call the original onFridaHookMe handler
    onFridaHookMe.call(this, v);

    // Set our values after running the original onClick handler
    // this.m.value = 0;
    // this.n.value = 1;
    // this.cnt.value = 999;

    // Log to the console that it's done, and we should have the flag!
    console.log('Done: onFridaHookMe');
  };
});
"""

process = frida.get_usb_device().attach('实验室')
script = process.create_script(jscode)
script.on('message', on_message)
print('[*] Running CTF')
script.load()
sys.stdin.read()