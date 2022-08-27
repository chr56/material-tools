# What's This?

This was once a part of [app-theme-helper](https://github.com/kabouzeid/app-theme-helper).

This is used for [Phonograph Plus](https://github.com/chr56/Phonograph_Plus).

I reorganized and refactor these codes, now this is just library to store theme color and handle
view tint.

# Setup

[![](https://jitpack.io/v/chr56/material-tools.svg)](https://jitpack.io/#chr56/material-tools)

This is a kotlin library now.

```groovy
dependencies {
    // mdColorRes: most material color values resource (non-kotlin)
    implementation("com.github.chr56.material-tools:mdColorRes:0.0.2")
    // mdUtil: utility
    implementation("com.github.chr56.material-tools:mdUtil:0.0.2")
    // mdPref: make preferences to store theme color
    implementation("com.github.chr56.material-tools:mdPref:0.0.2")
    // mdTint: tint the Views
    implementation("com.github.chr56.material-tools:mdTint:0.0.2")

    // also, you need to add `appcompat` manually
    implementation("androidx.appcompat:appcompat:1.4.1")
    // and Google material design library if you use mdTint
    implementation("com.google.android.material:material:1.4.0")
}
```

---

# Original README digest

## App Theme Helper

This is basically a copy of [App Theme Engine](https://github.com/afollestad/app-theme-engine)
by [Aidan Follestad](https://github.com/afollestad) which only includes the "Config" part. This
library is only for saving and querying theme values. The user is responsible to use those values (
applying to Views), unlike ATE this library won't automatically theme your views. As an extra this
library includes a few Util methods from ATE and myself to make theming easy.

---