var cmd=require('node-cmd');

cmd.get(
    'react-native bundle --platform android --dev false --entry-file index.android.js --bundle-output E:/AS_Proj/SuperAPP/app.pluginrn/src/main/assets/index.android.bundle',
    function(err, data, stderr){
        if(err){
        console.log(err);
        }else{
        console.log(data)
        }
    }
);