import frida, sys

def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)

jscode_java = """
/*Java.perform(() => {
// 打印所有的内存中的 so
    var enumMoudle = Process.enumerateModules();
      for (var i = 0; i < enumMoudle.length; i++){
        console.log("", enumMoudle[i].name)
      }
});*/


var pth = Module.findExportByName(null,"open");
    Interceptor.attach(ptr(pth),{
        onEnter:function(args){
            this.filename = args[0];
            console.log("",this.filename.readCString())
            if (this.filename.readCString().indexOf(".so") != -1){
                args[0] = ptr(0)
 
            }
 
        },onLeave:function(retval){
            return retval;
        }
    })
"""

# process = frida.get_usb_device().attach('实验室')
# script = process.create_script(jscode_java)
# script.on('message', on_message)
# print('[*] Running CTF')
# script.load()
# sys.stdin.read()

process = frida.get_usb_device()
pid = process.spawn(['实验室']) # app包名
session = process.attach(pid)  # 加载进程号
script = session.create_script(jscode) #创建js脚本
script.on('message',on_message) #加载回调函数，也就是js中执行send函数规定要执行的python函数
script.load() #加载脚本
process.resume(pid)  ########### 重启app
sys.stdin.read()