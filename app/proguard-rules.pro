  # Livebox SDK ProGuard Rules
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