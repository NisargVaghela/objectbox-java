<p align="center"><img width="466" src="https://raw.githubusercontent.com/objectbox/objectbox-java/master/logo.png"></p>

<p align="center">
  <a href="https://docs.objectbox.io/getting-started">Getting Started</a> •
  <a href="https://docs.objectbox.io">Documentation</a> •
  <a href="https://github.com/objectbox/objectbox-examples">Example Apps</a> •
  <a href="https://github.com/objectbox/objectbox-java/issues">Issues</a>
</p>

<p align="center">
  <a href="https://docs.objectbox.io/#objectbox-changelog">
    <img src="https://img.shields.io/github/v/release/objectbox/objectbox-java?color=7DDC7D&style=flat-square" alt="Latest Release">
  </a>
  <img src="https://img.shields.io/github/stars/objectbox/objectbox-java?color=17A6A6&logo=github&style=flat-square" alt="Star objectbox-java">
  <a href="https://github.com/objectbox/objectbox-java/blob/main/LICENSE.txt">
    <img src="https://img.shields.io/github/license/objectbox/objectbox-java?color=7DDC7D&logo=apache&style=flat-square" alt="Apache 2.0 license">
  </a>
  <a href="https://twitter.com/ObjectBox_io">
    <img src="https://img.shields.io/twitter/follow/objectbox_io?color=%20%2300aced&logo=twitter&style=flat-square" alt="Follow @ObjectBox_io">
  </a>
</p>

# Java Database (+ Kotlin, Android) for sustainable local data storage

Database for Java that's embedded into your Android, Linux, macOS, or Windows app. Store and manage data efficiently and effortlessly.

### Demo code

```java
// Java
Playlist playlist = new Playlist("My Favorites");
playlist.songs.add(new Song("Lalala"));
playlist.songs.add(new Song("Lololo"));
box.put(playlist);
```

```kotlin
// Kotlin
val playlist = Playlist("My Favorites")
playlist.songs.add(Song("Lalala"))
playlist.songs.add(Song("Lololo"))
box.put(playlist)
```
[More details in the docs.](https://docs.objectbox.io/)

## Table of Contents
- [Why use ObjectBox](#why-use-objectbox)
  - [Features](#features)
- [How to get started](#how-to-get-started)
  - [Gradle setup](#gradle-setup)
  - [First steps](#first-steps)
- [Already using ObjectBox?](#already-using-objectbox)
- [Other languages/bindings](#other-languagesbindings)
- [License](#license)


## Why use ObjectBox for Java data management?

This NoSQL Java database is built for storing data locally on mobile devices. It is optimized for high efficiency on restricted devices and uses minimal CPU and RAM. Being fully ACID-compliant, ObjectBox is faster than any alternative, outperforming SQLite and Realm across all CRUD (Create, Read, Update, Delete) operations. Check out our [Performance Benchmarking App repository](https://github.com/objectbox/objectbox-performance).

Additionally, our concise API is easy to learn and only requires a fraction of the code compared to SQLite. No more rows or columns, just plain objects with built-in relations. It's also build for handling large data volumes and allows changing your model whenever needed.

All of this makes ObjectBox a sustainable choice for Java data persistence - it's efficient, green, and scalable.

### Features

🏁 **High performance** on restricted devices, like IoT gateways, micro controllers, ECUs etc.\
🪂 **Resourceful** with minimal CPU, power and Memory usage for maximum flexibility and sustainability\
🔗 **[Relations](https://docs.objectbox.io/relations):** object links / relationships are built-in\
💻 **Multiplatform:** Linux, Windows, Android, iOS, macOS

🌱 **Scalable:** handling millions of objects resource-efficiently with ease\
💐 **[Queries](https://docs.objectbox.io/queries):** filter data as needed, even across relations\
🦮 **Statically typed:** compile time checks & optimizations\
📃 **Automatic schema migrations:** no update scripts needed

**And much more than just data persistence**\
🔄 **[ObjectBox Sync](https://objectbox.io/sync/):** keeps data in sync between devices and servers\
🕒 **[ObjectBox TS](https://objectbox.io/time-series-database/):** time series extension for time based data

## How to get started
### Gradle setup

For Android projects, add the ObjectBox Gradle plugin to your root `build.gradle`: 

```groovy
buildscript {
    ext.objectboxVersion = "3.3.1"
    repositories {        
        mavenCentral()    
    }
    dependencies {
        classpath("io.objectbox:objectbox-gradle-plugin:$objectboxVersion")
    }
}
```

And in your app's `build.gradle` apply the plugin:

```groovy
// Using plugins syntax:
plugins {
    id("io.objectbox") // Add after other plugins.
}

// Or using the old apply syntax:
apply plugin: "io.objectbox" // Add after other plugins.
```

### First steps

Create a data object class `@Entity`, for example "Playlist".
```
// Kotlin
@Entity data class Playlist( ... )

// Java
@Entity public class Playlist { ... }
```
Now build the project to let ObjectBox generate the class `MyObjectBox` for you.

Prepare the BoxStore object once for your app, e.g. in `onCreate` in your Application class:

```java
boxStore = MyObjectBox.builder().androidContext(this).build();
```

Then get a `Box` class for the Playlist entity class:

```java
Box<Playlist> box = boxStore.boxFor(Playlist.class);
```

The `Box` object gives you access to all major functions, like `put`, `get`, `remove`, and `query`.

For details please check the [docs](https://docs.objectbox.io).     

## Already using ObjectBox?

❤ **Your opinion matters to us!** Please fill in this 2-minute [Anonymous Feedback Form](https://forms.gle/bdktGBUmL4m48ruj7).

We believe, ObjectBox is super easy to use. We are on a mission to make developers’ lives better, by building developer tools that are intuitive and fun to code with. To do that, we want your feedback: what do you love? What's amiss? Where do you struggle in everyday app development?

**We're looking forward to receiving your comments and requests:**
- Add [GitHub issues](https://github.com/ObjectBox/objectbox-java/issues) 
- Upvote issues you find important by hitting the 👍/+1 reaction button
- Drop us a line via [@ObjectBox_io](https://twitter.com/ObjectBox_io/) or contact[at]objectbox.io
- ⭐ us, if you like what you see 

Thank you! 🙏

Keep in touch: For general news on ObjectBox, [check our blog](https://objectbox.io/blog)!

## Other languages/bindings

ObjectBox supports multiple platforms and languages.
Besides JVM based languages like Java and Kotlin, ObjectBox also offers: 

* [ObjectBox Swift Database](https://github.com/objectbox/objectbox-swift): build fast mobile apps for iOS (and macOS) 
* [ObjectBox Dart/Flutter Database](https://github.com/objectbox/objectbox-dart): cross-platform for mobile and desktop apps 
* [ObjectBox Go Database](https://github.com/objectbox/objectbox-go): great for data-driven tools and embedded server applications 
* [ObjectBox C and C++ Database](https://github.com/objectbox/objectbox-c): native speed with zero copy access to FlatBuffer objects


## License

    Copyright 2017-2022 ObjectBox Ltd. All rights reserved.
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
