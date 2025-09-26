androidApplication {
    namespace = "org.example.app"

    dependencies {
        // AndroidX core and appcompat
        implementation("androidx.core:core-ktx:1.13.1")
        implementation("androidx.appcompat:appcompat:1.7.0")

        // Material components for modern UI
        implementation("com.google.android.material:material:1.12.0")

        // RecyclerView for notes list
        implementation("androidx.recyclerview:recyclerview:1.3.2")

        // ConstraintLayout for flexible layouts
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")

        // Navigation Fragment and UI (non-Compose)
        implementation("androidx.navigation:navigation-fragment-ktx:2.8.3")
        implementation("androidx.navigation:navigation-ui-ktx:2.8.3")

        // Lifecycle ViewModel
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")

        // Remove sample dependencies
        // implementation("org.apache.commons:commons-text:1.11.0")
        // implementation(project(":utilities"))
    }
}
