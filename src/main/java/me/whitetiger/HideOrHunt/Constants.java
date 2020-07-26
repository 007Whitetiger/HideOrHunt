package me.whitetiger.HideOrHunt;/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */

import me.whitetiger.HideOrHunt.Utils.Utils;

public class Constants {
    public static String prefix = Utils.chat("&b[&6HOH&b] &f");

    /* permissions */
    public static String adminPerms = "hideorhunt.admin";
    public static String normalPerms = "hideorhunt.player";

    /* BlockPlaceListener messages */
    public static String alreadyPlaced = Utils.chat(prefix + " &cYou already placed an anchor!");
}
