package me.siasur.areacommunity.aogbot.utility;

/**
 * Collection of useful functions related to {@link String}s.
 * 
 */
public class StringUtils {

	/**
	 * Check if the given String is {@literal null} or empty.
	 * @param string The string to be checked
	 * @return true, if the string is {@literal null} or empty
	 */
	public static boolean isNullOrEmpty(String string)
	{
		return string == null || string.isEmpty();
	}
	
}
