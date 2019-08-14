import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

public class Main {

//CHANGE THESE TWO VARIABLES TO THE PROPER FOLDER NAMES!
	//These are the folders where the lock screen picture will end up!
	public final static String Desktop_Background_Folder = "C:\\Users\\johnn\\AppData\\Local\\Microsoft\\Windows\\Themes\\CustomPics";
	public final static String Phone_Background_Folder = "C:\\Users\\johnn\\Pictures\\Phone_Backgrounds";
/////////////
	
	//This is the folder where the lock screen pictures are located!
	//"AppData" is a hidden folder so if you want to see it in file explorer you must enable hidden folders (not needed to make program work)
	//lockPicFolder contains roughly 60 different files with no file endings. The lock screen pic is one of these!
	public final static String lockPicFolder = "C:\\Users\\johnn\\AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets";
	
	//This is the kilobyte cutoff value in order to identify which files in the lockPicFolder are the wanted images because there is a lot of "junk" in this folder
	//Generally a good desktop image will be over 500kb (I set it as 300 just to make sure I don't miss anything)
	//Usually there are multiple nice desktop and phone background pictures, not just the one in the lock screen!
	final static long KB_cut_off = 300;
	
	public static void main(String[] args) 
	{
		//First display the total number of pictures in the folder
		int initial_desktop = num_of_pics(Desktop_Background_Folder);
		System.out.println("Number of initial Desktop Pics: " + initial_desktop);
		
		int initial_phone = num_of_pics(Phone_Background_Folder);
		System.out.println("Number of initial Phone Pics: " + initial_phone);
		
		System.out.println();
		
		//This method is what actually copies over the pictures from lockPicFolder to desinationFolder
		System.out.println("Get Lock Screen Pics!");
		getLockScreenPics();
		System.out.println();
		
		//Prints out the new number of pictures in the folder
		int final_desktop = num_of_pics(Desktop_Background_Folder);
		System.out.println("Number of Desktop Pics After: " + final_desktop);
		int final_phone = num_of_pics(Phone_Background_Folder);
		System.out.println("Number of Phone Pics After: " + final_phone);
		
		System.out.println();
		
		//Calculated how many pictures were added.
		int pics_added_desktop = final_desktop - initial_desktop;
		System.out.println("Number of Pics added to Desktop Folder: " + pics_added_desktop);
		
		int pics_added_phone = final_phone - initial_phone;
		System.out.println("Number of Pics added to Phone Folder: " + pics_added_phone);
		System.out.println();
		
		System.out.println("COMPLETE!");
	}
	
	//This method returns the number of pictures in the destination folder
	public static int num_of_pics(String folder)
	{
		File dir = new File(folder);
		
		if(!dir.isDirectory())
		{
			System.out.println("BAD DIRECTORY");
			return -1;
		}
		
		File[] files = dir.listFiles();
		
		return files.length;
	}
	
	//This method copies all of the contents of lockPicFolder into Desktop_Background_Folder, deletes the "junk" files, and then sorts out the phone backgrounds
	public static void getLockScreenPics()
	{
		//Create the directory file object for the lockPicFolder
		File dir = new File(lockPicFolder);
		if(!dir.isDirectory())
		{
			System.out.println("BAD DIRECTORY");
			return;
		}
		
		//Make an array of all the files in the lockPicFolder
		File[] file_list = dir.listFiles();
		
		/* Loop through all of the lockPicFolder files
		 * Each loop does the following:
		 * 	   --Creates a new blank/empty file in destinationFolder with the same name as the one in lockPicFolder but with the 
		 * 		 correct .jpg extension (the lockPicFiles don't have any file extensions)
		 *     --Copy the contents (not the actual file) of the lockPicFolder file into its corresponding destinationFolder file
		 *       (the original file will still remain in lockPicFolder)
		 *     --Check the size of the file -> if it is below the cutoff value then just delete the file
		 *     --Check the dimensions of the file -> move all the phone backgrounds into the designated folder.
		 * 
		*/
		for(int i = 0; i < file_list.length; i++)
		{
			
			//Create a new file object with the correct name and .jpg ending
			//This new file is a blank file with no actual contents
			String lockPicFile = Desktop_Background_Folder + File.separator + file_list[i].getName() + ".jpg";
			File new_file = new File(lockPicFile);
			
			//Copy the contents (not the actual file) of the lockPicFolder picture into the newly created file
			try
			{
				//Files.copy moves all the contents of one File into another File
				//"REPLACE_EXISTING" will delete and replace any Files that have the same name as it (best option to use)
				Files.copy(file_list[i].toPath(), new_file.toPath(), StandardCopyOption.valueOf("REPLACE_EXISTING"));
			}
			catch(IOException ioe)
			{
				System.out.println("FILE NOT COPIED");
				System.out.println(ioe.getMessage());
				break;
			}
			
			//Get rid of all the "junk" files that are all too small to be a proper image
			//An actual picture will be above roughly 400KB (there is alot of junk in this directory)
			//Divide by 1000 to convert Bytes into Kilobytes
			long file_size_KB = new_file.length()/1000;
			if(file_size_KB < KB_cut_off)
			{
				new_file.delete();
				continue;
			}
			
			//Create and initialize a BufferedImage object (in order to check the files dimensions)
			BufferedImage bi;
			try
			{
				bi = ImageIO.read(new_file);
			}
			catch(IOException ioe)
			{
				System.out.println("IMAGE EXCEPTION");
				System.out.println(ioe.getMessage());
				break;
			}
			
			//Check the dimensions
			//If height is greater than the width it means it is a phone desktop background
			if(bi.getHeight() > bi.getWidth())
			{
				String phone_file_name = Phone_Background_Folder + File.separator + file_list[i].getName() + ".jpg";
				File phone_file = new File(phone_file_name);
				new_file.renameTo(phone_file);
			}
		}

		
	}
	

}
