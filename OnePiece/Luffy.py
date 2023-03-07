import frida, sys

def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)

jscode_java = """
Java.perform(() => {
    // Function to hook is defined here
    const class_a = Java.use('a.auu.a');
    const method_c = class_a.c;
    console.log(class_a.c("LQoZSw8WESsEBwBPHQw9SwMXAAMVKxdaKBgyFT4JHQYABwwhCw=="));//com.netease.nis.wrapper.MyApplication
    console.log(class_a.c("LwsQFw4aAWAEBBVPMgY6DAIMFQoxJhcRBAU="));  //android.app.ActivityThread
    console.log(class_a.c("LRAGFwQdEQ8GAAwXGhE3MRwXBBIB"));  //sPackageManager
    console.log(class_a.c("PTUVBgoSAisoFQsAFAA8"));  //android.content.pm.IPackageManager
    console.log(class_a.c("LwsQFw4aAWAGGwsVFgs6SwQITzo1LwYfBAYWKC8LFQIEAQ=="));  
    //KQAANQAQDi8CESwPFQo=
    console.log(class_a.c("KQAANQAQDi8CESwPFQo="));  
    //PREBBw==
    console.log(class_a.c("PREBBw=="));  
    //"YRERCBEfDCxKGAwDAEs0DAQ="
    console.log(class_a.c("YRERCBEfDCxKGAwDAEs0DAQ="));  
    //"YRERCBEfDCw="
    console.log(class_a.c("YRERCBEfDCw="));
    console.log(class_a.c("JwsHEQAfCQAEAAwXFiknBwYEEwo1LxEcSUEVCiIBERdBVhZuDAdFCB8JKwIVCUE="));
    console.log(class_a.c("YAsRFgQQOj4EAAYJ"));
    console.log(class_a.c("KwsQRQ4VRRUADBETEgY6KR0HPFMRJwgRXw=="));

    method_c.implementation = function (in_str) {
        // Show a message to know that the function got called
        //send('onFridaHookMe');

        // Call the original onFridaHookMe handler
        var result = method_c.call(this, in_str);
        //console.log("auto call result = "+result);

        return result;
        // Set our values after running the original onClick handler
        // this.m.value = 0;
        // this.n.value = 1;
        // this.cnt.value = 999;

        // Log to the console that it's done, and we should have the flag!
       
    };
});
"""

jscode_ndk = """

function jstring2Str(jstring) { //从frida_common_funs.js中copy出来
   var ret;
   Java.perform(function() {
       var String = Java.use("java.lang.String");
       ret = Java.cast(jstring, String);//jstring->String
   });
   return ret;
}
 

Java.perform(function () {
    var str_name_so = "libNdkInterface.so";    //需要hook的so名
    var n_addr_so = Module.findBaseAddress(str_name_so); //加载到内存后 函数地址 = so地址 + 函数偏移
    send("libNdkInterface.so 基地址：" + n_addr_so);
    // 未导出的函数我们需要手动的计算出函数地址，然后将其转化成一个NativePointer的对象然后进行hook操作
    // 函数地址 = so地址.add(偏移地址 + 1)  // 是否+1 取决于cpu平台型号
    //thumb和arm指令的区分，地址最后一位的奇偶性来进行标志，所以这里还需加1
    var n_addr_func_offset = 0x8D5;         //需要hook的函数的偏移
    var n_addr_func = parseInt(n_addr_so, 16) + n_addr_func_offset;
    var ptr_func = new NativePointer(n_addr_func);
    //导出函数可以直接通过函数名获取
    //var ptr_func = Module.findExportByName(str_name_so,"Java_singerstone_com_superapp_ndkinterface_NdkInterface_getServiceName") 
    Interceptor.attach(ptr_func, {
        //onEnter: 进入该函数前要执行的代码，其中args是传入的参数，一般so层函数第一个参数都是JniEnv，第二个参数是jclass，从第三个参数开始是我们java层传入的参数
        onEnter: function (args) {
            send("Hook NDK Method start");
            //send("args[2]=" + args[2]); //第一个传入的参数
            //send("args[3]=" + args[3]); //第二个参数
        },
        onLeave: function (retval) { //onLeave: 该函数执行结束要执行的代码，其中retval参数即是返回值
            send("return:" + retval); //返回值
            console.log("函数返回old值：", jstring2Str(retval));
            var env = Java.vm.getEnv();
            var jstring = env.newStringUtf("frida hook native 你要的jstring");
            retval.replace(ptr(jstring));//修改返回值
        }
    });
});
"""

# process = frida.get_usb_device().attach('实验室')
# script = process.create_script(jscode_java)
# script.on('message', on_message)
# print('[*] Running CTF')
# script.load()
# sys.stdin.read()

process = frida.get_device_manager().add_remote_device('127.0.0.1:27042')
pid = process.spawn(['com.moutai.mall']) # app包名
session = process.attach(pid)  # 加载进程号
script = session.create_script(jscode_java) #创建js脚本
script.on('message',on_message) #加载回调函数，也就是js中执行send函数规定要执行的python函数
script.load() #加载脚本
process.resume(pid)  ########### 重启app
sys.stdin.read()