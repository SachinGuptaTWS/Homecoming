-keepattributes *Annotation*, InnerClasses

-keepclassmembers class kotlinx.serialization.json.** { *** Companion; }
-keepclasseswithmembers class com.homecoming.app.core.navigation.Route { *; }

-keep class com.homecoming.app.core.model.** { *; }
-keep class com.homecoming.app.feature.**ViewModel { *; }

-assumenosideeffects class android.util.Log {
    public static int v(...);
    public static int d(...);
    public static int i(...);
}
