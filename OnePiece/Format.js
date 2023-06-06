Java.perform(() => {
    // Function to hook is defined here
    const class_a = Java.use('com.anythink.unitybridge.videoad.VideoHelper');
    const VideoListener = Java.use("com.anythink.unitybridge.videoad.VideoListener");
    const Object = Java.use("java.lang.Object");
    class_a.isAdReady.implementation = function () {
        console.log("isAdReady called");
        return true;
    }

    class_a.$init.overload('com.anythink.unitybridge.videoad.VideoListener').implementation = function (listener) {

        this.$init(listener);
        console.log("hook VideoHelper $init :", JSON.stringify(listener));
        var video_listener_instance = Java.cast(listener, Object);
        // console.log("hook VideoHelper $init :", video_listener_instance.hashcode());
        var throwable = Java.use("android.util.Log").getStackTraceString(Java.use("java.lang.Throwable").$new());
        console.log(throwable);
    }

    var Runnable = Java.use("java.lang.Runnable");

    var DisableSecureRunnable = Java.registerClass({
        name: "com.anythink.unitybridge.videoad.InnerRunnable",
        implements: [Runnable],
        fields: {
            videoListener: 'com.anythink.unitybridge.videoad.VideoListener'
        },
        methods: {
            $init: [{
                returnType: "void",
                argumentTypes: ["com.anythink.unitybridge.videoad.VideoListener"],
                implementation: function (listener) {
                    this.videoListener.value = listener;
                }
            }],
            run: function () {
                var Thread = Java.use('java.lang.Thread');
                console.log("run :", JSON.stringify(this.videoListener.value));
                var json = { "id": "8f3a08d2dd47ec075f47f74602fa9b2a_1636622_1685615334549", "publisher_revenue": 0.043925324040000005, "currency": "CNY", "country": "CN", "adunit_id": "b5f168c17ebdba", "adunit_format": "RewardedVideo", "precision": "exact", "network_type": "Network", "network_placement_id": "7003015881328834", "ecpm_level": 0, "segment_id": 0, "scenario_reward_name": "reward_item", "scenario_reward_number": 1, "network_firm_id": 8, "adsource_id": "1636622", "adsource_index": 2, "adsource_price": 43.92532404000001, "adsource_isheaderbidding": 1, "ext_info": { "mp": -1, "is_reward_ad": false, "request_id": "pmjvwo46ct2yq01", "gdt_trans_id": "c46bafaf3917e2c2d18c2d1bb29d805d", "token": "W8IBAuv1B5UZglI10Y-sgexZvei6-P2GgIyxKiauSGXor37nxZBg4YLABZJXKf9iOSvoFiFz7IFlCVlQ_A4ec_E2KVj1YlL0QqjyhkNCRYJmdqmZOZCxcIP4USTw3-OKDDj9FYvtQ7WSrDrx5B6VMkpNyRL7MSO1QUeAXQ0zUSBLaPPUVgGIkwUM6bTfdE6SQkcUVg" }, "reward_custom_data": "", "tp_bid_id": "eyJiaWRfaWQiOiJkOTAzODc4Yy0xYzk2LTQ1YWUtYmU5ZS1kYWVhZWIwNDhkYmYiLCJ2ZXIiOiJhZHhQbHVzIn0=", "dismiss_type": 3, "abtest_id": 24622 };
                this.videoListener.value.onRewardedVideoAdAgainPlayStart('b5f168c17ebdba', JSON.stringify(json));
                console.log("onRewardedVideoAdAgainPlayStart");
                Thread.sleep(1000);
                this.videoListener.value.onReward('b5f168c17ebdba', JSON.stringify(json));
                console.log("onReward");
                Thread.sleep(1000);
                this.videoListener.value.onRewardedVideoAdPlayEnd('b5f168c17ebdba', JSON.stringify(json));
                console.log("onRewardedVideoAdPlayEnd");
                Thread.sleep(1000);
                this.videoListener.value.onRewardedVideoAdClosed('b5f168c17ebdba', false, JSON.stringify(json));
                console.log("onRewardedVideoAdClosed");
            }
        }
    });


    //创建完类对象中，即可通过类对象调用对象的方法，如下所示
    class_a.showVideo.implementation = function (str) {
        console.log("showVideo invoke param str=" + str);
        Java.choose('com.anythink.unitybridge.videoad.VideoHelper', {
            onMatch: function (instance) {
                var listener = instance.mListener.value;
                // 调用  TaskManager.getInstance().run_proxy
                Java.choose("com.anythink.unitybridge.utils.TaskManager", {
                    "onMatch": function (instance2) {
                        var runnable = DisableSecureRunnable.$new(listener);
                        instance2.run_proxy(runnable);

                    },
                    "onComplete": function () { }
                });


            },
            onComplete: function () {

            }
        });


    }
});