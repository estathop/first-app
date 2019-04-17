package com.askisi1;

import java.util.ArrayList;
import java.util.List;
import com.askisi1.Image;

public class Images {
	public List<Image> images = new ArrayList<Image>();

	public Images() {
	}

	public List<Image> getOuterlist() {
		return images;
	}

	public void setOuterlist(List<Image> images) {
		this.images = images;
	}
}
