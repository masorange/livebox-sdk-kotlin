# Livebox SDK ProGuard Rules
# These rules are automatically applied to projects that consume this SDK

# Keep all SDK model classes (data classes, enums, sealed classes)
-keep class es.masorange.livebox.sdk.domain.** { *; }
-keepclassmembers class es.masorange.livebox.sdk.domain.** { *; }

# Keep all network adapters and their methods
-keep class es.masorange.livebox.sdk.network.adapters.** { *; }
-keepclassmembers class es.masorange.livebox.sdk.network.adapters.** { *; }

# Keep methods annotated with @FromJson and @ToJson (custom Moshi adapters)
-keepclassmembers class * {
    @com.squareup.moshi.FromJson <methods>;
    @com.squareup.moshi.ToJson <methods>;
}

# Keep Moshi annotations on all SDK classes
-keepclassmembers class es.masorange.livebox.sdk.** {
    @com.squareup.moshi.Json <fields>;
    @com.squareup.moshi.JsonClass <fields>;
}

# Keep generated JsonAdapters
-if class es.masorange.livebox.sdk.**
-keep class <1>JsonAdapter {
    <init>(...);
    <fields>;
}

# Keep JsonAdapter.Factory implementations
-keep class * implements com.squareup.moshi.JsonAdapter$Factory {
    <init>();
    <methods>;
}

# Keep all public API classes (controllers, repositories, etc.)
-keep public class es.masorange.livebox.sdk.** { *; }

# Preserve enum classes and their values
-keepclassmembers enum es.masorange.livebox.sdk.** {
    <fields>;
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
