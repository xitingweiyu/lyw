package com.wonders.bud.framework.common.util;

import java.util.UUID;

public class SeqUtils {
	public static String getLSH32(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
