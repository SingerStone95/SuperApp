import frida, sys

def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)

jscode_java = """
Java.perform(() => {
    // Function to hook is defined here
    const class_a = Java.use('com.anythink.unitybridge.videoad.VideoHelper');
    class_a.isAdReady.implementation = function () {
        console.log("isAdReady called");
        return true;

    }

    //创建完类对象中，即可通过类对象调用对象的方法，如下所示
    class_a.showVideo.implementation = function (str) {
        console.log("method_c invoke str=" + str);

        var listener;
        Java.choose('com.anythink.unitybridge.videoad.VideoHelper', {
            onMatch: function (instance) {
                listener = instance.mListener.value;
                console.log('找到实例listener：' + listener);

                var json = { "id": "8f3a08d2dd47ec075f47f74602fa9b2a_1636622_1685615334549", "publisher_revenue": 0.043925324040000005, "currency": "CNY", "country": "CN", "adunit_id": "b5f168c17ebdba", "adunit_format": "RewardedVideo", "precision": "exact", "network_type": "Network", "network_placement_id": "7003015881328834", "ecpm_level": 0, "segment_id": 0, "scenario_reward_name": "reward_item", "scenario_reward_number": 1, "network_firm_id": 8, "adsource_id": "1636622", "adsource_index": 2, "adsource_price": 43.92532404000001, "adsource_isheaderbidding": 1, "ext_info": { "mp": -1, "is_reward_ad": false, "request_id": "pmjvwo46ct2yq01", "gdt_trans_id": "c46bafaf3917e2c2d18c2d1bb29d805d", "token": "W8IBAuv1B5UZglI10Y-sgexZvei6-P2GgIyxKiauSGXor37nxZBg4YLABZJXKf9iOSvoFiFz7IFlCVlQ_A4ec_E2KVj1YlL0QqjyhkNCRYJmdqmZOZCxcIP4USTw3-OKDDj9FYvtQ7WSrDrx5B6VMkpNyRL7MSO1QUeAXQ0zUSBLaPPUVgGIkwUM6bTfdE6SQkcUVg" }, "reward_custom_data": "", "tp_bid_id": "eyJiaWRfaWQiOiJkOTAzODc4Yy0xYzk2LTQ1YWUtYmU5ZS1kYWVhZWIwNDhkYmYiLCJ2ZXIiOiJhZHhQbHVzIn0=", "dismiss_type": 3, "abtest_id": 24622 };
                console.log(JSON.stringify(json));
                listener.onRewardedVideoAdAgainPlayStart('b5f168c17ebdba', '{"id": "8f3a08d2dd47ec075f47f74602fa9b2a_1636622_1685615334549"}');
                console.log("onRewardedVideoAdAgainPlayStart");
                listener.onReward('b5f168c17ebdba', '{"id": "8f3a08d2dd47ec075f47f74602fa9b2a_1636622_1685615334549"}');
                console.log("onReward");
                listener.onRewardedVideoAdPlayEnd('b5f168c17ebdba','{"id": "8f3a08d2dd47ec075f47f74602fa9b2a_1636622_1685615334549"}');
                console.log("onRewardedVideoAdPlayEnd");
                listener.onRewardedVideoAdClosed('b5f168c17ebdba', false, '{"id": "8f3a08d2dd47ec075f47f74602fa9b2a_1636622_1685615334549"}');
                console.log("onRewardedVideoAdClosed");

            },
            onComplete: function () {

            }
        });


    }
});
"""

process = frida.get_device_manager().add_remote_device('127.0.0.1:27042')
pid = process.spawn(['com.ccyiyo.story']) # app包名
session = process.attach(pid)  # 加载进程号
script = session.create_script(jscode_java) #创建js脚本
script.on('message',on_message) #加载回调函数，也就是js中执行send函数规定要执行的python函数
script.load() #加载脚本
process.resume(pid)  ########### 重启app
sys.stdin.read()