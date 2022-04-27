/* by Yiyang Bu */

package youtubetrends.model;

public class Categories {

  protected Integer categoryId;
  protected String title;
  protected Boolean assignable;

  public Categories(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public Categories(String title, Boolean assignable) {
	super();
	this.title = title;
	this.assignable = assignable;
}

public Categories(Integer categoryId, String title, Boolean assignable) {
    this.categoryId = categoryId;
    this.title = title;
    this.assignable = assignable;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Boolean getAssignable() {
    return assignable;
  }

  public void setAssignable(Boolean assignable) {
    this.assignable = assignable;
  }

}