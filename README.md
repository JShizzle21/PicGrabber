# Windows 10 PicGrabber
The lock screen for Windows 10 generally has a very nice picture that would make a perfect desktop background. For example like the image below: 

![lock_screen_pic](readme_pics/lock-screen.png)

With my PicGrabber program you can grab the lock screen image and save it into the folder of your choice! Along with this in the folder where the lock screen picture is located there are usually a few other secret desktop background pictures along with some phone background!

For example, when I ran this program today I got the following images:

[sunset_desktop_background](readme_pics/desktop_sunset.jpg)

[sunset_phone_background](readme_pics/phone_sunset.jpg)

The fun thing is that neither of these pictures was currently my lock screen. After running the program it gave me 5 new desktop backgrounds and 5 new phone backgrounds (along with the current lock screen picture)!

## Preliminary steps
The PicGrabber program is a Java program. All that is included in this repository is the Main.java file for the program. It is not a complicated program so any development environment that has all of the java standard libraries should do. Unfortunately, I have not had the time or effort to create an executable with a GUI. However, the PicGrabber can be run through any Java IDE. I personally use [Eclipse Jee Oxygen](https://www.eclipse.org/downloads/).

I'm not going to include any download instructions in this repository because those can be found through many other sources! However, once you set up your IDE you should be able to just use my included Main.java file!

## Update Main.java
Before you run the program you need to change three file paths in the Main.java code. Basically you have to specify your username directory (this is the folder that contains Desktop, Documents, Pictures, etc.)

On line 13 change the value of [USERNAME] or specify an entirely different directory to save the desktop background images. 
The reason I chose this specific folder is explained below!

```
public final static String Desktop_Background_Folder = "C:\\Users\\[USERNAME]\\AppData\\Local\\Microsoft\\Windows\\Themes\\CustomPics";
```

On line 14 change the value of [USERNAME] or specify entirely different directory to save the phone background images
```
public final static String Phone_Background_Folder = "C:\\Users\\[USERNAME]\\Pictures\\Phone_Backgrounds";
```

** You can designate the same folder for both if you don't want them to be separated **

On line 20 change the value of [USERNAME] (don't change the rest of the filepath)

```
public final static String lockPicFolder = "C:\\Users\\[USERNAME]\\AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets";
```
Other than these three values nothing else needs to be changed

## Running Pic Grabber
Once the code is updated running the program should be very simple! All you need to do is open up your IDE and run the program whenever you see a new lock screen picture! 

**Note: The program won't run if the folders you designated don't currently exist!

Once you run the program you should see something like this: 

![eclipse_message](readme_pics/eclipse_message.JPG)

From what I have noticed you will only get new images once you actually see the lock screen image change when you start your computer. You can feel free to run the program at any time just to check though. Also once the program is run and new images are placed in your designated folders, the images will have crazy names like "411c0279b723462c7217a21cb5564c9d6798fbe76db1b0dcaca3d9705169ca29" you can feel free to change the name or leave it (I usually just leave it)

## Setting Windows Desktop to use grabbed images!

Personally I love finding great and unique desktop background images! Because of this I pull images from a variety of sources (other microsoft themes, lock screen pics, reddit-> my favorite is r/EarthPorn). In this section I'm going to detail the setting I have done in order to make this happen nice and efficiently!

With Windows 10, the folder that it used to get the desktop backgrounds can be customized. You can download themes from the microsoft store. However, you can also create a custom theme, and copy all the images you like from the various microsoft themes into this folder!

Themes can be downloaded by right-clicking the Desktop and selecting "Personalize" and then "Themes". From the theme page you can download themes, select the background images, colors, sounds, and Mouse cursor. However, you can also make a custom theme!

All of the Windows Themes are saved in the following folder:

```
C:\Users\[USERNAME]\AppData\Local\Microsoft\Windows\Themes
```
Whenever you download a Microsoft theme a new folder is created in this directory that contain all of the theme images.

In this folder I created a "CustomPics" folder 

In the "Background" tab select the CustomPics folder

You can now get all of the photos from other themes that you like and put them in the CustomPics folder!

Along with this the Pic Grabber program (as I have it originally) designates this CustomPics folder to be the destination folder for all of the lock screen backgrounds so there is no copying and pasting pictures necessary!

To add further simplicity, in my "Pictures" folder I added a shortcut to the CustomPics folder (right-click->New->Shortcut). This make it so I can easily find and add images that can be new desktop backgrounds! I also change the background settings to be a slideshow, to change every minute, and to shuffle!

