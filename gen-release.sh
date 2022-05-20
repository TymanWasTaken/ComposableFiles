
rm releases/* # Clean releases dir
./gradlew assembleRelease # Assemble release apk
cp app/build/outputs/apk/release/app-release.apk releases/ComposableFiles.apk # Copy apk to releases dir 
gpg --output releases/ComposableFiles.apk.sig --detach-sig releases/ComposableFiles.apk # Sign apk with gpg
cp app/build/outputs/mapping/release/mapping.txt releases/proguard-mappings.txt # Copy proguard mappings
gpg --output releases/proguard-mappings.txt.sig --detach-sig releases/proguard-mappings.txt # Sign mappings with gpg