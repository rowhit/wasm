# `wasm`
WebAssembly for Scala

Provide some AST and a way to convert them to wasm binary. There is also a printer for binary wasm.

There is two way to use `wasm`, either as a cli or a library.

# install
It's using `sbt` for building, simply ensure that you have it.

To ensure that it's working, you may want to run `sbt test` prior to using `wasm`.

## cli
It can be used directly from the `sbt` prompt, there is no system-wide binary installation.

## library
To add to the local ivy storage
```sh
sbt publishLocal
```
and add `libraryDependencies += "default" %% "scalawasm" % "0.1"` to your `build.sbt`

# use

## cli
To compile wat file to wasm file
```sh
sbt run compile watfile1 watfile2 ...
```

To print the content of a wasm file
```sh
sbt run print wasmfile1 wasmfile2 ...
```

## library
The main target is to be able to have a library to generate wasm, usable in scala, from a compiler perspective.

The compilation pipeline and data type used at each stage is
```
pipeline         text.Lexer -> text.Parser -> binary.ToBinaryAst -> binary.ToBinary
datatype    String  ->  ast.Token   ->   ast.Tree     ->     ast.Binary   ->   Stream[Byte]
```

The most suitable coding entrypoint is `wasm.ast.Tree`, it provide a convenient, yet nearly binary, tree.

A good code starter is
```scala
for {
  ast <- Module(
    name = Some("My Module"),
    funcs = Seq(
      Function(
        sig = Signature.Function(),
        instrs = Seq(
          Opcode.Nop,
      ))))
  p <- ToBinaryAst(ast)
} yield ToBinary(p)

```