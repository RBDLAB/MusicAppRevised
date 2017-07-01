package application;

import java.util.ArrayList;

public class ProjectTransformations {
	private String projectName;
	private String scale;
	private String finalis;
	private ArrayList<TransformationInfo> transformations;
	
	public ProjectTransformations(String projectName, String scale, String finalis) {
		super();
		this.projectName = projectName;
		this.scale = scale;
		this.finalis = finalis;
		this.transformations = new ArrayList<TransformationInfo>();
	}

	public ProjectTransformations(String projectName, String scale, String finalis,
			ArrayList<TransformationInfo> transformations) {
		super();
		this.projectName = projectName;
		this.scale = scale;
		this.finalis = finalis;
		this.transformations = new ArrayList<TransformationInfo>(transformations);
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getFinalis() {
		return finalis;
	}

	public void setFinalis(String finalis) {
		this.finalis = finalis;
	}

	public ArrayList<TransformationInfo> getTransformations() {
		return transformations;
	}

	public void addTransformation(TransformationInfo transformation) {
		this.transformations.add(transformation);
	}
	
	
	
}
