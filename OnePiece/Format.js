Java.perform(function () {
    var str_name_so = "libNdkInterface.so";    //需要hook的so名
    var n_addr_so = Module.findBaseAddress(str_name_so); //加载到内存后 函数地址 = so地址 + 函数偏移
    send("libNdkInterface.so 基地址：" + n_addr_so);
    if (n_addr_so) {
        send("step 1...");
        var method_getservice_name = Module.findExportByName(str_name_so, "Java_singerstone_com_superapp_ndkinterface_NdkInterface_getServiceName");
        console.log("method_getservice_name 地址：", method_getservice_name);
    }
    //未导出的函数我们需要手动的计算出函数地址，然后将其转化成一个NativePointer的对象然后进行hook操作
    // 函数地址 = so地址.add(偏移地址 + 1)  // 是否+1 取决于cpu平台型号
    //thumb和arm指令的区分，地址最后一位的奇偶性来进行标志，所以这里还需加1
    var n_addr_func_offset = 0x8D4 + 1;         //需要hook的函数的偏移
    var n_addr_func = parseInt(n_addr_so, 16) + n_addr_func_offset;
    send("method_getservice_name 地址：" + n_addr_func);
    var ptr_func = new NativePointer(n_addr_func);
    //var ptr_func = Module.findExportByName(str_name_so,"Java_singerstone_com_superapp_ndkinterface_NdkInterface_getServiceName") //对函数名hook(动态)
    send("step 2...");
    Interceptor.attach(ptr_func, {
        //onEnter: 进入该函数前要执行的代码，其中args是传入的参数，一般so层函数第一个参数都是JniEnv，第二个参数是jclass，从第三个参数开始是我们java层传入的参数
        onEnter: function (args) {
            send("Hook NDK Method start");
            //send("args[2]=" + args[2]); //第一个传入的参数
            //send("args[3]=" + args[3]); //第二个参数
        },
        onLeave: function (retval) { //onLeave: 该函数执行结束要执行的代码，其中retval参数即是返回值
            send("return:" + retval); //返回值
            //retval.replace('hook ndk success'); //替换返回值为100
        }
    });
});

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


Java.perform(() => {
    // Function to hook is defined here
    const class_a = Java.use('a.auu.a');

    // Whenever button is clicked
    const method_c = class_a.c;
    console.log(method_c);
    method_c.implementation = function (in_str) {
        // Show a message to know that the function got called
        //send('onFridaHookMe');

        // Call the original onFridaHookMe handler
        var result = method_c.call(this, in_str);
        console.log("result="+result);
        return result;

        // Set our values after running the original onClick handler
        // this.m.value = 0;
        // this.n.value = 1;
        // this.cnt.value = 999;

        // Log to the console that it's done, and we should have the flag!
       
    };
});