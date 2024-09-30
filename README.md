```
brew install asdf
asdf plugin add kotlin https://github.com/asdf-community/asdf-kotlin.git
asdf install kotlin 1.9.21
asdf global kotlin 1.9.21
```

```
cd KH11
kotlinc A.kt -include-runtime -d A.jar
java -jar A.jar > out.txt
```
