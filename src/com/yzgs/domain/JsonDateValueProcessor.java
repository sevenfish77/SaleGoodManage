package com.yzgs.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements JsonValueProcessor {
	private String datPattern = "yyyy-MM-dd";
	public static String Default_Format_Date = "yyyy-MM-dd";

	public JsonDateValueProcessor() {
		super();
	}

	public JsonDateValueProcessor(String format) {
		super();
		this.datPattern = format;
	}

	public Object processArrayValue(Object value, JsonConfig arg1) {
		return process(value);
	}

	public Object processObjectValue(String arg0, Object value, JsonConfig arg2) {
		return process(value);
	}

	private Object process(Object value) {
		try {
			if (value instanceof Date) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						this.datPattern);
				return simpleDateFormat.format(value);
			}
		} catch (Exception e) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					this.datPattern);
			return simpleDateFormat.format(value);
		}
		return "";
	}
}
