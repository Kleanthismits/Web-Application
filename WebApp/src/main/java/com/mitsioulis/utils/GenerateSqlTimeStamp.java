package com.mitsioulis.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class GenerateSqlTimeStamp {

		public static Timestamp now() {
			Calendar calendar = Calendar.getInstance();
			Date now = calendar.getTime();
			return new Timestamp(now.getTime());
		}
}
