# Livebox SDK ProGuard Rules
# These rules are automatically applied to projects that consume this SDK

# Keep all SDK classes to prevent obfuscation issues with Moshi/Retrofit
-keep class es.masorange.livebox.sdk.** { *; }
-keepclassmembers class es.masorange.livebox.sdk.** { *; }

# Keep Moshi annotations
-keepclassmembers class es.masorange.livebox.sdk.** {
    @com.squareup.moshi.* <fields>;
}

# Keep generated JsonAdapters
-if class es.masorange.livebox.sdk.**
-keep class <1>JsonAdapter {
    <init>(...);
    <fields>;
}
