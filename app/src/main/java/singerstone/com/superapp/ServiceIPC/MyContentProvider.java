package singerstone.com.superapp.ServiceIPC;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import singerstone.com.superapp.utils.L;

/**
 * author : yogachen
 * date   : 2019/2/15下午7:45
 * desc   :
 */
public class MyContentProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        L.e("MyContentProvider: onCreate");
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        L.e("MyContentProvider:" + method);
        Bundle result = new Bundle();
        result.putString("result", "success!");
        return result;
    }
}
/**

 //把下面这个代码复制到一个新的APP就可以测试
 Bundle result = null;
 // 与manifest中定义的authorities相对应
 Uri providerUri = Uri.parse("content://" + "com.singerstone.provider");
 try {
 Bundle bundle = getApplication().getContentResolver().call(providerUri,
 "get_user_token", null, null);
 log.setText(bundle.getString("result"));
 } catch (Exception e) {
 e.printStackTrace();
 }
 */