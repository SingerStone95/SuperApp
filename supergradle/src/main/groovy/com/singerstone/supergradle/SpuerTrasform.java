package com.singerstone.supergradle;


import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInvocation;

import java.io.IOException;
import java.util.Set;

/**
 * author : yogachen
 * date   : 2019/3/15下午5:37
 * desc   :
 */
public class SpuerTrasform extends Transform {


    @Override
    public String getName() {
        return "SpuerTrasform";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return null;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return null;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
    }
}
