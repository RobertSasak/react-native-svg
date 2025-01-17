/*
 * Copyright (c) 2015-present, Horcrux.
 * All rights reserved.
 *
 * This source code is licensed under the MIT-style license found in the
 * LICENSE file in the root directory of this source tree.
 */


package com.horcrux.svg;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import static com.horcrux.svg.RenderableViewManager.*;

public class SvgPackage implements ReactPackage {

    @Nonnull
    @Override
    public List<ViewManager> createViewManagers(@Nonnull ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
                new GroupViewManager(),
                new PathViewManager(),
                new CircleViewManager(),
                new EllipseViewManager(),
                new LineViewManager(),
                new RectViewManager(),
                new TextViewManager(),
                new TSpanViewManager(),
                new TextPathViewManager(),
                new ImageViewManager(),
                new ClipPathViewManager(),
                new DefsViewManager(),
                new UseViewManager(),
                new SymbolManager(),
                new LinearGradientManager(),
                new RadialGradientManager(),
                new PatternManager(),
                new MaskManager(),
                new ForeignObjectManager(),
                new MarkerManager(),
                new SvgViewManager());
    }

    @Nonnull
    @Override
    public List<NativeModule> createNativeModules(@Nonnull ReactApplicationContext reactContext) {
        if (BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
            // For Fabric, we load c++ native library here, this triggers svg's Fabric
            // component registration which is necessary in order to avoid asking users
            // to manually add init calls in their application code.
            // This should no longer be needed if RN's autolink mechanism has Fabric support
            SoLoader.loadLibrary("rnsvg_modules");
        }
        return Arrays.<NativeModule>asList(
                new SvgViewModule(reactContext),
                new RNSVGRenderableManager(reactContext)
        );
    }

    @SuppressWarnings("unused")
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }
}
