package com.askisi1;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Image implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String source;
	private List<String> tags = new ArrayList<String>();

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}