---
layout: single
title: Getting Started
permalink: /setup/
---

# If you are looking for the old setup help page, [click here.](/SQLI/start/)

# Table of Contents

- [Java Setup](#Java Initial Setup)

- [Pathing Help for Windows](#Pathing)

- [Antlr4 and Grammar Setup](#antlr4)

- [Maven Setup](#Maven)

- [Configuration](#Config)

- [Ruby Setup](#Ruby)

- [PowerShell for OSX 10.11 and lower](#Powershell on Mac Notes)


# Overall Notes

Note that some scripts are outdated due to using different build environments, but are kept for both backwards compatibility and to allow future maintainers to learn from and potentially use the code, if they find a use for it. Anything referencing "**make**", "**CMake**", or "**Ant**" can be assumed to not work with the antlr code, and the same goes for the jar and init commands. The install files options are still the same, however, so feel free to use that for setting up if needed.

Java is the main dependency for this project at this state, though using Powershell as it is (surprisingly) cross-platform is a good idea if you would like to go the script route.

The [Powershell Github repository](https://github.com/PowerShell/PowerShell#get-powershell) is a great place to start should you choose to use it (Windows has this by default, too).

With that said, there are Bash shell scripts, as well as Windows cmd batch and Mac command files available as well.

## <a name="Java Initial Setup"></a> Java Initial Setup

Java will need to be setup on your system in order to run this program. As it is currently a command line only tool (though this is set to change) you will need to setup a JDK for your system and ensure that command line tools can use it. 

Please note that our development versions of Java are currently between 10 and 11; if you cannot run the jar due to an "Unsupported Class Error", either update or let the team know so we can aim for higher compatibility, if possible. Below are steps to install and/or setup Java, depending on your system OS:

### Linux (Ubuntu-based)

Simply open up a terminal and run the command:

`sudo apt install default-jre`

This should get you the most recent Java installation up and running without too much hassle. 

If for some reason you do not receive Java 11 with this command, you can try 
this series instead:

```bash
sudo apt-get install openjdk-11-jdk
```

However, chances are high that this particular command will actually install openjdk **10**, as an oversight since the adoption of JDK 11 is not 100% ready for all systems. It doesn't hurt to try, but if it doesn't work, you can add the repo directly and pull from there:

```bash
sudo add-apt-repository ppa:openjdk-r/ppa \
&& sudo apt-get update -q \
&& sudo apt install -y openjdk-11-jdk
```
This one is a bit safer, since it just adds the repo keys for the open JDK,but if you are not running on an Ubuntu derivative, it may fail for you. The next one will work for all Linux-based systems:

```bash
wget https://download.java.net/java/GA/jdk11/28/GPL/openjdk-11+28_linux-x64_bin.tar.gz -O /tmp/openjdk-11+28_linux-x64_bin.tar.gz

sudo tar xfvz /tmp/openjdk-11+28_linux-x64_bin.tar.gz --directory /usr/lib/jvm

rm -f /tmp/openjdk-11+28_linux-x64_bin.tar.gz
```

### Windows

First, install Java 11 from [this site here](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html).

You will want the Windows file that ends in `.exe`. That install will allow you to run Java programs everywhere but the command line by default.

To get command line Java up and running, follow the instructions [located here](https://javatutorial.net/set-java-home-windows-10). If you have trouble with this guide, a few more are provided down below.

This will allow you to run java commands from any command line instance, which is not strictly necessary, but highly useful for any Java development on Windows.

If you are running Windows 10, you could also take advantage of the [ability to run Linux as an application inside Windows](https://docs.microsoft.com/en-us/windows/wsl/install-win10).

How-ToGeek has a good guide on that as well [located here](https://www.howtogeek.com/249966/how-to-install-and-use-the-linux-bash-shell-on-windows-10/).

### Mac

_Older systems may not be able to use some of the tools; if this is an issue, please let the team know and we may be able to help you. The test system for Mac OSX is currently on 10.13.6, but some consideration was taken for 10.10.5 while the system was still on that version._

With that said, installing the newest Java install is pretty simple. Just go grab the [Java 11 JDK .DMG](https://www.oracle.com/technetwork/java/javase/downloads/index.html). Run that, follow what the archive asks, and you’re good to go!

You could also install [Homebrew](https://brew.sh/), then run `brew cask install java` and let it do the work for you. Homebrew is wonderful for development on Mac in general, since options are otherwise limited.

If that doesn’t sound good, there is another option in [MacPorts](https:/www.macports.org/install.php), just be aware that you’ll need Xcode installed to use this properly while Homebrew does not.

## <a name="Pathing"></a> Pathing

If, for some reason, you need to run a seperate Java install or the Windows instructions were not clear, this is the offical Oracle documentation about the PATH variable for all systems (including Solaris, for anyone who happens to use that):

<https://java.com/en/download/help/path.xml>

<https://docs.oracle.com/javase/tutorial/essential/environment/paths.html>

Additionally, some parts of this project may call for pathing help outside of java specifically; if this is the case, this post has all the infomation you would ever need (and more links!):

<https://superuser.com/questions/284342/what-are-path-and-other-environment-variables-and-how-can-i-set-or-use-them>

## <a name="antlr4"></a> Antlr4 and Grammar Setup

Antlr integration is vital for this project, as it will scan and detect most of the attack types listed with no interaction, and for those it does not automatically detect, it will massively reduce the workload to find the issues. As such, please ensure that everything in this section is setup properly!

Using the [ANTLR website](https://www.antlr.org/) should show you instructions for your operating system (and if not, the three dots in the Quick Start windows controls that), but there are some things you may want to add for each OS on top of that.

This [mega tutorial](https://tomassetti.me/antlr-mega-tutorial/) can run you through how this tool is supposed to work, although the Java-specific implementation notes are a little lacking compared to other languages. It's still the best all-around resource for getting started, though.

### Linux

While not strictly necessary, to make working with ANTLR much easier, you will want to setup variables for the tools in your path. The way to do this varies from system to system, but for my Kubuntu install, adding the following to my .bashrc in my home directory did the trick.

**Replace *antlr-4.7.2-complete.jar* with whatever version you are using.**

```bash
export CLASSPATH=".:/usr/local/lib/antlr-4.7.2-complete.jar:$CLASSPATH"
alias grun='java -Xmx500M -cp "/usr/local/lib/antlr-4.7.2-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig'
```
Note that I placed the antlr jar file into the */usr/local/lib/* directory; you can choose anywhere as long as you are consistent, but it is wise to put it in one of the common library directories.

You can also run the command

`sudo apt install antlr4`

to get the libraries from a repo, just in case the jar isn't working for you. Keep the grun alias either way, it will be very useful for showing AST diagrams of your queries after running through the grammar.

### Windows

The install instructions from the ANTLR website are actually pretty complete here, though they expect you to be able to learn about the PATH variables (refer to [this section of the guide](/setup/#Pathing) for extra help). The only major note to add is that you may wish to create a folder just for your libraries where you put both the jar and batch files, such as `C:\lib`.

### Mac

Much like the Linux version, in both extra steps and tips. However, instead of using the package manager (since OSX doesn't come with one), you can use Homebrew to install the tool for you. Just run

`brew install antlr` 

and you should be set. 

## <a name="Maven"></a> Maven Setup

Maven bridges an important gap between the stand-alone framework (which at one point relied on handwritten parsing to get all information, not just for web crawling and extracting SQL queries from Java code) and the antlr4 libraries. Therefore, without a working Maven install, there is no working project.

You will need a basic Maven install to get started, since this build framework will automatically download needed tools and libraries based on the pom.xml. With that said, **DO NOT** mess with the pom.xml without a backup and some knowledge of what you are doing; you cannot make the two projects cooperate without it or another similar build framework, at least as far as I know.

For all OSes, if you are not planning/cannot use a package manager, [this site here](https://www.baeldung.com/install-maven-on-windows-linux-mac) will provide basic setup instructions no matter what kind of system you run.

### Windows

The install scripts with the project include the Chocolately method of installing Maven, but if you want to do it manually, try this website out:

[Manual Maven Setup](https://docs.wso2.com/display/IS323/Installing+Apache+Maven+on+Windows)

Chocolately will take care of your PATH for you, but the site also shows you how to set it up properly.

### Linux

`sudo apt install maven`

works for any Ubuntu derivative, and similar commands for different distributions should work, as well.

If for some reason that won't work, the source files are available here:

[Maven Source Files](http://maven.apache.org/download.cgi)

### Mac OSX

`brew install maven`

works for Homebrew users. Otherwise, the setup is much the same as the source file method described up above in the main header and the "Linux" section. There is apparently problems with the default High Sierra install, so be wary of that and follow Baeldungs instructions to avoid that.

## <a name="Config"></a> Configuration

With all of the major tools setup, you can now work in the command line on the main project with no issues! However, you may prefer to work with an IDE for the extra features they bring, and even if you don't, the config options offered can reduce the need for scripts to perform common actions. The following setups are for Intellij, but might be applicable to other IDEs with some work/luck.

### Initial Setup

Once you first pull down the repo (especially if you pull it straight from Github), you may notice that the default project directory is set to the 

`{SQLI directory}/src/main` or `{SQLI directory}\src\main`

directory; this is a problem, since all the default locations for everything is at the root of the entire project. You'll need to fix this by adding a new module in your Project Structure screen (File > Project Structure or Ctrl+Alt+Shift+S, then the Module tab, and the "plus" sign there). It should have the content root as your root directory, and sources in the src/main/generated-sources location, as shown in the picture below:

![Intellij Root Setup](../setup_1.png "Root Setup")

You should be able to delete the old one if everything goes well. I've also found that dropping the files into a new project seems to sidestep this issue, if it's still giving you trouble.

### Maven Lifecycle

The next thing you'll want to do is make sure that the Maven sidebar on the right side of the screen has detected your pom.xml; either hitting the "refresh" icon to have Intellij scan again or manually adding with the "plus" sign might be necessary if you can't get it up and running. It should look a little something like this once it's done:

![Maven Lifecycle Setup](../maven_setup.png "Lifecycle Setup")

This will allow you to easily run individual Maven tasks, which may make debugging any Maven issues much less tedious for you. Just click on the one you want and hit the green "play" button in the Maven sidebar.


### Test Configs

While you can most certainly integrate actual JUnit tests into Maven projects, we did not get that fair in our project, and cannot comment on that. However, for "hand-testing", so to speak, there is the option to setup different configurations of running the frame.jar once you have compilied it. To take a look at them, go to where the "BigQuery Interactive Verbose" option is in the below screenshot:

![Configs Setup](../setup_2.png "Individual Configs Setup")

First off, you must hit the plus sign and select "JAR Application" to get the proper items to shown up, and then you can input the proper configuration information. For example, I've setup the framework to run with the 

- "CLI input filename" -f
- "CLI safe query filename" -d
- "prompt user for output filename" -i
- "verbose output" -v
- "overwrite any output files without prompting" -w

flags set, as well as the input filename and safe query filename, in that order. The picture below shows this setup to run everytime I click the green button shown in the last picture, and I can pick the other examples once setup using the dropdown:

![BigQuery Interactive Setup](../setup_3.png "Interactive Setup")

![BigQuery Interactive Setup Zoom](../setup_3b.png "Interactive Setup Zoom")


As a different example using the same input and safe query, but setup to run the "CLI output root filename" flag as well, including the output filename inbetween the two other filenames:

![BigQuery Verbose Setup](../setup_4.png "Verbose Setup")

![BigQuery Verbose Setup Zoom](../setup_4b.png "Verbose Setup Zoom")

These two, and the help doc on the repo/website/running the -h command will help you make the CLI work as efficiently as possible. However, besides just running the jar with various options, you can also configure presets for Maven to run, just in case you need different options to run for whatever reason. I personally only used one setup,

`mvn clean compile package exec:java`

but that doesn't mean that you have to. Below is how to set that up in Maven to work with the dropdown menus like the JAR application:

![Maven Dropdown Setup](../setup_5.png "Maven Dropdown Setup")

![Maven Dropdown Setup Zoom](../setup_5b.png "Maven Dropdown Setup Zoom")

This should be enough to get you going with some easy-to-use manual testing of features, and even just reduce the amount you type by using the tools available to you. Keep in mind that all of the program install scripts can install Intellij for you; it's now in every level of the setup. Or you can set it up manually, your call.

## <a name="Ruby"></a> Ruby

Once it is available, if you would like to use the website hosting tools used to make this site, you will need to have Ruby installed and ready for deployment. 

For all platforms, a terminal will need to be opened and the following command ran:

`gem install bundler`

Jekyll, and therefore Github Pages, needs this technology to do anything.

Run this on all platforms to install all needed bundles, check for updates, and begin hosting the page locally:

```Ruby
bundle install
bundle update
bundle exec jekyll serve
```

If the website info is released, these will be built into scripts for ease of use.

### Linux Install

Run this command in order to install Ruby and the dependencies for Pages:

`sudo apt-get install build-essential patch ruby-dev zlib1g-dev liblzma-dev ruby-all-dev`

### Windows Install

Download the [RubyInstaller for Windows](https://rubyinstaller.org/) package, and install it. 

This will fix the PATH for you, but if something goes wrong there, the above Java documentation may be able to help.

Powershell or CMD will be able to run Ruby now.

### Mac Install

Do not use the built-in Ruby install for this! While you may not end up causing any problems, most advice I found while creating this guide indicated that this is a terrible idea, since it is quite a pain to revert it if something should go wrong. Instead, you'll want to setup a separate, most likely updated, install of Ruby that is free to be edited as needed.

Installing this updated version, at least from my testing, proved to be a bit cumbersome. [This Stack Overflow page](https://stackoverflow.com/questions/38194032/how-to-update-ruby-version-2-0-0-to-the-latest-version-in-mac-osx-yosemite) offers two ways to approach this problem; while the first, accepted solution is what I got working on my system, and is 100% recommended for anyone planning to seriously use Ruby outside of this project, the second answer, titled “**Brew only solution**” should be attempted first.

Simply put, the first solution installs the whole Ruby development kit, which is unnecessary for simply running the server gem included here. [Homebrew](https://brew.sh/) will handle just what is needed for the script, be more lightweight, and is more versatile 
in that it can install other apps.

## <a name="Powershell on Mac Notes"></a> Powershell on Mac Notes

If you happen to be using some older install of OSX (specifically, 10.11 or lower), you won’t be able to install the latest version of Powershell due to dependency issues. If you really, really want to run the Powershell scripts, then you’ll need to download an older version. I found that [Alpha 17](https://github.com/PowerShell/PowerShell/releases/tag/v6.0.0-alpha.17) worked for 10.10, and there should be somewhat newer versions that work with 10.11. 

Also, the best way to install Powershell via Microsoft’s own install pages is to use Homebrew using 

`brew cask install powershell`

which will work great on 10.12 or greater.
