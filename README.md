# Custom Chat Emotes _for Fabric_

A buildable source version of [AlgidSpace's](https://authors.curseforge.com/members/algidspace) mod
[Fabric Emotes](https://www.curseforge.com/minecraft/mc-mods/fabric-emotes).
All credit goes to them :D

This version is fully buildable from the source files and includes some minor bug fixes.


## How to add your own custom emotes

Adding custom emotes to the mod is pretty simple. 

### Regular Emotes

Regular emotes can be added in the `\src\main\resources\assets\customemotes\emotes` folder.

>They need to be `.png` files, preferably under 512x512 in size. 

Then add the images to `EmoteRegistry.java` in the `init()` catch block.

```java
// EmoteRegistry.java
public void init() {
    this.emoteIdMap = new ConcurrentHashMap<>();
    this.emoteMap = new ConcurrentHashMap<>();
        
    try { 
        // Copy the following line, edit it based off your needs
        registerEmote(new Emote("MyExtremelyAwesomeEmote", "awesomeemote.png"));
        ...
```

### Animated Emotes

Animated emotes can be added to the same `\src\main\resources\assets\customemotes\emotes` folder, 
but it's better if you make a **separate folder inside**, to differentiate them from the regular ones.

> **Animated emotes need to be formatted correctly. (Vertically stacked frames in a .png file)**

If you're using linux, here's an easy way to convert a `.gif` emote to the proper `.png` format:
1. Install imagemagick and graphicsmagick. If you're on ubuntu, run the following:

    `sudo apt install graphicsmagick` - For dumping the frames

    `sudo apt install imagemagick` - For combining the frames into a .png

2. Dump all the frames from the `.gif` to separate files: `gm convert ../../animated.gif +adjoin %3d.png`
3. Combine all the dumped frames into one vertical `.png`:  `convert -append *.png out.png`
4. Rename the `out.png` file to whatever you want
5. Move the renamed file to `\src\main\resources\assets\customemotes\emotes\animated`
6. Add this to your `EmoteRegistry.java` `init()` catch block.
```java
// EmoteRegistry.java
public void init() {
    this.emoteIdMap = new ConcurrentHashMap<>();
    this.emoteMap = new ConcurrentHashMap<>();
        
    try { 
        // Copy the following line, edit it based off your needs
        registerEmote(new Emote("AnimatedEmote", "animated/animated.png", 60)); // The last number is the time per frame, 60 is 0.6 s
        ...
```

    