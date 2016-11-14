package com.knifesurge.utils;

import org.lwjgl.system.*;

public class Timer {

	public long getSysTime()
	{
		return System.nanoTime() / 1000000;
	}
	
}
